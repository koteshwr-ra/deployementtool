package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;

public class CategoryExplorerTree extends JTree {
    protected CategoryExplorerModel _model;
    protected boolean _rootAlreadyExpanded = false;

    public CategoryExplorerTree(CategoryExplorerModel categoryExplorerModel) {
        super(categoryExplorerModel);
        this._model = categoryExplorerModel;
        init();
    }

    public CategoryExplorerTree() {
        CategoryExplorerModel categoryExplorerModel = new CategoryExplorerModel(new CategoryNode("Categories"));
        this._model = categoryExplorerModel;
        setModel(categoryExplorerModel);
        init();
    }

    public CategoryExplorerModel getExplorerModel() {
        return this._model;
    }

    public String getToolTipText(MouseEvent mouseEvent) {
        try {
            return CategoryExplorerTree.super.getToolTipText(mouseEvent);
        } catch (Exception unused) {
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        putClientProperty("JTree.lineStyle", "Angled");
        CategoryNodeRenderer categoryNodeRenderer = new CategoryNodeRenderer();
        setEditable(true);
        setCellRenderer(categoryNodeRenderer);
        setCellEditor(new CategoryImmediateEditor(this, new CategoryNodeRenderer(), new CategoryNodeEditor(this._model)));
        setShowsRootHandles(true);
        setToolTipText("");
        ensureRootExpansion();
    }

    /* access modifiers changed from: protected */
    public void expandRootNode() {
        if (!this._rootAlreadyExpanded) {
            this._rootAlreadyExpanded = true;
            expandPath(new TreePath(this._model.getRootCategoryNode().getPath()));
        }
    }

    /* access modifiers changed from: protected */
    public void ensureRootExpansion() {
        this._model.addTreeModelListener(new TreeModelAdapter() {
            public void treeNodesInserted(TreeModelEvent treeModelEvent) {
                CategoryExplorerTree.this.expandRootNode();
            }
        });
    }
}
