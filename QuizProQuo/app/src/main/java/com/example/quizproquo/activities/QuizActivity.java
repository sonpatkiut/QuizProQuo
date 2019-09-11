package com.example.quizproquo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizproquo.R;
import com.example.quizproquo.activities.MainActivity;
import com.example.quizproquo.databases.DatabaseHelper;
import com.example.quizproquo.model.QuizResponseModel;
import com.example.quizproquo.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    QuizResponseModel quizResponseModel, answerModel;
    Toolbar toolbar;
    Button submit;
    List<LinearLayout> listViews;
    LinearLayout quizLayout;
    TextView description;

    List<QuizResponseModel.QuestionsData> questions = new ArrayList<>();

    ProgressDialog progress;

    DatabaseHelper helper;

    private static boolean isAnyOptionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        helper = DatabaseHelper.getInstance(this, null);

        toolbar = findViewById(R.id.toolbar);
        submit = findViewById(R.id.submit);
        quizLayout = findViewById(R.id.quizLayout);
        description = findViewById(R.id.description);

        submit.setOnClickListener(this);

        setUpToolbar();
        listViews = new ArrayList<>();

        quizResponseModel = (QuizResponseModel) getIntent().getSerializableExtra(Constants.QUIZ_DATA);
        answerModel = (QuizResponseModel) getIntent().getSerializableExtra(Constants.QUIZ_DATA);

        questions.clear();
        questions.addAll(quizResponseModel.getQuestionsData());
        updateUI(questions);
//        clearAnswers(questions);
        disableButton();
        description.setText(quizResponseModel.getQuizData().getDescription());
    }

    public void updateUI(List<QuizResponseModel.QuestionsData> responses) {
        if (responses != null && responses.size() > 0) {
            for (QuizResponseModel.QuestionsData s : responses) {
                if (s.getQuestionType() == 1) {
                    radioTypeQuestion(s);
                } else if (s.getQuestionType() == 2) {
                    checkTypeQuestion(s);
                }
            }
            isAnyOptionSelected = false;
        }
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void enableButton() {
        submit.setEnabled(true);
        submit.setAlpha(1f);
    }

    private void disableButton() {
        submit.setEnabled(false);
        submit.setAlpha(0.2f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void radioTypeQuestion(QuizResponseModel.QuestionsData s) {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 15, 25, 25);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView question = new TextView(this);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 15, 25, 10);
        question.setLayoutParams(params);
        question.setText(s.getQuestion());
        question.setTextColor(getResources().getColor(R.color.black));
        question.setTextSize(15);
        linearLayout.addView(question);

        quizLayout.addView(linearLayout);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setId(listViews.size() + 100);
        radioGroup.setTag(listViews.size() + 100);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 10, 20, 5);
        radioGroup.setLayoutParams(params);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        List<QuizResponseModel.Options> options = s.getOptions();
        RadioButton[] rb = new RadioButton[options.size()];
        for (int i = 0; i < options.size(); i++) {
            rb[i] = new RadioButton(this);
            rb[i].setLayoutParams(params);
            rb[i].setText(options.get(i).getValue());
            rb[i].setId(i + 1);
            radioGroup.addView(rb[i]);
            rb[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {
                        enableButton();
                    } else {
                        if (checkIfAnyOptionSelected()) {
                            enableButton();
                        } else {
                            disableButton();
                        }
                    }
                }
            });
        }
        linearLayout.addView(radioGroup);
        listViews.add(linearLayout);
    }

    private void checkTypeQuestion(QuizResponseModel.QuestionsData s) {
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 15, 25, 25);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView question = new TextView(this);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(25, 15, 25, 10);
        question.setLayoutParams(params);
        question.setText(s.getQuestion());
        question.setTextColor(getResources().getColor(R.color.black));
        question.setTextSize(15);
        linearLayout.addView(question);

        quizLayout.addView(linearLayout);
        //add checkbox code here
        List<QuizResponseModel.Options> options = s.getOptions();
        for (int i = 0; i < options.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(options.get(i).getValue());
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 10, 20, 5);
            checkBox.setLayoutParams(params);
            linearLayout.addView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {
                        enableButton();
                    } else {
                        if (checkIfAnyOptionSelected()) {
                            enableButton();
                        } else {
                            disableButton();
                        }
                    }
                }
            });
        }
        listViews.add(linearLayout);
    }

    private boolean checkIfAnyOptionSelected() {
        for (int i = 0; i < listViews.size(); i++) {
            LinearLayout ll = listViews.get(i);
            if (ll.findViewWithTag(i + 100) instanceof RadioGroup) {
                //Radio button type
                RadioGroup rg = ll.findViewWithTag(i + 100); //position = i = 0, id = i+1 = 1
                int id = rg.getCheckedRadioButtonId();
                if (id > 0) {
                    isAnyOptionSelected = true;
                    return true;
                }
            } else if (ll.findViewWithTag(i + 100) instanceof LinearLayout) {
                //Text Type

            } else {
                //checkbox type
                for (int j = 0; j < ll.getChildCount(); j++) {
                    View child = ll.getChildAt(j);
                    if (child instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) child;
                        if (checkBox.isChecked()) {
                            isAnyOptionSelected = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void navigateToNextActivity(Object object, int total, int correct) {
        boolean status = (boolean) object;

        LinearLayout success = findViewById(R.id.success);
        ImageView icon = findViewById(R.id.icon);
        TextView congrats = findViewById(R.id.congratsText);
        TextView winnerMsg = findViewById(R.id.winnerMsg);
        TextView answerInfo = findViewById(R.id.answerInfo);
        Button back = findViewById(R.id.backtoquiz);

        success.setVisibility(View.VISIBLE);
        if (status) {
            icon.setBackgroundResource(R.drawable.surveyrewards);
            congrats.setVisibility(View.VISIBLE);
            winnerMsg.setText("You've won a reward");
        } else {
            icon.setBackgroundResource(R.drawable.loserimg);
            congrats.setVisibility(View.GONE);
            winnerMsg.setText("Better luck next time");
        }
        answerInfo.setText("you have answered " + correct + "/" + total + " correct answers");
        back.setOnClickListener(this);
    }

    public void postQuiz() {
        int total = listViews.size();
        int correct = 0;
        for (int i = 0; i < listViews.size(); i++) {
            LinearLayout ll = listViews.get(i);
            if (ll.findViewWithTag(i + 100) instanceof RadioGroup) {
                //Radio button type
                RadioGroup rg = ll.findViewWithTag(i + 100); //position = i = 0, id = i+1 = 1
                int id = rg.getCheckedRadioButtonId();
                if (id > 0) {
                    RadioButton rb = findViewById(id);
                    //Toast.makeText(this, "" + id + " " + rb.getText(), Toast.LENGTH_SHORT).show();

                    //set All others false
                    List<QuizResponseModel.Options> options = quizResponseModel.getQuestionsData().get(i).getOptions();

                    //set selected as true
                    if(quizResponseModel.getQuestionsData().get(i).getOptions().get(id - 1).isSelected()){
                        correct++;
                    }
                    isAnyOptionSelected = true;
                }
            } else if (ll.findViewWithTag(i + 100) instanceof LinearLayout) {
                //Text Type

            } else {
                //checkbox type
                boolean match = true;
                int checkIndex = 0;
                for (int j = 0; j < ll.getChildCount(); j++) {
                    View child = ll.getChildAt(j);
                    if (child instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) child;
                        List<QuizResponseModel.Options> options = quizResponseModel.getQuestionsData().get(i).getOptions();
                        if (checkBox.isChecked()) {
                            //Toast.makeText(this, checkBox.getText(), Toast.LENGTH_SHORT).show();
                            if(!options.get(checkIndex).isSelected()){
                                match = false;
                            }
                            isAnyOptionSelected = true;
                        } else {
                            if(options.get(checkIndex).isSelected()){
                                match = false;
                            }
                        }
                        quizResponseModel.getQuestionsData().get(i).setOptions(options);
                        checkIndex++;
                    }
                }
                if(match){
                    correct++;
                }
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(quizResponseModel);
        helper.insertDatabase(json, total, correct);
        try {
            JSONObject reqObj = new JSONObject(json);
            if(correct==total){
                navigateToNextActivity(true, total, correct);
            }else{
                navigateToNextActivity(false, total, correct);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                postQuiz();
                break;

            case R.id.backtoquiz:
                onBackPressed();
                break;
        }
    }
}
