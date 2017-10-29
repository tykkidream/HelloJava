package hellojava.concurrent.lesson01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tykkidream on 2017/10/29.
 */
public class SyncBusinessEntity {


    private List<String> data;

    public SyncBusinessEntity(){
        this.data = new ArrayList<>();
    }

    public void add(String prex, int key, int value){
        data.add(prex + "-" + key + "-" + value);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public int size(){
        return data.size();
    }
}
