package hellojava.syntax.lesson01;

/**
 * 获取汗字、全角字符的 Unicode 编码
 */
public class CharUnicodeCode {
    public static void main(String[] args) {
        char c = 'a';
        System.out.printf("\\u%04x\n", (int) c);

        c = '年';
        System.out.printf("\\u%04x\n", (int) c);

        c = '＋';
        System.out.printf("\\u%04x\n", (int) c);
    }
}
