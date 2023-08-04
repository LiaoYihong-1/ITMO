package lab3;

public abstract class Human implements Say,Wait,Walking{
    protected String name;
    protected int age;
    protected Sex sex;
    protected Status status;
    String destname;

    public Human() {
        name = "unknowed";
        sex = Sex.UNKNOWED;
        status = Status.UNKNOWED;
        age = (int) Math.random() * 100;
        System.out.println("There is a person, and we know nothing about him/her\n");
    }

    public Human(String name, int age, Sex sex) {
        this.sex = sex;
        this.name = name;
        this.age = age;
        if (this.sex == Sex.MALE) {
            System.out.printf("There is a man named %s\n", this.name);
        } else {
            System.out.printf("There is a woman named %s\n", this.name);
        }
    }

    public void setStatus(Status status){
        this.status=status;
    }


    public String getname() {
        return this.name;
    }

    public int getage() {
        return this.age;
    }

    public Sex getsex() {
        return this.sex;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public abstract void detailmove();

    public void setDestname(String n) {
        this.destname=n;
    }

    @Override
    public Void say(String words) {
        System.out.print(this.name+"says:"+words);
        return null;
    }

    @Override
    public Void waiting() {
        System.out.printf("%s is waiting\n",this.name);
        return null;
    }

    @Override
    public Void walk(String nameofdes) {
        System.out.printf("%s is walking to %s\n",this.name,nameofdes);
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Human hum = (Human) o;
        if (this.hashCode() != hum.hashCode()) {
            return false;
        }
        return (this.getage() == hum.getage() && this.getname() == hum.getname() && this.getsex() == hum.getsex() && this.getStatus() == hum.status);
    }

    @Override
    public int hashCode() {
        int code = 0;
        for (int i = 0; i < this.getname().length(); i++) {
            code = code + (int) this.getname().charAt(i);
        }
        if(this.getsex()==Sex.FEMALE){
            code=code+this.age+1;
        }else if(this.getsex()==Sex.MALE){
            code=code+this.age+0;
        }else {
            code=code+this.age-1;
        }
        return code;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", status=" + status +
                '}';
    }
}

