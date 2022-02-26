package hellojava.spi.lesson01.impl2;

import hellojava.spi.lesson01.api.Search;

import java.util.Arrays;
import java.util.List;

public class DatabaseSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        String result = "数据库搜索 " + keyword;

        System.out.println(result);

        return Arrays.asList(result);
    }
}
