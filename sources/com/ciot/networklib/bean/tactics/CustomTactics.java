package com.ciot.networklib.bean.tactics;

import android.text.TextUtils;
import com.ciot.realm.db.Tactics;
import java.util.ArrayList;
import java.util.List;

public class CustomTactics {
    private String company;
    private List<CustomTime> datas;
    private String description;
    private List<Integer> effect;
    private String id;
    private String name;
    private List<String> peoson;
    private List<String> person;
    private int range;
    private String ruletype;

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public String getRuletype() {
        return this.ruletype;
    }

    public void setRuletype(String str) {
        this.ruletype = str;
    }

    public int getRange() {
        return this.range;
    }

    public void setRange(int i) {
        this.range = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public List<CustomTime> getDatas() {
        return this.datas;
    }

    public void setDatas(List<CustomTime> list) {
        this.datas = list;
    }

    public List<Integer> getEffect() {
        return this.effect;
    }

    public void setEffect(List<Integer> list) {
        this.effect = list;
    }

    public List<String> getPerson() {
        return this.person;
    }

    public void setPerson(List<String> list) {
        this.person = list;
    }

    public List<String> getPeoson() {
        return this.peoson;
    }

    public void setPeoson(List<String> list) {
        this.peoson = list;
    }

    public List<Tactics> getTactics() {
        long j;
        ArrayList arrayList = new ArrayList();
        List<CustomTime> list = this.datas;
        if (list != null && !list.isEmpty()) {
            int i = 0;
            while (i < this.datas.size()) {
                CustomTime customTime = this.datas.get(i);
                Tactics tactics = new Tactics();
                tactics.setId(this.id + i);
                tactics.setCompany(this.company);
                tactics.setDescription(this.description);
                tactics.setRuletype(this.ruletype);
                tactics.setEffect(this.effect.toString());
                tactics.setPerson(this.person.toString());
                tactics.setType(customTime.getType());
                tactics.setStart(customTime.getStart());
                tactics.setEnd(customTime.getEnd());
                long timeMinute = getTimeMinute(customTime.getStart());
                long timeMinute2 = getTimeMinute(customTime.getEnd());
                if (i == 0) {
                    j = 0;
                } else {
                    j = timeMinute - ((long) (this.range / 60));
                }
                tactics.setStartTimeOpen(j);
                tactics.setStartTimeClose(timeMinute + ((long) (this.range / 60)));
                tactics.setEndTimeOpen(timeMinute2 - ((long) (this.range / 60)));
                tactics.setEndTimeClose(i == this.datas.size() + -1 ? 2147483647L : ((long) (this.range / 60)) + timeMinute2);
                arrayList.add(tactics);
                i++;
            }
        }
        return arrayList;
    }

    public static long getTimeMinute(String str) {
        if (TextUtils.isEmpty(str) || str.split(":").length != 2) {
            return 0;
        }
        String[] split = str.split(":");
        return (long) ((Integer.parseInt(split[0]) * 60) + Integer.parseInt(split[1]));
    }
}
