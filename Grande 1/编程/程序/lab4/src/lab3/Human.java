package lab3;

public class Human implements Say,Walkingto,Walkthrough{
    protected String name;
    protected int age;
    protected Sex sex;
    protected Status status;

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

    /*public static class location extends Place{
        public location(String desname){
            super(desname);
        }
    }*/

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

    @Override
    public void say(String words) {
        System.out.print(this.name+" says:"+words);
    }


    @Override
    public void walkto(Place nameofdes) {
        System.out.printf("%s is walking to %s\n",this.name,nameofdes.placename);
        setStatus(Status.WALKING);
    }

    @Override
    public void walkthrough(Place path) {
        System.out.printf("% is walking by %s\n",this.name,path.placename);
        setStatus(status.WALKING);
    }

    public void eat(String foodname){
        System.out.printf("%s is eating %s\n",this.name,foodname);
        setStatus(Status.EATING);
    }

    public void sit(){
        System.out.printf("%s sitted and took a rest\n",this.name);
        setStatus(Status.SITTING);
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

