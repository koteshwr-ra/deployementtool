package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ciot.base.storage.MySpUtils;
import com.ciot.sentrymove.R;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;

public class LabelCameraView extends FrameLayout implements Runnable {
    private static final String TAG = "LabelCameraView";
    private TextView cameraState;
    private Handler handler;
    private ImageView ivCamera;
    private ImageView ivClose;
    /* access modifiers changed from: private */
    public OnCloseListener onCloseListener;
    private final String url;

    public interface OnCloseListener {
        void onClose();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener2) {
        this.onCloseListener = onCloseListener2;
    }

    public LabelCameraView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LabelCameraView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LabelCameraView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.url = "http://%s:9099/snapshot?topic=/usb_camera/image_raw&width=320&height=240";
        this.handler = new Handler();
        initView(LayoutInflater.from(context).inflate(R.layout.label_camera, this, true));
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            Glide.with((View) this).asGif().load(Integer.valueOf(R.raw.bg_label_camera_loading)).into(this.ivCamera);
            this.cameraState.setText(R.string.text_label_camera_statu_connecting);
            new Thread(new Runnable() {
                public final void run() {
                    LabelCameraView.this.run();
                }
            }).start();
        }
    }

    public void run() {
        String string = MySpUtils.getInstance().getString(SpConstant.CHASSIS_IP, DeploymentToolConstant.CHASSIS_IP);
        while (getVisibility() == 0) {
            Bitmap labelCameraImage = getLabelCameraImage(String.format("http://%s:9099/snapshot?topic=/usb_camera/image_raw&width=320&height=240", new Object[]{string}));
            if (labelCameraImage != null) {
                this.handler.post(new Runnable(labelCameraImage) {
                    public final /* synthetic */ Bitmap f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        LabelCameraView.this.lambda$run$0$LabelCameraView(this.f$1);
                    }
                });
            }
            SystemClock.sleep(50);
        }
    }

    public /* synthetic */ void lambda$run$0$LabelCameraView(Bitmap bitmap) {
        this.ivCamera.setImageBitmap(bitmap);
        this.cameraState.setText(R.string.text_label_camera_statu_connected);
    }

    public Bitmap getLabelCameraImage(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod(HttpProxyConstants.GET);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return decodeByteArray;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initView(View view) {
        this.cameraState = (TextView) view.findViewById(R.id.camera_state);
        this.ivCamera = (ImageView) view.findViewById(R.id.iv_camera);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_close);
        this.ivClose = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LabelCameraView.this.onCloseListener != null) {
                    LabelCameraView.this.onCloseListener.onClose();
                }
            }
        });
    }
}
