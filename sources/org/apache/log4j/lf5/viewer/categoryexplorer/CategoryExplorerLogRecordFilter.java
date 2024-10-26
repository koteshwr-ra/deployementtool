package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.Enumeration;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;

public class CategoryExplorerLogRecordFilter implements LogRecordFilter {
    protected CategoryExplorerModel _model;

    public CategoryExplorerLogRecordFilter(CategoryExplorerModel categoryExplorerModel) {
        this._model = categoryExplorerModel;
    }

    public boolean passes(LogRecord logRecord) {
        return this._model.isCategoryPathActive(new CategoryPath(logRecord.getCategory()));
    }

    public void reset() {
        resetAllNodes();
    }

    /* access modifiers changed from: protected */
    public void resetAllNodes() {
        Enumeration depthFirstEnumeration = this._model.getRootCategoryNode().depthFirstEnumeration();
        while (depthFirstEnumeration.hasMoreElements()) {
            CategoryNode categoryNode = (CategoryNode) depthFirstEnumeration.nextElement();
            categoryNode.resetNumberOfContainedRecords();
            this._model.nodeChanged(categoryNode);
        }
    }
}
