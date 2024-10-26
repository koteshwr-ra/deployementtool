package org.apache.log4j.xml;

import org.apache.log4j.helpers.LogLog;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SAXErrorHandler implements ErrorHandler {
    public void error(SAXParseException sAXParseException) {
        emitMessage("Continuable parsing error ", sAXParseException);
    }

    public void fatalError(SAXParseException sAXParseException) {
        emitMessage("Fatal parsing error ", sAXParseException);
    }

    public void warning(SAXParseException sAXParseException) {
        emitMessage("Parsing warning ", sAXParseException);
    }

    private static void emitMessage(String str, SAXParseException sAXParseException) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(sAXParseException.getLineNumber());
        stringBuffer.append(" and column ");
        stringBuffer.append(sAXParseException.getColumnNumber());
        LogLog.warn(stringBuffer.toString());
        LogLog.warn(sAXParseException.getMessage(), sAXParseException.getException());
    }
}
