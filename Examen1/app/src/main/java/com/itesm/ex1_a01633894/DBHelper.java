package com.itesm.ex1_a01633894;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_QUIZ = "quiz";
    public static final String QUESTION_ID ="id_question";
    public static final String QUESTION ="question";
    public static final String ANS_1 ="ans1";
    public static final String ANS_2 ="ans2";
    public static final String ANS_3 ="ans3";
    public static final String ANS_4 ="ans4";
    public static final String CORRECT ="correct";

    public static final String DATABASE_NAME = "mydatabase.db";

    private static final String CREATE_TABLE_QUIZ= "CREATE TABLE "+TABLE_QUIZ+
            "("+QUESTION_ID+"INTEGER PRIMARY KEY AUTOINCREMENT, "+
            QUESTION+"TEXT NOT NULL, "+
            ANS_1+"TEXT NOT NULL, "+
            ANS_2+"TEXT NOT NULL, "+
            ANS_3+"TEXT NOT NULL, "+
            ANS_4+"TEXT NOT NULL, "+
            CORRECT+"TEXT NOT NULL);";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }



    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_QUIZ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_QUIZ);
        onCreate(db);
    }


    public boolean insertQuestion(Question q) {
        Integer id_question;
        String question;
        String a1;
        String a2;
        String a3;
        String a4;
        String correcta;

        id_question=q.id_question;
        question=q.question;
        a1=q.ans_1;
        a2=q.ans_2;
        a3=q.ans_3;
        a4=q.ans_4;
        correcta=q.correct;



        SQLiteDatabase database = this.getWritableDatabase(); // We obtain an instance of our database
        ContentValues contentValues = new ContentValues();  // We insert the data using one variable of type ContentValues

        contentValues.put("id_pregunta", id_question);
        contentValues.put("pquestion",question);
        contentValues.put("a1", a1);
        contentValues.put("a2", a2);
        contentValues.put("a3", a3);
        contentValues.put("a4", a4);
        contentValues.put("correcta", correcta);


        long ins = database.insert("pregunta", null, contentValues); //We insert
        if (ins == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Question getQuestionById(int i){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("Select * from users where nickname=?", new String[]{});

        result.moveToNext();
        Question q = new Question(
                result.getInt(result.getColumnIndex(QUESTION_ID)),
                result.getString(result.getColumnIndex(QUESTION)),
                result.getString(result.getColumnIndex(ANS_1)),
                result.getString(result.getColumnIndex(ANS_2)),
                result.getString(result.getColumnIndex(ANS_3)),
                result.getString(result.getColumnIndex(ANS_4)),
                result.getString(result.getColumnIndex(CORRECT))

        );
        ArrayList array = new ArrayList();
        array.add(q.getId_question());
        array.add(q.getQuestion());
        array.add(q.getAns_1());
        array.add(q.getAns_2());
        array.add(q.getAns_3());
        array.add(q.getAns_4());
        array.add(q.getCorrect());
        return q;

    }


}

