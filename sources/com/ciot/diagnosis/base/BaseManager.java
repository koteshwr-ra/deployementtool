package com.ciot.diagnosis.base;

import com.ciot.diagnosis.RobotType;
import com.ciot.diagnosis.diagnosis.FaceRecognizeDiagnosis;
import com.ciot.diagnosis.diagnosis.InfraredTemperatureDiagnosis;
import com.ciot.diagnosis.diagnosis.LightDiagnosis;
import com.ciot.diagnosis.diagnosis.MonitorDiagnosis;
import com.ciot.diagnosis.diagnosis.NetworkDiagnosis;
import com.ciot.diagnosis.diagnosis.ScannerDiagnosis;
import com.ciot.diagnosis.diagnosis.SelfChassisDiagnosis;
import com.ciot.diagnosis.diagnosis.SemanticDiagnosis;
import com.ciot.diagnosis.diagnosis.VoiceRecognitionDiagnosis;
import com.ciot.diagnosis.diagnosis.WaterChassisDiagnosis;
import com.ciot.diagnosis.util.DiagnosisUtil;
import java.util.ArrayList;
import java.util.List;

public class BaseManager {
    public static boolean isLeLe = false;
    protected List<BaseGroupDiagnosis> diagnosisGroupList;

    /* access modifiers changed from: protected */
    public void init(RobotType robotType, int i) {
        this.diagnosisGroupList = new ArrayList();
        if (robotType == RobotType.WelcomePatrol) {
            initWelcomePatrol(i);
        } else if (robotType == RobotType.LeLe) {
            initLeLe();
        } else if (robotType == RobotType.Spray) {
            initSpray(i);
        }
    }

    private void initSpray(int i) {
        this.diagnosisGroupList.add(new NetworkDiagnosis());
        if (i == 2) {
            this.diagnosisGroupList.add(new WaterChassisDiagnosis());
        } else if (i == 1) {
            this.diagnosisGroupList.add(new SelfChassisDiagnosis());
        }
        this.diagnosisGroupList.add(new LightDiagnosis());
    }

    private void initLeLe() {
        this.diagnosisGroupList.add(new NetworkDiagnosis());
        this.diagnosisGroupList.add(new SemanticDiagnosis());
        this.diagnosisGroupList.add(new VoiceRecognitionDiagnosis());
        this.diagnosisGroupList.add(new FaceRecognizeDiagnosis());
        this.diagnosisGroupList.add(new InfraredTemperatureDiagnosis());
        this.diagnosisGroupList.add(new LightDiagnosis());
        this.diagnosisGroupList.add(new ScannerDiagnosis());
    }

    private void initWelcomePatrol(int i) {
        this.diagnosisGroupList.add(new NetworkDiagnosis());
        this.diagnosisGroupList.add(new SemanticDiagnosis());
        this.diagnosisGroupList.add(new VoiceRecognitionDiagnosis());
        this.diagnosisGroupList.add(new FaceRecognizeDiagnosis());
        this.diagnosisGroupList.add(new MonitorDiagnosis());
        if (i == 2) {
            this.diagnosisGroupList.add(new WaterChassisDiagnosis());
        } else if (i == 1) {
            this.diagnosisGroupList.add(new SelfChassisDiagnosis());
        }
        this.diagnosisGroupList.add(new LightDiagnosis());
        this.diagnosisGroupList.add(new ScannerDiagnosis());
    }

    public List<BaseGroupDiagnosis> getDiagnosisGroupList() {
        return this.diagnosisGroupList;
    }

    public WaterChassisDiagnosis getWaterChassisDiagnosis() {
        return (WaterChassisDiagnosis) DiagnosisUtil.getGroupDiagnosis(WaterChassisDiagnosis.NAME, this.diagnosisGroupList);
    }

    public SelfChassisDiagnosis getSelfChassisDiagnosis() {
        return (SelfChassisDiagnosis) DiagnosisUtil.getGroupDiagnosis(SelfChassisDiagnosis.NAME, this.diagnosisGroupList);
    }

    public FaceRecognizeDiagnosis getFaceRecognizeDiagnosis() {
        return (FaceRecognizeDiagnosis) DiagnosisUtil.getGroupDiagnosis(FaceRecognizeDiagnosis.NAME, this.diagnosisGroupList);
    }

    public LightDiagnosis getLightDiagnosis() {
        return (LightDiagnosis) DiagnosisUtil.getGroupDiagnosis(LightDiagnosis.NAME, this.diagnosisGroupList);
    }

    public MonitorDiagnosis getMonitorDiagnosis() {
        return (MonitorDiagnosis) DiagnosisUtil.getGroupDiagnosis(MonitorDiagnosis.NAME, this.diagnosisGroupList);
    }

    public NetworkDiagnosis getNetworkDiagnosis() {
        return (NetworkDiagnosis) DiagnosisUtil.getGroupDiagnosis(NetworkDiagnosis.NAME, this.diagnosisGroupList);
    }

    public ScannerDiagnosis getScannerDiagnosis() {
        return (ScannerDiagnosis) DiagnosisUtil.getGroupDiagnosis(ScannerDiagnosis.NAME, this.diagnosisGroupList);
    }

    public SemanticDiagnosis getSemanticDiagnosis() {
        return (SemanticDiagnosis) DiagnosisUtil.getGroupDiagnosis(SemanticDiagnosis.NAME, this.diagnosisGroupList);
    }

    public VoiceRecognitionDiagnosis getVoiceRecognitionDiagnosis() {
        return (VoiceRecognitionDiagnosis) DiagnosisUtil.getGroupDiagnosis(VoiceRecognitionDiagnosis.NAME, this.diagnosisGroupList);
    }

    public InfraredTemperatureDiagnosis getInfraredTemperatureDiagnosis() {
        return (InfraredTemperatureDiagnosis) DiagnosisUtil.getGroupDiagnosis(InfraredTemperatureDiagnosis.NAME, this.diagnosisGroupList);
    }
}
