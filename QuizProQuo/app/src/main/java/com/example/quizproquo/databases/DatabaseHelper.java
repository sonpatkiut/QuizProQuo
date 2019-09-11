package com.example.quizproquo.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper databaseHelper = null;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "quiz.db";

    public static final String TABLE_QUIZ = "QuizAttempted";

    public static final String ID = "Id";

    public static final String QUIZ_DATA = "quiz_data";

    public static final String TOTAL = "total";

    public static final String CORRECT = "correct";

    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context, SQLiteDatabase.CursorFactory factory) {
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(context, factory);

        return databaseHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_QUIZ + " (" +
                ID + " VARCHAR PRIMARY KEY, " +
                TOTAL + " INTEGER, " +
                CORRECT + " INTEGER, " +
                QUIZ_DATA + " VARCHAR " +
                "); ";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        onCreate(sqLiteDatabase);
    }

    public boolean insertDatabase(String quizData, int total, int correct){

        String id = "";

        try {
            JSONObject jsonObject = new JSONObject(quizData);
            id = jsonObject.getString("_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(QUIZ_DATA, quizData);
        contentValues.put(TOTAL, total);
        contentValues.put(CORRECT, correct);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long rowInserted = sqLiteDatabase.insert(TABLE_QUIZ, null, contentValues);
        sqLiteDatabase.close();

        if (rowInserted != -1)
            return true;
        else
            return false;
    }

    public JSONObject getQuizData(String id){
        String quiz_data = "";
        int total = 0;
        int correct = 0;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_QUIZ + " WHERE "+ ID + " = '" + id + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
            try{
                quiz_data = cursor.getString(cursor.getColumnIndex(QUIZ_DATA));
                total = cursor.getInt(cursor.getColumnIndex(TOTAL));
                correct = cursor.getInt(cursor.getColumnIndex(CORRECT));
            }catch (Exception e){
                return null;
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(quiz_data);
            jsonObject.put("TOTAL", total);
            jsonObject.put("CORRECT", correct);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean hasQuizData(String id){
        String quiz_data = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT " + QUIZ_DATA + " FROM "+ TABLE_QUIZ + " WHERE "+ ID + " = '" + id + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
            try{
                quiz_data = cursor.getString(cursor.getColumnIndex(QUIZ_DATA));
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }
}
