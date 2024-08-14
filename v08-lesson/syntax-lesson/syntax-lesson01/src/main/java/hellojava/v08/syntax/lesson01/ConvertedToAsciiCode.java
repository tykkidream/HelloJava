package hellojava.v08.syntax.lesson01;


public class ConvertedToAsciiCode {

	public static void main(String[] args) {
		char a = '1';
		char b = 'A';

		int i1 = (int)a;
		int i2 = (int)b;
		int i3 = Integer.valueOf(String.valueOf(a));

		System.out.println(i1);
		System.out.println(i2);
		System.out.println(i3);
	}
}
