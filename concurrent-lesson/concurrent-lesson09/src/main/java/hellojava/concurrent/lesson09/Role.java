package hellojava.concurrent.lesson09;

/**
 * Created by Tykkidream on 2018/1/12.
 */
public class Role {
    protected volatile String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
