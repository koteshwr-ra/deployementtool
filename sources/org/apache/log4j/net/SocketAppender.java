package org.apache.log4j.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class SocketAppender extends AppenderSkeleton {
    static final int DEFAULT_PORT = 4560;
    static final int DEFAULT_RECONNECTION_DELAY = 30000;
    private static final int RESET_FREQUENCY = 1;
    InetAddress address;
    /* access modifiers changed from: private */
    public Connector connector;
    int counter = 0;
    boolean locationInfo = false;
    ObjectOutputStream oos;
    int port = DEFAULT_PORT;
    int reconnectionDelay = 30000;
    String remoteHost;

    public boolean requiresLayout() {
        return false;
    }

    public SocketAppender() {
    }

    public SocketAppender(InetAddress inetAddress, int i) {
        this.address = inetAddress;
        this.remoteHost = inetAddress.getHostName();
        this.port = i;
        connect(inetAddress, i);
    }

    public SocketAppender(String str, int i) {
        this.port = i;
        InetAddress addressByName = getAddressByName(str);
        this.address = addressByName;
        this.remoteHost = str;
        connect(addressByName, i);
    }

    public void activateOptions() {
        connect(this.address, this.port);
    }

    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            cleanUp();
        }
    }

    public void cleanUp() {
        ObjectOutputStream objectOutputStream = this.oos;
        if (objectOutputStream != null) {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                LogLog.error("Could not close oos.", e);
            }
            this.oos = null;
        }
        Connector connector2 = this.connector;
        if (connector2 != null) {
            connector2.interrupted = true;
            this.connector = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void connect(InetAddress inetAddress, int i) {
        if (this.address != null) {
            try {
                cleanUp();
                this.oos = new ObjectOutputStream(new Socket(inetAddress, i).getOutputStream());
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not connect to remote log4j server at [");
                stringBuffer.append(inetAddress.getHostName());
                stringBuffer.append("].");
                String stringBuffer2 = stringBuffer.toString();
                if (this.reconnectionDelay > 0) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append(stringBuffer2);
                    stringBuffer3.append(" We will try again later.");
                    stringBuffer2 = stringBuffer3.toString();
                    fireConnector();
                }
                LogLog.error(stringBuffer2, e);
            }
        }
    }

    public void append(LoggingEvent loggingEvent) {
        if (loggingEvent != null) {
            if (this.address == null) {
                ErrorHandler errorHandler = this.errorHandler;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("No remote host is set for SocketAppender named \"");
                stringBuffer.append(this.name);
                stringBuffer.append("\".");
                errorHandler.error(stringBuffer.toString());
            } else if (this.oos != null) {
                try {
                    if (this.locationInfo) {
                        loggingEvent.getLocationInformation();
                    }
                    this.oos.writeObject(loggingEvent);
                    this.oos.flush();
                    int i = this.counter + 1;
                    this.counter = i;
                    if (i >= 1) {
                        this.counter = 0;
                        this.oos.reset();
                    }
                } catch (IOException e) {
                    this.oos = null;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Detected problem with connection: ");
                    stringBuffer2.append(e);
                    LogLog.warn(stringBuffer2.toString());
                    if (this.reconnectionDelay > 0) {
                        fireConnector();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void fireConnector() {
        if (this.connector == null) {
            LogLog.debug("Starting a new connector thread.");
            Connector connector2 = new Connector();
            this.connector = connector2;
            connector2.setDaemon(true);
            this.connector.setPriority(1);
            this.connector.start();
        }
    }

    static InetAddress getAddressByName(String str) {
        try {
            return InetAddress.getByName(str);
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not find address of [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            LogLog.error(stringBuffer.toString(), e);
            return null;
        }
    }

    public void setRemoteHost(String str) {
        this.address = getAddressByName(str);
        this.remoteHost = str;
    }

    public String getRemoteHost() {
        return this.remoteHost;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public int getPort() {
        return this.port;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public void setReconnectionDelay(int i) {
        this.reconnectionDelay = i;
    }

    public int getReconnectionDelay() {
        return this.reconnectionDelay;
    }

    class Connector extends Thread {
        boolean interrupted = false;

        Connector() {
        }

        public void run() {
            while (!this.interrupted) {
                try {
                    Thread.sleep((long) SocketAppender.this.reconnectionDelay);
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Attempting connection to ");
                    stringBuffer.append(SocketAppender.this.address.getHostName());
                    LogLog.debug(stringBuffer.toString());
                    Socket socket = new Socket(SocketAppender.this.address, SocketAppender.this.port);
                    synchronized (this) {
                        SocketAppender.this.oos = new ObjectOutputStream(socket.getOutputStream());
                        Connector unused = SocketAppender.this.connector = null;
                        LogLog.debug("Connection established. Exiting connector thread.");
                    }
                    return;
                } catch (InterruptedException unused2) {
                    LogLog.debug("Connector interrupted. Leaving loop.");
                    return;
                } catch (ConnectException unused3) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Remote host ");
                    stringBuffer2.append(SocketAppender.this.address.getHostName());
                    stringBuffer2.append(" refused connection.");
                    LogLog.debug(stringBuffer2.toString());
                } catch (IOException e) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Could not connect to ");
                    stringBuffer3.append(SocketAppender.this.address.getHostName());
                    stringBuffer3.append(". Exception is ");
                    stringBuffer3.append(e);
                    LogLog.debug(stringBuffer3.toString());
                }
            }
        }
    }
}
