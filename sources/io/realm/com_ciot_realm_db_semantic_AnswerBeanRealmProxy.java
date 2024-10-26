package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.base.constant.FileConstant;
import com.ciot.realm.db.semantic.ActionBean;
import com.ciot.realm.db.semantic.AnswerBean;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_semantic_ActionBeanRealmProxy;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_semantic_AnswerBeanRealmProxy extends AnswerBean implements RealmObjectProxy, com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmList<String> buttonsRealmList;
    private AnswerBeanColumnInfo columnInfo;
    private RealmList<String> listRealmList;
    private ProxyState<AnswerBean> proxyState;
    private RealmList<String> recommendationsRealmList;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "AnswerBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class AnswerBeanColumnInfo extends ColumnInfo {
        long actionColKey;
        long ansColKey;
        long buttonsColKey;
        long listColKey;
        long musicColKey;
        long pictureColKey;
        long recommendationsColKey;
        long sessionStateColKey;
        long showansColKey;
        long textColKey;
        long titleColKey;
        long typeColKey;
        long urlColKey;
        long videoColKey;
        long videocoverColKey;

        AnswerBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(15);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.actionColKey = addColumnDetails("action", "action", objectSchemaInfo);
            this.ansColKey = addColumnDetails("ans", "ans", objectSchemaInfo);
            this.showansColKey = addColumnDetails("showans", "showans", objectSchemaInfo);
            this.buttonsColKey = addColumnDetails("buttons", "buttons", objectSchemaInfo);
            this.listColKey = addColumnDetails("list", "list", objectSchemaInfo);
            this.musicColKey = addColumnDetails("music", "music", objectSchemaInfo);
            this.pictureColKey = addColumnDetails("picture", "picture", objectSchemaInfo);
            this.recommendationsColKey = addColumnDetails("recommendations", "recommendations", objectSchemaInfo);
            this.sessionStateColKey = addColumnDetails("sessionState", "sessionState", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.titleColKey = addColumnDetails("title", "title", objectSchemaInfo);
            this.textColKey = addColumnDetails("text", "text", objectSchemaInfo);
            this.videoColKey = addColumnDetails(FileConstant.VIDEO_FILE_NAME, FileConstant.VIDEO_FILE_NAME, objectSchemaInfo);
            this.videocoverColKey = addColumnDetails("videocover", "videocover", objectSchemaInfo);
            this.urlColKey = addColumnDetails(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, objectSchemaInfo);
        }

        AnswerBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new AnswerBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            AnswerBeanColumnInfo answerBeanColumnInfo = (AnswerBeanColumnInfo) columnInfo;
            AnswerBeanColumnInfo answerBeanColumnInfo2 = (AnswerBeanColumnInfo) columnInfo2;
            answerBeanColumnInfo2.actionColKey = answerBeanColumnInfo.actionColKey;
            answerBeanColumnInfo2.ansColKey = answerBeanColumnInfo.ansColKey;
            answerBeanColumnInfo2.showansColKey = answerBeanColumnInfo.showansColKey;
            answerBeanColumnInfo2.buttonsColKey = answerBeanColumnInfo.buttonsColKey;
            answerBeanColumnInfo2.listColKey = answerBeanColumnInfo.listColKey;
            answerBeanColumnInfo2.musicColKey = answerBeanColumnInfo.musicColKey;
            answerBeanColumnInfo2.pictureColKey = answerBeanColumnInfo.pictureColKey;
            answerBeanColumnInfo2.recommendationsColKey = answerBeanColumnInfo.recommendationsColKey;
            answerBeanColumnInfo2.sessionStateColKey = answerBeanColumnInfo.sessionStateColKey;
            answerBeanColumnInfo2.typeColKey = answerBeanColumnInfo.typeColKey;
            answerBeanColumnInfo2.titleColKey = answerBeanColumnInfo.titleColKey;
            answerBeanColumnInfo2.textColKey = answerBeanColumnInfo.textColKey;
            answerBeanColumnInfo2.videoColKey = answerBeanColumnInfo.videoColKey;
            answerBeanColumnInfo2.videocoverColKey = answerBeanColumnInfo.videocoverColKey;
            answerBeanColumnInfo2.urlColKey = answerBeanColumnInfo.urlColKey;
        }
    }

    com_ciot_realm_db_semantic_AnswerBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AnswerBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<AnswerBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public ActionBean realmGet$action() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.actionColKey)) {
            return null;
        }
        return (ActionBean) this.proxyState.getRealm$realm().get(ActionBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.actionColKey), false, Collections.emptyList());
    }

    public void realmSet$action(ActionBean actionBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (actionBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.actionColKey);
                return;
            }
            this.proxyState.checkValidObject(actionBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.actionColKey, ((RealmObjectProxy) actionBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("action")) {
            if (actionBean != null && !RealmObject.isManaged(actionBean)) {
                actionBean = (ActionBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(actionBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (actionBean == null) {
                row$realm.nullifyLink(this.columnInfo.actionColKey);
                return;
            }
            this.proxyState.checkValidObject(actionBean);
            row$realm.getTable().setLink(this.columnInfo.actionColKey, row$realm.getObjectKey(), ((RealmObjectProxy) actionBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$ans() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ansColKey);
    }

    public void realmSet$ans(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ansColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ansColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.ansColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.ansColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$showans() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.showansColKey);
    }

    public void realmSet$showans(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.showansColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.showansColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.showansColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.showansColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public RealmList<String> realmGet$buttons() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<String> realmList = this.buttonsRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<String> realmList2 = new RealmList<>(String.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.buttonsColKey, RealmFieldType.STRING_LIST), this.proxyState.getRealm$realm());
        this.buttonsRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$buttons(RealmList<String> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("buttons"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.buttonsColKey, RealmFieldType.STRING_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<String> it = realmList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next == null) {
                        valueList.addNull();
                    } else {
                        valueList.addString(next);
                    }
                }
            }
        }
    }

    public RealmList<String> realmGet$list() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<String> realmList = this.listRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<String> realmList2 = new RealmList<>(String.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.listColKey, RealmFieldType.STRING_LIST), this.proxyState.getRealm$realm());
        this.listRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$list(RealmList<String> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("list"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.listColKey, RealmFieldType.STRING_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<String> it = realmList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next == null) {
                        valueList.addNull();
                    } else {
                        valueList.addString(next);
                    }
                }
            }
        }
    }

    public String realmGet$music() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.musicColKey);
    }

    public void realmSet$music(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.musicColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.musicColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.musicColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.musicColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$picture() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pictureColKey);
    }

    public void realmSet$picture(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pictureColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pictureColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pictureColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pictureColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public RealmList<String> realmGet$recommendations() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<String> realmList = this.recommendationsRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<String> realmList2 = new RealmList<>(String.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.recommendationsColKey, RealmFieldType.STRING_LIST), this.proxyState.getRealm$realm());
        this.recommendationsRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$recommendations(RealmList<String> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("recommendations"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.recommendationsColKey, RealmFieldType.STRING_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<String> it = realmList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next == null) {
                        valueList.addNull();
                    } else {
                        valueList.addString(next);
                    }
                }
            }
        }
    }

    public String realmGet$sessionState() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.sessionStateColKey);
    }

    public void realmSet$sessionState(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.sessionStateColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.sessionStateColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.sessionStateColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.sessionStateColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.typeColKey);
    }

    public void realmSet$type(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.typeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.typeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$title() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.titleColKey);
    }

    public void realmSet$title(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.titleColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.titleColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.titleColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.titleColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$text() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.textColKey);
    }

    public void realmSet$text(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.textColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.textColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.textColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.textColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$video() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.videoColKey);
    }

    public void realmSet$video(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.videoColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.videoColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.videoColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.videoColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$videocover() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.videocoverColKey);
    }

    public void realmSet$videocover(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.videocoverColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.videocoverColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.videocoverColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.videocoverColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$url() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.urlColKey);
    }

    public void realmSet$url(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.urlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.urlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.urlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.urlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 15, 0);
        builder.addPersistedLinkProperty("action", RealmFieldType.OBJECT, com_ciot_realm_db_semantic_ActionBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("ans", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("showans", RealmFieldType.STRING, false, false, false);
        builder.addPersistedValueListProperty("buttons", RealmFieldType.STRING_LIST, false);
        builder.addPersistedValueListProperty("list", RealmFieldType.STRING_LIST, false);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("music", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("picture", RealmFieldType.STRING, false, false, false);
        builder.addPersistedValueListProperty("recommendations", RealmFieldType.STRING_LIST, false);
        OsObjectSchemaInfo.Builder builder4 = builder;
        builder4.addPersistedProperty("sessionState", RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder4.addPersistedProperty("title", RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty("text", RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty(FileConstant.VIDEO_FILE_NAME, RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty("videocover", RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AnswerBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new AnswerBeanColumnInfo(osSchemaInfo);
    }

    public static AnswerBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(4);
        if (jSONObject.has("action")) {
            arrayList.add("action");
        }
        if (jSONObject.has("buttons")) {
            arrayList.add("buttons");
        }
        if (jSONObject.has("list")) {
            arrayList.add("list");
        }
        if (jSONObject.has("recommendations")) {
            arrayList.add("recommendations");
        }
        AnswerBean answerBean = (AnswerBean) realm.createObjectInternal(AnswerBean.class, true, arrayList);
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean;
        if (jSONObject.has("action")) {
            if (jSONObject.isNull("action")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$action((ActionBean) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$action(com_ciot_realm_db_semantic_ActionBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject.getJSONObject("action"), z));
            }
        }
        if (jSONObject.has("ans")) {
            if (jSONObject.isNull("ans")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$ans((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$ans(jSONObject.getString("ans"));
            }
        }
        if (jSONObject.has("showans")) {
            if (jSONObject.isNull("showans")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$showans((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$showans(jSONObject.getString("showans"));
            }
        }
        ProxyUtils.setRealmListWithJsonObject(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons(), jSONObject, "buttons");
        ProxyUtils.setRealmListWithJsonObject(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list(), jSONObject, "list");
        if (jSONObject.has("music")) {
            if (jSONObject.isNull("music")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$music((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$music(jSONObject.getString("music"));
            }
        }
        if (jSONObject.has("picture")) {
            if (jSONObject.isNull("picture")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$picture((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$picture(jSONObject.getString("picture"));
            }
        }
        ProxyUtils.setRealmListWithJsonObject(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations(), jSONObject, "recommendations");
        if (jSONObject.has("sessionState")) {
            if (jSONObject.isNull("sessionState")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$sessionState((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$sessionState(jSONObject.getString("sessionState"));
            }
        }
        if (jSONObject.has("type")) {
            if (!jSONObject.isNull("type")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$type(jSONObject.getInt("type"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
            }
        }
        if (jSONObject.has("title")) {
            if (jSONObject.isNull("title")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$title((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$title(jSONObject.getString("title"));
            }
        }
        if (jSONObject.has("text")) {
            if (jSONObject.isNull("text")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$text((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$text(jSONObject.getString("text"));
            }
        }
        if (jSONObject.has(FileConstant.VIDEO_FILE_NAME)) {
            if (jSONObject.isNull(FileConstant.VIDEO_FILE_NAME)) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$video((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$video(jSONObject.getString(FileConstant.VIDEO_FILE_NAME));
            }
        }
        if (jSONObject.has("videocover")) {
            if (jSONObject.isNull("videocover")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$videocover((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$videocover(jSONObject.getString("videocover"));
            }
        }
        if (jSONObject.has(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL)) {
            if (jSONObject.isNull(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL)) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$url((String) null);
            } else {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$url(jSONObject.getString(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL));
            }
        }
        return answerBean;
    }

    public static AnswerBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        AnswerBean answerBean = new AnswerBean();
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("action")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$action((ActionBean) null);
                } else {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$action(com_ciot_realm_db_semantic_ActionBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("ans")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$ans(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$ans((String) null);
                }
            } else if (nextName.equals("showans")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$showans(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$showans((String) null);
                }
            } else if (nextName.equals("buttons")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$buttons(ProxyUtils.createRealmListWithJsonStream(String.class, jsonReader));
            } else if (nextName.equals("list")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$list(ProxyUtils.createRealmListWithJsonStream(String.class, jsonReader));
            } else if (nextName.equals("music")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$music(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$music((String) null);
                }
            } else if (nextName.equals("picture")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$picture(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$picture((String) null);
                }
            } else if (nextName.equals("recommendations")) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$recommendations(ProxyUtils.createRealmListWithJsonStream(String.class, jsonReader));
            } else if (nextName.equals("sessionState")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$sessionState(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$sessionState((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("title")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$title(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$title((String) null);
                }
            } else if (nextName.equals("text")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$text(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$text((String) null);
                }
            } else if (nextName.equals(FileConstant.VIDEO_FILE_NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$video(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$video((String) null);
                }
            } else if (nextName.equals("videocover")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$videocover(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$videocover((String) null);
                }
            } else if (!nextName.equals(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$url(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$url((String) null);
            }
        }
        jsonReader.endObject();
        return (AnswerBean) realm.copyToRealm(answerBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_semantic_AnswerBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class), false, Collections.emptyList());
        com_ciot_realm_db_semantic_AnswerBeanRealmProxy com_ciot_realm_db_semantic_answerbeanrealmproxy = new com_ciot_realm_db_semantic_AnswerBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_semantic_answerbeanrealmproxy;
    }

    public static AnswerBean copyOrUpdate(Realm realm, AnswerBeanColumnInfo answerBeanColumnInfo, AnswerBean answerBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((answerBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(answerBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) answerBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return answerBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(answerBean);
        if (realmObjectProxy2 != null) {
            return (AnswerBean) realmObjectProxy2;
        }
        return copy(realm, answerBeanColumnInfo, answerBean, z, map, set);
    }

    public static AnswerBean copy(Realm realm, AnswerBeanColumnInfo answerBeanColumnInfo, AnswerBean answerBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(answerBean);
        if (realmObjectProxy != null) {
            return (AnswerBean) realmObjectProxy;
        }
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(AnswerBean.class), set);
        osObjectBuilder.addString(answerBeanColumnInfo.ansColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$ans());
        osObjectBuilder.addString(answerBeanColumnInfo.showansColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$showans());
        osObjectBuilder.addStringList(answerBeanColumnInfo.buttonsColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons());
        osObjectBuilder.addStringList(answerBeanColumnInfo.listColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list());
        osObjectBuilder.addString(answerBeanColumnInfo.musicColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$music());
        osObjectBuilder.addString(answerBeanColumnInfo.pictureColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$picture());
        osObjectBuilder.addStringList(answerBeanColumnInfo.recommendationsColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations());
        osObjectBuilder.addString(answerBeanColumnInfo.sessionStateColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$sessionState());
        osObjectBuilder.addInteger(answerBeanColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$type()));
        osObjectBuilder.addString(answerBeanColumnInfo.titleColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$title());
        osObjectBuilder.addString(answerBeanColumnInfo.textColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$text());
        osObjectBuilder.addString(answerBeanColumnInfo.videoColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$video());
        osObjectBuilder.addString(answerBeanColumnInfo.videocoverColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$videocover());
        osObjectBuilder.addString(answerBeanColumnInfo.urlColKey, com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$url());
        com_ciot_realm_db_semantic_AnswerBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(answerBean, newProxyInstance);
        ActionBean realmGet$action = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$action();
        if (realmGet$action == null) {
            newProxyInstance.realmSet$action((ActionBean) null);
        } else {
            ActionBean actionBean = (ActionBean) map.get(realmGet$action);
            if (actionBean != null) {
                newProxyInstance.realmSet$action(actionBean);
            } else {
                newProxyInstance.realmSet$action(com_ciot_realm_db_semantic_ActionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_ActionBeanRealmProxy.ActionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class), realmGet$action, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, AnswerBean answerBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        Realm realm2 = realm;
        AnswerBean answerBean2 = answerBean;
        Map<RealmModel, Long> map2 = map;
        if ((answerBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(answerBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) answerBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(AnswerBean.class);
        long nativePtr = table.getNativePtr();
        AnswerBeanColumnInfo answerBeanColumnInfo = (AnswerBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(answerBean2, Long.valueOf(createRow));
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean2;
        ActionBean realmGet$action = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$action();
        if (realmGet$action != null) {
            Long l = map2.get(realmGet$action);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_semantic_ActionBeanRealmProxy.insert(realm2, realmGet$action, map2));
            }
            j = createRow;
            Table.nativeSetLink(nativePtr, answerBeanColumnInfo.actionColKey, createRow, l.longValue(), false);
        } else {
            j = createRow;
        }
        String realmGet$ans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$ans();
        if (realmGet$ans != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.ansColKey, j, realmGet$ans, false);
        }
        String realmGet$showans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$showans();
        if (realmGet$showans != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.showansColKey, j, realmGet$showans, false);
        }
        RealmList<String> realmGet$buttons = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons();
        if (realmGet$buttons != null) {
            j2 = j;
            OsList osList = new OsList(table.getUncheckedRow(j2), answerBeanColumnInfo.buttonsColKey);
            Iterator<String> it = realmGet$buttons.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addString(next);
                }
            }
        } else {
            j2 = j;
        }
        RealmList<String> realmGet$list = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list();
        if (realmGet$list != null) {
            OsList osList2 = new OsList(table.getUncheckedRow(j2), answerBeanColumnInfo.listColKey);
            Iterator<String> it2 = realmGet$list.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (next2 == null) {
                    osList2.addNull();
                } else {
                    osList2.addString(next2);
                }
            }
        }
        String realmGet$music = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$music();
        if (realmGet$music != null) {
            j3 = j2;
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.musicColKey, j2, realmGet$music, false);
        } else {
            j3 = j2;
        }
        String realmGet$picture = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$picture();
        if (realmGet$picture != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.pictureColKey, j3, realmGet$picture, false);
        }
        RealmList<String> realmGet$recommendations = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations();
        if (realmGet$recommendations != null) {
            j4 = j3;
            OsList osList3 = new OsList(table.getUncheckedRow(j4), answerBeanColumnInfo.recommendationsColKey);
            Iterator<String> it3 = realmGet$recommendations.iterator();
            while (it3.hasNext()) {
                String next3 = it3.next();
                if (next3 == null) {
                    osList3.addNull();
                } else {
                    osList3.addString(next3);
                }
            }
        } else {
            j4 = j3;
        }
        String realmGet$sessionState = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$sessionState();
        if (realmGet$sessionState != null) {
            j5 = j4;
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.sessionStateColKey, j4, realmGet$sessionState, false);
        } else {
            j5 = j4;
        }
        Table.nativeSetLong(nativePtr, answerBeanColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$type(), false);
        String realmGet$title = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.titleColKey, j5, realmGet$title, false);
        }
        String realmGet$text = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$text();
        if (realmGet$text != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.textColKey, j5, realmGet$text, false);
        }
        String realmGet$video = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$video();
        if (realmGet$video != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.videoColKey, j5, realmGet$video, false);
        }
        String realmGet$videocover = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$videocover();
        if (realmGet$videocover != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.videocoverColKey, j5, realmGet$videocover, false);
        }
        String realmGet$url = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.urlColKey, j5, realmGet$url, false);
        }
        return j5;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(AnswerBean.class);
        long nativePtr = table.getNativePtr();
        AnswerBeanColumnInfo answerBeanColumnInfo = (AnswerBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class);
        while (it.hasNext()) {
            AnswerBean answerBean = (AnswerBean) it.next();
            if (!map2.containsKey(answerBean)) {
                if ((answerBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(answerBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) answerBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(answerBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(answerBean, Long.valueOf(createRow));
                com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean;
                ActionBean realmGet$action = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$action();
                if (realmGet$action != null) {
                    Long l = map2.get(realmGet$action);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_semantic_ActionBeanRealmProxy.insert(realm2, realmGet$action, map2));
                    }
                    j = createRow;
                    table.setLink(answerBeanColumnInfo.actionColKey, createRow, l.longValue(), false);
                } else {
                    j = createRow;
                }
                String realmGet$ans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$ans();
                if (realmGet$ans != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.ansColKey, j, realmGet$ans, false);
                }
                String realmGet$showans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$showans();
                if (realmGet$showans != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.showansColKey, j, realmGet$showans, false);
                }
                RealmList<String> realmGet$buttons = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons();
                if (realmGet$buttons != null) {
                    j2 = j;
                    OsList osList = new OsList(table.getUncheckedRow(j2), answerBeanColumnInfo.buttonsColKey);
                    Iterator<String> it2 = realmGet$buttons.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addString(next);
                        }
                    }
                } else {
                    j2 = j;
                }
                RealmList<String> realmGet$list = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list();
                if (realmGet$list != null) {
                    OsList osList2 = new OsList(table.getUncheckedRow(j2), answerBeanColumnInfo.listColKey);
                    Iterator<String> it3 = realmGet$list.iterator();
                    while (it3.hasNext()) {
                        String next2 = it3.next();
                        if (next2 == null) {
                            osList2.addNull();
                        } else {
                            osList2.addString(next2);
                        }
                    }
                }
                String realmGet$music = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$music();
                if (realmGet$music != null) {
                    j3 = j2;
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.musicColKey, j2, realmGet$music, false);
                } else {
                    j3 = j2;
                }
                String realmGet$picture = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$picture();
                if (realmGet$picture != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.pictureColKey, j3, realmGet$picture, false);
                }
                RealmList<String> realmGet$recommendations = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations();
                if (realmGet$recommendations != null) {
                    j4 = j3;
                    OsList osList3 = new OsList(table.getUncheckedRow(j4), answerBeanColumnInfo.recommendationsColKey);
                    Iterator<String> it4 = realmGet$recommendations.iterator();
                    while (it4.hasNext()) {
                        String next3 = it4.next();
                        if (next3 == null) {
                            osList3.addNull();
                        } else {
                            osList3.addString(next3);
                        }
                    }
                } else {
                    j4 = j3;
                }
                String realmGet$sessionState = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$sessionState();
                if (realmGet$sessionState != null) {
                    j5 = j4;
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.sessionStateColKey, j4, realmGet$sessionState, false);
                } else {
                    j5 = j4;
                }
                Table.nativeSetLong(nativePtr, answerBeanColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$type(), false);
                String realmGet$title = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.titleColKey, j5, realmGet$title, false);
                }
                String realmGet$text = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$text();
                if (realmGet$text != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.textColKey, j5, realmGet$text, false);
                }
                String realmGet$video = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$video();
                if (realmGet$video != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.videoColKey, j5, realmGet$video, false);
                }
                String realmGet$videocover = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$videocover();
                if (realmGet$videocover != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.videocoverColKey, j5, realmGet$videocover, false);
                }
                String realmGet$url = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.urlColKey, j5, realmGet$url, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, AnswerBean answerBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        AnswerBean answerBean2 = answerBean;
        Map<RealmModel, Long> map2 = map;
        if ((answerBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(answerBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) answerBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(AnswerBean.class);
        long nativePtr = table.getNativePtr();
        AnswerBeanColumnInfo answerBeanColumnInfo = (AnswerBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(answerBean2, Long.valueOf(createRow));
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean2;
        ActionBean realmGet$action = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$action();
        if (realmGet$action != null) {
            Long l = map2.get(realmGet$action);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_semantic_ActionBeanRealmProxy.insertOrUpdate(realm2, realmGet$action, map2));
            }
            j = createRow;
            Table.nativeSetLink(nativePtr, answerBeanColumnInfo.actionColKey, createRow, l.longValue(), false);
        } else {
            j = createRow;
            Table.nativeNullifyLink(nativePtr, answerBeanColumnInfo.actionColKey, j);
        }
        String realmGet$ans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$ans();
        if (realmGet$ans != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.ansColKey, j, realmGet$ans, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.ansColKey, j, false);
        }
        String realmGet$showans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$showans();
        if (realmGet$showans != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.showansColKey, j, realmGet$showans, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.showansColKey, j, false);
        }
        long j4 = j;
        OsList osList = new OsList(table.getUncheckedRow(j4), answerBeanColumnInfo.buttonsColKey);
        osList.removeAll();
        RealmList<String> realmGet$buttons = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons();
        if (realmGet$buttons != null) {
            Iterator<String> it = realmGet$buttons.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addString(next);
                }
            }
        }
        OsList osList2 = new OsList(table.getUncheckedRow(j4), answerBeanColumnInfo.listColKey);
        osList2.removeAll();
        RealmList<String> realmGet$list = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list();
        if (realmGet$list != null) {
            Iterator<String> it2 = realmGet$list.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (next2 == null) {
                    osList2.addNull();
                } else {
                    osList2.addString(next2);
                }
            }
        }
        String realmGet$music = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$music();
        if (realmGet$music != null) {
            j2 = j4;
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.musicColKey, j4, realmGet$music, false);
        } else {
            j2 = j4;
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.musicColKey, j2, false);
        }
        String realmGet$picture = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$picture();
        if (realmGet$picture != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.pictureColKey, j2, realmGet$picture, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.pictureColKey, j2, false);
        }
        long j5 = j2;
        OsList osList3 = new OsList(table.getUncheckedRow(j5), answerBeanColumnInfo.recommendationsColKey);
        osList3.removeAll();
        RealmList<String> realmGet$recommendations = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations();
        if (realmGet$recommendations != null) {
            Iterator<String> it3 = realmGet$recommendations.iterator();
            while (it3.hasNext()) {
                String next3 = it3.next();
                if (next3 == null) {
                    osList3.addNull();
                } else {
                    osList3.addString(next3);
                }
            }
        }
        String realmGet$sessionState = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$sessionState();
        if (realmGet$sessionState != null) {
            j3 = j5;
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.sessionStateColKey, j5, realmGet$sessionState, false);
        } else {
            j3 = j5;
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.sessionStateColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, answerBeanColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$type(), false);
        String realmGet$title = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.titleColKey, j3, realmGet$title, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.titleColKey, j3, false);
        }
        String realmGet$text = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$text();
        if (realmGet$text != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.textColKey, j3, realmGet$text, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.textColKey, j3, false);
        }
        String realmGet$video = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$video();
        if (realmGet$video != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.videoColKey, j3, realmGet$video, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.videoColKey, j3, false);
        }
        String realmGet$videocover = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$videocover();
        if (realmGet$videocover != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.videocoverColKey, j3, realmGet$videocover, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.videocoverColKey, j3, false);
        }
        String realmGet$url = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(nativePtr, answerBeanColumnInfo.urlColKey, j3, realmGet$url, false);
        } else {
            Table.nativeSetNull(nativePtr, answerBeanColumnInfo.urlColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(AnswerBean.class);
        long nativePtr = table.getNativePtr();
        AnswerBeanColumnInfo answerBeanColumnInfo = (AnswerBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class);
        while (it.hasNext()) {
            AnswerBean answerBean = (AnswerBean) it.next();
            if (!map2.containsKey(answerBean)) {
                if ((answerBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(answerBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) answerBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(answerBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(answerBean, Long.valueOf(createRow));
                com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean;
                ActionBean realmGet$action = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$action();
                if (realmGet$action != null) {
                    Long l = map2.get(realmGet$action);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_semantic_ActionBeanRealmProxy.insertOrUpdate(realm2, realmGet$action, map2));
                    }
                    j = createRow;
                    Table.nativeSetLink(nativePtr, answerBeanColumnInfo.actionColKey, createRow, l.longValue(), false);
                } else {
                    j = createRow;
                    Table.nativeNullifyLink(nativePtr, answerBeanColumnInfo.actionColKey, j);
                }
                String realmGet$ans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$ans();
                if (realmGet$ans != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.ansColKey, j, realmGet$ans, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.ansColKey, j, false);
                }
                String realmGet$showans = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$showans();
                if (realmGet$showans != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.showansColKey, j, realmGet$showans, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.showansColKey, j, false);
                }
                long j4 = j;
                OsList osList = new OsList(table.getUncheckedRow(j4), answerBeanColumnInfo.buttonsColKey);
                osList.removeAll();
                RealmList<String> realmGet$buttons = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons();
                if (realmGet$buttons != null) {
                    Iterator<String> it2 = realmGet$buttons.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addString(next);
                        }
                    }
                }
                OsList osList2 = new OsList(table.getUncheckedRow(j4), answerBeanColumnInfo.listColKey);
                osList2.removeAll();
                RealmList<String> realmGet$list = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list();
                if (realmGet$list != null) {
                    Iterator<String> it3 = realmGet$list.iterator();
                    while (it3.hasNext()) {
                        String next2 = it3.next();
                        if (next2 == null) {
                            osList2.addNull();
                        } else {
                            osList2.addString(next2);
                        }
                    }
                }
                String realmGet$music = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$music();
                if (realmGet$music != null) {
                    j2 = j4;
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.musicColKey, j4, realmGet$music, false);
                } else {
                    j2 = j4;
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.musicColKey, j2, false);
                }
                String realmGet$picture = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$picture();
                if (realmGet$picture != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.pictureColKey, j2, realmGet$picture, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.pictureColKey, j2, false);
                }
                long j5 = j2;
                OsList osList3 = new OsList(table.getUncheckedRow(j5), answerBeanColumnInfo.recommendationsColKey);
                osList3.removeAll();
                RealmList<String> realmGet$recommendations = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations();
                if (realmGet$recommendations != null) {
                    Iterator<String> it4 = realmGet$recommendations.iterator();
                    while (it4.hasNext()) {
                        String next3 = it4.next();
                        if (next3 == null) {
                            osList3.addNull();
                        } else {
                            osList3.addString(next3);
                        }
                    }
                }
                String realmGet$sessionState = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$sessionState();
                if (realmGet$sessionState != null) {
                    j3 = j5;
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.sessionStateColKey, j5, realmGet$sessionState, false);
                } else {
                    j3 = j5;
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.sessionStateColKey, j3, false);
                }
                Table.nativeSetLong(nativePtr, answerBeanColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$type(), false);
                String realmGet$title = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$title();
                if (realmGet$title != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.titleColKey, j3, realmGet$title, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.titleColKey, j3, false);
                }
                String realmGet$text = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$text();
                if (realmGet$text != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.textColKey, j3, realmGet$text, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.textColKey, j3, false);
                }
                String realmGet$video = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$video();
                if (realmGet$video != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.videoColKey, j3, realmGet$video, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.videoColKey, j3, false);
                }
                String realmGet$videocover = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$videocover();
                if (realmGet$videocover != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.videocoverColKey, j3, realmGet$videocover, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.videocoverColKey, j3, false);
                }
                String realmGet$url = com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(nativePtr, answerBeanColumnInfo.urlColKey, j3, realmGet$url, false);
                } else {
                    Table.nativeSetNull(nativePtr, answerBeanColumnInfo.urlColKey, j3, false);
                }
            }
        }
    }

    public static AnswerBean createDetachedCopy(AnswerBean answerBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        AnswerBean answerBean2;
        if (i > i2 || answerBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(answerBean);
        if (cacheData == null) {
            answerBean2 = new AnswerBean();
            map.put(answerBean, new RealmObjectProxy.CacheData(i, answerBean2));
        } else if (i >= cacheData.minDepth) {
            return (AnswerBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            answerBean2 = (AnswerBean) cacheData.object;
        }
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface = answerBean2;
        com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2 = answerBean;
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$action(com_ciot_realm_db_semantic_ActionBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$action(), i + 1, i2, map));
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$ans(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$ans());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$showans(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$showans());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$buttons(new RealmList());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$buttons().addAll(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$buttons());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$list(new RealmList());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$list().addAll(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$list());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$music(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$music());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$picture(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$picture());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$recommendations(new RealmList());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmGet$recommendations().addAll(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$recommendations());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$sessionState(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$sessionState());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$type(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$title(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$title());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$text(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$text());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$video(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$video());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$videocover(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$videocover());
        com_ciot_realm_db_semantic_answerbeanrealmproxyinterface.realmSet$url(com_ciot_realm_db_semantic_answerbeanrealmproxyinterface2.realmGet$url());
        return answerBean2;
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }

    public int hashCode() {
        String path = this.proxyState.getRealm$realm().getPath();
        String name = this.proxyState.getRow$realm().getTable().getName();
        long objectKey = this.proxyState.getRow$realm().getObjectKey();
        int i = 0;
        int hashCode = (527 + (path != null ? path.hashCode() : 0)) * 31;
        if (name != null) {
            i = name.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((objectKey >>> 32) ^ objectKey));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        com_ciot_realm_db_semantic_AnswerBeanRealmProxy com_ciot_realm_db_semantic_answerbeanrealmproxy = (com_ciot_realm_db_semantic_AnswerBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_semantic_answerbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_semantic_answerbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_semantic_answerbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
