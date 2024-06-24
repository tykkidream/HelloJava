package hellojava.concurrent.lesson05;

/**
 * Created by Tykkidream on 2017/11/11.
 */
public interface TestCase {

    /**
     *
     */
    void sumValue();

    /**
     * 对long的操作是非原子的，原子操作只针对32位
     * long是64位，底层操作的时候分2个32位读写，因此不是线程安全
     */
    long getValue();
}
