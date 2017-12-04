package com.example.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.greendao.Entity.Student;
import com.example.greendao.greenDao.DaoSession;
import com.example.greendao.greenDao.StudentDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                add();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.update:
                update();
                break;
            case R.id.cha:
                cha();
                break;
            case R.id.other:
                other();
                break;
        }
    }

    /**
     * 增
     */
    private void add() {
        Student student = new Student();
        if (i % 2 == 0) {
            student = new Student(null, "杨幂", i, "女", "游泳");
        } else {
            student = new Student(null, "王珞丹", i, "女", "跑步");
        }
        AppApplication.getInstances().getDaoSession().insert(student);
        i = i + 1;
    }

    /**
     * 删
     */
    private void delete() {
        DaoSession daoSession = AppApplication.getInstances().getDaoSession();
        StudentDao studentDao = daoSession.getStudentDao();
        QueryBuilder<Student> builder = studentDao.queryBuilder();
        List<Student> list = builder.list();
        if (list == null || list.isEmpty()) {
            Log.i("********", "删除的时候暂无数据");
            return;
        }
        for (int j = 0; j < list.size(); j++) {
            Student student = list.get(j);
            if (student.getName().equals("杨幂") && student.getAge() % 3 == 0) {
                daoSession.delete(student);
                Log.i("********", "删除： [name:" + student.getName() + "],[age:" + student.getAge() + "],[sex:" + student.getSex() + "]"
                        + ",[love:" + student.getLove() + "]");
            }
        }
    }

    /**
     * 改
     */
    private void update() {
        StudentDao studentDao = AppApplication.getInstances().getDaoSession().getStudentDao();
        QueryBuilder<Student> builder = studentDao.queryBuilder();
        List<Student> wangList = builder.where(StudentDao.Properties.Name.eq("王珞丹")).list();
        if (wangList == null || wangList.isEmpty()) {
            Log.i("********", "修改的时候暂无数据");
            return;
        }

        for (int j = 0; j < wangList.size(); j++) {
            Student student = wangList.get(j);
            if (student.getName().equals("王珞丹")) {
                if (j % 2 == 0) {
                    student.setName("朱茵");
                    student.setLove("孙悟空");
                } else {
                    student.setName("王祖贤");
                    student.setLove("令采臣");
                }
                studentDao.update(student);
                Log.i("********", "修改： [name:" + student.getName() + "],[age:" + student.getAge() + "],[sex:" + student.getSex() + "]"
                        + ",[love:" + student.getLove() + "]");
            }
        }
    }

    /**
     * 查
     */
    private void cha() {
        StudentDao studentDao = AppApplication.getInstances().getDaoSession().getStudentDao();
        QueryBuilder<Student> builder = studentDao.queryBuilder();
        List<Student> list = builder.list();
        if (list == null || list.isEmpty()) {
            Log.i("********", "查询的时候暂无数据");
            return;
        }
        for (int j = 0; j < list.size(); j++) {
            Log.i("********", "查询： [name:" + list.get(j).getName() + "],[age:" + list.get(j).getAge() + "],[sex:" + list.get(j).getSex() + "]"
                    + ",[love:" + list.get(j).getLove() + "]");
        }
    }

    /**
     * 查
     */
    private void other() {
        DaoSession daoSession = AppApplication.getInstances().getDaoSession();
        StudentDao studentDao = daoSession.getStudentDao();
        QueryBuilder<Student> builder = studentDao.queryBuilder();
        List<Student> list = builder.where(builder.and(StudentDao.Properties.Love.eq("孙悟空"), StudentDao.Properties.Age.eq(9))).list();
//        List<Student> list = builder.where(builder.or(StudentDao.Properties.Name.eq("朱茵"), StudentDao.Properties.Love.eq("令采臣"))).list();
        if (list == null || list.isEmpty()) {
            Log.i("********", "其他的时候暂无数据");
            return;
        }

        for (int j = 0; j < list.size(); j++) {
            Student student = list.get(j);
            Log.i("********", "查询： [name:" + student.getName() + "],[age:" + student.getAge() + "],[sex:" + student.getSex() + "]"
                    + ",[love:" + student.getLove() + "]");
        }
    }
}
