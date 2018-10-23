package com.linxi.wanandroid.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.linxi.wanandroid.core.dao.DaoMaster;
import com.linxi.wanandroid.core.dao.DaoSession;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;

public class LxWanAndroidApplication extends Application {

    @Inject
    DispatchingAndroidInjector<Activity> mAndroidInjector;

    private RefWatcher refWatcher;
    public static boolean isFirstRun = true;
//    private static volatile AppComponent appComponent;
    private DaoSession mDaoSession;

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
//    static {
//        AppCompatDelegate.setDefaultNightMode(
//                AppCompatDelegate.MODE_NIGHT_NO);
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
//            //全局设置主题颜色
//            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//            //指定为Delivery Header，默认是贝塞尔雷达Header
//            return new DeliveryHeader(context);
//        });
//        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
//            //默认是 BallPulseFooter
//            return new BallPulseFooter(context).setAnimatingColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        });
//    }

    private static LxWanAndroidApplication instance;


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


//    public static synchronized AppComponent getAppComponent() {
//        if (appComponent == null) {
//            appComponent = DaggerAppComponent.builder()
//                    .appModule(new AppModule(instance))
//                    .httpModule(new HttpModule())
//                    .build();
//        }
//        return appComponent;
//    }
}
