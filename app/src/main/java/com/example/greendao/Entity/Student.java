package com.example.greendao.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by John on 2017/11/14.
 */
@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int age;
    private String sex;
    private String love;
    public String getLove() {
        return this.love;
    }
    public void setLove(String love) {
        this.love = love;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 78470668)
    public Student(Long id, String name, int age, String sex, String love) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.love = love;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
}
