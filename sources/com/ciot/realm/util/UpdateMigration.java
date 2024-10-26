package com.ciot.realm.util;

import androidx.core.app.NotificationCompat;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.com_ciot_realm_db_ChildTaskRealmProxy;
import io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy;
import io.realm.com_ciot_realm_db_MusicBeanRealmProxy;
import io.realm.com_ciot_realm_db_PointFRealmProxy;
import io.realm.com_ciot_realm_db_TaskRealmProxy;
import io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy;
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;
import xcrash.TombstoneParser;

class UpdateMigration implements RealmMigration {
    UpdateMigration() {
    }

    public void migrate(DynamicRealm dynamicRealm, long j, long j2) {
        long j3;
        RealmSchema schema = dynamicRealm.getSchema();
        if (j2 == 7) {
            if (j == 1) {
                RealmObjectSchema realmObjectSchema = schema.get(com_ciot_realm_db_ChildTaskRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                if (realmObjectSchema != null) {
                    realmObjectSchema.addField("p_type", Integer.TYPE, new FieldAttribute[0]);
                }
                j3 = j + 1;
            } else {
                j3 = j;
            }
            if (j3 == 2) {
                RealmObjectSchema realmObjectSchema2 = schema.get(com_ciot_realm_db_EmployeeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                if (realmObjectSchema2 != null && !realmObjectSchema2.hasField("staffno")) {
                    realmObjectSchema2.addField("staffno", String.class, new FieldAttribute[0]);
                }
                if (!schema.contains("MeetingBean")) {
                    schema.create("MeetingBean").addField(ServiceProvider.NAME, String.class, new FieldAttribute[0]).addField("begin", Long.TYPE, new FieldAttribute[0]).addField("end", Long.TYPE, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]).addField("address", String.class, new FieldAttribute[0]).addField("createtime", Long.TYPE, new FieldAttribute[0]).addField("id", String.class, new FieldAttribute[0]);
                }
                if (!schema.contains("ParticipantBean")) {
                    schema.create("ParticipantBean").addField("meeting", String.class, new FieldAttribute[0]).addField("person", String.class, new FieldAttribute[0]).addField(ServiceProvider.NAME, String.class, new FieldAttribute[0]).addField("type", String.class, new FieldAttribute[0]).addField(NotificationCompat.CATEGORY_STATUS, String.class, new FieldAttribute[0]).addField("phone", String.class, new FieldAttribute[0]).addField(TombstoneParser.keyCode, String.class, new FieldAttribute[0]).addField("description", String.class, new FieldAttribute[0]).addField("createtime", Integer.TYPE, new FieldAttribute[0]).addField("id", String.class, new FieldAttribute[0]).addField("meetingName", String.class, new FieldAttribute[0]);
                }
                j3++;
            }
            if (j3 == 3) {
                if (!schema.contains(com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME)) {
                    schema.create(com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME).addField("x", Float.TYPE, new FieldAttribute[0]).addField("y", Float.TYPE, new FieldAttribute[0]);
                }
                if (!schema.contains(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME)) {
                    RealmObjectSchema create = schema.create(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                    create.addField("id", String.class, FieldAttribute.PRIMARY_KEY).addField(ScheduleFragment.FLOOR, Integer.TYPE, new FieldAttribute[0]).addField("inAngle", Float.TYPE, new FieldAttribute[0]).addField("outAngle", Float.TYPE, new FieldAttribute[0]).addField("turnstileHost", String.class, new FieldAttribute[0]).addField("turnstileId", String.class, new FieldAttribute[0]).addField("turnstilePort", Integer.TYPE, new FieldAttribute[0]);
                    RealmObjectSchema realmObjectSchema3 = schema.get(com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                    if (realmObjectSchema3 != null) {
                        create.addRealmListField("area", realmObjectSchema3).addRealmObjectField("inPoint", realmObjectSchema3).addRealmObjectField("outPoint", realmObjectSchema3);
                    }
                }
                j3++;
            }
            if (j3 == 4) {
                RealmObjectSchema realmObjectSchema4 = schema.get(com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                if (realmObjectSchema4 != null) {
                    realmObjectSchema4.addField("turnstileType", Integer.TYPE, new FieldAttribute[0]);
                }
                j3++;
            }
            if (j3 == 5) {
                if (!schema.contains("HelloWordBean")) {
                    schema.create("HelloWordBean").addField("word", String.class, FieldAttribute.PRIMARY_KEY);
                }
                RealmObjectSchema realmObjectSchema5 = schema.get(com_ciot_realm_db_EmployeeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                if (realmObjectSchema5 != null) {
                    realmObjectSchema5.addField("greetings", String.class, new FieldAttribute[0]);
                }
                j3++;
            }
            if (j3 == 6 && !schema.contains(com_ciot_realm_db_MusicBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME)) {
                RealmObjectSchema create2 = schema.create(com_ciot_realm_db_MusicBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                create2.addField("id", Integer.TYPE, FieldAttribute.PRIMARY_KEY).addField(ServiceProvider.NAME, String.class, new FieldAttribute[0]).addField("path", String.class, new FieldAttribute[0]).addField("singer", String.class, new FieldAttribute[0]).addField("duration", Integer.TYPE, new FieldAttribute[0]).addField("size", Long.TYPE, new FieldAttribute[0]).addField("isSelect", Boolean.TYPE, new FieldAttribute[0]);
                RealmObjectSchema realmObjectSchema6 = schema.get(com_ciot_realm_db_TaskRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
                if (realmObjectSchema6 != null) {
                    realmObjectSchema6.addRealmObjectField("music", create2);
                }
            }
        }
    }
}
