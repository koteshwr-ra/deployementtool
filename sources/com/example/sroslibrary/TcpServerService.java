package com.example.sroslibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.constant.NetConstant;
import com.ciot.base.util.ByteUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.server.IoSessionManager;
import com.ciot.server.SocketUtils;
import com.ciot.tcpclient.CharsetCodecFactory;
import com.ciot.tcpclient.CharsetDecoder;
import com.ciot.tcpclient.CharsetEncoder;
import com.example.sroslibrary.transpond.TranspondHandlerAdapter;
import com.example.sroslibrary.transpond.TranspondHandlerCallback;
import com.example.sroslibrary.transpond.TranspondRequestUtils;
import com.example.sroslibrary.transpond.TranspondSendMsgUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class TcpServerService extends Service {
    private static final String TAG = "NETWORK_TAG";
    private byte[] head = new byte[2];
    private byte[] headProtocol = new byte[7];
    private byte[] lengths = new byte[4];
    private LogicRunnable mLogicRunnable;
    private SocketUtils mSocketUtils;
    TranspondHandlerCallback transpondHandlerCallback = new TranspondHandlerCallback();

    /* access modifiers changed from: private */
    public void encoderEncode(Object obj, ProtocolEncoderOutput protocolEncoderOutput) {
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        MyLogUtils.Logi("TcpServerService onCreate");
        CharsetCodecFactory charsetCodecFactory = new CharsetCodecFactory();
        charsetCodecFactory.setOnCharsetEncoderListener(new CharsetEncoder.OnCharsetEncoderListener() {
            public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) {
                TcpServerService.this.encoderEncode(obj, protocolEncoderOutput);
            }

            public void dispose(IoSession ioSession) {
                TcpServerService.this.encoderDispose();
            }
        });
        charsetCodecFactory.setOnCharsetDecoderListener(new CharsetDecoder.OnCharsetDecoderListener() {
            public final boolean doDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
                return TcpServerService.this.decoderDoDecode(ioBuffer, protocolDecoderOutput);
            }
        });
        TranspondHandlerAdapter transpondHandlerAdapter = new TranspondHandlerAdapter();
        transpondHandlerAdapter.setOnTranspondHandlerCallBack(this.transpondHandlerCallback);
        SocketUtils socketUtils = new SocketUtils();
        this.mSocketUtils = socketUtils;
        socketUtils.serviceStart(NetConstant.TCP_SERVER_PORT, charsetCodecFactory, transpondHandlerAdapter);
        this.mLogicRunnable = new LogicRunnable();
        ThreadUtils.getSinglePool().execute(this.mLogicRunnable);
    }

    private class LogicRunnable implements Runnable {
        private LogicRunnable() {
        }

        public void run() {
            TranspondSendMsgUtil.getInstance();
            IoSessionManager.getInstance().sendAll("");
            SystemClock.sleep(3000);
        }
    }

    /* access modifiers changed from: private */
    public void encoderDispose() {
        MyLogUtils.Loge("NETWORK_TAG", "TcpServerService CharsetEncoder:dispose");
    }

    /* access modifiers changed from: private */
    public boolean decoderDoDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
        while (true) {
            if (!ioBuffer.hasRemaining()) {
                break;
            }
            ioBuffer.get(this.head);
            byte[] bArr = this.head;
            if (bArr[0] == -85 && bArr[1] == -84) {
                ioBuffer.get(this.headProtocol);
                System.arraycopy(this.headProtocol, 3, this.lengths, 0, 4);
                int bytes2int = ByteUtils.bytes2int(this.lengths);
                byte[] bArr2 = new byte[bytes2int];
                int i = bytes2int + 7;
                if (i <= ioBuffer.limit()) {
                    ioBuffer.get(bArr2);
                } else {
                    ioBuffer.limit(i);
                    ioBuffer.get(bArr2);
                }
                byte[] bArr3 = new byte[(bytes2int + 9)];
                byte[] bArr4 = this.head;
                System.arraycopy(bArr4, 0, bArr3, 0, bArr4.length);
                byte[] bArr5 = this.headProtocol;
                System.arraycopy(bArr5, 0, bArr3, 3, bArr5.length);
                System.arraycopy(bArr2, 0, bArr3, 7, bytes2int);
                protocolDecoderOutput.write(TranspondRequestUtils.bytes2Bean(bArr3));
            } else {
                ioBuffer.clear();
            }
        }
        ioBuffer.clear();
        return true;
    }

    public void onDestroy() {
        SocketUtils socketUtils = this.mSocketUtils;
        if (socketUtils != null) {
            socketUtils.serviceStop();
        }
        super.onDestroy();
    }
}
