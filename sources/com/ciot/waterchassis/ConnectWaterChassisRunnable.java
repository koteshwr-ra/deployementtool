package com.ciot.waterchassis;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.ciot.tcpclient.CharsetCodecFactory;
import com.ciot.tcpclient.CharsetDecoder;
import com.ciot.tcpclient.CharsetEncoder;
import com.ciot.tcpclient.ClientIoHandlerAdapter;
import com.ciot.tcpclient.ClientSocketUtil;
import com.ciot.waterchassis.listener.OnConnectListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ConnectWaterChassisRunnable implements Runnable {
    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private static final String TAG = ConnectWaterChassisRunnable.class.getSimpleName();
    private List<Byte> arrayList = new ArrayList();
    private volatile boolean connectWaterChassis = true;
    private ClientSocketUtil mClientSocketUtil;
    private OnConnectListener mOnConnectListener;

    ConnectWaterChassisRunnable(ClientIoHandlerAdapter.OnMessageReceivedCallBack onMessageReceivedCallBack) {
        CharsetCodecFactory charsetCodecFactory = new CharsetCodecFactory();
        charsetCodecFactory.setOnCharsetEncoderListener(new CharsetEncoder.OnCharsetEncoderListener() {
            public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
                ConnectWaterChassisRunnable.this.encoderEncode(obj, protocolEncoderOutput);
            }

            public void dispose(IoSession ioSession) {
                ConnectWaterChassisRunnable.this.encoderDispose();
            }
        });
        charsetCodecFactory.setOnCharsetDecoderListener(new CharsetDecoder.OnCharsetDecoderListener() {
            public boolean doDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
                return ConnectWaterChassisRunnable.this.decoderDoDecode(ioBuffer, protocolDecoderOutput);
            }
        });
        ClientIoHandlerAdapter clientIoHandlerAdapter = new ClientIoHandlerAdapter();
        clientIoHandlerAdapter.setOnMessageReceivedCallBack(onMessageReceivedCallBack);
        try {
            this.mClientSocketUtil = new ClientSocketUtil("192.168.10.10", 31001, charsetCodecFactory, clientIoHandlerAdapter, 0);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("NAVIGATION_TAG", "ClientSocketUtil init failed");
        }
    }

    public void run() {
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        while (this.connectWaterChassis) {
            ClientSocketUtil clientSocketUtil = this.mClientSocketUtil;
            if (clientSocketUtil != null) {
                z = clientSocketUtil.connect();
            }
            if (z) {
                if (!z2) {
                    WaterChassisHelper.getInstance().sendGetRobotInfo();
                    z2 = true;
                }
                WaterChassisHelper.getInstance().sendGetRobotStatus();
                WaterChassisHelper.getInstance().sendGetMaxSpeedRatio();
                WaterChassisHelper.getInstance().sendGetRobotCurrentMap();
                SystemClock.sleep(800);
                if (i > 0) {
                    i = 0;
                }
            } else {
                i++;
                SystemClock.sleep((long) Math.min(i * 500, 5000));
            }
            OnConnectListener onConnectListener = this.mOnConnectListener;
            if (onConnectListener != null) {
                onConnectListener.onConnect(z);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean decoderDoDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
        while (true) {
            if (!ioBuffer.hasRemaining()) {
                return false;
            }
            byte b = ioBuffer.get();
            if (b != 10) {
                this.arrayList.add(Byte.valueOf(b));
            } else if (this.arrayList.size() != 0) {
                byte[] bArr = new byte[this.arrayList.size()];
                for (int i = 0; i < this.arrayList.size(); i++) {
                    bArr[i] = this.arrayList.get(i).byteValue();
                }
                String trim = new String(bArr, CHARSET_UTF8).trim();
                if (!TextUtils.isEmpty(trim)) {
                    protocolDecoderOutput.write(trim);
                    this.arrayList.clear();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void encoderEncode(Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        IoBuffer autoExpand = IoBuffer.allocate(100).setAutoExpand(true);
        autoExpand.putString(obj.toString(), CHARSET_UTF8.newEncoder());
        autoExpand.flip();
        protocolEncoderOutput.write(autoExpand);
    }

    /* access modifiers changed from: private */
    public void encoderDispose() {
        Log.e(TAG, "CharsetEncoder:dispose");
    }

    /* access modifiers changed from: package-private */
    public void disConnect() {
        this.connectWaterChassis = false;
        this.mClientSocketUtil.serviceStop();
    }

    public void setOnConnectListener(OnConnectListener onConnectListener) {
        this.mOnConnectListener = onConnectListener;
    }
}
