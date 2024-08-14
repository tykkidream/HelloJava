package hellojava.v08.syntax.lesson01;

/**
 * 获取汗字、全角字符的 Unicode 编码
 */
public class CharUnicodeCode {
    public static void main(String[] args) {
        // 在 Java 中，字符使用的是 UTF-16 编码，大部分字符用一个 UTF-16 编码就可以表示，对于这些字符，UTF-16 编码对应 Unicode 代码点。一个 UTF-16 代码单元需要16bit，而 Java 的 char 类型占用空间也是 16 bit。
        // 可以用下面的方式输出字符的 UTF-16 编码：
        char c = 'a';
        System.out.printf("\\u%04x\n", (int) c);

        c = '年';
        System.out.printf("\\u%04x\n", (int) c);

        c = '＋';
        System.out.printf("\\u%04x\n", (int) c);

        // 在 print 相关函数中可以用\\uxxxx表示一个字符:
        System.out.println("\u5e74");

        // 表情符号和一些生僻字需要用两个 UTF-16 代码单元表示，这意味着 char 无法表示这些字符。不过可以用 String 来表示。代码示例：
        String str = "😊";
        System.out.println("字符串长度: " + str.length());
        System.out.println("字符串 UTF-16 表示: ");
        for (char c1 : str.toCharArray()) {
            System.out.printf("\\u%04x\n", (int) c1);
        }
    }
}
