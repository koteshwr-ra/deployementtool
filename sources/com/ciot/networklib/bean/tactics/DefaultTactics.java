package com.ciot.networklib.bean.tactics;

import com.ciot.realm.db.Tactics;

public class DefaultTactics {
    private String company;
    private String description;
    private String end;
    private String id;
    private String ruletype;
    private String start;
    private int type;

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String str) {
        this.start = str;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String str) {
        this.end = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getRuletype() {
        return this.ruletype;
    }

    public void setRuletype(String str) {
        this.ruletype = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public Tactics toTactics() {
        Tactics tactics = new Tactics();
        tactics.setId(this.id);
        tactics.setCompany(this.company);
        tactics.setStart(this.start);
        tactics.setEnd(this.end);
        tactics.setDescription(this.description);
        tactics.setRuletype(this.ruletype);
        tactics.setType(this.type);
        return tactics;
    }
}
