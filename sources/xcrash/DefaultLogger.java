package xcrash;

import android.util.Log;

class DefaultLogger implements ILogger {
    DefaultLogger() {
    }

    public void v(String str, String str2) {
        Log.v(str, str2);
    }

    public void v(String str, String str2, Throwable th) {
        Log.v(str, str2, th);
    }

    public void d(String str, String str2) {
        Log.d(str, str2);
    }

    public void d(String str, String str2, Throwable th) {
        Log.d(str, str2, th);
    }

    public void i(String str, String str2) {
        Log.i(str, str2);
    }

    public void i(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    public void w(String str, String str2) {
        Log.w(str, str2);
    }

    public void w(String str, String str2, Throwable th) {
        Log.w(str, str2, th);
    }

    public void e(String str, String str2) {
        Log.e(str, str2);
    }

    public void e(String str, String str2, Throwable th) {
        Log.e(str, str2, th);
    }
}
