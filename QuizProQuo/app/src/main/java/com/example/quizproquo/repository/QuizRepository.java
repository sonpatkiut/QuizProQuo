package com.example.quizproquo.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.example.quizproquo.model.QuizResponseModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class QuizRepository {
    private Context context;
    private MutableLiveData<List<QuizResponseModel>> quizList = new MutableLiveData<>();
    public QuizRepository(Context context) {
        this.context = context;
    }

    public MutableLiveData<List<QuizResponseModel>> fetchAllQuizzes(){
        JSONObject jsonObject = getQuizJSON();
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<List<QuizResponseModel>>() {
        }.getType();
        List<QuizResponseModel> quizResponses = new Gson().fromJson(jsonArray.toString(), type);
        quizList.setValue(quizResponses);
        return quizList;
    }

    public JSONObject getQuizJSON(){
        try {
            return new JSONObject("{\n" +
                    "  \"status_code\": 200,\n" +
                    "  \"message\": \"success\",\n" +
                    "  \"result\": [\n" +
                    "    {\n" +
                    "      \"_id\": \"5d75e6ad5d3d132ed86d4e34\",\n" +
                    "      \"QuizData\": {\n" +
                    "        \"Program\": \"9165885688440141369\",\n" +
                    "        \"QuizName\": \"About India\",\n" +
                    "        \"TagLine\": \"Tag line\",\n" +
                    "        \"Description\": \"This Quiz is about places of India\",\n" +
                    "        \"StartDate\": \"2019-09-09T00:00:00.000Z\",\n" +
                    "        \"EndDate\": \"2019-09-12T00:00:00.000Z\"\n" +
                    "      },\n" +
                    "      \"QuestionsData\": [\n" +
                    "        {\n" +
                    "          \"QuestionId\": 1,\n" +
                    "          \"Question\": \"what is the southern most part of indian mainland?\",\n" +
                    "          \"QuestionType\": 1,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Rameshwaram\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Kanyakumari\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Chennai\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Sriharikota\",\n" +
                    "              \"isSelected\": false\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"QuestionId\": 2,\n" +
                    "          \"Question\": \"which of the following countries have larger area than india?\",\n" +
                    "          \"QuestionType\": 2,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Canada\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Brazil\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Australia\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Pakistan\",\n" +
                    "              \"isSelected\": false\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"isPublished\": true,\n" +
                    "      \"QuizStatus\": \"Done\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"_id\": \"5d75eaef5d3d132ed86d4e38\",\n" +
                    "      \"QuizData\": {\n" +
                    "        \"Program\": \"9165885688440141369\",\n" +
                    "        \"QuizName\": \"Indian boundaries \",\n" +
                    "        \"TagLine\": \"Tag line\",\n" +
                    "        \"Description\": \"Let's see how much you know!\",\n" +
                    "        \"StartDate\": \"2019-09-09T00:00:00.000Z\",\n" +
                    "        \"EndDate\": \"2019-09-12T00:00:00.000Z\"\n" +
                    "      },\n" +
                    "      \"QuestionsData\": [\n" +
                    "        {\n" +
                    "          \"QuestionId\": 1,\n" +
                    "          \"Question\": \"which of the countries share the longest common border with india?\",\n" +
                    "          \"QuestionType\": 1,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"China\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Bangladesh\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Afghanistan\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Bhutan\",\n" +
                    "              \"isSelected\": false\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"QuestionId\": 2,\n" +
                    "          \"Question\": \"which of the countries have common border with india?\",\n" +
                    "          \"QuestionType\": 2,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Israel\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Myanmar\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"France\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Bhutan\",\n" +
                    "              \"isSelected\": true\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"isPublished\": true,\n" +
                    "      \"QuizStatus\": \"Done\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"_id\": \"5d76394c5d3d132ed86d4e42\",\n" +
                    "      \"QuizData\": {\n" +
                    "        \"Program\": \"9165885688440141369\",\n" +
                    "        \"QuizName\": \"Indian Judiciary\",\n" +
                    "        \"TagLine\": \"Sample2\",\n" +
                    "        \"Description\": \"This will get you!\",\n" +
                    "        \"StartDate\": \"2019-09-09T00:00:00.000Z\",\n" +
                    "        \"EndDate\": \"2019-09-21T00:00:00.000Z\"\n" +
                    "      },\n" +
                    "      \"QuestionsData\": [\n" +
                    "        {\n" +
                    "          \"QuestionId\": 1,\n" +
                    "          \"Question\": \"which is the oldest high court in india?\",\n" +
                    "          \"QuestionType\": 1,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Calcutta high court\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Indore high court\",\n" +
                    "              \"isSelected\": false\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"QuestionId\": 2,\n" +
                    "          \"Question\": \"which of the following languages have been conferred the status of a Classical Language?\",\n" +
                    "          \"QuestionType\": 2,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Sanskrit\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Kannada\",\n" +
                    "              \"isSelected\": true\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"isPublished\": true,\n" +
                    "      \"QuizStatus\": \"Done\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"_id\": \"5d76490d5d3d132ed86d4e48\",\n" +
                    "      \"QuizData\": {\n" +
                    "        \"Program\": \"9165885688440141369\",\n" +
                    "        \"QuizName\": \"Indian high courts\",\n" +
                    "        \"TagLine\": \"Sample3\",\n" +
                    "        \"Description\": \"About the law & order\",\n" +
                    "        \"StartDate\": \"2019-09-09T00:00:00.000Z\",\n" +
                    "        \"EndDate\": \"2019-09-25T00:00:00.000Z\"\n" +
                    "      },\n" +
                    "      \"QuestionsData\": [\n" +
                    "        {\n" +
                    "          \"QuestionId\": 1,\n" +
                    "          \"Question\": \"what is the total number of high courts in india?\",\n" +
                    "          \"QuestionType\": 1,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"45\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"36\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"25\",\n" +
                    "              \"isSelected\": true\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"QuestionId\": 2,\n" +
                    "          \"Question\": \"which of the following two states have Sanskrit as one of their official languages?\",\n" +
                    "          \"QuestionType\": 2,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Himachal pradesh\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Jharkhand\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Uttarakhand\",\n" +
                    "              \"isSelected\": true\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"isPublished\": true,\n" +
                    "      \"QuizStatus\": \"New\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"_id\": \"5d7649ae5d3d132ed86d4e4a\",\n" +
                    "      \"QuizData\": {\n" +
                    "        \"Program\": \"9165885688440141369\",\n" +
                    "        \"QuizName\": \"Languages\",\n" +
                    "        \"TagLine\": \"Tag\",\n" +
                    "        \"Description\": \"Diverse country\",\n" +
                    "        \"StartDate\": \"2019-09-09T00:00:00.000Z\",\n" +
                    "        \"EndDate\": \"2019-09-27T00:00:00.000Z\"\n" +
                    "      },\n" +
                    "      \"QuestionsData\": [\n" +
                    "        {\n" +
                    "          \"QuestionId\": 1,\n" +
                    "          \"Question\": \"what is the official language of Goa?\",\n" +
                    "          \"QuestionType\": 1,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Marathi\",\n" +
                    "              \"isSelected\": false\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Konkani\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Portugese\",\n" +
                    "              \"isSelected\": false\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"QuestionId\": 2,\n" +
                    "          \"Question\": \"which of the following states are neither an international boundary nor a coastline?\",\n" +
                    "          \"QuestionType\": 2,\n" +
                    "          \"Option\": [\n" +
                    "            {\n" +
                    "              \"value\": \"Haryana\",\n" +
                    "              \"isSelected\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "              \"value\": \"Jharkhand\",\n" +
                    "              \"isSelected\": true\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"isPublished\": true,\n" +
                    "      \"QuizStatus\": \"New\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
