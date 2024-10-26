package org.apache.log4j.lf5.viewer;

import com.ciot.base.constant.HttpConstant;
import com.ciot.base.constant.NetConstant;
import com.tencent.bugly.Bugly;
import com.tencent.smtt.sdk.TbsListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.apache.log4j.lf5.util.LogFileParser;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerTree;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.apache.log4j.lf5.viewer.configure.MRUFileManager;

public class LogBrokerMonitor {
    public static final String DETAILED_VIEW = "Detailed";
    protected String _NDCTextFilter = "";
    protected boolean _callSystemExitOnClose = false;
    protected CategoryExplorerTree _categoryExplorerTree;
    protected List _columns = null;
    protected ConfigurationManager _configurationManager = null;
    protected String _currentView = DETAILED_VIEW;
    protected List _displayedLogBrokerProperties = new Vector();
    protected File _fileLocation = null;
    protected String _fontName = "Dialog";
    protected int _fontSize = 10;
    protected JComboBox _fontSizeCombo;
    protected boolean _isDisposed = false;
    protected Dimension _lastTableViewportSize;
    protected LogLevel _leastSevereDisplayedLogLevel = LogLevel.DEBUG;
    protected List _levels = null;
    protected boolean _loadSystemFonts = false;
    protected Object _lock = new Object();
    protected Map _logLevelMenuItems = new HashMap();
    protected JFrame _logMonitorFrame;
    protected int _logMonitorFrameHeight = 500;
    protected int _logMonitorFrameWidth = 550;
    protected Map _logTableColumnMenuItems = new HashMap();
    protected JScrollPane _logTableScrollPane;
    protected MRUFileManager _mruFileManager = null;
    protected String _searchText;
    protected JLabel _statusLabel;
    protected LogTable _table;
    protected boolean _trackTableScrollPane = true;

    /* access modifiers changed from: protected */
    public void trackTableScrollPane() {
    }

    public LogBrokerMonitor(List list) {
        this._levels = list;
        this._columns = LogTableColumn.getLogTableColumns();
        String property = System.getProperty("monitor.exit");
        if ((property == null ? Bugly.SDK_IS_DEV : property).trim().toLowerCase().equals("true")) {
            this._callSystemExitOnClose = true;
        }
        initComponents();
        this._logMonitorFrame.addWindowListener(new LogBrokerMonitorWindowAdaptor(this));
    }

    public void show(final int i) {
        if (!this._logMonitorFrame.isVisible()) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Thread.yield();
                    LogBrokerMonitor.this.pause(i);
                    LogBrokerMonitor.this._logMonitorFrame.setVisible(true);
                }
            });
        }
    }

    public void show() {
        show(0);
    }

    public void dispose() {
        this._logMonitorFrame.dispose();
        this._isDisposed = true;
        if (this._callSystemExitOnClose) {
            System.exit(0);
        }
    }

    public void hide() {
        this._logMonitorFrame.setVisible(false);
    }

    public DateFormatManager getDateFormatManager() {
        return this._table.getDateFormatManager();
    }

    public void setDateFormatManager(DateFormatManager dateFormatManager) {
        this._table.setDateFormatManager(dateFormatManager);
    }

    public boolean getCallSystemExitOnClose() {
        return this._callSystemExitOnClose;
    }

    public void setCallSystemExitOnClose(boolean z) {
        this._callSystemExitOnClose = z;
    }

    public void addMessage(final LogRecord logRecord) {
        if (!this._isDisposed) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    LogBrokerMonitor.this._categoryExplorerTree.getExplorerModel().addLogRecord(logRecord);
                    LogBrokerMonitor.this._table.getFilteredLogTableModel().addLogRecord(logRecord);
                    LogBrokerMonitor.this.updateStatusLabel();
                }
            });
        }
    }

    public void setMaxNumberOfLogRecords(int i) {
        this._table.getFilteredLogTableModel().setMaxNumberOfLogRecords(i);
    }

    public JFrame getBaseFrame() {
        return this._logMonitorFrame;
    }

    public void setTitle(String str) {
        JFrame jFrame = this._logMonitorFrame;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" - LogFactor5");
        jFrame.setTitle(stringBuffer.toString());
    }

    public void setFrameSize(int i, int i2) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (i > 0 && i < screenSize.width) {
            this._logMonitorFrameWidth = i;
        }
        if (i2 > 0 && i2 < screenSize.height) {
            this._logMonitorFrameHeight = i2;
        }
        updateFrameSize();
    }

    public void setFontSize(int i) {
        changeFontSizeCombo(this._fontSizeCombo, i);
    }

    public void addDisplayedProperty(Object obj) {
        this._displayedLogBrokerProperties.add(obj);
    }

    public Map getLogLevelMenuItems() {
        return this._logLevelMenuItems;
    }

    public Map getLogTableColumnMenuItems() {
        return this._logTableColumnMenuItems;
    }

    public JCheckBoxMenuItem getTableColumnMenuItem(LogTableColumn logTableColumn) {
        return getLogTableColumnMenuItem(logTableColumn);
    }

    public CategoryExplorerTree getCategoryExplorerTree() {
        return this._categoryExplorerTree;
    }

    public String getNDCTextFilter() {
        return this._NDCTextFilter;
    }

    public void setNDCLogRecordFilter(String str) {
        this._table.getFilteredLogTableModel().setLogRecordFilter(createNDCLogRecordFilter(str));
    }

    /* access modifiers changed from: protected */
    public void setSearchText(String str) {
        this._searchText = str;
    }

    /* access modifiers changed from: protected */
    public void setNDCTextFilter(String str) {
        if (str == null) {
            this._NDCTextFilter = "";
        } else {
            this._NDCTextFilter = str;
        }
    }

    /* access modifiers changed from: protected */
    public void sortByNDC() {
        String str = this._NDCTextFilter;
        if (str != null && str.length() != 0) {
            this._table.getFilteredLogTableModel().setLogRecordFilter(createNDCLogRecordFilter(str));
        }
    }

    /* access modifiers changed from: protected */
    public void findSearchText() {
        String str = this._searchText;
        if (str != null && str.length() != 0) {
            selectRow(findRecord(getFirstSelectedRow(), str, this._table.getFilteredLogTableModel().getFilteredRecords()));
        }
    }

    /* access modifiers changed from: protected */
    public int getFirstSelectedRow() {
        return this._table.getSelectionModel().getMinSelectionIndex();
    }

    /* access modifiers changed from: protected */
    public void selectRow(int i) {
        if (i == -1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this._searchText);
            stringBuffer.append(" not found.");
            JOptionPane.showMessageDialog(this._logMonitorFrame, stringBuffer.toString(), "Text not found", 1);
            return;
        }
        LF5SwingUtils.selectRow(i, this._table, this._logTableScrollPane);
    }

    /* access modifiers changed from: protected */
    public int findRecord(int i, String str, List list) {
        int i2 = i < 0 ? 0 : i + 1;
        int size = list.size();
        for (int i3 = i2; i3 < size; i3++) {
            if (matches((LogRecord) list.get(i3), str)) {
                return i3;
            }
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (matches((LogRecord) list.get(i4), str)) {
                return i4;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public boolean matches(LogRecord logRecord, String str) {
        String message = logRecord.getMessage();
        String ndc = logRecord.getNDC();
        if ((message == null && ndc == null) || str == null) {
            return false;
        }
        if (message.toLowerCase().indexOf(str.toLowerCase()) == -1 && ndc.toLowerCase().indexOf(str.toLowerCase()) == -1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void refresh(JTextArea jTextArea) {
        String text = jTextArea.getText();
        jTextArea.setText("");
        jTextArea.setText(text);
    }

    /* access modifiers changed from: protected */
    public void refreshDetailTextArea() {
        refresh(this._table._detailTextArea);
    }

    /* access modifiers changed from: protected */
    public void clearDetailTextArea() {
        this._table._detailTextArea.setText("");
    }

    /* access modifiers changed from: protected */
    public int changeFontSizeCombo(JComboBox jComboBox, int i) {
        int itemCount = jComboBox.getItemCount();
        Object itemAt = jComboBox.getItemAt(0);
        int parseInt = Integer.parseInt(String.valueOf(itemAt));
        for (int i2 = 0; i2 < itemCount; i2++) {
            Object itemAt2 = jComboBox.getItemAt(i2);
            int parseInt2 = Integer.parseInt(String.valueOf(itemAt2));
            if (parseInt < parseInt2 && parseInt2 <= i) {
                itemAt = itemAt2;
                parseInt = parseInt2;
            }
        }
        jComboBox.setSelectedItem(itemAt);
        return parseInt;
    }

    /* access modifiers changed from: protected */
    public void setFontSizeSilently(int i) {
        this._fontSize = i;
        setFontSize(this._table._detailTextArea, i);
        selectRow(0);
        setFontSize(this._table, i);
    }

    /* access modifiers changed from: protected */
    public void setFontSize(Component component, int i) {
        Font font = component.getFont();
        component.setFont(new Font(font.getFontName(), font.getStyle(), i));
    }

    /* access modifiers changed from: protected */
    public void updateFrameSize() {
        this._logMonitorFrame.setSize(this._logMonitorFrameWidth, this._logMonitorFrameHeight);
        centerFrame(this._logMonitorFrame);
    }

    /* access modifiers changed from: protected */
    public void pause(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void initComponents() {
        JFrame jFrame = new JFrame("LogFactor5");
        this._logMonitorFrame = jFrame;
        jFrame.setDefaultCloseOperation(0);
        URL resource = getClass().getResource("/org/apache/log4j/lf5/viewer/images/lf5_small_icon.gif");
        if (resource != null) {
            this._logMonitorFrame.setIconImage(new ImageIcon(resource).getImage());
        }
        updateFrameSize();
        JTextArea createDetailTextArea = createDetailTextArea();
        JScrollPane jScrollPane = new JScrollPane(createDetailTextArea);
        LogTable logTable = new LogTable(createDetailTextArea);
        this._table = logTable;
        setView(this._currentView, logTable);
        this._table.setFont(new Font(this._fontName, 0, this._fontSize));
        JScrollPane jScrollPane2 = new JScrollPane(this._table);
        this._logTableScrollPane = jScrollPane2;
        if (this._trackTableScrollPane) {
            jScrollPane2.getVerticalScrollBar().addAdjustmentListener(new TrackingAdjustmentListener());
        }
        JSplitPane jSplitPane = new JSplitPane();
        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setOrientation(0);
        jSplitPane.setLeftComponent(this._logTableScrollPane);
        jSplitPane.setRightComponent(jScrollPane);
        jSplitPane.setDividerLocation(350);
        this._categoryExplorerTree = new CategoryExplorerTree();
        this._table.getFilteredLogTableModel().setLogRecordFilter(createLogRecordFilter());
        JScrollPane jScrollPane3 = new JScrollPane(this._categoryExplorerTree);
        jScrollPane3.setPreferredSize(new Dimension(130, TbsListener.ErrorCode.INFO_CODE_BASE));
        this._mruFileManager = new MRUFileManager();
        JSplitPane jSplitPane2 = new JSplitPane();
        jSplitPane2.setOneTouchExpandable(true);
        jSplitPane2.setRightComponent(jSplitPane);
        jSplitPane2.setLeftComponent(jScrollPane3);
        jSplitPane2.setDividerLocation(130);
        this._logMonitorFrame.getRootPane().setJMenuBar(createMenuBar());
        this._logMonitorFrame.getContentPane().add(jSplitPane2, "Center");
        this._logMonitorFrame.getContentPane().add(createToolBar(), "North");
        this._logMonitorFrame.getContentPane().add(createStatusArea(), "South");
        makeLogTableListenToCategoryExplorer();
        addTableModelProperties();
        this._configurationManager = new ConfigurationManager(this, this._table);
    }

    /* access modifiers changed from: protected */
    public LogRecordFilter createLogRecordFilter() {
        return new LogRecordFilter() {
            public boolean passes(LogRecord logRecord) {
                return LogBrokerMonitor.this.getMenuItem(logRecord.getLevel()).isSelected() && LogBrokerMonitor.this._categoryExplorerTree.getExplorerModel().isCategoryPathActive(new CategoryPath(logRecord.getCategory()));
            }
        };
    }

    /* access modifiers changed from: protected */
    public LogRecordFilter createNDCLogRecordFilter(String str) {
        this._NDCTextFilter = str;
        return new LogRecordFilter() {
            public boolean passes(LogRecord logRecord) {
                String ndc = logRecord.getNDC();
                CategoryPath categoryPath = new CategoryPath(logRecord.getCategory());
                if (ndc == null || LogBrokerMonitor.this._NDCTextFilter == null || ndc.toLowerCase().indexOf(LogBrokerMonitor.this._NDCTextFilter.toLowerCase()) == -1 || !LogBrokerMonitor.this.getMenuItem(logRecord.getLevel()).isSelected() || !LogBrokerMonitor.this._categoryExplorerTree.getExplorerModel().isCategoryPathActive(categoryPath)) {
                    return false;
                }
                return true;
            }
        };
    }

    /* access modifiers changed from: protected */
    public void updateStatusLabel() {
        this._statusLabel.setText(getRecordsDisplayedMessage());
    }

    /* access modifiers changed from: protected */
    public String getRecordsDisplayedMessage() {
        FilteredLogTableModel filteredLogTableModel = this._table.getFilteredLogTableModel();
        return getStatusText(filteredLogTableModel.getRowCount(), filteredLogTableModel.getTotalRowCount());
    }

    /* access modifiers changed from: protected */
    public void addTableModelProperties() {
        final FilteredLogTableModel filteredLogTableModel = this._table.getFilteredLogTableModel();
        addDisplayedProperty(new Object() {
            public String toString() {
                return LogBrokerMonitor.this.getRecordsDisplayedMessage();
            }
        });
        addDisplayedProperty(new Object() {
            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Maximum number of displayed LogRecords: ");
                stringBuffer.append(filteredLogTableModel._maxNumberOfLogRecords);
                return stringBuffer.toString();
            }
        });
    }

    /* access modifiers changed from: protected */
    public String getStatusText(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Displaying: ");
        stringBuffer.append(i);
        stringBuffer.append(" records out of a total of: ");
        stringBuffer.append(i2);
        stringBuffer.append(" records.");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public void makeLogTableListenToCategoryExplorer() {
        this._categoryExplorerTree.getExplorerModel().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
                LogBrokerMonitor.this.updateStatusLabel();
            }
        });
    }

    /* access modifiers changed from: protected */
    public JPanel createStatusArea() {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("No log records to display.");
        this._statusLabel = jLabel;
        jLabel.setHorizontalAlignment(2);
        jPanel.setBorder(BorderFactory.createEtchedBorder());
        jPanel.setLayout(new FlowLayout(0, 0, 0));
        jPanel.add(jLabel);
        return jPanel;
    }

    /* access modifiers changed from: protected */
    public JTextArea createDetailTextArea() {
        JTextArea jTextArea = new JTextArea();
        jTextArea.setFont(new Font("Monospaced", 0, 14));
        jTextArea.setTabSize(3);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(false);
        return jTextArea;
    }

    /* access modifiers changed from: protected */
    public JMenuBar createMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createEditMenu());
        jMenuBar.add(createLogLevelMenu());
        jMenuBar.add(createViewMenu());
        jMenuBar.add(createConfigureMenu());
        jMenuBar.add(createHelpMenu());
        return jMenuBar;
    }

    /* access modifiers changed from: protected */
    public JMenu createLogLevelMenu() {
        JMenu jMenu = new JMenu("Log Level");
        jMenu.setMnemonic('l');
        Iterator logLevels = getLogLevels();
        while (logLevels.hasNext()) {
            jMenu.add(getMenuItem((LogLevel) logLevels.next()));
        }
        jMenu.addSeparator();
        jMenu.add(createAllLogLevelsMenuItem());
        jMenu.add(createNoLogLevelsMenuItem());
        jMenu.addSeparator();
        jMenu.add(createLogLevelColorMenu());
        jMenu.add(createResetLogLevelColorMenuItem());
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createAllLogLevelsMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Show all LogLevels");
        jMenuItem.setMnemonic('s');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.selectAllLogLevels(true);
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
                LogBrokerMonitor.this.updateStatusLabel();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createNoLogLevelsMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Hide all LogLevels");
        jMenuItem.setMnemonic('h');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.selectAllLogLevels(false);
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
                LogBrokerMonitor.this.updateStatusLabel();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenu createLogLevelColorMenu() {
        JMenu jMenu = new JMenu("Configure LogLevel Colors");
        jMenu.setMnemonic('c');
        Iterator logLevels = getLogLevels();
        while (logLevels.hasNext()) {
            jMenu.add(createSubMenuItem((LogLevel) logLevels.next()));
        }
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createResetLogLevelColorMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Reset LogLevel Colors");
        jMenuItem.setMnemonic('r');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogLevel.resetLogLevelColorMap();
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void selectAllLogLevels(boolean z) {
        Iterator logLevels = getLogLevels();
        while (logLevels.hasNext()) {
            getMenuItem((LogLevel) logLevels.next()).setSelected(z);
        }
    }

    /* access modifiers changed from: protected */
    public JCheckBoxMenuItem getMenuItem(LogLevel logLevel) {
        JCheckBoxMenuItem jCheckBoxMenuItem = (JCheckBoxMenuItem) this._logLevelMenuItems.get(logLevel);
        if (jCheckBoxMenuItem != null) {
            return jCheckBoxMenuItem;
        }
        JCheckBoxMenuItem createMenuItem = createMenuItem(logLevel);
        this._logLevelMenuItems.put(logLevel, createMenuItem);
        return createMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createSubMenuItem(final LogLevel logLevel) {
        final JMenuItem jMenuItem = new JMenuItem(logLevel.toString());
        jMenuItem.setMnemonic(logLevel.toString().charAt(0));
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.showLogLevelColorChangeDialog(jMenuItem, logLevel);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void showLogLevelColorChangeDialog(JMenuItem jMenuItem, LogLevel logLevel) {
        Color showDialog = JColorChooser.showDialog(this._logMonitorFrame, "Choose LogLevel Color", jMenuItem.getForeground());
        if (showDialog != null) {
            logLevel.setLogLevelColorMap(logLevel, showDialog);
            this._table.getFilteredLogTableModel().refresh();
        }
    }

    /* access modifiers changed from: protected */
    public JCheckBoxMenuItem createMenuItem(LogLevel logLevel) {
        JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(logLevel.toString());
        jCheckBoxMenuItem.setSelected(true);
        jCheckBoxMenuItem.setMnemonic(logLevel.toString().charAt(0));
        jCheckBoxMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
                LogBrokerMonitor.this.updateStatusLabel();
            }
        });
        return jCheckBoxMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenu createViewMenu() {
        JMenu jMenu = new JMenu("View");
        jMenu.setMnemonic('v');
        Iterator logTableColumns = getLogTableColumns();
        while (logTableColumns.hasNext()) {
            jMenu.add(getLogTableColumnMenuItem((LogTableColumn) logTableColumns.next()));
        }
        jMenu.addSeparator();
        jMenu.add(createAllLogTableColumnsMenuItem());
        jMenu.add(createNoLogTableColumnsMenuItem());
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JCheckBoxMenuItem getLogTableColumnMenuItem(LogTableColumn logTableColumn) {
        JCheckBoxMenuItem jCheckBoxMenuItem = (JCheckBoxMenuItem) this._logTableColumnMenuItems.get(logTableColumn);
        if (jCheckBoxMenuItem != null) {
            return jCheckBoxMenuItem;
        }
        JCheckBoxMenuItem createLogTableColumnMenuItem = createLogTableColumnMenuItem(logTableColumn);
        this._logTableColumnMenuItems.put(logTableColumn, createLogTableColumnMenuItem);
        return createLogTableColumnMenuItem;
    }

    /* access modifiers changed from: protected */
    public JCheckBoxMenuItem createLogTableColumnMenuItem(LogTableColumn logTableColumn) {
        JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(logTableColumn.toString());
        jCheckBoxMenuItem.setSelected(true);
        jCheckBoxMenuItem.setMnemonic(logTableColumn.toString().charAt(0));
        jCheckBoxMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this._table.setView(LogBrokerMonitor.this.updateView());
            }
        });
        return jCheckBoxMenuItem;
    }

    /* access modifiers changed from: protected */
    public List updateView() {
        ArrayList arrayList = new ArrayList();
        for (LogTableColumn logTableColumn : this._columns) {
            if (getLogTableColumnMenuItem(logTableColumn).isSelected()) {
                arrayList.add(logTableColumn);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createAllLogTableColumnsMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Show all Columns");
        jMenuItem.setMnemonic('s');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.selectAllLogTableColumns(true);
                LogBrokerMonitor.this._table.setView(LogBrokerMonitor.this.updateView());
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createNoLogTableColumnsMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Hide all Columns");
        jMenuItem.setMnemonic('h');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.selectAllLogTableColumns(false);
                LogBrokerMonitor.this._table.setView(LogBrokerMonitor.this.updateView());
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void selectAllLogTableColumns(boolean z) {
        Iterator logTableColumns = getLogTableColumns();
        while (logTableColumns.hasNext()) {
            getLogTableColumnMenuItem((LogTableColumn) logTableColumns.next()).setSelected(z);
        }
    }

    /* access modifiers changed from: protected */
    public JMenu createFileMenu() {
        JMenu jMenu = new JMenu("File");
        jMenu.setMnemonic('f');
        jMenu.add(createOpenMI());
        jMenu.add(createOpenURLMI());
        jMenu.addSeparator();
        jMenu.add(createCloseMI());
        createMRUFileListMI(jMenu);
        jMenu.addSeparator();
        jMenu.add(createExitMI());
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createOpenMI() {
        JMenuItem jMenuItem = new JMenuItem("Open...");
        jMenuItem.setMnemonic('o');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.requestOpen();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createOpenURLMI() {
        JMenuItem jMenuItem = new JMenuItem("Open URL...");
        jMenuItem.setMnemonic('u');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.requestOpenURL();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createCloseMI() {
        JMenuItem jMenuItem = new JMenuItem("Close");
        jMenuItem.setMnemonic('c');
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.requestClose();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void createMRUFileListMI(JMenu jMenu) {
        String[] mRUFileList = this._mruFileManager.getMRUFileList();
        if (mRUFileList != null) {
            jMenu.addSeparator();
            int i = 0;
            while (i < mRUFileList.length) {
                StringBuffer stringBuffer = new StringBuffer();
                int i2 = i + 1;
                stringBuffer.append(i2);
                stringBuffer.append(StringUtils.SPACE);
                stringBuffer.append(mRUFileList[i]);
                JMenuItem jMenuItem = new JMenuItem(stringBuffer.toString());
                jMenuItem.setMnemonic(i2);
                jMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        LogBrokerMonitor.this.requestOpenMRU(actionEvent);
                    }
                });
                jMenu.add(jMenuItem);
                i = i2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public JMenuItem createExitMI() {
        JMenuItem jMenuItem = new JMenuItem("Exit");
        jMenuItem.setMnemonic('x');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.requestExit();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenu createConfigureMenu() {
        JMenu jMenu = new JMenu("Configure");
        jMenu.setMnemonic('c');
        jMenu.add(createConfigureSave());
        jMenu.add(createConfigureReset());
        jMenu.add(createConfigureMaxRecords());
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createConfigureSave() {
        JMenuItem jMenuItem = new JMenuItem("Save");
        jMenuItem.setMnemonic('s');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.saveConfiguration();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createConfigureReset() {
        JMenuItem jMenuItem = new JMenuItem("Reset");
        jMenuItem.setMnemonic('r');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.resetConfiguration();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createConfigureMaxRecords() {
        JMenuItem jMenuItem = new JMenuItem("Set Max Number of Records");
        jMenuItem.setMnemonic('m');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.setMaxRecordConfiguration();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void saveConfiguration() {
        this._configurationManager.save();
    }

    /* access modifiers changed from: protected */
    public void resetConfiguration() {
        this._configurationManager.reset();
    }

    /* access modifiers changed from: protected */
    public void setMaxRecordConfiguration() {
        String text = new LogFactor5InputDialog(getBaseFrame(), "Set Max Number of Records", "", 10).getText();
        if (text != null) {
            try {
                setMaxNumberOfLogRecords(Integer.parseInt(text));
            } catch (NumberFormatException unused) {
                JFrame baseFrame = getBaseFrame();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("'");
                stringBuffer.append(text);
                stringBuffer.append("' is an invalid parameter.\nPlease try again.");
                new LogFactor5ErrorDialog(baseFrame, stringBuffer.toString());
                setMaxRecordConfiguration();
            }
        }
    }

    /* access modifiers changed from: protected */
    public JMenu createHelpMenu() {
        JMenu jMenu = new JMenu("Help");
        jMenu.setMnemonic('h');
        jMenu.add(createHelpProperties());
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createHelpProperties() {
        JMenuItem jMenuItem = new JMenuItem("LogFactor5 Properties");
        jMenuItem.setMnemonic('l');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.showPropertiesDialog("LogFactor5 Properties");
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void showPropertiesDialog(String str) {
        JOptionPane.showMessageDialog(this._logMonitorFrame, this._displayedLogBrokerProperties.toArray(), str, -1);
    }

    /* access modifiers changed from: protected */
    public JMenu createEditMenu() {
        JMenu jMenu = new JMenu("Edit");
        jMenu.setMnemonic('e');
        jMenu.add(createEditFindMI());
        jMenu.add(createEditFindNextMI());
        jMenu.addSeparator();
        jMenu.add(createEditSortNDCMI());
        jMenu.add(createEditRestoreAllNDCMI());
        return jMenu;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createEditFindNextMI() {
        JMenuItem jMenuItem = new JMenuItem("Find Next");
        jMenuItem.setMnemonic('n');
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("F3"));
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.findSearchText();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createEditFindMI() {
        JMenuItem jMenuItem = new JMenuItem("Find");
        jMenuItem.setMnemonic('f');
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke("control F"));
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.setSearchText(JOptionPane.showInputDialog(LogBrokerMonitor.this._logMonitorFrame, "Find text: ", "Search Record Messages", 3));
                LogBrokerMonitor.this.findSearchText();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createEditSortNDCMI() {
        JMenuItem jMenuItem = new JMenuItem("Sort by NDC");
        jMenuItem.setMnemonic('s');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.setNDCTextFilter(JOptionPane.showInputDialog(LogBrokerMonitor.this._logMonitorFrame, "Sort by this NDC: ", "Sort Log Records by NDC", 3));
                LogBrokerMonitor.this.sortByNDC();
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
                LogBrokerMonitor.this.updateStatusLabel();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createEditRestoreAllNDCMI() {
        JMenuItem jMenuItem = new JMenuItem("Restore all NDCs");
        jMenuItem.setMnemonic('r');
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this._table.getFilteredLogTableModel().setLogRecordFilter(LogBrokerMonitor.this.createLogRecordFilter());
                LogBrokerMonitor.this.setNDCTextFilter("");
                LogBrokerMonitor.this._table.getFilteredLogTableModel().refresh();
                LogBrokerMonitor.this.updateStatusLabel();
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JToolBar createToolBar() {
        String[] strArr;
        JToolBar jToolBar = new JToolBar();
        jToolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
        JComboBox jComboBox = new JComboBox();
        JComboBox jComboBox2 = new JComboBox();
        this._fontSizeCombo = jComboBox2;
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        URL resource = classLoader.getResource("org/apache/log4j/lf5/viewer/images/channelexplorer_new.gif");
        ImageIcon imageIcon = null;
        if (resource != null) {
            imageIcon = new ImageIcon(resource);
        }
        JButton jButton = new JButton("Clear Log Table");
        if (imageIcon != null) {
            jButton.setIcon(imageIcon);
        }
        jButton.setToolTipText("Clear Log Table.");
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this._table.clearLogRecords();
                LogBrokerMonitor.this._categoryExplorerTree.getExplorerModel().resetAllNodeCounts();
                LogBrokerMonitor.this.updateStatusLabel();
                LogBrokerMonitor.this.clearDetailTextArea();
                LogRecord.resetSequenceNumber();
            }
        });
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        if (this._loadSystemFonts) {
            strArr = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        } else {
            strArr = defaultToolkit.getFontList();
        }
        for (String addItem : strArr) {
            jComboBox.addItem(addItem);
        }
        jComboBox.setSelectedItem(this._fontName);
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String str = (String) ((JComboBox) actionEvent.getSource()).getSelectedItem();
                LogBrokerMonitor.this._table.setFont(new Font(str, 0, LogBrokerMonitor.this._fontSize));
                LogBrokerMonitor.this._fontName = str;
            }
        });
        jComboBox2.addItem(NetConstant.PAGE_ID_PRODUCT_INTRODUCE);
        jComboBox2.addItem(NetConstant.PAGE_ID_RCMD);
        jComboBox2.addItem("10");
        jComboBox2.addItem("12");
        jComboBox2.addItem("14");
        jComboBox2.addItem("16");
        jComboBox2.addItem("18");
        jComboBox2.addItem("24");
        jComboBox2.setSelectedItem(String.valueOf(this._fontSize));
        jComboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int intValue = Integer.valueOf((String) ((JComboBox) actionEvent.getSource()).getSelectedItem()).intValue();
                LogBrokerMonitor.this.setFontSizeSilently(intValue);
                LogBrokerMonitor.this.refreshDetailTextArea();
                LogBrokerMonitor.this._fontSize = intValue;
            }
        });
        jToolBar.add(new JLabel(" Font: "));
        jToolBar.add(jComboBox);
        jToolBar.add(jComboBox2);
        jToolBar.addSeparator();
        jToolBar.addSeparator();
        jToolBar.add(jButton);
        jButton.setAlignmentY(0.5f);
        jButton.setAlignmentX(0.5f);
        jComboBox.setMaximumSize(jComboBox.getPreferredSize());
        jComboBox2.setMaximumSize(jComboBox2.getPreferredSize());
        return jToolBar;
    }

    /* access modifiers changed from: protected */
    public void setView(String str, LogTable logTable) {
        if (DETAILED_VIEW.equals(str)) {
            logTable.setDetailedView();
            this._currentView = str;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("does not match a supported view.");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public JComboBox createLogLevelCombo() {
        JComboBox jComboBox = new JComboBox();
        Iterator logLevels = getLogLevels();
        while (logLevels.hasNext()) {
            jComboBox.addItem(logLevels.next());
        }
        jComboBox.setSelectedItem(this._leastSevereDisplayedLogLevel);
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                LogBrokerMonitor.this.setLeastSevereDisplayedLogLevel((LogLevel) ((JComboBox) actionEvent.getSource()).getSelectedItem());
            }
        });
        jComboBox.setMaximumSize(jComboBox.getPreferredSize());
        return jComboBox;
    }

    /* access modifiers changed from: protected */
    public void setLeastSevereDisplayedLogLevel(LogLevel logLevel) {
        if (logLevel != null && this._leastSevereDisplayedLogLevel != logLevel) {
            this._leastSevereDisplayedLogLevel = logLevel;
            this._table.getFilteredLogTableModel().refresh();
            updateStatusLabel();
        }
    }

    /* access modifiers changed from: protected */
    public void centerFrame(JFrame jFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = jFrame.getSize();
        jFrame.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
    }

    /* access modifiers changed from: protected */
    public void requestOpen() {
        JFileChooser jFileChooser;
        if (this._fileLocation == null) {
            jFileChooser = new JFileChooser();
        } else {
            jFileChooser = new JFileChooser(this._fileLocation);
        }
        if (jFileChooser.showOpenDialog(this._logMonitorFrame) == 0) {
            File selectedFile = jFileChooser.getSelectedFile();
            if (loadLogFile(selectedFile)) {
                this._fileLocation = jFileChooser.getSelectedFile();
                this._mruFileManager.set(selectedFile);
                updateMRUList();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void requestOpenURL() {
        String text = new LogFactor5InputDialog(getBaseFrame(), "Open URL", "URL:").getText();
        if (text != null) {
            if (text.indexOf("://") == -1) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(HttpConstant.HTTP_URL);
                stringBuffer.append(text);
                text = stringBuffer.toString();
            }
            try {
                URL url = new URL(text);
                if (loadLogFile(url)) {
                    this._mruFileManager.set(url);
                    updateMRUList();
                }
            } catch (MalformedURLException unused) {
                new LogFactor5ErrorDialog(getBaseFrame(), "Error reading URL.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateMRUList() {
        JMenu menu = this._logMonitorFrame.getJMenuBar().getMenu(0);
        menu.removeAll();
        menu.add(createOpenMI());
        menu.add(createOpenURLMI());
        menu.addSeparator();
        menu.add(createCloseMI());
        createMRUFileListMI(menu);
        menu.addSeparator();
        menu.add(createExitMI());
    }

    /* access modifiers changed from: protected */
    public void requestClose() {
        setCallSystemExitOnClose(false);
        closeAfterConfirm();
    }

    /* access modifiers changed from: protected */
    public void requestOpenMRU(ActionEvent actionEvent) {
        StringTokenizer stringTokenizer = new StringTokenizer(actionEvent.getActionCommand());
        String trim = stringTokenizer.nextToken().trim();
        String nextToken = stringTokenizer.nextToken(StringUtils.LF);
        try {
            int parseInt = Integer.parseInt(trim) - 1;
            new LogFileParser(this._mruFileManager.getInputStream(parseInt)).parse(this);
            this._mruFileManager.moveToTop(parseInt);
            updateMRUList();
        } catch (Exception unused) {
            JFrame baseFrame = getBaseFrame();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to load file ");
            stringBuffer.append(nextToken);
            new LogFactor5ErrorDialog(baseFrame, stringBuffer.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void requestExit() {
        this._mruFileManager.save();
        setCallSystemExitOnClose(true);
        closeAfterConfirm();
    }

    /* access modifiers changed from: protected */
    public void closeAfterConfirm() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!this._callSystemExitOnClose) {
            stringBuffer.append("Are you sure you want to close the logging ");
            stringBuffer.append("console?\n");
            stringBuffer.append("(Note: This will not shut down the Virtual Machine,\n");
            stringBuffer.append("or the Swing event thread.)");
        } else {
            stringBuffer.append("Are you sure you want to exit?\n");
            stringBuffer.append("This will shut down the Virtual Machine.\n");
        }
        if (JOptionPane.showConfirmDialog(this._logMonitorFrame, stringBuffer.toString(), this._callSystemExitOnClose ? "Are you sure you want to exit?" : "Are you sure you want to dispose of the Logging Console?", 2, 3, (Icon) null) == 0) {
            dispose();
        }
    }

    /* access modifiers changed from: protected */
    public Iterator getLogLevels() {
        return this._levels.iterator();
    }

    /* access modifiers changed from: protected */
    public Iterator getLogTableColumns() {
        return this._columns.iterator();
    }

    /* access modifiers changed from: protected */
    public boolean loadLogFile(File file) {
        try {
            new LogFileParser(file).parse(this);
            return true;
        } catch (IOException unused) {
            JFrame baseFrame = getBaseFrame();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Error reading ");
            stringBuffer.append(file.getName());
            new LogFactor5ErrorDialog(baseFrame, stringBuffer.toString());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean loadLogFile(URL url) {
        try {
            new LogFileParser(url.openStream()).parse(this);
            return true;
        } catch (IOException unused) {
            JFrame baseFrame = getBaseFrame();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Error reading URL:");
            stringBuffer.append(url.getFile());
            new LogFactor5ErrorDialog(baseFrame, stringBuffer.toString());
            return false;
        }
    }

    class LogBrokerMonitorWindowAdaptor extends WindowAdapter {
        protected LogBrokerMonitor _monitor;

        public LogBrokerMonitorWindowAdaptor(LogBrokerMonitor logBrokerMonitor) {
            this._monitor = logBrokerMonitor;
        }

        public void windowClosing(WindowEvent windowEvent) {
            this._monitor.requestClose();
        }
    }
}
