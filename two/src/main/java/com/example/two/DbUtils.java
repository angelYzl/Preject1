package com.example.two;

import com.example.two.bean.StudentBean;
import com.example.two.dao.DaoMaster;
import com.example.two.dao.DaoSession;
import com.example.two.dao.StudentBeanDao;

import java.util.List;

public class DbUtils {
    private static DbUtils dbUtils;
    private final StudentBeanDao studentBeanDao;

    public DbUtils() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(BaseApplication.getApp(), "stu.db");
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        DaoSession newSession = daoMaster.newSession();
        studentBeanDao = newSession.getStudentBeanDao();

    }

    public static DbUtils getDbUtils() {
        if (dbUtils == null) {
            synchronized (DbUtils.class) {
                if (dbUtils == null) {
                    dbUtils = new DbUtils();
                }
            }
        }
        return dbUtils;
    }

    public long insert(StudentBean studentBean) {
        if (!isHach(studentBean)) {
            long l = studentBeanDao.insertOrReplace(studentBean);
            return l;
        }
        return -1;
    }

    public List<StudentBean> loadAll() {
        List<StudentBean> studentBeans = studentBeanDao.loadAll();
        return studentBeans;
    }


    private boolean isHach(StudentBean studentBean) {
        List<StudentBean> list = studentBeanDao.queryBuilder().where(StudentBeanDao.Properties.Id.eq(studentBean.getId())).list();
        if (list.size() > 0 && list != null) {
            return true;
        }
        return false;
    }


}
