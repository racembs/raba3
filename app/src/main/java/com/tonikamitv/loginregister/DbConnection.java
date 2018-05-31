package com.tonikamitv.loginregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by RBS on 30-Jan-18.
 */

public class DbConnection extends SQLiteOpenHelper {
    public static final String DbName="rabaa";
    public static final int version=1;

    public DbConnection(Context context) {
        super(context, DbName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table IF NOT EXISTS user(id INTEGER primary key,username TEXT,password TEXT,mail TEXT,university TEXT,remember TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("Drop table if EXISTS user");
        onCreate(db);
    }

    public void InsertRowUser(int id,String username,String password,String mail,String university,String remember){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("username",username);
        contentValues.put("mail",mail);
        contentValues.put("password",password);
        contentValues.put("university",university);
        contentValues.put("remember",remember);
        db.insert("user",null,contentValues);

    }
    public ArrayList getAll(){
        User user=new User();
        ArrayList<User> arrayList=new ArrayList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from user",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            user.setId(res.getInt(res.getColumnIndex("id")));
            user.setUsername(res.getString(res.getColumnIndex("username")));
            user.setPassword(res.getString(res.getColumnIndex("password")));
            user.setMail(res.getString(res.getColumnIndex("mail")));
            user.setUniversity(res.getString(res.getColumnIndex("university")));
            user.setRemember(res.getString(res.getColumnIndex("remember")));
            arrayList.add(user);
            res.moveToNext();
        }
        return arrayList;
    }
    public void truncate(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from user");
    }
    public void updateuser(String id,String username,String password,String mail,String university){

        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("UPDATE user set username = '"+username+"' , password = '"+password+"' , mail = '"+mail+"' , university = '"+university+"' where id="+id);

    }
}
