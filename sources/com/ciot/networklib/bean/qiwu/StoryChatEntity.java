package com.ciot.networklib.bean.qiwu;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class StoryChatEntity implements MultiItemEntity {
    public static int CHAT_LEFT = 0;
    public static int CHAT_RIGHT = 1;
    public String audio;
    public String audioUrl;
    public int itemType;
    public String npcName;
    public String person;
    public String text;

    public StoryChatEntity(int i, String str) {
        this.itemType = i;
        this.text = str;
    }

    public int getItemType() {
        return this.itemType;
    }
}
