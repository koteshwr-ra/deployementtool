package com.getkeepsafe.relinker;

import com.alibaba.android.arouter.utils.Consts;
import java.util.Arrays;

public class MissingLibraryException extends RuntimeException {
    public MissingLibraryException(String str, String[] strArr, String[] strArr2) {
        super("Could not find '" + str + "'. Looked for: " + Arrays.toString(strArr) + ", but only found: " + Arrays.toString(strArr2) + Consts.DOT);
    }
}
