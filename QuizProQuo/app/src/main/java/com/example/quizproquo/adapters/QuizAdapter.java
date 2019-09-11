package com.example.quizproquo.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizproquo.activities.QuizActivity;
import com.example.quizproquo.R;
import com.example.quizproquo.databases.DatabaseHelper;
import com.example.quizproquo.model.QuizResponseModel;
import com.example.quizproquo.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder>{

    private Context context;
    private List<QuizResponseModel> list;
    private DatabaseHelper helper;

    public QuizAdapter(Context context, List<QuizResponseModel> list) {
        this.list = list;
        this.context = context;
        helper = DatabaseHelper.getInstance(this.context, null);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quiz_list_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.quizName.setText(list.get(i).getQuizData().getQuizName());
        if(!helper.hasQuizData(list.get(i).getId())){
            myViewHolder.imageView.setVisibility(View.GONE);
        }
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject quizData = helper.getQuizData(list.get(i).getId());

                if(quizData!=null && quizData.has("_id")){
                    //already attempted
                    try{
                        new AlertDialog.Builder(context)
                                .setTitle("Already attempted!!")
                                .setMessage("Total questions : " + quizData.getInt("TOTAL") +
                                        "\nCorrectly answered : " + quizData.getInt("CORRECT"))
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                                .setCancelable(false)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else{
                    //new Quiz
                    Intent intent = new Intent(context, QuizActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.QUIZ_DATA, list.get(i));
                    bundle.putBoolean(Constants.IS_ATTEMPTED, false);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView quizName;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quizName = itemView.findViewById(R.id.quizName);
            imageView = itemView.findViewById(R.id.done);
        }
    }
}
