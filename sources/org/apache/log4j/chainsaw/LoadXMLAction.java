package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class LoadXMLAction extends AbstractAction {
    private static final Logger LOG;
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$LoadXMLAction;
    private final JFileChooser mChooser;
    private final XMLFileHandler mHandler;
    private final JFrame mParent;
    private final XMLReader mParser;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$chainsaw$LoadXMLAction;
        if (cls == null) {
            cls = class$("org.apache.log4j.chainsaw.LoadXMLAction");
            class$org$apache$log4j$chainsaw$LoadXMLAction = cls;
        }
        LOG = Logger.getLogger(cls);
    }

    LoadXMLAction(JFrame jFrame, MyTableModel myTableModel) throws SAXException, ParserConfigurationException {
        JFileChooser jFileChooser = new JFileChooser();
        this.mChooser = jFileChooser;
        jFileChooser.setMultiSelectionEnabled(false);
        this.mChooser.setFileSelectionMode(0);
        this.mParent = jFrame;
        this.mHandler = new XMLFileHandler(myTableModel);
        XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        this.mParser = xMLReader;
        xMLReader.setContentHandler(this.mHandler);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        LOG.info("load file called");
        if (this.mChooser.showOpenDialog(this.mParent) == 0) {
            LOG.info("Need to load a file");
            File selectedFile = this.mChooser.getSelectedFile();
            Logger logger = LOG;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("loading the contents of ");
            stringBuffer.append(selectedFile.getAbsolutePath());
            logger.info(stringBuffer.toString());
            try {
                int loadFile = loadFile(selectedFile.getAbsolutePath());
                JFrame jFrame = this.mParent;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Loaded ");
                stringBuffer2.append(loadFile);
                stringBuffer2.append(" events.");
                JOptionPane.showMessageDialog(jFrame, stringBuffer2.toString(), "CHAINSAW", 1);
            } catch (Exception e) {
                LOG.warn("caught an exception loading the file", e);
                JFrame jFrame2 = this.mParent;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Error parsing file - ");
                stringBuffer3.append(e.getMessage());
                JOptionPane.showMessageDialog(jFrame2, stringBuffer3.toString(), "CHAINSAW", 0);
            }
        }
    }

    private int loadFile(String str) throws SAXException, IOException {
        int numEvents;
        synchronized (this.mParser) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
            stringBuffer.append("<!DOCTYPE log4j:eventSet ");
            stringBuffer.append("[<!ENTITY data SYSTEM \"file:///");
            stringBuffer.append(str);
            stringBuffer.append("\">]>\n");
            stringBuffer.append("<log4j:eventSet xmlns:log4j=\"Claira\">\n");
            stringBuffer.append("&data;\n");
            stringBuffer.append("</log4j:eventSet>\n");
            this.mParser.parse(new InputSource(new StringReader(stringBuffer.toString())));
            numEvents = this.mHandler.getNumEvents();
        }
        return numEvents;
    }
}
