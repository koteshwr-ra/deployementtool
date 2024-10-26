package com.ciot.realm.db.semantic;

import java.util.List;

public class XunFeiAnsBean {
    public AnswerBean answer;
    public int no_nlu_result;
    public String operation;
    public int rc;
    public String service;
    public String serviceCategory;
    public String serviceName;
    public String serviceType;
    public String sid;
    public String text;
    public String uuid;
    public List<VoiceAnswerBean> voice_answer;

    public static class AnswerBean {
        public String answerType;
        public String emotion;
        public QuestionBean question;
        public String text;
        public String topicID;
        public String type;

        public static class QuestionBean {
            public String question;
            public String question_ws;
        }
    }

    public static class VoiceAnswerBean {
        public String content;
        public String type;
    }
}
