package org.apache.log4j.net;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

public class SocketNode implements Runnable {
    static /* synthetic */ Class class$org$apache$log4j$net$SocketNode;
    static Logger logger;
    LoggerRepository hierarchy;
    ObjectInputStream ois;
    Socket socket;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$net$SocketNode;
        if (cls == null) {
            cls = class$("org.apache.log4j.net.SocketNode");
            class$org$apache$log4j$net$SocketNode = cls;
        }
        logger = Logger.getLogger(cls);
    }

    public SocketNode(Socket socket2, LoggerRepository loggerRepository) {
        this.socket = socket2;
        this.hierarchy = loggerRepository;
        try {
            this.ois = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
        } catch (Exception e) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not open ObjectInputStream to ");
            stringBuffer.append(socket2);
            logger2.error(stringBuffer.toString(), e);
        }
    }

    public void run() {
        while (true) {
            try {
                LoggingEvent loggingEvent = (LoggingEvent) this.ois.readObject();
                Logger logger2 = this.hierarchy.getLogger(loggingEvent.getLoggerName());
                if (loggingEvent.getLevel().isGreaterOrEqual(logger2.getEffectiveLevel())) {
                    logger2.callAppenders(loggingEvent);
                }
            } catch (EOFException unused) {
                logger.info("Caught java.io.EOFException closing conneciton.");
                try {
                    this.ois.close();
                    return;
                } catch (Exception e) {
                    logger.info("Could not close connection.", e);
                    return;
                }
            } catch (SocketException unused2) {
                logger.info("Caught java.net.SocketException closing conneciton.");
                this.ois.close();
                return;
            } catch (IOException e2) {
                Logger logger3 = logger;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Caught java.io.IOException: ");
                stringBuffer.append(e2);
                logger3.info(stringBuffer.toString());
                logger.info("Closing connection.");
                this.ois.close();
                return;
            } catch (Exception e3) {
                logger.error("Unexpected exception. Closing conneciton.", e3);
                this.ois.close();
                return;
            }
        }
    }
}
