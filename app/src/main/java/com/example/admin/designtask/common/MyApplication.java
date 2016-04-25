package com.example.admin.designtask.common;
import android.app.Application;
import android.content.Context;

import com.example.admin.designtask.data.SqLiteDbHelper;

@SuppressWarnings("ALL")
public class MyApplication extends Application {
    private static MyApplication ourInstance = null;
    private SqLiteDbHelper sqlDb;

    public MyApplication() {

    }

    public synchronized static MyApplication getInstance() {

        if (ourInstance == null) {
            ourInstance = new MyApplication();
        }
        return ourInstance;
    }

    public void initContext(Context context) {
        Context mContext = context;
    }

    public SqLiteDbHelper getSqlDb() {
        return sqlDb;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;

        init();
    }

    private void init() {
        sqlDb = new SqLiteDbHelper(this);
        sqlDb.insertAuthors();
        sqlDb.insertPosts();
        sqlDb.insertComments();
        sqlDb.getAllPosts();
    }
}
