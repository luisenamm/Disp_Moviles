package com.itesm.hw2_a01633894;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DBHelper extends SQLiteOpenHelper
{

    public static final String TABLE_USERS = "users";
    public static final String USERS_ID ="id_user";
    public static final String USERS_NAME ="name";
    public static final String USERS_LAST_NAME ="last_name";
    public static final String USERS_DOB ="dob";
    public static final String USERS_ROLE ="role";
    public static final String USERS_SEMESTER ="semester";
    public static final String USERS_NICKNAME ="nickname";
    public static final String USERS_PASSWORD="password";



    public static final String DATABASE_NAME = "mydatabase.db";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+TABLE_USERS+
            "("+USERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            USERS_NAME+" TEXT NOT NULL, "+
            USERS_LAST_NAME+" TEXT NOT NULL, "+
            USERS_DOB+" TEXT NOT NULL, "+
            USERS_ROLE+" TEXT NOT NULL, "+
            USERS_SEMESTER+" INTEGER NOT NULL, "+
            USERS_NICKNAME+" TEXT NOT NULL, "+
            USERS_PASSWORD+" TEXT NOT NULL);";



    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {


        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }



    public boolean insertUser(User user)
    {
        Integer id_user;
        String name;
        String last_name;
        String dob;
        String role;
        Integer semester;
        String nickname;
        String password;

        id_user = user.id_user;
        name = user.name;
        last_name = user.last_name;
        dob = user.dob;
        role = user.role;
        semester = user.semester;
        nickname = user.nickname;
        password = user.password;

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user", id_user);
        contentValues.put("name", name);
        contentValues.put("last_name", last_name);
        contentValues.put("dob", dob);
        contentValues.put("role", role);
        contentValues.put("semester", semester);
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);


        long ins = database.insert("users",null,contentValues); //We insert
        if(ins == -1){
            return false;
        }
        else{
            return true;
        }
    }




    public boolean auth(String nickname, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where nickname=? and password=?", new String[]{nickname, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }


    public User getUserByNickname(String nickname){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("Select * from users where nickname=?", new String[]{nickname});

        result.moveToNext();
        User obtained_user = new User(
                result.getString(result.getColumnIndex(USERS_NAME)),
                result.getString(result.getColumnIndex(USERS_LAST_NAME)),
                result.getString(result.getColumnIndex(USERS_DOB)),
                result.getString(result.getColumnIndex(USERS_ROLE)),
                result.getInt(result.getColumnIndex(USERS_SEMESTER)),
                result.getString(result.getColumnIndex(USERS_NICKNAME)),
                result.getString(result.getColumnIndex(USERS_PASSWORD))
        );
        return obtained_user;

    }


}