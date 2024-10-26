package com.alibaba.android.arouter.compiler.utils;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;
import org.apache.commons.lang3.StringUtils;

public class Logger {
    private Messager msg;

    public Logger(Messager messager) {
        this.msg = messager;
    }

    public void info(CharSequence charSequence) {
        if (StringUtils.isNotEmpty(charSequence)) {
            Messager messager = this.msg;
            Diagnostic.Kind kind = Diagnostic.Kind.NOTE;
            messager.printMessage(kind, "ARouter::Compiler " + charSequence);
        }
    }

    public void error(CharSequence charSequence) {
        if (StringUtils.isNotEmpty(charSequence)) {
            Messager messager = this.msg;
            Diagnostic.Kind kind = Diagnostic.Kind.ERROR;
            messager.printMessage(kind, "ARouter::Compiler An exception is encountered, [" + charSequence + "]");
        }
    }

    public void error(Throwable th) {
        if (th != null) {
            Messager messager = this.msg;
            Diagnostic.Kind kind = Diagnostic.Kind.ERROR;
            messager.printMessage(kind, "ARouter::Compiler An exception is encountered, [" + th.getMessage() + "]\n" + formatStackTrace(th.getStackTrace()));
        }
    }

    public void warning(CharSequence charSequence) {
        if (StringUtils.isNotEmpty(charSequence)) {
            Messager messager = this.msg;
            Diagnostic.Kind kind = Diagnostic.Kind.WARNING;
            messager.printMessage(kind, "ARouter::Compiler " + charSequence);
        }
    }

    private String formatStackTrace(StackTraceElement[] stackTraceElementArr) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            sb.append("    at ");
            sb.append(stackTraceElement.toString());
            sb.append(StringUtils.LF);
        }
        return sb.toString();
    }
}
