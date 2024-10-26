package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.alibaba.android.arouter.utils.Consts;
import java.util.LinkedList;
import java.util.StringTokenizer;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

public class CategoryPath {
    protected LinkedList _categoryElements = new LinkedList();

    public CategoryPath() {
    }

    public CategoryPath(String str) {
        str = str == null ? "Debug" : str;
        str.replace('/', ClassUtils.PACKAGE_SEPARATOR_CHAR);
        StringTokenizer stringTokenizer = new StringTokenizer(str.replace('\\', ClassUtils.PACKAGE_SEPARATOR_CHAR), Consts.DOT);
        while (stringTokenizer.hasMoreTokens()) {
            addCategoryElement(new CategoryElement(stringTokenizer.nextToken()));
        }
    }

    public int size() {
        return this._categoryElements.size();
    }

    public boolean isEmpty() {
        return this._categoryElements.size() == 0;
    }

    public void removeAllCategoryElements() {
        this._categoryElements.clear();
    }

    public void addCategoryElement(CategoryElement categoryElement) {
        this._categoryElements.addLast(categoryElement);
    }

    public CategoryElement categoryElementAt(int i) {
        return (CategoryElement) this._categoryElements.get(i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(StringUtils.LF);
        stringBuffer.append("===========================\n");
        stringBuffer.append("CategoryPath:                   \n");
        stringBuffer.append("---------------------------\n");
        stringBuffer.append("\nCategoryPath:\n\t");
        if (size() > 0) {
            for (int i = 0; i < size(); i++) {
                stringBuffer.append(categoryElementAt(i).toString());
                stringBuffer.append("\n\t");
            }
        } else {
            stringBuffer.append("<<NONE>>");
        }
        stringBuffer.append(StringUtils.LF);
        stringBuffer.append("===========================\n");
        return stringBuffer.toString();
    }
}
