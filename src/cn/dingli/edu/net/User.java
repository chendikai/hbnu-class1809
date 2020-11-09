package cn.dingli.edu.net;

import java.io.Serializable;

/**
 * @author 陈迪凯
 * @date 2020-11-09 15:13
 */
public class User implements Serializable {
    private static final long serialVersionUID = 6040771343333559739L;

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
