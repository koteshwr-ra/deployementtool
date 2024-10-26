package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreePath;

public class CategoryImmediateEditor extends DefaultTreeCellEditor {
    protected Icon editingIcon = null;
    private CategoryNodeRenderer renderer;

    public CategoryImmediateEditor(JTree jTree, CategoryNodeRenderer categoryNodeRenderer, CategoryNodeEditor categoryNodeEditor) {
        super(jTree, categoryNodeRenderer, categoryNodeEditor);
        this.renderer = categoryNodeRenderer;
        categoryNodeRenderer.setIcon((Icon) null);
        categoryNodeRenderer.setLeafIcon((Icon) null);
        categoryNodeRenderer.setOpenIcon((Icon) null);
        categoryNodeRenderer.setClosedIcon((Icon) null);
        this.editingIcon = null;
    }

    public boolean shouldSelectCell(EventObject eventObject) {
        if (!(eventObject instanceof MouseEvent)) {
            return false;
        }
        MouseEvent mouseEvent = (MouseEvent) eventObject;
        return ((CategoryNode) this.tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY()).getLastPathComponent()).isLeaf();
    }

    public boolean inCheckBoxHitRegion(MouseEvent mouseEvent) {
        TreePath pathForLocation = this.tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
        if (pathForLocation == null) {
            return false;
        }
        CategoryNode categoryNode = (CategoryNode) pathForLocation.getLastPathComponent();
        Rectangle rowBounds = this.tree.getRowBounds(this.lastRow);
        Dimension checkBoxOffset = this.renderer.getCheckBoxOffset();
        rowBounds.translate(this.offset + checkBoxOffset.width, checkBoxOffset.height);
        rowBounds.contains(mouseEvent.getPoint());
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean canEditImmediately(EventObject eventObject) {
        if (eventObject instanceof MouseEvent) {
            return inCheckBoxHitRegion((MouseEvent) eventObject);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void determineOffset(JTree jTree, Object obj, boolean z, boolean z2, boolean z3, int i) {
        this.offset = 0;
    }
}
