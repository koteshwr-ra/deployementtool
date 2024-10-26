package mc.csst.com.selfchassislibrary.websocket;

public interface IReceiveMessage {
    void onClosed();

    void onClosing();

    void onConnectFailed(Throwable th);

    void onConnectSuccess();

    void onMessage(String str);

    void onMessage(byte[] bArr);
}
