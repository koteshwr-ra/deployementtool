package org.apache.log4j.lf5.viewer;

import com.tencent.smtt.sdk.TbsListener;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.log4j.lf5.LogLevel;

public class LogTableRowRenderer extends DefaultTableCellRenderer {
    protected Color _color = new Color(TbsListener.ErrorCode.RENAME_SUCCESS, TbsListener.ErrorCode.RENAME_SUCCESS, TbsListener.ErrorCode.RENAME_SUCCESS);
    protected boolean _highlightFatal = true;

    public Component getTableCellRendererComponent(JTable jTable, Object obj, boolean z, boolean z2, int i, int i2) {
        if (i % 2 == 0) {
            setBackground(this._color);
        } else {
            setBackground(Color.white);
        }
        setForeground(getLogLevelColor(jTable.getModel().getFilteredRecord(i).getLevel()));
        return LogTableRowRenderer.super.getTableCellRendererComponent(jTable, obj, z, z2, i, i2);
    }

    /* access modifiers changed from: protected */
    public Color getLogLevelColor(LogLevel logLevel) {
        return (Color) LogLevel.getLogLevelColorMap().get(logLevel);
    }
}
