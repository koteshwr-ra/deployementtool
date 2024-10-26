package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class SocketHubAppender extends AppenderSkeleton {
    static final int DEFAULT_PORT = 4560;
    private boolean locationInfo = false;
    private Vector oosList = new Vector();
    private int port = DEFAULT_PORT;
    private ServerMonitor serverMonitor = null;

    public boolean requiresLayout() {
        return false;
    }

    public SocketHubAppender() {
    }

    public SocketHubAppender(int i) {
        this.port = i;
        startServer();
    }

    public void activateOptions() {
        startServer();
    }

    public synchronized void close() {
        if (!this.closed) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("closing SocketHubAppender ");
            stringBuffer.append(getName());
            LogLog.debug(stringBuffer.toString());
            this.closed = true;
            cleanUp();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("SocketHubAppender ");
            stringBuffer2.append(getName());
            stringBuffer2.append(" closed");
            LogLog.debug(stringBuffer2.toString());
        }
    }

    public void cleanUp() {
        LogLog.debug("stopping ServerSocket");
        this.serverMonitor.stopMonitor();
        this.serverMonitor = null;
        LogLog.debug("closing client connections");
        while (this.oosList.size() != 0) {
            ObjectOutputStream objectOutputStream = (ObjectOutputStream) this.oosList.elementAt(0);
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    LogLog.error("could not close oos.", e);
                }
                this.oosList.removeElementAt(0);
            }
        }
    }

    public void append(LoggingEvent loggingEvent) {
        if (loggingEvent != null && this.oosList.size() != 0) {
            if (this.locationInfo) {
                loggingEvent.getLocationInformation();
            }
            int i = 0;
            while (i < this.oosList.size()) {
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = (ObjectOutputStream) this.oosList.elementAt(i);
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.writeObject(loggingEvent);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    } catch (IOException unused2) {
                        this.oosList.removeElementAt(i);
                        LogLog.debug("dropped connection");
                        i--;
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
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

    private void startServer() {
        this.serverMonitor = new ServerMonitor(this.port, this.oosList);
    }

    private class ServerMonitor implements Runnable {
        private boolean keepRunning = true;
        private Thread monitorThread;
        private Vector oosList;
        private int port;

        public ServerMonitor(int i, Vector vector) {
            this.port = i;
            this.oosList = vector;
            Thread thread = new Thread(this);
            this.monitorThread = thread;
            thread.setDaemon(true);
            this.monitorThread.start();
        }

        public synchronized void stopMonitor() {
            if (this.keepRunning) {
                LogLog.debug("server monitor thread shutting down");
                this.keepRunning = false;
                try {
                    this.monitorThread.join();
                } catch (InterruptedException unused) {
                }
                this.monitorThread = null;
                LogLog.debug("server monitor thread shut down");
            }
        }

        public void run() {
            Socket socket;
            try {
                ServerSocket serverSocket = new ServerSocket(this.port);
                serverSocket.setSoTimeout(1000);
                try {
                    serverSocket.setSoTimeout(1000);
                    while (this.keepRunning) {
                        try {
                            socket = null;
                            socket = serverSocket.accept();
                        } catch (InterruptedIOException unused) {
                        } catch (SocketException e) {
                            LogLog.error("exception accepting socket, shutting down server socket.", e);
                            this.keepRunning = false;
                        } catch (IOException e2) {
                            LogLog.error("exception accepting socket.", e2);
                        } catch (Throwable th) {
                            try {
                                serverSocket.close();
                            } catch (IOException unused2) {
                            }
                            throw th;
                        }
                        if (socket != null) {
                            try {
                                InetAddress inetAddress = socket.getInetAddress();
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("accepting connection from ");
                                stringBuffer.append(inetAddress.getHostName());
                                stringBuffer.append(" (");
                                stringBuffer.append(inetAddress.getHostAddress());
                                stringBuffer.append(")");
                                LogLog.debug(stringBuffer.toString());
                                this.oosList.addElement(new ObjectOutputStream(socket.getOutputStream()));
                            } catch (IOException e3) {
                                LogLog.error("exception creating output stream on socket.", e3);
                            }
                        }
                    }
                    try {
                        serverSocket.close();
                    } catch (IOException unused3) {
                    }
                } catch (SocketException e4) {
                    LogLog.error("exception setting timeout, shutting down server socket.", e4);
                    try {
                        serverSocket.close();
                    } catch (IOException unused4) {
                    }
                }
            } catch (Exception e5) {
                LogLog.error("exception setting timeout, shutting down server socket.", e5);
                this.keepRunning = false;
            }
        }
    }
}
