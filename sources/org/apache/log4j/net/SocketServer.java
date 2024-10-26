package org.apache.log4j.net;

import java.io.File;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;

public class SocketServer {
    static String CONFIG_FILE_EXT = ".lcf";
    static String GENERIC = "generic";
    static Logger cat;
    static /* synthetic */ Class class$org$apache$log4j$net$SocketServer;
    static int port;
    static SocketServer server;
    File dir;
    LoggerRepository genericHierarchy;
    Hashtable hierarchyMap = new Hashtable(11);

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$net$SocketServer;
        if (cls == null) {
            cls = class$("org.apache.log4j.net.SocketServer");
            class$org$apache$log4j$net$SocketServer = cls;
        }
        cat = Logger.getLogger(cls);
    }

    public static void main(String[] strArr) {
        if (strArr.length == 3) {
            init(strArr[0], strArr[1], strArr[2]);
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
                InetAddress inetAddress = accept.getInetAddress();
                Logger logger2 = cat;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Connected to client at ");
                stringBuffer2.append(inetAddress);
                logger2.info(stringBuffer2.toString());
                LoggerRepository loggerRepository = (LoggerRepository) server.hierarchyMap.get(inetAddress);
                if (loggerRepository == null) {
                    loggerRepository = server.configureHierarchy(inetAddress);
                }
                cat.info("Starting new socket node.");
                new Thread(new SocketNode(accept, loggerRepository)).start();
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
        Class cls = class$org$apache$log4j$net$SocketServer;
        if (cls == null) {
            cls = class$("org.apache.log4j.net.SocketServer");
            class$org$apache$log4j$net$SocketServer = cls;
        }
        stringBuffer.append(cls.getName());
        stringBuffer.append(" port configFile directory");
        printStream.println(stringBuffer.toString());
        System.exit(1);
    }

    static void init(String str, String str2, String str3) {
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
        PropertyConfigurator.configure(str2);
        File file = new File(str3);
        if (!file.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("[");
            stringBuffer2.append(str3);
            stringBuffer2.append("] is not a directory.");
            usage(stringBuffer2.toString());
        }
        server = new SocketServer(file);
    }

    public SocketServer(File file) {
        this.dir = file;
    }

    /* access modifiers changed from: package-private */
    public LoggerRepository configureHierarchy(InetAddress inetAddress) {
        Logger logger = cat;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Locating configuration file for ");
        stringBuffer.append(inetAddress);
        logger.info(stringBuffer.toString());
        String inetAddress2 = inetAddress.toString();
        int indexOf = inetAddress2.indexOf("/");
        if (indexOf == -1) {
            Logger logger2 = cat;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not parse the inetAddress [");
            stringBuffer2.append(inetAddress);
            stringBuffer2.append("]. Using default hierarchy.");
            logger2.warn(stringBuffer2.toString());
            return genericHierarchy();
        }
        String substring = inetAddress2.substring(0, indexOf);
        File file = this.dir;
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(substring);
        stringBuffer3.append(CONFIG_FILE_EXT);
        File file2 = new File(file, stringBuffer3.toString());
        if (file2.exists()) {
            Hierarchy hierarchy = new Hierarchy(new RootLogger((Level) Priority.DEBUG));
            this.hierarchyMap.put(inetAddress, hierarchy);
            new PropertyConfigurator().doConfigure(file2.getAbsolutePath(), (LoggerRepository) hierarchy);
            return hierarchy;
        }
        Logger logger3 = cat;
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("Could not find config file [");
        stringBuffer4.append(file2);
        stringBuffer4.append("].");
        logger3.warn(stringBuffer4.toString());
        return genericHierarchy();
    }

    /* access modifiers changed from: package-private */
    public LoggerRepository genericHierarchy() {
        if (this.genericHierarchy == null) {
            File file = this.dir;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(GENERIC);
            stringBuffer.append(CONFIG_FILE_EXT);
            File file2 = new File(file, stringBuffer.toString());
            if (file2.exists()) {
                this.genericHierarchy = new Hierarchy(new RootLogger((Level) Priority.DEBUG));
                new PropertyConfigurator().doConfigure(file2.getAbsolutePath(), this.genericHierarchy);
            } else {
                Logger logger = cat;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not find config file [");
                stringBuffer2.append(file2);
                stringBuffer2.append("]. Will use the default hierarchy.");
                logger.warn(stringBuffer2.toString());
                this.genericHierarchy = LogManager.getLoggerRepository();
            }
        }
        return this.genericHierarchy;
    }
}
