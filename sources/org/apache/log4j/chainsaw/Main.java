package org.apache.log4j.chainsaw;

import com.alibaba.android.arouter.utils.Consts;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main extends JFrame {
    private static final int DEFAULT_PORT = 4445;
    private static final Logger LOG;
    public static final String PORT_PROP_NAME = "chainsaw.port";
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$Main;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$chainsaw$Main;
        if (cls == null) {
            cls = class$("org.apache.log4j.chainsaw.Main");
            class$org$apache$log4j$chainsaw$Main = cls;
        }
        LOG = Logger.getLogger(cls);
    }

    private Main() {
        super("CHAINSAW - Log4J Log Viewer");
        MyTableModel myTableModel = new MyTableModel();
        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);
        JMenu jMenu = new JMenu("File");
        jMenuBar.add(jMenu);
        try {
            LoadXMLAction loadXMLAction = new LoadXMLAction(this, myTableModel);
            JMenuItem jMenuItem = new JMenuItem("Load file...");
            jMenu.add(jMenuItem);
            jMenuItem.addActionListener(loadXMLAction);
        } catch (NoClassDefFoundError e) {
            LOG.info("Missing classes for XML parser", e);
            JOptionPane.showMessageDialog(this, "XML parser not in classpath - unable to load XML events.", "CHAINSAW", 0);
        } catch (Exception e2) {
            LOG.info("Unable to create the action to load XML files", e2);
            JOptionPane.showMessageDialog(this, "Unable to create a XML parser - unable to load XML events.", "CHAINSAW", 0);
        }
        JMenuItem jMenuItem2 = new JMenuItem("Exit");
        jMenu.add(jMenuItem2);
        jMenuItem2.addActionListener(ExitAction.INSTANCE);
        getContentPane().add(new ControlPanel(myTableModel), "North");
        JTable jTable = new JTable(myTableModel);
        jTable.setSelectionMode(0);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBorder(BorderFactory.createTitledBorder("Events: "));
        jScrollPane.setPreferredSize(new Dimension(TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_TIMED_TEXT_ERROR, 300));
        DetailPanel detailPanel = new DetailPanel(jTable, myTableModel);
        detailPanel.setPreferredSize(new Dimension(TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_TIMED_TEXT_ERROR, 300));
        getContentPane().add(new JSplitPane(0, jScrollPane, detailPanel), "Center");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                ExitAction.INSTANCE.actionPerformed((ActionEvent) null);
            }
        });
        pack();
        setVisible(true);
        setupReceiver(myTableModel);
    }

    private void setupReceiver(MyTableModel myTableModel) {
        int i;
        String property = System.getProperty(PORT_PROP_NAME);
        if (property != null) {
            try {
                i = Integer.parseInt(property);
            } catch (NumberFormatException unused) {
                Logger logger = LOG;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to parse chainsaw.port property with value ");
                stringBuffer.append(property);
                stringBuffer.append(Consts.DOT);
                logger.fatal(stringBuffer.toString());
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Unable to parse port number from '");
                stringBuffer2.append(property);
                stringBuffer2.append("', quitting.");
                JOptionPane.showMessageDialog(this, stringBuffer2.toString(), "CHAINSAW", 0);
                System.exit(1);
            }
            new LoggingReceiver(myTableModel, i).start();
        }
        i = DEFAULT_PORT;
        try {
            new LoggingReceiver(myTableModel, i).start();
        } catch (IOException e) {
            LOG.fatal("Unable to connect to socket server, quiting", e);
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Unable to create socket on port ");
            stringBuffer3.append(i);
            stringBuffer3.append(", quitting.");
            JOptionPane.showMessageDialog(this, stringBuffer3.toString(), "CHAINSAW", 0);
            System.exit(1);
        }
    }

    private static void initLog4J() {
        Properties properties = new Properties();
        properties.setProperty("log4j.rootLogger", "DEBUG, A1");
        properties.setProperty("log4j.appender.A1", "org.apache.log4j.ConsoleAppender");
        properties.setProperty("log4j.appender.A1.layout", "org.apache.log4j.TTCCLayout");
        PropertyConfigurator.configure(properties);
    }

    public static void main(String[] strArr) {
        initLog4J();
        new Main();
    }
}
