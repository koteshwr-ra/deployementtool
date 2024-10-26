package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class RegisterControlBean implements Serializable {
    private static final long serialVersionUID = 8609475127631660139L;
    @SerializedName("nowtime")
    @Expose(deserialize = true, serialize = true)
    private Date Nowtime;
    @SerializedName("placeid")
    @Expose(deserialize = true, serialize = true)
    private String Placeid;
    @SerializedName("robotid")
    @Expose(deserialize = true, serialize = true)
    private String RobotID;
    @SerializedName("robotip")
    @Expose(deserialize = true, serialize = true)
    private String RobotIP;
    @SerializedName("robotinfo")
    @Expose(deserialize = true, serialize = true)
    private RobotInfo RobotInfo;
    @SerializedName("robotmodel")
    @Expose(deserialize = true, serialize = true)
    private String RobotModel;
    @SerializedName("robottype")
    @Expose(deserialize = true, serialize = true)
    private int RobotType;
    @SerializedName("version")
    @Expose(deserialize = true, serialize = true)
    private String Version;

    public String getPlaceid() {
        return this.Placeid;
    }

    public void setPlaceid(String str) {
        this.Placeid = str;
    }

    public int getRobotType() {
        return this.RobotType;
    }

    public void setRobotType(int i) {
        this.RobotType = i;
    }

    public String getRobotIP() {
        return this.RobotIP;
    }

    public void setRobotIP(String str) {
        this.RobotIP = str;
    }

    public String getRobotID() {
        return this.RobotID;
    }

    public void setRobotID(String str) {
        this.RobotID = str;
    }

    public Date getNowtime() {
        return this.Nowtime;
    }

    public void setNowtime(Date date) {
        this.Nowtime = date;
    }

    public String getVersion() {
        return this.Version;
    }

    public void setVersion(String str) {
        this.Version = str;
    }

    public String getRobotModel() {
        return this.RobotModel;
    }

    public void setRobotModel(String str) {
        this.RobotModel = str;
    }

    public RobotInfo getRobotInfo() {
        return this.RobotInfo;
    }

    public void setRobotInfo(RobotInfo robotInfo) {
        this.RobotInfo = robotInfo;
    }

    public static class RobotInfo {
        @SerializedName("bat")
        @Expose(deserialize = true, serialize = true)
        private Bat Bat;
        @SerializedName("light")
        @Expose(deserialize = true, serialize = true)
        private Light Light;
        @SerializedName("screen")
        @Expose(deserialize = true, serialize = true)
        private int Screen;
        @SerializedName("sensor")
        @Expose(deserialize = true, serialize = true)
        private Sensor Sensor;
        @SerializedName("video")
        @Expose(deserialize = true, serialize = true)
        private Video Video;
        @SerializedName("voice")
        @Expose(deserialize = true, serialize = true)
        private int Voice;

        public Video getVideo() {
            return this.Video;
        }

        public void setVideo(Video video) {
            this.Video = video;
        }

        public Sensor getSensor() {
            return this.Sensor;
        }

        public void setSensor(Sensor sensor) {
            this.Sensor = sensor;
        }

        public Bat getBat() {
            return this.Bat;
        }

        public void setBat(Bat bat) {
            this.Bat = bat;
        }

        public int getScreen() {
            return this.Screen;
        }

        public void setScreen(int i) {
            this.Screen = i;
        }

        public int getVoice() {
            return this.Voice;
        }

        public void setVoice(int i) {
            this.Voice = i;
        }

        public Light getLight() {
            return this.Light;
        }

        public void setLight(Light light) {
            this.Light = light;
        }

        public static class Video {
            @SerializedName("ip")
            @Expose(deserialize = true, serialize = true)
            private String IP;
            @SerializedName("mainid")
            @Expose(deserialize = true, serialize = true)
            private int MainId;
            @SerializedName("model")
            @Expose(deserialize = true, serialize = true)
            private int Model;
            @SerializedName("name")
            @Expose(deserialize = true, serialize = true)
            private String Name;
            @SerializedName("num")
            @Expose(deserialize = true, serialize = true)
            private int Num;
            @SerializedName("pass")
            @Expose(deserialize = true, serialize = true)
            private String Pass;
            @SerializedName("port")
            @Expose(deserialize = true, serialize = true)
            private int Port;
            @SerializedName("protocol")
            @Expose(deserialize = true, serialize = true)
            private int Protocol;
            @SerializedName("resolution")
            @Expose(deserialize = true, serialize = true)
            private int Resolution;

            public int getModel() {
                return this.Model;
            }

            public void setModel(int i) {
                this.Model = i;
            }

            public String getIP() {
                return this.IP;
            }

            public void setIP(String str) {
                this.IP = str;
            }

            public int getPort() {
                return this.Port;
            }

            public void setPort(int i) {
                this.Port = i;
            }

            public String getName() {
                return this.Name;
            }

            public void setName(String str) {
                this.Name = str;
            }

            public String getPass() {
                return this.Pass;
            }

            public void setPass(String str) {
                this.Pass = str;
            }

            public int getProtocol() {
                return this.Protocol;
            }

            public void setProtocol(int i) {
                this.Protocol = i;
            }

            public int getNum() {
                return this.Num;
            }

            public void setNum(int i) {
                this.Num = i;
            }

            public int getMainId() {
                return this.MainId;
            }

            public void setMainId(int i) {
                this.MainId = i;
            }

            public int getResolution() {
                return this.Resolution;
            }

            public void setResolution(int i) {
                this.Resolution = i;
            }

            public String toString() {
                return "Video{Model=" + this.Model + ", IP='" + this.IP + '\'' + ", Port=" + this.Port + ", Name=" + this.Name + ", Pass=" + this.Pass + ", Protocol=" + this.Protocol + ", Num=" + this.Num + ", MainId=" + this.MainId + ", Resolution=" + this.Resolution + '}';
            }
        }

        public static class Sensor {
            @SerializedName("laser")
            @Expose(deserialize = true, serialize = true)
            private int Laser;
            @SerializedName("shock")
            @Expose(deserialize = true, serialize = true)
            private int Shock;
            @SerializedName("smoke")
            @Expose(deserialize = true, serialize = true)
            private int Smoke;
            @SerializedName("sonic")
            @Expose(deserialize = true, serialize = true)
            private int Sonic;
            @SerializedName("temp")
            @Expose(deserialize = true, serialize = true)
            private int Temp;

            public int getLaser() {
                return this.Laser;
            }

            public void setLaser(int i) {
                this.Laser = i;
            }

            public int getSonic() {
                return this.Sonic;
            }

            public void setSonic(int i) {
                this.Sonic = i;
            }

            public int getSmoke() {
                return this.Smoke;
            }

            public void setSmoke(int i) {
                this.Smoke = i;
            }

            public int getTemp() {
                return this.Temp;
            }

            public void setTemp(int i) {
                this.Temp = i;
            }

            public int getShock() {
                return this.Shock;
            }

            public void setShock(int i) {
                this.Shock = i;
            }

            public String toString() {
                return "Sensor{Laser=" + this.Laser + ", Sonic=" + this.Sonic + ", Smoke=" + this.Smoke + ", Temp=" + this.Temp + ", Shock=" + this.Shock + '}';
            }
        }

        public static class Bat {
            @SerializedName("capacity")
            @Expose(deserialize = true, serialize = true)
            private int capacity;
            @SerializedName("model")
            @Expose(deserialize = true, serialize = true)
            private int model;

            public int getModel() {
                return this.model;
            }

            public void setModel(int i) {
                this.model = i;
            }

            public int getCapacity() {
                return this.capacity;
            }

            public void setCapacity(int i) {
                this.capacity = i;
            }

            public String toString() {
                return "Bat{model=" + this.model + ", capacity=" + this.capacity + '}';
            }
        }

        public static class Light {
            @SerializedName("alarm")
            @Expose(deserialize = true, serialize = true)
            private int Alarm;
            @SerializedName("back")
            @Expose(deserialize = true, serialize = true)
            private int Back;
            @SerializedName("expression")
            @Expose(deserialize = true, serialize = true)
            private int Expression;
            @SerializedName("front")
            @Expose(deserialize = true, serialize = true)
            private int Front;

            public int getFront() {
                return this.Front;
            }

            public void setFront(int i) {
                this.Front = i;
            }

            public int getBack() {
                return this.Back;
            }

            public void setBack(int i) {
                this.Back = i;
            }

            public int getAlarm() {
                return this.Alarm;
            }

            public void setAlarm(int i) {
                this.Alarm = i;
            }

            public int getExpression() {
                return this.Expression;
            }

            public void setExpression(int i) {
                this.Expression = i;
            }

            public String toString() {
                return "Light{Front=" + this.Front + ", Back=" + this.Back + ", Alarm=" + this.Alarm + ", Expression=" + this.Expression + '}';
            }
        }

        public String toString() {
            return "RobotInfo{Video=" + this.Video + ", Sensor=" + this.Sensor + ", Bat=" + this.Bat + ", Screen=" + this.Screen + ", Voice=" + this.Voice + ", Light=" + this.Light + '}';
        }
    }

    public String toString() {
        return "RegisterControlBean{RobotType=" + this.RobotType + ", RobotIP='" + this.RobotIP + '\'' + ", RobotID='" + this.RobotID + '\'' + ", Nowtime=" + this.Nowtime + ", Version='" + this.Version + '\'' + ", RobotModel='" + this.RobotModel + '\'' + ", RobotInfo=" + this.RobotInfo + ", placeid='" + this.Placeid + '\'' + '}';
    }
}
