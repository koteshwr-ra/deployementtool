package mc.csst.com.selfchassislibrary.websocket;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.ciot.base.util.MyLogUtils;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketClientManager {
    private static int MAX_RETRY_CONNECT_COUNT = Integer.MAX_VALUE;
    private static final int MILLIS = 5000;
    private static final String TAG = "NAVIGATION_TAG";
    private static volatile WebSocketClientManager mInstance;
    private OkHttpClient mClient;
    /* access modifiers changed from: private */
    public int mConnectCount = 0;
    /* access modifiers changed from: private */
    public IReceiveMessage mIReceiveMessage;
    /* access modifiers changed from: private */
    public boolean mIsConnect = false;
    private boolean mIsNeedReConnect = true;
    private Request mRequest;
    /* access modifiers changed from: private */
    public WebSocket mWebSocket;

    private WebSocketClientManager() {
    }

    public static WebSocketClientManager getInstance() {
        if (mInstance == null) {
            synchronized (WebSocketClientManager.class) {
                if (mInstance == null) {
                    mInstance = new WebSocketClientManager();
                }
            }
        }
        return mInstance;
    }

    public void init(String str, IReceiveMessage iReceiveMessage) {
        this.mClient = new OkHttpClient.Builder().writeTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS).build();
        this.mRequest = new Request.Builder().url(str).build();
        this.mIReceiveMessage = iReceiveMessage;
        connect();
    }

    public void connect() {
        this.mIsNeedReConnect = true;
        MyLogUtils.Logd("NAVIGATION_TAG", "web socket connect run");
        if (this.mIsConnect) {
            MyLogUtils.Logd("NAVIGATION_TAG", "web socket connected");
        } else {
            this.mClient.newWebSocket(this.mRequest, new MyWebSocketListener());
        }
    }

    public void close() {
        this.mIsNeedReConnect = false;
        MyLogUtils.Logd("NAVIGATION_TAG", "web socket close");
        if (this.mIsConnect) {
            this.mConnectCount = 0;
            this.mIsConnect = false;
            this.mWebSocket.cancel();
            this.mWebSocket.close(1000, "客服端主动关闭");
        }
    }

    public void reconnect() {
        if (this.mIsNeedReConnect) {
            MyLogUtils.Logd("NAVIGATION_TAG", "web socket reconnect");
            if (this.mConnectCount <= MAX_RETRY_CONNECT_COUNT) {
                SystemClock.sleep(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
                connect();
                this.mConnectCount++;
            }
        }
    }

    public boolean sendMessage(String str) {
        if (!this.mIsConnect) {
            MyLogUtils.Logd("NAVIGATION_TAG", "send msg failed: " + str);
            return false;
        }
        MyLogUtils.Logd("NAVIGATION_TAG", "send msg : " + str);
        return this.mWebSocket.send(str);
    }

    public boolean sendMessage(ByteString byteString) {
        if (!this.mIsConnect) {
            return false;
        }
        return this.mWebSocket.send(byteString);
    }

    public boolean isConnect() {
        return this.mWebSocket != null && this.mIsConnect;
    }

    private class MyWebSocketListener extends WebSocketListener {
        private MyWebSocketListener() {
        }

        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            WebSocket unused = WebSocketClientManager.this.mWebSocket = webSocket;
            MyLogUtils.Logd("NAVIGATION_TAG", "Connect:" + response.code());
            boolean unused2 = WebSocketClientManager.this.mIsConnect = response.code() == 101;
            if (WebSocketClientManager.this.mIsConnect) {
                MyLogUtils.Logd("NAVIGATION_TAG", "WebSocket Connect Success");
                if (WebSocketClientManager.this.mIReceiveMessage != null) {
                    WebSocketClientManager.this.mIReceiveMessage.onConnectSuccess();
                    return;
                }
                return;
            }
            WebSocketClientManager.this.reconnect();
        }

        public void onMessage(WebSocket webSocket, String str) {
            super.onMessage(webSocket, str);
            MyLogUtils.Logv("NAVIGATION_TAG", "WebSocket onMessage(text):" + str);
            if (!TextUtils.isEmpty(str)) {
                WebSocketClientManager.this.mIReceiveMessage.onMessage(str);
            }
        }

        public void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
            byte[] byteArray = byteString.toByteArray();
            MyLogUtils.Logv("NAVIGATION_TAG", "WebSocket onMessage(bytes):" + byteArray);
            if (byteArray.length != 0) {
                WebSocketClientManager.this.mIReceiveMessage.onMessage(byteArray);
            }
        }

        public void onClosing(WebSocket webSocket, int i, String str) {
            super.onClosing(webSocket, i, str);
            WebSocket unused = WebSocketClientManager.this.mWebSocket = null;
            boolean unused2 = WebSocketClientManager.this.mIsConnect = false;
            int unused3 = WebSocketClientManager.this.mConnectCount = 0;
            MyLogUtils.Logd("NAVIGATION_TAG", "closing：" + i);
            if (WebSocketClientManager.this.mIReceiveMessage != null) {
                WebSocketClientManager.this.mIReceiveMessage.onClosing();
            }
        }

        public void onClosed(WebSocket webSocket, int i, String str) {
            super.onClosed(webSocket, i, str);
            WebSocket unused = WebSocketClientManager.this.mWebSocket = null;
            boolean unused2 = WebSocketClientManager.this.mIsConnect = false;
            int unused3 = WebSocketClientManager.this.mConnectCount = 0;
            MyLogUtils.Logd("NAVIGATION_TAG", "closed：" + i);
            if (WebSocketClientManager.this.mIReceiveMessage != null) {
                WebSocketClientManager.this.mIReceiveMessage.onClosed();
            }
        }

        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            super.onFailure(webSocket, th, response);
            if (response != null) {
                MyLogUtils.Loge("NAVIGATION_TAG", "connect failed：" + response.message());
            }
            MyLogUtils.Logd("NAVIGATION_TAG", "connect failed throwable：" + th.getMessage());
            MyLogUtils.Logd("NAVIGATION_TAG", "connect failed throwable：" + Log.getStackTraceString(th));
            boolean unused = WebSocketClientManager.this.mIsConnect = false;
            int unused2 = WebSocketClientManager.this.mConnectCount = 0;
            WebSocketClientManager.this.mIReceiveMessage.onClosing();
            WebSocketClientManager.this.mIReceiveMessage.onClosed();
            if (WebSocketClientManager.this.mIReceiveMessage != null) {
                WebSocketClientManager.this.mIReceiveMessage.onConnectFailed(th);
            }
            WebSocketClientManager.this.reconnect();
        }
    }

    private WebSocketListener createListener() {
        return new MyWebSocketListener();
    }
}
