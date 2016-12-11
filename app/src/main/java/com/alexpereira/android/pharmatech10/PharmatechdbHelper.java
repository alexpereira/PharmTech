package com.alexpereira.android.pharmatech10;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IDIR on 12/11/2016.
 */

public class PharmatechdbHelper extends SQLiteOpenHelper {
//private static final int DATABASE_VERSION=1;

    // change my com.example.idir.pharma_tech with ur package name !!!
    private static final String DATA_BASE_PATH="/data/data/com.alexpereira.android.pharmatech10/";
    private static final String DATABASE_NAME="PharmaTechDB.db";
    private Context mContext ;
    private SQLiteDatabase mSQLiteDatabase;
    public PharmatechdbHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        this.mContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // TEST IF THE DATABASE EXISTS
    private boolean test_DATA_BASE(){
        SQLiteDatabase test_DB=null;
        try {
            String db_path=DATA_BASE_PATH+DATABASE_NAME;
            test_DB= SQLiteDatabase.openDatabase(db_path,null, SQLiteDatabase.OPEN_READWRITE);

        }catch (SQLiteException e){

        }

        if (test_DB!=null) test_DB.close();
        return test_DB!=null? true:false;

    }
    // Copy the data base to the memory
    private void copy_DB() throws IOException {
        InputStream mInputStream=mContext.getAssets().open(DATABASE_NAME);
        String mSourceFile=DATA_BASE_PATH+DATABASE_NAME;
        OutputStream mOutPutStream=new FileOutputStream(mSourceFile);
        byte[] mBuffer =new byte[1024];
        int length;
        while ((length=mInputStream.read(mBuffer))>0){
            mOutPutStream.write(mBuffer,0,length);
        }
        mOutPutStream.flush();
        mOutPutStream.close();
        mInputStream.close();
    }
    public void db_OPEN() throws SQLiteException {
        String db_path=DATA_BASE_PATH+DATABASE_NAME;
        mSQLiteDatabase= SQLiteDatabase.openDatabase(db_path,null, SQLiteDatabase.OPEN_READWRITE);

    }
    public void ExeSQLDATA (String sql) throws SQLiteException {
        mSQLiteDatabase.execSQL(sql);
    }

    public Cursor mQuery(String query) throws SQLiteException {
        return mSQLiteDatabase.rawQuery(query,null);
    }

    @Override
    public synchronized void close(){
        if (mSQLiteDatabase!=null)
            mSQLiteDatabase.close();
        super.close();

    }
    public void checkAndCopyDB(){
        boolean dbExist=test_DATA_BASE();
        if (dbExist){
            Log.d("TAG","Database Exists Already!");
        }else {
            this.getReadableDatabase();
            try {
                copy_DB();
            }catch(IOException e) {
                Log.d("TAG","ERROR copying database");
            }


        }
    }
}
