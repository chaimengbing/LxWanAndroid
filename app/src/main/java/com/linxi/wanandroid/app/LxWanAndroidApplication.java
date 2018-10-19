package com.linxi.wanandroid.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.leakcanary.RefWatcher;

public class LxWanAndroidApplication extends Application {

    private static LxWanAndroidApplication instance;

    private DaoSession mDaoSession;
    private RefWatcher refWatcher;


    public static LxWanAndroidApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initGreenDao();
    }


    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


    public static RefWatcher getRefWatcher(Context context) {
        LxWanAndroidApplication application = (LxWanAndroidApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
