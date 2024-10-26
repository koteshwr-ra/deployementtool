package mc.csst.com.selfchassis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.ciot.sentrymove.R;
import java.util.List;
import mc.csst.com.selfchassis.adapter.SensorAdapter;
import mc.csst.com.selfchassislibrary.bean.msg.SensorFeaturesBean;

public class SensorAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private List<SensorFeaturesBean> featuresBeans;
    public OnItemSwitchClickListener listener;

    public interface OnItemSwitchClickListener {
        void onItemSwitch(int i, boolean z);
    }

    public SensorAdapter(Context context2) {
        this.context = context2;
    }

    public void refreshData(List<SensorFeaturesBean> list) {
        this.featuresBeans = list;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_sensor, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        SensorFeaturesBean sensorFeaturesBean = this.featuresBeans.get(i);
        viewHolder.tvSensorName.setText(getSensorName(sensorFeaturesBean.getName()));
        viewHolder.rbSensorOpen.setChecked(sensorFeaturesBean.isEnable());
        viewHolder.rbSensorClose.setChecked(!sensorFeaturesBean.isEnable());
        viewHolder.rbSensorOpen.setOnClickListener((View.OnClickListener) null);
        viewHolder.rbSensorClose.setOnClickListener((View.OnClickListener) null);
        viewHolder.rgSensor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(i, viewHolder) {
            public final /* synthetic */ int f$1;
            public final /* synthetic */ SensorAdapter.ViewHolder f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                SensorAdapter.this.lambda$onBindViewHolder$0$SensorAdapter(this.f$1, this.f$2, radioGroup, i);
            }
        });
        if (i == this.featuresBeans.size() - 1) {
            viewHolder.viewLine.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$SensorAdapter(int i, ViewHolder viewHolder, RadioGroup radioGroup, int i2) {
        OnItemSwitchClickListener onItemSwitchClickListener = this.listener;
        if (onItemSwitchClickListener != null) {
            onItemSwitchClickListener.onItemSwitch(i, viewHolder.rbSensorOpen.isChecked());
        }
    }

    public int getItemCount() {
        List<SensorFeaturesBean> list = this.featuresBeans;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getSensorName(java.lang.String r3) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "未知"
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            int r1 = r3.hashCode()
            switch(r1) {
                case -2016108571: goto L_0x006a;
                case -1367751899: goto L_0x0060;
                case -1095722133: goto L_0x0055;
                case -105765052: goto L_0x004b;
                case 3165387: goto L_0x0041;
                case 3321611: goto L_0x0037;
                case 822634227: goto L_0x002d;
                case 2033758950: goto L_0x0023;
                case 2131565795: goto L_0x0019;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x0074
        L_0x0019:
            java.lang.String r1 = "ultrasound"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 0
            goto L_0x0075
        L_0x0023:
            java.lang.String r1 = "cliff_detection"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 3
            goto L_0x0075
        L_0x002d:
            java.lang.String r1 = "astra_camera"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 2
            goto L_0x0075
        L_0x0037:
            java.lang.String r1 = "lift"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 5
            goto L_0x0075
        L_0x0041:
            java.lang.String r1 = "gate"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 6
            goto L_0x0075
        L_0x004b:
            java.lang.String r1 = "turnstile"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 7
            goto L_0x0075
        L_0x0055:
            java.lang.String r1 = "complex_scene_location_mode"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 8
            goto L_0x0075
        L_0x0060:
            java.lang.String r1 = "camera"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 1
            goto L_0x0075
        L_0x006a:
            java.lang.String r1 = "warning_tape"
            boolean r3 = r3.equals(r1)
            if (r3 == 0) goto L_0x0074
            r3 = 4
            goto L_0x0075
        L_0x0074:
            r3 = -1
        L_0x0075:
            switch(r3) {
                case 0: goto L_0x00c9;
                case 1: goto L_0x00bf;
                case 2: goto L_0x00b5;
                case 3: goto L_0x00ab;
                case 4: goto L_0x00a1;
                case 5: goto L_0x0097;
                case 6: goto L_0x008d;
                case 7: goto L_0x0083;
                case 8: goto L_0x0079;
                default: goto L_0x0078;
            }
        L_0x0078:
            goto L_0x00d2
        L_0x0079:
            android.content.Context r3 = r2.context
            r0 = 2131755129(0x7f100079, float:1.9141129E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x0083:
            android.content.Context r3 = r2.context
            r0 = 2131755471(0x7f1001cf, float:1.9141822E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x008d:
            android.content.Context r3 = r2.context
            r0 = 2131755465(0x7f1001c9, float:1.914181E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x0097:
            android.content.Context r3 = r2.context
            r0 = 2131755467(0x7f1001cb, float:1.9141814E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x00a1:
            android.content.Context r3 = r2.context
            r0 = 2131755138(0x7f100082, float:1.9141147E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x00ab:
            android.content.Context r3 = r2.context
            r0 = 2131755463(0x7f1001c7, float:1.9141806E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x00b5:
            android.content.Context r3 = r2.context
            r0 = 2131755464(0x7f1001c8, float:1.9141808E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x00bf:
            android.content.Context r3 = r2.context
            r0 = 2131755466(0x7f1001ca, float:1.9141812E38)
            java.lang.String r0 = r3.getString(r0)
            goto L_0x00d2
        L_0x00c9:
            android.content.Context r3 = r2.context
            r0 = 2131755472(0x7f1001d0, float:1.9141824E38)
            java.lang.String r0 = r3.getString(r0)
        L_0x00d2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.adapter.SensorAdapter.getSensorName(java.lang.String):java.lang.String");
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatRadioButton rbSensorClose;
        AppCompatRadioButton rbSensorOpen;
        RadioGroup rgSensor;
        TextView tvSensorName;
        View viewLine;

        public ViewHolder(View view) {
            super(view);
            this.tvSensorName = (TextView) view.findViewById(R.id.tv_sensor_name);
            this.rgSensor = (RadioGroup) view.findViewById(R.id.rg_sensor);
            this.rbSensorOpen = (AppCompatRadioButton) view.findViewById(R.id.rb_sensor_open);
            this.rbSensorClose = (AppCompatRadioButton) view.findViewById(R.id.rb_sensor_close);
            this.viewLine = view.findViewById(R.id.view_line);
        }
    }

    public void setOnItemSwitchListener(OnItemSwitchClickListener onItemSwitchClickListener) {
        this.listener = onItemSwitchClickListener;
    }
}
