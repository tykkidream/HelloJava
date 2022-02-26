package hellojava.spi.lesson01.test;

import hellojava.spi.lesson01.api.Search;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<Search> searches = ServiceLoader.load(Search.class);

        for (Search search : searches) {
            List<String> result = search.searchDoc("hello world");

            System.out.println(result);
        }

        Iterator<Search> iterator = searches.iterator();
        while (iterator.hasNext()) {
            Search search =  iterator.next();
            search.searchDoc("hello world");
        }
    }
}
