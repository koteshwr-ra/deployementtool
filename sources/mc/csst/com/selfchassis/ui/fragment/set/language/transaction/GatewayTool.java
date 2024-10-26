package mc.csst.com.selfchassis.ui.fragment.set.language.transaction;

import android.util.Log;
import com.ciot.base.storage.MySpUtils;
import com.ciot.base.util.GsonUtils;
import com.ciot.networklib.interceptor.HttpLoggingInterceptor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.CommonAddressBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.bean.GetGatewayRespBean;
import mc.csst.com.selfchassis.ui.fragment.set.language.mode.IGateway;
import mc.csst.com.selfchassis.utils.SpConstant;
import mc.csst.com.selfchassis.utils.constant.DeploymentToolConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GatewayTool {
    private static final String TAG = "GatewayRetrofitManager";
    public static Map<String, String> abbreviationUrlMap;
    public static GetGatewayRespBean allGateway;
    public static GetGatewayRespBean currentGateway;
    private static volatile GatewayTool instance = new GatewayTool();
    public static List<CommonAddressBean> localAddressList = new ArrayList();
    public static List<CommonAddressBean> wuHanAddressList = new ArrayList();
    private IGateway service;

    static {
        HashMap hashMap = new HashMap();
        abbreviationUrlMap = hashMap;
        hashMap.put("http://robot.csstrobot.com:9899", "[cn]");
        abbreviationUrlMap.put("http://gfai-robotics.com:9899", "[hk]");
        abbreviationUrlMap.put("http://usom.gfai-robotics.com:9899", "[us]");
    }

    public String getCurrentGateway() {
        GetGatewayRespBean getGatewayRespBean = currentGateway;
        if (getGatewayRespBean == null) {
            return "";
        }
        return getGatewayRespBean.getDefaultAddress();
    }

    public static GatewayTool getInstance() {
        return instance;
    }

    private GatewayTool() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor("DeployTool"));
        this.service = (IGateway) new Retrofit.Builder().client(builder.build()).baseUrl(String.format("http://%s:8082/", new Object[]{MySpUtils.getInstance().getString(SpConstant.CHASSIS_IP, DeploymentToolConstant.CHASSIS_IP)})).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build().create(IGateway.class);
    }

    public void initWuhanGateway() {
        GetGatewayRespBean getGatewayRespBean = allGateway;
        if (getGatewayRespBean != null) {
            wuHanAddressList.add(new CommonAddressBean(getGatewayRespBean.getCn(), true));
            wuHanAddressList.add(new CommonAddressBean(allGateway.getHk(), true));
            wuHanAddressList.add(new CommonAddressBean(allGateway.getUs(), true));
        }
        if (currentGateway != null) {
            for (CommonAddressBean next : wuHanAddressList) {
                if (getInstance().getCurrentGateway().equalsIgnoreCase(next.getUrl())) {
                    next.setSelected(true);
                    return;
                }
            }
        }
        CommonAddressBean commonAddressBean = new CommonAddressBean(getInstance().getCurrentGateway(), false);
        commonAddressBean.setSelected(true);
        localAddressList.add(commonAddressBean);
    }

    public IGateway getService() {
        return this.service;
    }

    public void getGateway(final Boolean bool) {
        this.service.getGateway(bool).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GetGatewayRespBean>() {
            public void accept(GetGatewayRespBean getGatewayRespBean) throws Exception {
                Log.d(GatewayTool.TAG, "accept: " + GsonUtils.toJson(getGatewayRespBean));
                if (bool.booleanValue()) {
                    GatewayTool.currentGateway = getGatewayRespBean;
                } else {
                    GatewayTool.allGateway = getGatewayRespBean;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Log.e(GatewayTool.TAG, "accept: ", th);
                if (bool.booleanValue()) {
                    GatewayTool.currentGateway = null;
                } else {
                    GatewayTool.allGateway = null;
                }
            }
        });
    }
}
