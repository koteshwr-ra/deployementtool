package mc.csst.com.selfchassis.utils;

import android.graphics.Matrix;

public class MatrixUtils {
    public static float getMatrixScaleX(float f, Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return (f * fArr[0]) + (fArr[2] * 1.0f);
    }

    public static float getMatrixScaleY(float f, Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return (f * fArr[4]) + (fArr[5] * 1.0f);
    }
}
