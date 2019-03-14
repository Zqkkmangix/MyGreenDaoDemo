package greendaodemo.com.mygreendaodemo.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import greendaodemo.com.mygreendaodemo.IdCard;
import greendaodemo.com.mygreendaodemo.Student;

import greendaodemo.com.mygreendaodemo.gen.IdCardDao;
import greendaodemo.com.mygreendaodemo.gen.StudentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig idCardDaoConfig;
    private final DaoConfig studentDaoConfig;

    private final IdCardDao idCardDao;
    private final StudentDao studentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        idCardDaoConfig = daoConfigMap.get(IdCardDao.class).clone();
        idCardDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        idCardDao = new IdCardDao(idCardDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);

        registerDao(IdCard.class, idCardDao);
        registerDao(Student.class, studentDao);
    }
    
    public void clear() {
        idCardDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
    }

    public IdCardDao getIdCardDao() {
        return idCardDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

}
