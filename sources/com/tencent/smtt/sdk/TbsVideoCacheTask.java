package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

public class TbsVideoCacheTask {
    public static final String KEY_VIDEO_CACHE_PARAM_FILENAME = "filename";
    public static final String KEY_VIDEO_CACHE_PARAM_FOLDERPATH = "folderPath";
    public static final String KEY_VIDEO_CACHE_PARAM_HEADER = "header";
    public static final String KEY_VIDEO_CACHE_PARAM_URL = "url";
    Context a = null;
    TbsVideoCacheListener b = null;
    private boolean c = false;
    private q d = null;
    private String e;
    private String f;
    private Object g = null;

    public TbsVideoCacheTask(Context context, Bundle bundle, TbsVideoCacheListener tbsVideoCacheListener) {
        this.a = context;
        this.b = tbsVideoCacheListener;
        if (bundle != null) {
            this.e = bundle.getString("taskId");
            this.f = bundle.getString(KEY_VIDEO_CACHE_PARAM_URL);
        }
        a(bundle);
    }

    private void a(Bundle bundle) {
        TbsVideoCacheListener tbsVideoCacheListener;
        String str;
        DexLoader dexLoader;
        if (this.d == null) {
            f.a(true).a(this.a, false, false);
            u a2 = f.a(true).a();
            if (a2 != null) {
                dexLoader = a2.b();
            } else {
                this.b.onVideoDownloadError(this, -1, "init engine error!", (Bundle) null);
                dexLoader = null;
            }
            if (dexLoader != null) {
                this.d = new q(dexLoader);
            } else {
                this.b.onVideoDownloadError(this, -1, "Java dexloader invalid!", (Bundle) null);
            }
        }
        q qVar = this.d;
        if (qVar != null) {
            Object a3 = qVar.a(this.a, this, bundle);
            this.g = a3;
            if (a3 == null) {
                tbsVideoCacheListener = this.b;
                str = "init task error!";
            } else {
                return;
            }
        } else {
            tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                str = "init error!";
            } else {
                return;
            }
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, str, (Bundle) null);
    }

    public long getContentLength() {
        q qVar = this.d;
        if (qVar != null && this.g != null) {
            return qVar.d();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getContentLength failed, init uncompleted!", (Bundle) null);
        return 0;
    }

    public int getDownloadedSize() {
        q qVar = this.d;
        if (qVar != null && this.g != null) {
            return qVar.e();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getDownloadedSize failed, init uncompleted!", (Bundle) null);
        return 0;
    }

    public int getProgress() {
        q qVar = this.d;
        if (qVar != null && this.g != null) {
            return qVar.f();
        }
        TbsVideoCacheListener tbsVideoCacheListener = this.b;
        if (tbsVideoCacheListener == null) {
            return 0;
        }
        tbsVideoCacheListener.onVideoDownloadError(this, -1, "getProgress failed, init uncompleted!", (Bundle) null);
        return 0;
    }

    public String getTaskID() {
        return this.e;
    }

    public String getTaskUrl() {
        return this.f;
    }

    public void pauseTask() {
        q qVar = this.d;
        if (qVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "pauseTask failed, init uncompleted!", (Bundle) null);
                return;
            }
            return;
        }
        qVar.a();
    }

    public void removeTask(boolean z) {
        q qVar = this.d;
        if (qVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "removeTask failed, init uncompleted!", (Bundle) null);
                return;
            }
            return;
        }
        qVar.a(z);
    }

    public void resumeTask() {
        q qVar = this.d;
        if (qVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "resumeTask failed, init uncompleted!", (Bundle) null);
                return;
            }
            return;
        }
        qVar.b();
    }

    public void stopTask() {
        q qVar = this.d;
        if (qVar == null || this.g == null) {
            TbsVideoCacheListener tbsVideoCacheListener = this.b;
            if (tbsVideoCacheListener != null) {
                tbsVideoCacheListener.onVideoDownloadError(this, -1, "stopTask failed, init uncompleted!", (Bundle) null);
                return;
            }
            return;
        }
        qVar.c();
    }
}
