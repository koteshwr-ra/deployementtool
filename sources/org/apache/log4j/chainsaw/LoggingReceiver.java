package org.apache.log4j.chainsaw;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

class LoggingReceiver extends Thread {
    /* access modifiers changed from: private */
    public static final Logger LOG;
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$LoggingReceiver;
    /* access modifiers changed from: private */
    public MyTableModel mModel;
    private ServerSocket mSvrSock;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$chainsaw$LoggingReceiver;
        if (cls == null) {
            cls = class$("org.apache.log4j.chainsaw.LoggingReceiver");
            class$org$apache$log4j$chainsaw$LoggingReceiver = cls;
        }
        LOG = Logger.getLogger(cls);
    }

    private class Slurper implements Runnable {
        private final Socket mClient;

        Slurper(Socket socket) {
            this.mClient = socket;
        }

        public void run() {
            LoggingReceiver.LOG.debug("Starting to get data");
            try {
                while (true) {
                    LoggingReceiver.this.mModel.addEvent(new EventDetails((LoggingEvent) new ObjectInputStream(this.mClient.getInputStream()).readObject()));
                }
            } catch (EOFException unused) {
                LoggingReceiver.LOG.info("Reached EOF, closing connection");
                try {
                    this.mClient.close();
                } catch (IOException e) {
                    LoggingReceiver.LOG.warn("Error closing connection", e);
                }
            } catch (SocketException unused2) {
                LoggingReceiver.LOG.info("Caught SocketException, closing connection");
                this.mClient.close();
            } catch (IOException e2) {
                LoggingReceiver.LOG.warn("Got IOException, closing connection", e2);
                this.mClient.close();
            } catch (ClassNotFoundException e3) {
                LoggingReceiver.LOG.warn("Got ClassNotFoundException, closing connection", e3);
                this.mClient.close();
            }
        }
    }

    LoggingReceiver(MyTableModel myTableModel, int i) throws IOException {
        setDaemon(true);
        this.mModel = myTableModel;
        this.mSvrSock = new ServerSocket(i);
    }

    public void run() {
        LOG.info("Thread started");
        while (true) {
            try {
                LOG.debug("Waiting for a connection");
                Socket accept = this.mSvrSock.accept();
                Logger logger = LOG;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Got a connection from ");
                stringBuffer.append(accept.getInetAddress().getHostName());
                logger.debug(stringBuffer.toString());
                Thread thread = new Thread(new Slurper(accept));
                thread.setDaemon(true);
                thread.start();
            } catch (IOException e) {
                LOG.error("Error in accepting connections, stopping.", e);
                return;
            }
        }
    }
}
