package com.dicoding.intifada.sm5.mydb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dicoding.intifada.sm5.mydbentity.MoviesModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import timber.log.Timber;

import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.*;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_BACK_PATH;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_OVERVIEW;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_POSTER_PATH;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_RELEASE_DATE;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_TITLE;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_VOTE_AVERAGE;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.COLUMN_VOTE_COUNT;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.ID;
import static com.dicoding.intifada.sm5.mydb.DatabaseContract.MovieColumns.TABLE_NAME;



public class MoviesHelper {
    private static final String TAG = MoviesHelper.class.getSimpleName();
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static MoviesHelper INSTANCE;

    private static SQLiteDatabase database;

    public MoviesHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MoviesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MoviesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();
        if (database.isOpen())
            database.close();
        Log.d(TAG, "query : database.close();");
    }

    //CRUD
    public ArrayList<MoviesModel> query() {
        ArrayList<MoviesModel> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " DESC",
                null);
        cursor.moveToFirst();
        MoviesModel moviesModel;
        if (cursor.getCount() > 0) {
            do {
                moviesModel = new MoviesModel();
                moviesModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                moviesModel.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                moviesModel.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RELEASE_DATE)));
                moviesModel.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_VOTE_AVERAGE)));
                moviesModel.setVote_count(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VOTE_COUNT)));
                moviesModel.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OVERVIEW)));
                moviesModel.setPoster_path(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTER_PATH)));
                moviesModel.setBackdrop_path(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BACK_PATH)));

                moviesModel.setLogin(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOGIN)));
                moviesModel.setAvatar_url(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR_URL)));

                arrayList.add(moviesModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null,
                ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                COLUMN_TITLE + " ASC");
    }

    public long insertProvider(ContentValues values) {
        Timber.d("response Onclick: %s", new Gson().toJson(values));
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, ID + " = ?", new String[]{id});
    }
}
