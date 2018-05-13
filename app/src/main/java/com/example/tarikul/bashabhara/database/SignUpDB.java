package com.example.tarikul.bashabhara.database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.tarikul.bashabhara.activity.LoginActivity;

import java.util.ArrayList;

/**
 * Created by Tarikul on 4/26/2018.
 */

public class SignUpDB {
    // db version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bashabhara";
    private static final String DATABASE_TABLE_SIGN_UP = "table_sign_up";
    private SignUpDB.DBHelper dbhelper;
    private final Context context;
    private SQLiteDatabase database;

    public static final String KEY_ROWID = "id";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USER_NAME= "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_MOBILE_NUMBER = "mobilenumber";


    private static class DBHelper extends SQLiteOpenHelper {

        @SuppressLint("NewApi")
        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // create table to store msgs
            db.execSQL(" CREATE TABLE " + DATABASE_TABLE_SIGN_UP + " ("
                    + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_EMAIL + " TEXT, "
                    + KEY_USER_NAME + " TEXT, "
                    + KEY_PASSWORD + " TEXT, "
                    + KEY_MOBILE_NUMBER + " TEXT );");


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_SIGN_UP);


            onCreate(db);
        }

    }
    // constructor
    public SignUpDB(Context c) {
        context = c;
    }

    // open db
    public SignUpDB open() {
        dbhelper = new  DBHelper(context);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    // close db
    public void close() {
        dbhelper.close();
    }


    public long saveInfo(String email,String userName,String password,String mobileNumber

    ){
        ContentValues cv = new ContentValues();
        cv.put(KEY_EMAIL, email);
        cv.put(KEY_USER_NAME, userName);
        cv.put(KEY_PASSWORD, password);
        cv.put(KEY_MOBILE_NUMBER,  mobileNumber);

        long dbInsert = database.insert(DATABASE_TABLE_SIGN_UP, null, cv);

        if(dbInsert != -1) {

            Toast.makeText(context, "New row added , row id: " + dbInsert, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            ActivityCompat.finishAffinity((Activity) context);
        }else{
            Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
        }

        return dbInsert;

    }
    public boolean checkUser(String userName,String password){
        Cursor cursor = database.query(DATABASE_TABLE_SIGN_UP, null, " username=? and password=?",
                new String[] { userName,password }, null, null, null);

        if (cursor != null) {
            if(cursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }

//    public ArrayList getInputData(){
//
//        ArrayList<HelloCmpOneModel> helloCmpOneModelArrayList = new ArrayList<>();
//        String select_query = "SELECT * FROM " + DATABASE_TABLE_INFO ;
//
//        Cursor cursor = database.rawQuery(select_query,null);
//        int rows = cursor.getCount();
//        int columns = cursor.getColumnCount();
//
//        int iUniqueID = cursor.getColumnIndex(KEY_ROWID);
//        int iCriminalInfo = cursor.getColumnIndex(KEY_INFO_CRIMINAL);
//        int iDivision = cursor.getColumnIndex(KEY_DIVISION);
//        int iDistrict = cursor.getColumnIndex(KEY_DISTRICT);
//        int iThana = cursor.getColumnIndex(KEY_THANA);
//        int iCameraImgPath = cursor.getColumnIndex(KEY_CAMERA_IMAGE);
//        int iAudioPath = cursor.getColumnIndex(KEY_AUDIO);
//        int iGalleryImgPath = cursor.getColumnIndex(KEY_GALLARY_IMAGE);
//
//
//
//
//        for (cursor.moveToLast(); ! cursor.isBeforeFirst(); cursor.moveToPrevious()) {
//            //    for (cursor.moveToFirst(); ! cursor.isAfterLast(); cursor.moveToNext()) {
//            String criminalInfo = cursor.getString(iCriminalInfo);
//            String division = cursor.getString(iDivision);
//            String district = cursor.getString(iDistrict);
//            String thana = cursor.getString(iThana);
//            String cameraImgP = cursor.getString(iCameraImgPath);
//            String audioP = cursor.getString(iAudioPath);
//            String galleryImgP = cursor.getString(iGalleryImgPath);
//            int    uniqueId = cursor.getInt(iUniqueID);
//
//
//            helloCmpOneModelArrayList.add(new HelloCmpOneModel(criminalInfo,division,district,thana,cameraImgP,audioP,galleryImgP,String.valueOf(uniqueId)));
//
//        }
//        cursor.close();
//        return helloCmpOneModelArrayList;
//    }

}
