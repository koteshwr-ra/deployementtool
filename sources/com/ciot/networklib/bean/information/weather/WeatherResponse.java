package com.ciot.networklib.bean.information.weather;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    private CurrentBean current;
    private List<ForecastBean> forecast;
    private LocationBean location;

    public LocationBean getLocation() {
        return this.location;
    }

    public void setLocation(LocationBean locationBean) {
        this.location = locationBean;
    }

    public CurrentBean getCurrent() {
        return this.current;
    }

    public void setCurrent(CurrentBean currentBean) {
        this.current = currentBean;
    }

    public List<ForecastBean> getForecast() {
        return this.forecast;
    }

    public void setForecast(List<ForecastBean> list) {
        this.forecast = list;
    }

    public static class LocationBean {
        private String alert;
        private String degreetype;
        private String imagerelativeurl;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String name;
        private String timezone;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getLat() {
            return this.lat;
        }

        public void setLat(String str) {
            this.lat = str;
        }

        public String getLongX() {
            return this.longX;
        }

        public void setLongX(String str) {
            this.longX = str;
        }

        public String getTimezone() {
            return this.timezone;
        }

        public void setTimezone(String str) {
            this.timezone = str;
        }

        public String getAlert() {
            return this.alert;
        }

        public void setAlert(String str) {
            this.alert = str;
        }

        public String getDegreetype() {
            return this.degreetype;
        }

        public void setDegreetype(String str) {
            this.degreetype = str;
        }

        public String getImagerelativeurl() {
            return this.imagerelativeurl;
        }

        public void setImagerelativeurl(String str) {
            this.imagerelativeurl = str;
        }
    }

    public static class CurrentBean {
        private String date;
        private String day;
        private String feelslike;
        private String humidity;
        private String imageUrl;
        private String observationpoint;
        private String observationtime;
        private String shortday;
        private String skycode;
        private String skytext;
        private String temperature;
        private String winddisplay;
        private String windspeed;

        /* access modifiers changed from: package-private */
        public String getSky(int i) {
            if (i == 3200) {
                return "暂无数据";
            }
            switch (i) {
                case 0:
                    return "龙卷风";
                case 1:
                    return "热带风暴";
                case 2:
                    return "飓风";
                case 3:
                    return "强雷暴";
                case 4:
                    return "雷暴";
                case 5:
                    return "雨夹雪";
                case 6:
                    return "雨冰雹";
                case 7:
                    return "雨雪";
                case 8:
                    return "冻小雨";
                case 9:
                    return "小雨";
                case 10:
                    return "冻雨";
                case 11:
                case 12:
                    return "阵雨";
                case 13:
                    return "飘雪";
                case 14:
                    return "小阵雪";
                case 15:
                    return "风雪";
                case 16:
                    return "雪";
                case 17:
                    return "冰雹";
                case 18:
                    return "雨雪";
                case 19:
                    return "灰尘";
                case 20:
                    return "多雾";
                case 21:
                    return "薄雾";
                case 22:
                    return "烟";
                case 23:
                    return "大风";
                case 24:
                    return "有风";
                case 25:
                    return "冷";
                case 26:
                    return "阴天";
                case 27:
                case 28:
                    return "多云";
                case 29:
                case 30:
                    return "晴间多云";
                case 31:
                    return "晴天";
                case 32:
                    return "暖和";
                case 33:
                case 34:
                    return "晴朗";
                case 35:
                    return "雨夹冰雹";
                case 36:
                    return "热";
                case 37:
                    return "局部雷暴";
                case 38:
                case 39:
                    return "零散雷雨";
                case 40:
                    return "零星阵雨";
                case 41:
                    return "分散阵雪";
                case 42:
                    return "零星阵雪";
                case 43:
                    return "大雪";
                case 44:
                    return "晴间多云";
                case 45:
                    return "雷阵雨";
                case 46:
                    return "阵雪";
                case 47:
                    return "局部雷阵雨";
                case 48:
                    return "暂无数据";
                default:
                    return "'暂无数据'";
            }
        }

        public String getTemperature() {
            return this.temperature;
        }

        public void setTemperature(String str) {
            this.temperature = str;
        }

        public String getSkycode() {
            int i;
            try {
                i = Integer.parseInt(this.skycode);
            } catch (NumberFormatException unused) {
                i = -1;
            }
            return getSky(i);
        }

        public int getRealSkyCode() {
            try {
                return Integer.parseInt(this.skycode);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        public void setSkycode(String str) {
            this.skycode = str;
        }

        public String getSkytext() {
            return this.skytext;
        }

        public void setSkytext(String str) {
            this.skytext = str;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public String getObservationtime() {
            return this.observationtime;
        }

        public void setObservationtime(String str) {
            this.observationtime = str;
        }

        public String getObservationpoint() {
            return this.observationpoint;
        }

        public void setObservationpoint(String str) {
            this.observationpoint = str;
        }

        public String getFeelslike() {
            return this.feelslike;
        }

        public void setFeelslike(String str) {
            this.feelslike = str;
        }

        public String getHumidity() {
            return this.humidity;
        }

        public void setHumidity(String str) {
            this.humidity = str;
        }

        public String getWinddisplay() {
            return this.winddisplay;
        }

        public void setWinddisplay(String str) {
            this.winddisplay = str;
        }

        public String getDay() {
            return this.day;
        }

        public void setDay(String str) {
            this.day = str;
        }

        public String getShortday() {
            return this.shortday;
        }

        public void setShortday(String str) {
            this.shortday = str;
        }

        public String getWindspeed() {
            return this.windspeed;
        }

        public void setWindspeed(String str) {
            this.windspeed = str;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String toString() {
            return "CurrentBean{temperature='" + this.temperature + '\'' + ", skycode='" + this.skycode + '\'' + ", skytext='" + this.skytext + '\'' + ", date='" + this.date + '\'' + ", observationtime='" + this.observationtime + '\'' + ", observationpoint='" + this.observationpoint + '\'' + ", feelslike='" + this.feelslike + '\'' + ", humidity='" + this.humidity + '\'' + ", winddisplay='" + this.winddisplay + '\'' + ", day='" + this.day + '\'' + ", shortday='" + this.shortday + '\'' + ", windspeed='" + this.windspeed + '\'' + ", imageUrl='" + this.imageUrl + '\'' + '}';
        }
    }

    public static class ForecastBean {
        private String date;
        private String day;
        private String high;
        private String low;
        private String precip;
        private String shortday;
        private String skycodeday;
        private String skytextday;

        public String getLow() {
            return this.low;
        }

        public void setLow(String str) {
            this.low = str;
        }

        public String getHigh() {
            return this.high;
        }

        public void setHigh(String str) {
            this.high = str;
        }

        public String getSkycodeday() {
            return this.skycodeday;
        }

        public void setSkycodeday(String str) {
            this.skycodeday = str;
        }

        public String getSkytextday() {
            return this.skytextday;
        }

        public void setSkytextday(String str) {
            this.skytextday = str;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }

        public String getDay() {
            return this.day;
        }

        public void setDay(String str) {
            this.day = str;
        }

        public String getShortday() {
            return this.shortday;
        }

        public void setShortday(String str) {
            this.shortday = str;
        }

        public String getPrecip() {
            return this.precip;
        }

        public void setPrecip(String str) {
            this.precip = str;
        }

        public String toString() {
            return "ForecastBean{low='" + this.low + '\'' + ", high='" + this.high + '\'' + ", skycodeday='" + this.skycodeday + '\'' + ", skytextday='" + this.skytextday + '\'' + ", date='" + this.date + '\'' + ", day='" + this.day + '\'' + ", shortday='" + this.shortday + '\'' + ", precip='" + this.precip + '\'' + '}';
        }
    }

    public String toString() {
        return "CityWeatherInfo{location=" + this.location + ", current=" + this.current + ", forecast=" + this.forecast + '}';
    }
}
