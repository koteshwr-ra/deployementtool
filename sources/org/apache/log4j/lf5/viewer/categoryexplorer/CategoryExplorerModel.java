package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.apache.log4j.lf5.LogRecord;

public class CategoryExplorerModel extends DefaultTreeModel {
    protected ActionEvent _event = new ActionEvent(this, 1001, "Nodes Selection changed");
    protected ActionListener _listener = null;
    protected boolean _renderFatal = true;

    public CategoryExplorerModel(CategoryNode categoryNode) {
        super(categoryNode);
    }

    public void addLogRecord(LogRecord logRecord) {
        CategoryPath categoryPath = new CategoryPath(logRecord.getCategory());
        addCategory(categoryPath);
        CategoryNode categoryNode = getCategoryNode(categoryPath);
        categoryNode.addRecord();
        if (this._renderFatal && logRecord.isFatal()) {
            CategoryNode[] pathToRoot = getPathToRoot(categoryNode);
            int length = pathToRoot.length;
            for (int i = 1; i < length - 1; i++) {
                CategoryNode categoryNode2 = pathToRoot[i];
                categoryNode2.setHasFatalChildren(true);
                nodeChanged(categoryNode2);
            }
            categoryNode.setHasFatalRecords(true);
            nodeChanged(categoryNode);
        }
    }

    public CategoryNode getRootCategoryNode() {
        return (CategoryNode) getRoot();
    }

    public CategoryNode getCategoryNode(String str) {
        return getCategoryNode(new CategoryPath(str));
    }

    public CategoryNode getCategoryNode(CategoryPath categoryPath) {
        CategoryNode categoryNode;
        boolean z;
        CategoryNode categoryNode2 = (CategoryNode) getRoot();
        int i = 0;
        while (i < categoryPath.size()) {
            CategoryElement categoryElementAt = categoryPath.categoryElementAt(i);
            Enumeration children = categoryNode2.children();
            while (true) {
                if (children.hasMoreElements()) {
                    categoryNode = (CategoryNode) children.nextElement();
                    if (categoryNode.getTitle().toLowerCase().equals(categoryElementAt.getTitle().toLowerCase())) {
                        z = true;
                        break;
                    }
                } else {
                    categoryNode = categoryNode2;
                    z = false;
                    break;
                }
            }
            if (!z) {
                return null;
            }
            i++;
            categoryNode2 = categoryNode;
        }
        return categoryNode2;
    }

    public boolean isCategoryPathActive(CategoryPath categoryPath) {
        boolean z;
        DefaultMutableTreeNode defaultMutableTreeNode = (CategoryNode) getRoot();
        boolean z2 = false;
        for (int i = 0; i < categoryPath.size(); i++) {
            CategoryElement categoryElementAt = categoryPath.categoryElementAt(i);
            Enumeration children = defaultMutableTreeNode.children();
            while (true) {
                z = true;
                if (!children.hasMoreElements()) {
                    z2 = false;
                    z = false;
                    break;
                }
                DefaultMutableTreeNode defaultMutableTreeNode2 = (CategoryNode) children.nextElement();
                if (defaultMutableTreeNode2.getTitle().toLowerCase().equals(categoryElementAt.getTitle().toLowerCase())) {
                    if (defaultMutableTreeNode2.isSelected()) {
                        defaultMutableTreeNode = defaultMutableTreeNode2;
                        z2 = true;
                    } else {
                        defaultMutableTreeNode = defaultMutableTreeNode2;
                        z2 = false;
                    }
                }
            }
            if (!z2 || !z) {
                return false;
            }
        }
        return z2;
    }

    public CategoryNode addCategory(CategoryPath categoryPath) {
        CategoryNode categoryNode;
        boolean z;
        CategoryNode categoryNode2 = (CategoryNode) getRoot();
        for (int i = 0; i < categoryPath.size(); i++) {
            CategoryElement categoryElementAt = categoryPath.categoryElementAt(i);
            Enumeration children = categoryNode2.children();
            while (true) {
                if (children.hasMoreElements()) {
                    categoryNode = (CategoryNode) children.nextElement();
                    if (categoryNode.getTitle().toLowerCase().equals(categoryElementAt.getTitle().toLowerCase())) {
                        z = true;
                        break;
                    }
                } else {
                    categoryNode = categoryNode2;
                    z = false;
                    break;
                }
            }
            if (!z) {
                categoryNode2 = new CategoryNode(categoryElementAt.getTitle());
                insertNodeInto(categoryNode2, categoryNode, categoryNode.getChildCount());
                refresh(categoryNode2);
            } else {
                categoryNode2 = categoryNode;
            }
        }
        return categoryNode2;
    }

    public void update(CategoryNode categoryNode, boolean z) {
        if (categoryNode.isSelected() != z) {
            if (z) {
                setParentSelection(categoryNode, true);
            } else {
                setDescendantSelection(categoryNode, false);
            }
        }
    }

    public void setDescendantSelection(CategoryNode categoryNode, boolean z) {
        Enumeration depthFirstEnumeration = categoryNode.depthFirstEnumeration();
        while (depthFirstEnumeration.hasMoreElements()) {
            CategoryNode categoryNode2 = (CategoryNode) depthFirstEnumeration.nextElement();
            if (categoryNode2.isSelected() != z) {
                categoryNode2.setSelected(z);
                nodeChanged(categoryNode2);
            }
        }
        notifyActionListeners();
    }

    public void setParentSelection(CategoryNode categoryNode, boolean z) {
        CategoryNode[] pathToRoot = getPathToRoot(categoryNode);
        int length = pathToRoot.length;
        for (int i = 1; i < length; i++) {
            CategoryNode categoryNode2 = pathToRoot[i];
            if (categoryNode2.isSelected() != z) {
                categoryNode2.setSelected(z);
                nodeChanged(categoryNode2);
            }
        }
        notifyActionListeners();
    }

    public synchronized void addActionListener(ActionListener actionListener) {
        this._listener = AWTEventMulticaster.add(this._listener, actionListener);
    }

    public synchronized void removeActionListener(ActionListener actionListener) {
        this._listener = AWTEventMulticaster.remove(this._listener, actionListener);
    }

    public void resetAllNodeCounts() {
        Enumeration depthFirstEnumeration = getRootCategoryNode().depthFirstEnumeration();
        while (depthFirstEnumeration.hasMoreElements()) {
            CategoryNode categoryNode = (CategoryNode) depthFirstEnumeration.nextElement();
            categoryNode.resetNumberOfContainedRecords();
            nodeChanged(categoryNode);
        }
    }

    public TreePath getTreePathToRoot(CategoryNode categoryNode) {
        if (categoryNode == null) {
            return null;
        }
        return new TreePath(getPathToRoot(categoryNode));
    }

    /* access modifiers changed from: protected */
    public void notifyActionListeners() {
        ActionListener actionListener = this._listener;
        if (actionListener != null) {
            actionListener.actionPerformed(this._event);
        }
    }

    /* access modifiers changed from: protected */
    public void refresh(final CategoryNode categoryNode) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CategoryExplorerModel.this.nodeChanged(categoryNode);
            }
        });
    }
}
