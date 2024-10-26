package org.apache.log4j.lf5.viewer;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.lf5.util.DateFormatManager;

public class LogTable extends JTable {
    protected int _colCategory;
    protected int _colDate;
    protected int _colLevel;
    protected int _colLocation;
    protected int _colMessage;
    protected int _colMessageNum;
    protected int _colNDC;
    protected LogTableColumn[] _colNames = LogTableColumn.getLogTableColumnArray();
    protected int _colThread;
    protected int _colThrown;
    protected int[] _colWidths = {40, 40, 40, 70, 70, 360, 440, 200, 60};
    protected DateFormatManager _dateFormatManager;
    protected JTextArea _detailTextArea;
    protected int _numCols = 9;
    protected int _rowHeight = 30;
    protected TableColumn[] _tableColumns = new TableColumn[9];

    public LogTable(JTextArea jTextArea) {
        int i = 0;
        this._colDate = 0;
        this._colThread = 1;
        this._colMessageNum = 2;
        this._colLevel = 3;
        this._colNDC = 4;
        this._colCategory = 5;
        this._colMessage = 6;
        this._colLocation = 7;
        this._colThrown = 8;
        this._dateFormatManager = null;
        init();
        this._detailTextArea = jTextArea;
        setModel(new FilteredLogTableModel());
        Enumeration columns = getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn tableColumn = (TableColumn) columns.nextElement();
            tableColumn.setCellRenderer(new LogTableRowRenderer());
            tableColumn.setPreferredWidth(this._colWidths[i]);
            this._tableColumns[i] = tableColumn;
            i++;
        }
        getSelectionModel().addListSelectionListener(new LogTableListSelectionListener(this));
    }

    public DateFormatManager getDateFormatManager() {
        return this._dateFormatManager;
    }

    public void setDateFormatManager(DateFormatManager dateFormatManager) {
        this._dateFormatManager = dateFormatManager;
    }

    public synchronized void clearLogRecords() {
        getFilteredLogTableModel().clear();
    }

    public FilteredLogTableModel getFilteredLogTableModel() {
        return getModel();
    }

    public void setDetailedView() {
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < this._numCols; i++) {
            columnModel.removeColumn(this._tableColumns[i]);
        }
        for (int i2 = 0; i2 < this._numCols; i2++) {
            columnModel.addColumn(this._tableColumns[i2]);
        }
        sizeColumnsToFit(-1);
    }

    public void setView(List list) {
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < this._numCols; i++) {
            columnModel.removeColumn(this._tableColumns[i]);
        }
        Vector columnNameAndNumber = getColumnNameAndNumber();
        for (Object indexOf : list) {
            columnModel.addColumn(this._tableColumns[columnNameAndNumber.indexOf(indexOf)]);
        }
        sizeColumnsToFit(-1);
    }

    public void setFont(Font font) {
        LogTable.super.setFont(font);
        Graphics graphics = getGraphics();
        if (graphics != null) {
            int height = graphics.getFontMetrics(font).getHeight();
            int i = height + (height / 3);
            this._rowHeight = i;
            setRowHeight(i);
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        setRowHeight(this._rowHeight);
        setSelectionMode(0);
    }

    /* access modifiers changed from: protected */
    public Vector getColumnNameAndNumber() {
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            LogTableColumn[] logTableColumnArr = this._colNames;
            if (i >= logTableColumnArr.length) {
                return vector;
            }
            vector.add(i, logTableColumnArr[i]);
            i++;
        }
    }

    class LogTableListSelectionListener implements ListSelectionListener {
        protected JTable _table;

        public LogTableListSelectionListener(JTable jTable) {
            this._table = jTable;
        }

        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            if (!listSelectionEvent.getValueIsAdjusting()) {
                ListSelectionModel listSelectionModel = (ListSelectionModel) listSelectionEvent.getSource();
                if (!listSelectionModel.isSelectionEmpty()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    int minSelectionIndex = listSelectionModel.getMinSelectionIndex();
                    for (int i = 0; i < LogTable.this._numCols - 1; i++) {
                        Object valueAt = this._table.getModel().getValueAt(minSelectionIndex, i);
                        String obj = valueAt != null ? valueAt.toString() : "";
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append(LogTable.this._colNames[i]);
                        stringBuffer2.append(":");
                        stringBuffer.append(stringBuffer2.toString());
                        stringBuffer.append("\t");
                        if (i == LogTable.this._colThread || i == LogTable.this._colMessage || i == LogTable.this._colLevel) {
                            stringBuffer.append("\t");
                        }
                        if (i == LogTable.this._colDate || i == LogTable.this._colNDC) {
                            stringBuffer.append("\t\t");
                        }
                        stringBuffer.append(obj);
                        stringBuffer.append(StringUtils.LF);
                    }
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append(LogTable.this._colNames[LogTable.this._numCols - 1]);
                    stringBuffer3.append(":\n");
                    stringBuffer.append(stringBuffer3.toString());
                    Object valueAt2 = this._table.getModel().getValueAt(minSelectionIndex, LogTable.this._numCols - 1);
                    if (valueAt2 != null) {
                        stringBuffer.append(valueAt2.toString());
                    }
                    LogTable.this._detailTextArea.setText(stringBuffer.toString());
                }
            }
        }
    }
}
