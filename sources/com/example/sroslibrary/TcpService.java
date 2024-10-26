package com.example.sroslibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.ThreadUtils;
import com.ciot.base.util.AppSpUtil;
import com.ciot.base.util.ByteUtils;
import com.ciot.base.util.MyLogUtils;
import com.ciot.base.util.SystemUtils;
import com.ciot.tcpclient.CharsetCodecFactory;
import com.ciot.tcpclient.CharsetDecoder;
import com.ciot.tcpclient.CharsetEncoder;
import com.ciot.tcpclient.ClientSocketUtil;
import com.example.sroslibrary.TcpService;
import com.example.sroslibrary.bean.NetInitProcessBean;
import com.example.sroslibrary.bean.ProtocolBean;
import com.example.sroslibrary.livedata.NetInitProcesLiveData;
import com.example.sroslibrary.sros.SrosHandlerAdapter;
import com.example.sroslibrary.sros.SrosHandlerCallback;
import com.example.sroslibrary.sros.SrosRequestUtils;
import com.example.sroslibrary.sros.SrosSendMsgUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class TcpService extends Service implements LifecycleOwner {
    private static final String TAG = "NETWORK_TAG";
    volatile boolean connectSros = true;
    /* access modifiers changed from: private */
    public boolean mConnectedServer = false;
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private ConnectRunnable mRunnable;
    SrosHandlerCallback onSrosHandlerCallBack = new SrosHandlerCallback();

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Log.e("NETWORK_TAG", "TcpService onCreate");
        super.onCreate();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (SystemUtils.isAppForgroud(this)) {
            MyLogUtils.Logd("NETWORK_TAG", "TcpService onStartCommand ");
        } else {
            MyLogUtils.Logd("NETWORK_TAG", "TcpService onStartCommand in App Backgroud");
        }
        connectTcp();
        NetInitProcesLiveData.Companion.get().observe(this, new Observer<NetInitProcessBean>() {
            public void onChanged(NetInitProcessBean netInitProcessBean) {
                if (netInitProcessBean.isActivateTimeout()) {
                    TcpService.this.disconnectTcp();
                    TcpService.this.connectTcp();
                }
            }
        });
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
        return super.onStartCommand(intent, i, i2);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    class ConnectRunnable implements Runnable {
        byte[] head = new byte[4];
        byte[] headProtocol = new byte[47];
        byte[] lengths = new byte[4];
        private final ClientSocketUtil mClientSocketUtil;

        ConnectRunnable() {
            CharsetCodecFactory charsetCodecFactory = new CharsetCodecFactory();
            charsetCodecFactory.setOnCharsetEncoderListener(new CharsetEncoder.OnCharsetEncoderListener(TcpService.this) {
                public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) {
                    ConnectRunnable.this.encoderEncode(obj, protocolEncoderOutput);
                }

                public void dispose(IoSession ioSession) {
                    ConnectRunnable.this.encoderDispose();
                }
            });
            charsetCodecFactory.setOnCharsetDecoderListener(new CharsetDecoder.OnCharsetDecoderListener() {
                public final boolean doDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
                    return TcpService.ConnectRunnable.this.decoderDoDecode(ioBuffer, protocolDecoderOutput);
                }
            });
            SrosHandlerAdapter srosHandlerAdapter = new SrosHandlerAdapter();
            TcpService.this.onSrosHandlerCallBack.setConnectedServerListener(new SrosHandlerCallback.OnConnectedServerListener() {
                public final void onConnectedServer() {
                    TcpService.ConnectRunnable.this.onConnectedServer();
                }
            });
            srosHandlerAdapter.setOnSrosHandlerCallBack(TcpService.this.onSrosHandlerCallBack);
            this.mClientSocketUtil = new ClientSocketUtil(AppSpUtil.getInstance().getTcpIp(), 28888, charsetCodecFactory, srosHandlerAdapter, 1);
        }

        public void run() {
            while (TcpService.this.connectSros) {
                if (this.mClientSocketUtil.connect() && TcpService.this.mConnectedServer) {
                    SrosSendMsgUtil.getInstance().sendHeartBeat();
                }
                SystemClock.sleep(3000);
            }
        }

        /* access modifiers changed from: private */
        public boolean decoderDoDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
            while (true) {
                if (!ioBuffer.hasRemaining()) {
                    break;
                }
                ioBuffer.get(this.head);
                byte[] bArr = this.head;
                if (bArr[0] == -1 && bArr[1] == -1 && bArr[2] == -52 && bArr[3] == -52) {
                    ioBuffer.get(this.headProtocol);
                    System.arraycopy(this.headProtocol, 43, this.lengths, 0, 4);
                    int bytes2int = ByteUtils.bytes2int(this.lengths);
                    byte[] bArr2 = new byte[bytes2int];
                    int i = bytes2int + 51;
                    if (i <= ioBuffer.limit()) {
                        ioBuffer.get(bArr2);
                    } else {
                        ioBuffer.limit(i);
                        ioBuffer.get(bArr2);
                    }
                    byte[] bArr3 = new byte[i];
                    System.arraycopy(this.head, 0, bArr3, 0, 4);
                    byte[] bArr4 = this.headProtocol;
                    System.arraycopy(bArr4, 0, bArr3, 4, bArr4.length);
                    System.arraycopy(bArr2, 0, bArr3, 51, bytes2int);
                    if (bArr3[45] == 0 && bArr3[46] == 1) {
                        protocolDecoderOutput.write(bArr3);
                    } else {
                        protocolDecoderOutput.write(SrosRequestUtils.bytes2Bean(bArr3));
                    }
                } else {
                    ioBuffer.clear();
                }
            }
            ioBuffer.clear();
            return true;
        }

        /* access modifiers changed from: private */
        public void encoderEncode(Object obj, ProtocolEncoderOutput protocolEncoderOutput) {
            if (obj instanceof ProtocolBean) {
                IoBuffer autoExpand = IoBuffer.allocate(100).setAutoExpand(true);
                autoExpand.put(SrosRequestUtils.bean2Bytes((ProtocolBean) obj));
                autoExpand.flip();
                protocolEncoderOutput.write(autoExpand);
            }
        }

        /* access modifiers changed from: private */
        public void encoderDispose() {
            MyLogUtils.Loge("NETWORK_TAG", "CharsetEncoder:dispose");
        }

        /* access modifiers changed from: package-private */
        public void disConnect() {
            TcpService.this.connectSros = false;
            this.mClientSocketUtil.serviceStop();
        }

        /* access modifiers changed from: private */
        public void onConnectedServer() {
            boolean unused = TcpService.this.mConnectedServer = true;
        }
    }

    public void onDestroy() {
        Log.e("NETWORK_TAG", "TcpService onDestroy");
        NetInitProcesLiveData.Companion.get().removeObservers(this);
        disconnectTcp();
        this.mLifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void connectTcp() {
        MyLogUtils.Logd("NETWORK_TAG", "TcpService connectTcp");
        SrosHandlerCallback srosHandlerCallback = this.onSrosHandlerCallBack;
        if (srosHandlerCallback != null && srosHandlerCallback.getCurrentState() == 3) {
            this.onSrosHandlerCallBack.sendMsg2RetrofitManager();
        }
        this.mRunnable = new ConnectRunnable();
        ThreadUtils.getSinglePool().execute(this.mRunnable);
    }

    /* access modifiers changed from: private */
    public void disconnectTcp() {
        MyLogUtils.Logd("NETWORK_TAG", "TcpService disconnectTcp");
        this.connectSros = false;
        ConnectRunnable connectRunnable = this.mRunnable;
        if (connectRunnable != null) {
            connectRunnable.disConnect();
        }
    }
}
