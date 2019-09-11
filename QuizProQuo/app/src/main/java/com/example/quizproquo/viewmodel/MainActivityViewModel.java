package com.example.quizproquo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.quizproquo.model.QuizResponseModel;
import com.example.quizproquo.repository.QuizRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private QuizRepository quizRepository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        quizRepository = new QuizRepository(application.getApplicationContext());
    }

    public LiveData<List<QuizResponseModel>> getQuizList(){
        return quizRepository.fetchAllQuizzes();
    }
}
