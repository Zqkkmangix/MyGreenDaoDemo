package greendaodemo.com.mygreendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import greendaodemo.com.mygreendaodemo.gen.DaoMaster;
import greendaodemo.com.mygreendaodemo.gen.DaoSession;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "daodemo.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
