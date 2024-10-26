package com.example.sroslibrary;

import com.example.sroslibrary.bean.ActivateLicenseBean;
import com.example.sroslibrary.bean.ActivateLicenseBeanR;
import com.example.sroslibrary.bean.HeartBeatBeanR;
import com.example.sroslibrary.bean.NotificationBean;
import com.example.sroslibrary.bean.NotificationBeanR;
import com.example.sroslibrary.bean.QueryNavigationBean;
import com.example.sroslibrary.bean.QueryNavigationBeanR;
import com.example.sroslibrary.bean.RegisterControlBean;
import com.example.sroslibrary.bean.ResultBean;
import com.example.sroslibrary.monitor.monitor.request.ControlPlaybackBean;
import com.example.sroslibrary.monitor.monitor.request.PlaybackQueryRequestBean;
import com.example.sroslibrary.monitor.monitor.request.StartLiveRequestBean;
import com.example.sroslibrary.monitor.monitor.request.StartLocalPhoneCallBean;
import com.example.sroslibrary.monitor.monitor.request.StartPhoneCallRequestBean;
import com.example.sroslibrary.monitor.monitor.request.StartPlaybackRequestBean;
import com.example.sroslibrary.monitor.monitor.request.StopLocalPhoneCallBean;
import com.example.sroslibrary.monitor.monitor.request.StopPhoneCallRequestBean;
import com.example.sroslibrary.monitor.monitor.request.StopVideoRequestBean;
import com.example.sroslibrary.monitor.monitor.result.CommonResultBean;
import com.example.sroslibrary.monitor.monitor.result.ControlPlaybackResultBean;
import com.example.sroslibrary.monitor.monitor.result.PlaybackQueryResultBean;
import com.example.sroslibrary.monitor.monitor.result.StartPhoneCallResultBean;
import com.example.sroslibrary.monitor.monitor.result.StartVideoResultBean;
import com.example.sroslibrary.monitor.monitor.result.StopPhoneCallResultBean;
import com.example.sroslibrary.monitor.monitor.result.StopVideoResultBean;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.limpoxe.support.servicemanager.util.ParamUtil;
import java.lang.reflect.Type;
import java.util.HashMap;

public class GsonUtils {
    private static final byte REQUEST_QA = 0;
    private static final String TAG = GsonUtils.class.getSimpleName();
    private static Gson gson;
    private static Type sActivateLicenseJsonType;
    private static Type sActivateLicenseRJsonType;
    private static Type sHeartBeatRJsonType;
    private static Type sNotificationJsonType;
    private static Type sNotificationRJsonType;
    private static Type sQueryNavigationPointRType;
    private static Type sQueryNavigationPointType;
    private static Type sRegisterControlJsonType;
    private static Type sResultJsonType;
    private static HashMap<String, Type> typeHashMap = new HashMap<>();

    public static Type getType(short s, byte b) {
        if (s != -30975) {
            if (s != -30968) {
                if (s != 17) {
                    if (s != 522) {
                        if (s != 2064) {
                            if (s != 2065) {
                                switch (s) {
                                    case -30207:
                                        if (b == 0) {
                                            return StartLiveRequestBean.class;
                                        }
                                        return StartVideoResultBean.class;
                                    case -30206:
                                    case -30203:
                                        if (b == 0) {
                                            return StopVideoRequestBean.class;
                                        }
                                        return StopVideoResultBean.class;
                                    case -30205:
                                        if (b == 0) {
                                            return PlaybackQueryRequestBean.class;
                                        }
                                        return PlaybackQueryResultBean.class;
                                    case -30204:
                                        if (b == 0) {
                                            return StartPlaybackRequestBean.class;
                                        }
                                        return StartVideoResultBean.class;
                                    default:
                                        switch (s) {
                                            case -30201:
                                                if (b == 0) {
                                                    return ControlPlaybackBean.class;
                                                }
                                                return ControlPlaybackResultBean.class;
                                            case -30200:
                                                if (b == 0) {
                                                    return StartPhoneCallRequestBean.class;
                                                }
                                                return StartPhoneCallResultBean.class;
                                            case -30199:
                                                if (b == 0) {
                                                    return StopPhoneCallRequestBean.class;
                                                }
                                                return StopPhoneCallResultBean.class;
                                            default:
                                                if (b == 0) {
                                                    if (sHeartBeatRJsonType == null) {
                                                        sHeartBeatRJsonType = new TypeToken<HeartBeatBeanR>() {
                                                        }.getType();
                                                    }
                                                    return sHeartBeatRJsonType;
                                                }
                                                if (sResultJsonType == null) {
                                                    sResultJsonType = new TypeToken<ResultBean>() {
                                                    }.getType();
                                                }
                                                return sResultJsonType;
                                        }
                                }
                            } else if (b == 0) {
                                return StopLocalPhoneCallBean.class;
                            } else {
                                return CommonResultBean.class;
                            }
                        } else if (b == 0) {
                            return StartLocalPhoneCallBean.class;
                        } else {
                            return CommonResultBean.class;
                        }
                    } else if (b == 0) {
                        if (sQueryNavigationPointType == null) {
                            sQueryNavigationPointType = new TypeToken<QueryNavigationBean>() {
                            }.getType();
                        }
                        return sQueryNavigationPointType;
                    } else {
                        if (sQueryNavigationPointRType == null) {
                            sQueryNavigationPointRType = new TypeToken<QueryNavigationBeanR>() {
                            }.getType();
                        }
                        return sQueryNavigationPointRType;
                    }
                } else if (b == 0) {
                    if (sNotificationJsonType == null) {
                        sNotificationJsonType = new TypeToken<NotificationBean>() {
                        }.getType();
                    }
                    return sNotificationJsonType;
                } else {
                    if (sNotificationRJsonType == null) {
                        sNotificationRJsonType = new TypeToken<NotificationBeanR>() {
                        }.getType();
                    }
                    return sNotificationRJsonType;
                }
            } else if (b == 0) {
                if (sActivateLicenseJsonType == null) {
                    sActivateLicenseJsonType = new TypeToken<ActivateLicenseBean>() {
                    }.getType();
                }
                return sActivateLicenseJsonType;
            } else {
                if (sActivateLicenseRJsonType == null) {
                    sActivateLicenseRJsonType = new TypeToken<ActivateLicenseBeanR>() {
                    }.getType();
                }
                return sActivateLicenseRJsonType;
            }
        } else if (b == 0) {
            if (sRegisterControlJsonType == null) {
                sRegisterControlJsonType = new TypeToken<RegisterControlBean>() {
                }.getType();
            }
            return sRegisterControlJsonType;
        } else {
            if (sResultJsonType == null) {
                sResultJsonType = new TypeToken<ResultBean>() {
                }.getType();
            }
            return sResultJsonType;
        }
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        }
        return gson;
    }

    public static Gson getGsonR(short s, boolean z) {
        return new GsonBuilder().setExclusionStrategies(ExclusionStrategyHelper.getExclusionStrategy(s, z)).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    static class ExclusionStrategyHelper {
        ExclusionStrategyHelper() {
        }

        static ExclusionStrategy getExclusionStrategy(final short s, final boolean z) {
            return new ExclusionStrategy() {
                public boolean shouldSkipClass(Class<?> cls) {
                    return false;
                }

                public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                    return ExclusionStrategyHelper.isinclude(fieldAttributes, s, z);
                }
            };
        }

        static boolean isinclude(FieldAttributes fieldAttributes, short s, boolean z) {
            if (z) {
                if (s != -30975) {
                    return "reason".equals(fieldAttributes.getName());
                }
                return false;
            } else if (s == -30975 || "reason".equals(fieldAttributes.getName()) || ParamUtil.result.equals(fieldAttributes.getName())) {
                return false;
            } else {
                return true;
            }
        }
    }
}
