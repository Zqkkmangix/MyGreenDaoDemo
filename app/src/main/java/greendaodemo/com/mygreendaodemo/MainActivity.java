package greendaodemo.com.mygreendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.ArrayList;
import java.util.List;

import greendaodemo.com.mygreendaodemo.gen.DaoSession;
import greendaodemo.com.mygreendaodemo.gen.StudentDao;

@SuppressWarnings("unchecked")
public class MainActivity extends AppCompatActivity {
    private MyApplication app;

    private DaoSession daoSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app=(MyApplication)getApplication();
        daoSession =  app.getDaoSession();

//        for(int i=0;i<20;i++){
//            Student st=new Student();
//            st.setId((long)i);
//            st.setStudentNo(i);
//            st.setAge(i);
//            st.setTelPhone("188888888888");
//            st.setSex(i%2==0?"男":"女");
//            st.setName(i+"号机器人");
//            st.setAddress(i+"XXXXXXXXXXXXXXXXXXXX");
//            st.setSchoolName(i+"xxx学校");
//            st.setGrade(i+"年级");
//            daoSession.insertOrReplace(st);
//        }



        List<Student>sts=new ArrayList<>();
        sts=queryAll();
        Toast.makeText(MainActivity.this,sts.toString(),Toast.LENGTH_SHORT).show();

        List<Student> stss=queryData("男");

//        addStudent();
        List<Student> sss=queryAllList();

        List<Student> stsss=queryListByMessage("男");

        List<Student> stssss=queryListBySqL();

        List<Student> stsssss=queryList();


    }

    @SuppressWarnings("unchecked")
    public List<Student> queryAll(){
        List<Student> students =new ArrayList<Student>();
        students= daoSession.loadAll(Student.class);
        return students;
    }


    @SuppressWarnings("unchecked")
    public List<Student> queryData(String s) {
        List<Student> students = daoSession.queryRaw(Student.class, " where sex = ?",s);
        return students;
    }

    @SuppressWarnings("unchecked")
    public List<Student> queryAllList(){
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class);
        List<Student> list = qb.list(); // 查出所有的数据
        return list;
    }


    @SuppressWarnings("unchecked")
    public List<Student> queryListByMessage(String name){
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class);
        QueryBuilder<Student> studentQueryBuilder = qb.where(StudentDao.Properties.Name.eq("1号机器人")).orderAsc(StudentDao.Properties.Name);
        List<Student> studentList = studentQueryBuilder.list(); //查出当前对应的数据
        return studentList;
    }


    public List<Student> queryListBySqL(){
// 查询ID大于5的所有学生
        Query<Student> query = daoSession.queryBuilder(Student.class).where(
                new WhereCondition.StringCondition("_ID IN " +
                        "(SELECT _ID FROM STUDENT WHERE _ID > 5)")
        ).build();
        List<Student> list = query.list();
        return list;
    }

    public List<Student> queryList(){//ID大于5小于10，并且id是8号机器人的
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class);
        qb = daoSession.queryBuilder(Student.class);
        List<Student> list2 = qb.where(StudentDao.Properties.Name.eq("8号机器人"),
                qb.and(StudentDao.Properties.Id.gt(5),
                        StudentDao.Properties.Id.le(10))).list();
        return  list2;
    }

    public List<Student> queryListByOther(){
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class);

        //搜索条件为Id值大于1，即结果为[2,3,4,5,6,7,8,9,10,11];
        // offset(2)表示往后偏移2个，结果为[4,5,6,7,8,9,10,11,12,13];
        List<Student> list = qb.where(StudentDao.Properties.Id.gt(1)).limit(10).offset(2).list();
        return list;
    }

//    例子：删除数据库中id大于5的所有其他数据
    public boolean deleteItem(){
        QueryBuilder<Student> where = daoSession.queryBuilder(Student.class).where(StudentDao.Properties.Id.gt(5));
        DeleteQuery<Student> deleteQuery = where.buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
        return false;
    }


    public void addStudent(){
        for(int i=0;i<20;i++){
            Student st=new Student();
            st.setId((long)i);
            st.setStudentNo(i);
            st.setAge(i);
            st.setTelPhone("188888888888");
            st.setSex(i%2==0?"男":"女");
            st.setName(i+"号机器人");
            st.setAddress(i+"XXXXXXXXXXXXXXXXXXXX");
            st.setSchoolName(i+"xxx学校");
            st.setGrade(i+"年级");
            daoSession.insertOrReplace(st);
            //插入对应的IdCard数据
            IdCard idCard = new IdCard();
            idCard.setUserName(st.getName());
            idCard.setIdNo("21312312312312312312");
            daoSession.insertOrReplace(idCard);
        }

    }
}
