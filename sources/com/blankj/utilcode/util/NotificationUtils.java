package com.blankj.utilcode.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.blankj.utilcode.util.Utils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class NotificationUtils {
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Importance {
    }

    public static boolean areNotificationsEnabled() {
        return NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled();
    }

    public static void notify(int i, Utils.Consumer<NotificationCompat.Builder> consumer) {
        notify((String) null, i, ChannelConfig.DEFAULT_CHANNEL_CONFIG, consumer);
    }

    public static void notify(String str, int i, Utils.Consumer<NotificationCompat.Builder> consumer) {
        notify(str, i, ChannelConfig.DEFAULT_CHANNEL_CONFIG, consumer);
    }

    public static void notify(int i, ChannelConfig channelConfig, Utils.Consumer<NotificationCompat.Builder> consumer) {
        notify((String) null, i, channelConfig, consumer);
    }

    public static void notify(String str, int i, ChannelConfig channelConfig, Utils.Consumer<NotificationCompat.Builder> consumer) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) Utils.getApp().getSystemService("notification")).createNotificationChannel(channelConfig.getNotificationChannel());
        }
        NotificationManagerCompat from = NotificationManagerCompat.from(Utils.getApp());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Utils.getApp());
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId(channelConfig.mNotificationChannel.getId());
        }
        consumer.accept(builder);
        from.notify(str, i, builder.build());
    }

    public static void cancel(String str, int i) {
        NotificationManagerCompat.from(Utils.getApp()).cancel(str, i);
    }

    public static void cancel(int i) {
        NotificationManagerCompat.from(Utils.getApp()).cancel(i);
    }

    public static void cancelAll() {
        NotificationManagerCompat.from(Utils.getApp()).cancelAll();
    }

    public static void setNotificationBarVisibility(boolean z) {
        String str;
        if (z) {
            str = Build.VERSION.SDK_INT <= 16 ? "expand" : "expandNotificationsPanel";
        } else {
            str = Build.VERSION.SDK_INT <= 16 ? "collapse" : "collapsePanels";
        }
        invokePanels(str);
    }

    private static void invokePanels(String str) {
        try {
            Class.forName("android.app.StatusBarManager").getMethod(str, new Class[0]).invoke(Utils.getApp().getSystemService("statusbar"), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ChannelConfig {
        public static final ChannelConfig DEFAULT_CHANNEL_CONFIG = new ChannelConfig(Utils.getApp().getPackageName(), Utils.getApp().getPackageName(), 3);
        /* access modifiers changed from: private */
        public NotificationChannel mNotificationChannel;

        public ChannelConfig(String str, CharSequence charSequence, int i) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel = new NotificationChannel(str, charSequence, i);
            }
        }

        public NotificationChannel getNotificationChannel() {
            return this.mNotificationChannel;
        }

        public ChannelConfig setBypassDnd(boolean z) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setBypassDnd(z);
            }
            return this;
        }

        public ChannelConfig setDescription(String str) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setDescription(str);
            }
            return this;
        }

        public ChannelConfig setGroup(String str) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setGroup(str);
            }
            return this;
        }

        public ChannelConfig setImportance(int i) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setImportance(i);
            }
            return this;
        }

        public ChannelConfig setLightColor(int i) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setLightColor(i);
            }
            return this;
        }

        public ChannelConfig setLockscreenVisibility(int i) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setLockscreenVisibility(i);
            }
            return this;
        }

        public ChannelConfig setName(CharSequence charSequence) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setName(charSequence);
            }
            return this;
        }

        public ChannelConfig setShowBadge(boolean z) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setShowBadge(z);
            }
            return this;
        }

        public ChannelConfig setSound(Uri uri, AudioAttributes audioAttributes) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setSound(uri, audioAttributes);
            }
            return this;
        }

        public ChannelConfig setVibrationPattern(long[] jArr) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mNotificationChannel.setVibrationPattern(jArr);
            }
            return this;
        }
    }
}
