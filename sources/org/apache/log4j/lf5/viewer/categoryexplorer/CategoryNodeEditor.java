package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.tencent.smtt.sdk.TbsListener;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class CategoryNodeEditor extends CategoryAbstractCellEditor {
    protected CategoryExplorerModel _categoryModel;
    protected JCheckBox _checkBox;
    protected CategoryNode _lastEditedNode;
    protected CategoryNodeEditorRenderer _renderer;
    protected JTree _tree;

    public CategoryNodeEditor(CategoryExplorerModel categoryExplorerModel) {
        CategoryNodeEditorRenderer categoryNodeEditorRenderer = new CategoryNodeEditorRenderer();
        this._renderer = categoryNodeEditorRenderer;
        JCheckBox checkBox = categoryNodeEditorRenderer.getCheckBox();
        this._checkBox = checkBox;
        this._categoryModel = categoryExplorerModel;
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this._categoryModel.update(CategoryNodeEditor.this._lastEditedNode, CategoryNodeEditor.this._checkBox.isSelected());
                CategoryNodeEditor.this.stopCellEditing();
            }
        });
        this._renderer.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                if ((mouseEvent.getModifiers() & 4) != 0) {
                    CategoryNodeEditor categoryNodeEditor = CategoryNodeEditor.this;
                    categoryNodeEditor.showPopup(categoryNodeEditor._lastEditedNode, mouseEvent.getX(), mouseEvent.getY());
                }
                CategoryNodeEditor.this.stopCellEditing();
            }
        });
    }

    public Component getTreeCellEditorComponent(JTree jTree, Object obj, boolean z, boolean z2, boolean z3, int i) {
        this._lastEditedNode = (CategoryNode) obj;
        this._tree = jTree;
        return this._renderer.getTreeCellRendererComponent(jTree, obj, z, z2, z3, i, true);
    }

    public Object getCellEditorValue() {
        return this._lastEditedNode.getUserObject();
    }

    /* access modifiers changed from: protected */
    public JMenuItem createPropertiesMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Properties");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this.showPropertiesDialog(categoryNode);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void showPropertiesDialog(CategoryNode categoryNode) {
        JTree jTree = this._tree;
        Object displayedProperties = getDisplayedProperties(categoryNode);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Category Properties: ");
        stringBuffer.append(categoryNode.getTitle());
        JOptionPane.showMessageDialog(jTree, displayedProperties, stringBuffer.toString(), -1);
    }

    /* access modifiers changed from: protected */
    public Object getDisplayedProperties(CategoryNode categoryNode) {
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Category: ");
        stringBuffer.append(categoryNode.getTitle());
        arrayList.add(stringBuffer.toString());
        if (categoryNode.hasFatalRecords()) {
            arrayList.add("Contains at least one fatal LogRecord.");
        }
        if (categoryNode.hasFatalChildren()) {
            arrayList.add("Contains descendants with a fatal LogRecord.");
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("LogRecords in this category alone: ");
        stringBuffer2.append(categoryNode.getNumberOfContainedRecords());
        arrayList.add(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("LogRecords in descendant categories: ");
        stringBuffer3.append(categoryNode.getNumberOfRecordsFromChildren());
        arrayList.add(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("LogRecords in this category including descendants: ");
        stringBuffer4.append(categoryNode.getTotalNumberOfRecords());
        arrayList.add(stringBuffer4.toString());
        return arrayList.toArray();
    }

    /* access modifiers changed from: protected */
    public void showPopup(CategoryNode categoryNode, int i, int i2) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.setSize(150, TbsListener.ErrorCode.INFO_CODE_BASE);
        if (categoryNode.getParent() == null) {
            jPopupMenu.add(createRemoveMenuItem());
            jPopupMenu.addSeparator();
        }
        jPopupMenu.add(createSelectDescendantsMenuItem(categoryNode));
        jPopupMenu.add(createUnselectDescendantsMenuItem(categoryNode));
        jPopupMenu.addSeparator();
        jPopupMenu.add(createExpandMenuItem(categoryNode));
        jPopupMenu.add(createCollapseMenuItem(categoryNode));
        jPopupMenu.addSeparator();
        jPopupMenu.add(createPropertiesMenuItem(categoryNode));
        jPopupMenu.show(this._renderer, i, i2);
    }

    /* access modifiers changed from: protected */
    public JMenuItem createSelectDescendantsMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Select All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this._categoryModel.setDescendantSelection(categoryNode, true);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createUnselectDescendantsMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Deselect All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this._categoryModel.setDescendantSelection(categoryNode, false);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createExpandMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Expand All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this.expandDescendants(categoryNode);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createCollapseMenuItem(final CategoryNode categoryNode) {
        JMenuItem jMenuItem = new JMenuItem("Collapse All Descendant Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CategoryNodeEditor.this.collapseDescendants(categoryNode);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public JMenuItem createRemoveMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Remove All Empty Categories");
        jMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                do {
                } while (CategoryNodeEditor.this.removeUnusedNodes() > 0);
            }
        });
        return jMenuItem;
    }

    /* access modifiers changed from: protected */
    public void expandDescendants(CategoryNode categoryNode) {
        Enumeration depthFirstEnumeration = categoryNode.depthFirstEnumeration();
        while (depthFirstEnumeration.hasMoreElements()) {
            expand((CategoryNode) depthFirstEnumeration.nextElement());
        }
    }

    /* access modifiers changed from: protected */
    public void collapseDescendants(CategoryNode categoryNode) {
        Enumeration depthFirstEnumeration = categoryNode.depthFirstEnumeration();
        while (depthFirstEnumeration.hasMoreElements()) {
            collapse((CategoryNode) depthFirstEnumeration.nextElement());
        }
    }

    /* access modifiers changed from: protected */
    public int removeUnusedNodes() {
        Enumeration depthFirstEnumeration = this._categoryModel.getRootCategoryNode().depthFirstEnumeration();
        int i = 0;
        while (depthFirstEnumeration.hasMoreElements()) {
            CategoryNode categoryNode = (CategoryNode) depthFirstEnumeration.nextElement();
            if (categoryNode.isLeaf() && categoryNode.getNumberOfContainedRecords() == 0 && categoryNode.getParent() != null) {
                this._categoryModel.removeNodeFromParent(categoryNode);
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void expand(CategoryNode categoryNode) {
        this._tree.expandPath(getTreePath(categoryNode));
    }

    /* access modifiers changed from: protected */
    public TreePath getTreePath(CategoryNode categoryNode) {
        return new TreePath(categoryNode.getPath());
    }

    /* access modifiers changed from: protected */
    public void collapse(CategoryNode categoryNode) {
        this._tree.collapsePath(getTreePath(categoryNode));
    }
}
