package org.apache.log4j.lf5.viewer;

import javax.swing.table.DefaultTableModel;

public class LogTableModel extends DefaultTableModel {
    public boolean isCellEditable(int i, int i2) {
        return false;
    }

    public LogTableModel(Object[] objArr, int i) {
        super(objArr, i);
    }
}
