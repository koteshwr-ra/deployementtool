package com.tencent.mars.logger;

public interface Printer {
    void addAdapter(LogAdapter logAdapter);

    void clearLogAdapters();

    void d(String str, String str2, Object... objArr);

    void e(String str, String str2, Object... objArr);

    void e(String str, Throwable th, String str2, Object... objArr);

    void i(String str, String str2, Object... objArr);

    void json(String str, String str2);

    void log(int i, String str, String str2, Throwable th);

    void v(String str, String str2, Object... objArr);

    void w(String str, String str2, Object... objArr);

    void wtf(String str, String str2, Object... objArr);

    void xml(String str, String str2);
}
