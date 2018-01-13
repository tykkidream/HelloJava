package hellojava.concurrent.lesson09;

/**
 * Created by Tykkidream on 2018/1/12.
 */
public class Role {

    protected volatile long id;

    protected volatile String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
