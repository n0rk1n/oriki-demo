package cn.oriki.reflect.demo;

public class Student {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 公开无参构造方法
    public Student() {
        super();
    }

    // 私有有参构造方法
    private Student(Integer id) {
        super();
        this.id = id;
    }

    // 公开有参构造方法
    public Student(String name) {
        super();
        this.name = name;
    }

    // 公开有参构造方法
    public Student(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}
