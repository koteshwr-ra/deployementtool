package com.ciot.udpclient;

import com.ciot.tcpclient.CharsetCodecFactory;
import com.ciot.tcpclient.CharsetDecoder;
import com.ciot.tcpclient.CharsetEncoder;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

public class UdpClient {
    private byte[] lengths = new byte[64];
    private NioDatagramConnector mConnector;
    private IoSession mIoSession;

    /* access modifiers changed from: private */
    public void encoderDispose() {
    }

    public UdpClient(String str, int i, OnUdpListener onUdpListener) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i);
        NioDatagramConnector nioDatagramConnector = new NioDatagramConnector();
        this.mConnector = nioDatagramConnector;
        nioDatagramConnector.setHandler(new UdpClientIoHandler(onUdpListener));
        CharsetCodecFactory charsetCodecFactory = new CharsetCodecFactory();
        charsetCodecFactory.setOnCharsetEncoderListener(new CharsetEncoder.OnCharsetEncoderListener() {
            public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) {
                UdpClient.this.encoderEncode(obj, protocolEncoderOutput);
            }

            public void dispose(IoSession ioSession) {
                UdpClient.this.encoderDispose();
            }
        });
        charsetCodecFactory.setOnCharsetDecoderListener(new CharsetDecoder.OnCharsetDecoderListener() {
            public boolean doDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
                return UdpClient.this.decoderDoDecode(ioBuffer, protocolDecoderOutput);
            }
        });
        this.mConnector.getFilterChain().addLast("protocol", new ProtocolCodecFilter(charsetCodecFactory));
        try {
            ConnectFuture connect = this.mConnector.connect((SocketAddress) inetSocketAddress);
            connect.awaitUninterruptibly();
            this.mIoSession = connect.getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public boolean decoderDoDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) {
        int i = 0;
        while (ioBuffer.hasRemaining()) {
            this.lengths[i] = (byte) (ioBuffer.get() & 255);
            i++;
        }
        protocolDecoderOutput.write(this.lengths);
        return true;
    }

    /* access modifiers changed from: private */
    public void encoderEncode(Object obj, ProtocolEncoderOutput protocolEncoderOutput) {
        if (obj instanceof byte[]) {
            IoBuffer autoExpand = IoBuffer.allocate(100).setAutoExpand(true);
            autoExpand.put((byte[]) obj);
            autoExpand.flip();
            protocolEncoderOutput.write(autoExpand);
        }
    }

    public IoSession getIoSession() {
        return this.mIoSession;
    }

    public void disConnect() {
        try {
            if (this.mIoSession != null && this.mIoSession.isConnected()) {
                this.mIoSession.close(true);
            }
            if (this.mConnector != null && !this.mConnector.isDisposed()) {
                this.mConnector.dispose();
            }
            this.mConnector = null;
            this.mIoSession = null;
        } catch (Exception unused) {
            this.mConnector = null;
            this.mIoSession = null;
        }
    }
}
