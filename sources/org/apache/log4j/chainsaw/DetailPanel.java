package org.apache.log4j.chainsaw;

import java.awt.BorderLayout;
import java.text.MessageFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

class DetailPanel extends JPanel implements ListSelectionListener {
    private static final MessageFormat FORMATTER = new MessageFormat("<b>Time:</b> <code>{0,time,medium}</code>&nbsp;&nbsp;<b>Priority:</b> <code>{1}</code>&nbsp;&nbsp;<b>Thread:</b> <code>{2}</code>&nbsp;&nbsp;<b>NDC:</b> <code>{3}</code><br><b>Logger:</b> <code>{4}</code><br><b>Location:</b> <code>{5}</code><br><b>Message:</b><pre>{6}</pre><b>Throwable:</b><pre>{7}</pre>");
    private static final Logger LOG;
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$DetailPanel;
    private final JEditorPane mDetails;
    private final MyTableModel mModel;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$chainsaw$DetailPanel;
        if (cls == null) {
            cls = class$("org.apache.log4j.chainsaw.DetailPanel");
            class$org$apache$log4j$chainsaw$DetailPanel = cls;
        }
        LOG = Logger.getLogger(cls);
    }

    DetailPanel(JTable jTable, MyTableModel myTableModel) {
        this.mModel = myTableModel;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Details: "));
        JEditorPane jEditorPane = new JEditorPane();
        this.mDetails = jEditorPane;
        jEditorPane.setEditable(false);
        this.mDetails.setContentType("text/html");
        add(new JScrollPane(this.mDetails), "Center");
        jTable.getSelectionModel().addListSelectionListener(this);
    }

    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting()) {
            ListSelectionModel listSelectionModel = (ListSelectionModel) listSelectionEvent.getSource();
            if (listSelectionModel.isSelectionEmpty()) {
                this.mDetails.setText("Nothing selected");
                return;
            }
            EventDetails eventDetails = this.mModel.getEventDetails(listSelectionModel.getMinSelectionIndex());
            this.mDetails.setText(FORMATTER.format(new Object[]{new Date(eventDetails.getTimeStamp()), eventDetails.getPriority(), escape(eventDetails.getThreadName()), escape(eventDetails.getNDC()), escape(eventDetails.getCategoryName()), escape(eventDetails.getLocationDetails()), escape(eventDetails.getMessage()), escape(getThrowableStrRep(eventDetails))}));
            this.mDetails.setCaretPosition(0);
        }
    }

    private static String getThrowableStrRep(EventDetails eventDetails) {
        String[] throwableStrRep = eventDetails.getThrowableStrRep();
        if (throwableStrRep == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : throwableStrRep) {
            stringBuffer.append(append);
            stringBuffer.append(StringUtils.LF);
        }
        return stringBuffer.toString();
    }

    private String escape(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                stringBuffer.append("&quot;");
            } else if (charAt == '&') {
                stringBuffer.append("&amp;");
            } else if (charAt == '<') {
                stringBuffer.append("&lt;");
            } else if (charAt != '>') {
                stringBuffer.append(charAt);
            } else {
                stringBuffer.append("&gt;");
            }
        }
        return stringBuffer.toString();
    }
}
