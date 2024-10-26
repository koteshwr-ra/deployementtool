package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;

public class CategoryNode extends DefaultMutableTreeNode {
    protected boolean _hasFatalChildren = false;
    protected boolean _hasFatalRecords = false;
    protected int _numberOfContainedRecords = 0;
    protected int _numberOfRecordsFromChildren = 0;
    protected boolean _selected = true;

    public CategoryNode(String str) {
        setUserObject(str);
    }

    public String getTitle() {
        return (String) getUserObject();
    }

    public void setSelected(boolean z) {
        if (z != this._selected) {
            this._selected = z;
        }
    }

    public boolean isSelected() {
        return this._selected;
    }

    public void setAllDescendantsSelected() {
        Enumeration children = children();
        while (children.hasMoreElements()) {
            CategoryNode categoryNode = (CategoryNode) children.nextElement();
            categoryNode.setSelected(true);
            categoryNode.setAllDescendantsSelected();
        }
    }

    public void setAllDescendantsDeSelected() {
        Enumeration children = children();
        while (children.hasMoreElements()) {
            CategoryNode categoryNode = (CategoryNode) children.nextElement();
            categoryNode.setSelected(false);
            categoryNode.setAllDescendantsDeSelected();
        }
    }

    public String toString() {
        return getTitle();
    }

    public boolean equals(Object obj) {
        return (obj instanceof CategoryNode) && getTitle().toLowerCase().equals(((CategoryNode) obj).getTitle().toLowerCase());
    }

    public int hashCode() {
        return getTitle().hashCode();
    }

    public void addRecord() {
        this._numberOfContainedRecords++;
        addRecordToParent();
    }

    public int getNumberOfContainedRecords() {
        return this._numberOfContainedRecords;
    }

    public void resetNumberOfContainedRecords() {
        this._numberOfContainedRecords = 0;
        this._numberOfRecordsFromChildren = 0;
        this._hasFatalRecords = false;
        this._hasFatalChildren = false;
    }

    public boolean hasFatalRecords() {
        return this._hasFatalRecords;
    }

    public boolean hasFatalChildren() {
        return this._hasFatalChildren;
    }

    public void setHasFatalRecords(boolean z) {
        this._hasFatalRecords = z;
    }

    public void setHasFatalChildren(boolean z) {
        this._hasFatalChildren = z;
    }

    /* access modifiers changed from: protected */
    public int getTotalNumberOfRecords() {
        return getNumberOfRecordsFromChildren() + getNumberOfContainedRecords();
    }

    /* access modifiers changed from: protected */
    public void addRecordFromChild() {
        this._numberOfRecordsFromChildren++;
        addRecordToParent();
    }

    /* access modifiers changed from: protected */
    public int getNumberOfRecordsFromChildren() {
        return this._numberOfRecordsFromChildren;
    }

    /* access modifiers changed from: protected */
    public void addRecordToParent() {
        CategoryNode parent = getParent();
        if (parent != null) {
            parent.addRecordFromChild();
        }
    }
}
