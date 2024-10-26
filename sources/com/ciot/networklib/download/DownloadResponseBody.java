package com.ciot.networklib.download;

import android.os.Message;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class DownloadResponseBody extends ResponseBody {
    private BufferedSource bufferedSource;
    /* access modifiers changed from: private */
    public DownloadHandler mDownloadHandler;
    /* access modifiers changed from: private */
    public float mDownloadProgress = -1.0f;
    /* access modifiers changed from: private */
    public ResponseBody responseBody;

    public DownloadResponseBody(ResponseBody responseBody2, DownloadHandler downloadHandler) {
        this.responseBody = responseBody2;
        this.mDownloadHandler = downloadHandler;
        sendStartDownloadMsg(responseBody2.contentLength());
    }

    private void sendStartDownloadMsg(long j) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = Long.valueOf(j);
        this.mDownloadHandler.sendMessage(obtain);
    }

    public MediaType contentType() {
        return this.responseBody.contentType();
    }

    public long contentLength() {
        return this.responseBody.contentLength();
    }

    public BufferedSource source() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.responseBody.source()));
        }
        return this.bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0;

            public long read(Buffer buffer, long j) throws IOException {
                long read = super.read(buffer, j);
                int i = (read > -1 ? 1 : (read == -1 ? 0 : -1));
                long j2 = this.totalBytesRead + (i != 0 ? read : 0);
                this.totalBytesRead = j2;
                float round = ((float) Math.round((((float) j2) * 10000.0f) / ((float) DownloadResponseBody.this.responseBody.contentLength()))) / 100.0f;
                if (!(i == 0 || DownloadResponseBody.this.mDownloadProgress == round)) {
                    float unused = DownloadResponseBody.this.mDownloadProgress = round;
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = Float.valueOf(round);
                    DownloadResponseBody.this.mDownloadHandler.sendMessage(obtain);
                }
                return read;
            }
        };
    }
}
