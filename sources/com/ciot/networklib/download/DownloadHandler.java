package com.ciot.networklib.download;

import android.os.Handler;
import android.os.Message;

public class DownloadHandler extends Handler {
    public static final int DOWNLOAD_FAIL = 3;
    public static final int DOWNLOAD_PROGRESS = 2;
    public static final int START_DOWNLOAD = 1;
    private DownloadFileListener mDownloadListener;

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i != 1) {
            if (i == 2 && this.mDownloadListener != null) {
                this.mDownloadListener.onProgress(((Float) message.obj).floatValue());
            }
        } else if (this.mDownloadListener != null) {
            this.mDownloadListener.onStartDownload(((Long) message.obj).longValue());
        }
    }

    public void setDownloadListener(DownloadFileListener downloadFileListener) {
        this.mDownloadListener = downloadFileListener;
    }
}
