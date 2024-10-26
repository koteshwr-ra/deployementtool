package org.apache.log4j.net;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class SimpleSocketServer {
    static Logger cat;
    static /* synthetic */ Class class$org$apache$log4j$net$SimpleSocketServer;
    static int port;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$net$SimpleSocketServer;
        if (cls == null) {
            cls = class$("org.apache.log4j.net.SimpleSocketServer");
            class$org$apache$log4j$net$SimpleSocketServer = cls;
        }
        cat = Logger.getLogger(cls);
    }

    public static void main(String[] strArr) {
        if (strArr.length == 2) {
            init(strArr[0], strArr[1]);
        } else {
            usage("Wrong number of arguments.");
        }
        try {
            Logger logger = cat;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Listening on port ");
            stringBuffer.append(port);
            logger.info(stringBuffer.toString());
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                cat.info("Waiting to accept a new client.");
                Socket accept = serverSocket.accept();
                Logger logger2 = cat;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Connected to client at ");
                stringBuffer2.append(accept.getInetAddress());
                logger2.info(stringBuffer2.toString());
                cat.info("Starting new socket node.");
                new Thread(new SocketNode(accept, LogManager.getLoggerRepository())).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void usage(String str) {
        System.err.println(str);
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Usage: java ");
        Class cls = class$org$apache$log4j$net$SimpleSocketServer;
        if (cls == null) {
            cls = class$("org.apache.log4j.net.SimpleSocketServer");
            class$org$apache$log4j$net$SimpleSocketServer = cls;
        }
        stringBuffer.append(cls.getName());
        stringBuffer.append(" port configFile");
        printStream.println(stringBuffer.toString());
        System.exit(1);
    }

    static void init(String str, String str2) {
        try {
            port = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not interpret port number [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            usage(stringBuffer.toString());
        }
        if (str2.endsWith(".xml")) {
            new DOMConfigurator();
            DOMConfigurator.configure(str2);
            return;
        }
        new PropertyConfigurator();
        PropertyConfigurator.configure(str2);
    }
}
