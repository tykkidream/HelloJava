package hellojava.spi.lesson01.impl1;

import hellojava.spi.lesson01.api.Search;

import java.util.Arrays;
import java.util.List;

public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        String result = "文件搜索 " + keyword;

        System.out.println(result);

        return Arrays.asList(result);
    }
}
