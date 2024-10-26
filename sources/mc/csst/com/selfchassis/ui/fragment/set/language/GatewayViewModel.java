package mc.csst.com.selfchassis.ui.fragment.set.language;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ciot.base.util.GsonUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.SetGatewayRespBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.transaction.GatewayTool;
import okhttp3.ResponseBody;

public class GatewayViewModel extends ViewModel {
    private static final String TAG = "GatewayModel";
    public MutableLiveData<Boolean> setGateway = new MutableLiveData<>();

    public void setGateway(String str) {
        GatewayTool.getInstance().getService().setGateway(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                SetGatewayRespBean setGatewayRespBean = (SetGatewayRespBean) GsonUtils.fromLocalJson(responseBody.string().replace("\\", "").replace("\"{", "{").replace("}\"", "}"), SetGatewayRespBean.class);
                if (setGatewayRespBean == null || !setGatewayRespBean.getResult().equalsIgnoreCase("ok")) {
                    GatewayViewModel.this.setGateway.setValue(false);
                } else {
                    GatewayViewModel.this.setGateway.setValue(true);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Log.e(GatewayViewModel.TAG, "accept: ", th);
                GatewayViewModel.this.setGateway.setValue(false);
            }
        });
    }
}
