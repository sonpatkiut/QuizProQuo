package com.example.quizproquo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuizResponseModel implements Serializable {

    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("QuizData")
    private QuizData quizData;

    @Expose
    @SerializedName("QuestionsData")
    private List<QuestionsData> questionsData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuizData getQuizData() {
        return quizData;
    }

    public void setQuizData(QuizData quizData) {
        this.quizData = quizData;
    }

    public List<QuestionsData> getQuestionsData() {
        return questionsData;
    }

    public void setQuestionsData(List<QuestionsData> questionsData) {
        this.questionsData = questionsData;
    }

    public class QuizData implements Serializable{
        @Expose
        @SerializedName("QuizName")
        private String quizName;

        @Expose
        @SerializedName("Description")
        private String description;

        public String getQuizName() {
            return quizName;
        }

        public void setQuizName(String quizName) {
            this.quizName = quizName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public class QuestionsData implements Serializable{
        @Expose
        @SerializedName("QuestionId")
        private String questionId;

        @Expose
        @SerializedName("Question")
        private String question;

        @Expose
        @SerializedName("QuestionType")
        private int questionType;

        @Expose
        @SerializedName("Option")
        private List<Options> options;

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getQuestionType() {
            return questionType;
        }

        public void setQuestionType(int questionType) {
            this.questionType = questionType;
        }

        public List<Options> getOptions() {
            return options;
        }

        public void setOptions(List<Options> options) {
            this.options = options;
        }
    }

    public class Options implements Serializable{
        @Expose
        @SerializedName("value")
        private String value;

        @Expose
        @SerializedName("isSelected")
        private boolean isSelected;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }
}
