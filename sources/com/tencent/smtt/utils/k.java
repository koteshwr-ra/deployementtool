package com.tencent.smtt.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.alibaba.android.arouter.utils.Consts;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;

public class k implements Runnable {
    public static String a = "TBSFileLock";
    private static Object f = new Object();
    private static Object g = new Object();
    private static HashMap<k, Object> h = null;
    private static Handler i = null;
    File b = null;
    RandomAccessFile c = null;
    FileLock d = null;
    long e = 0;

    public k(File file, String str) {
        this.b = new File(file, Consts.DOT + str + ".lock");
    }

    /* access modifiers changed from: package-private */
    public Handler a() {
        if (i == null) {
            synchronized (k.class) {
                if (i == null) {
                    HandlerThread handlerThread = new HandlerThread("QBFileLock.Thread");
                    handlerThread.start();
                    i = new Handler(handlerThread.getLooper());
                }
            }
        }
        return i;
    }

    public synchronized void a(boolean z) {
        String str = a;
        Log.d(str, ">>> release lock: " + this.b.getName());
        if (this.d != null) {
            try {
                this.d.release();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.d = null;
        }
        if (this.c != null) {
            try {
                this.c.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.c = null;
        }
        if (i != null && this.e > 0) {
            i.removeCallbacks(this);
        }
        if (z) {
            d();
        }
    }

    public synchronized void b() {
        FileChannel channel;
        try {
            this.c = new RandomAccessFile(this.b, "rw");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!(this.c == null || (channel = this.c.getChannel()) == null)) {
            if (this.e > 0) {
                a().postDelayed(this, this.e);
            }
            FileLock fileLock = null;
            long currentTimeMillis = System.currentTimeMillis();
            while (true) {
                try {
                    fileLock = channel.lock();
                    if (fileLock != null) {
                        break;
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    Log.d(a, ">>> lock failed, sleep...");
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                if (Math.abs(System.currentTimeMillis() - currentTimeMillis) >= 1000) {
                    Log.d(a, ">>> lock timeout, quit...");
                    break;
                }
            }
            this.d = fileLock;
            String str = a;
            Log.d(str, ">>> lock [" + this.b.getName() + "] cost: " + (System.currentTimeMillis() - currentTimeMillis));
        }
        if (this.d != null) {
            c();
        }
        return;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        synchronized (g) {
            if (h == null) {
                h = new HashMap<>();
            }
            h.put(this, f);
        }
    }

    /* access modifiers changed from: package-private */
    public void d() {
        synchronized (g) {
            if (h != null) {
                h.remove(this);
            }
        }
    }

    public void e() {
        a(true);
    }

    public void run() {
        Log.d(a, ">>> releaseLock on TimeOut");
        e();
    }
}
