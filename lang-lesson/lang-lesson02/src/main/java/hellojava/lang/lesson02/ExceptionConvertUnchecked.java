package hellojava.lang.lesson02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExceptionConvertUnchecked {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionConvertUnchecked.class);

    /**
     * 这个方法会将检查型异常转换为非检查型异常
     *
     *
     *
     * @param t
     * @param <T> 这里的 T 会被视为 unchecked exception
     * @param <R>
     * @return
     * @throws T
     */
    public static <T extends Exception, R> R throwException(Throwable t) throws T {
        throw (T) t;
    }

    /**
     * 测试业务方法，认为其它业务方法不抛出检查型异常，所以未处理任何异常，
     *
     * @return
     */
    public static Object process() {
        return handle();
    }

    /**
     * 测试业务方法，没有声明检查型异常，捕获了检查型异常转换为非检查型异常。
     *
     * @return
     */
    public static Object handle() {
        try {
            return abc();
        } catch (IOException ioException) {
            return ExceptionConvertUnchecked.throwException(ioException);
        }
    }

    /**
     * 测试业务方法，声明了抛出检查型异常。
     * @return
     * @throws IOException
     */
    private static Object abc() throws IOException {
        logger.info("abc ......");
        throw new IOException();
    }

    public static void main(String[] args) {
        logger.info("========= 开始 =========");

        try {
            Object obj = process();
            System.out.println(obj);
        } catch (Throwable throwable) {
            logger.error("异常 === {} === {}", throwable.getClass().getName(), throwable.getMessage(), throwable);
        }
    }
}
