package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Roller {
    static Logger cat;
    static /* synthetic */ Class class$org$apache$log4j$varia$Roller;
    static String host;
    static int port;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$varia$Roller;
        if (cls == null) {
            cls = class$("org.apache.log4j.varia.Roller");
            class$org$apache$log4j$varia$Roller = cls;
        }
        cat = Logger.getLogger(cls);
    }

    Roller() {
    }

    public static void main(String[] strArr) {
        BasicConfigurator.configure();
        if (strArr.length == 2) {
            init(strArr[0], strArr[1]);
        } else {
            usage("Wrong number of arguments.");
        }
        roll();
    }

    static void usage(String str) {
        System.err.println(str);
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Usage: java ");
        Class cls = class$org$apache$log4j$varia$Roller;
        if (cls == null) {
            cls = class$("org.apache.log4j.varia.Roller");
            class$org$apache$log4j$varia$Roller = cls;
        }
        stringBuffer.append(cls.getName());
        stringBuffer.append("host_name port_number");
        printStream.println(stringBuffer.toString());
        System.exit(1);
    }

    static void init(String str, String str2) {
        host = str;
        try {
            port = Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Second argument ");
            stringBuffer.append(str2);
            stringBuffer.append(" is not a valid integer.");
            usage(stringBuffer.toString());
        }
    }

    static void roll() {
        try {
            Socket socket = new Socket(host, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream.writeUTF(ExternallyRolledFileAppender.ROLL_OVER);
            String readUTF = dataInputStream.readUTF();
            if ("OK".equals(readUTF)) {
                cat.info("Roll over signal acknowledged by remote appender.");
            } else {
                Logger logger = cat;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unexpected return code ");
                stringBuffer.append(readUTF);
                stringBuffer.append(" from remote entity.");
                logger.warn(stringBuffer.toString());
                System.exit(2);
            }
        } catch (IOException e) {
            Logger logger2 = cat;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not send roll signal on host ");
            stringBuffer2.append(host);
            stringBuffer2.append(" port ");
            stringBuffer2.append(port);
            stringBuffer2.append(" .");
            logger2.error(stringBuffer2.toString(), e);
            System.exit(2);
        }
        System.exit(0);
    }
}
