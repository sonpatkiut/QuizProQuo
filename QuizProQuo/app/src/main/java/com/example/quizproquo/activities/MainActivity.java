package com.example.quizproquo.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.quizproquo.viewmodel.MainActivityViewModel;
import com.example.quizproquo.R;
import com.example.quizproquo.adapters.QuizAdapter;
import com.example.quizproquo.model.QuizResponseModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuizAdapter adapter;
    private MainActivityViewModel mainActivityViewModel;
    private List<QuizResponseModel> quizResponseModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.quizItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getQuizList().observe(this, new Observer<List<QuizResponseModel>>() {
            @Override
            public void onChanged(@Nullable List<QuizResponseModel> quizResponseModelsLiveData) {
                quizResponseModelList.clear();
                quizResponseModelList.addAll(quizResponseModelsLiveData);
                showOnRecyclerView();
            }
        });
    }

    private void showOnRecyclerView() {
        adapter = new QuizAdapter(this, quizResponseModelList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
