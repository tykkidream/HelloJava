package hellojava.function.lesson;

import java.util.function.BiConsumer;

public class Demo02 {
	public static void main(String[] args) {
		Person person = new Person();

		Address address1 = new Address();

		Address address2 = new Address();

		String city = "太原";



		// 测试简单直接使用类上的函数
		System.out.println("当前 person  的 address 属性为： " + person.getAddress());

		test1(person, address1);

		System.out.println("处理 person  的 address 属性为： " + person.getAddress());

		System.out.println("===================================================================================");

		System.out.println("当前 person  的 address 属性为： " + person.getAddress());

		test2(person, address2);

		System.out.println("处理 person  的 address 属性为： " + person.getAddress());

		System.out.println("===================================================================================");

		// 测试级联使用类的属性上的函数
		System.out.println("当前 address 的 city    属性为： " + address2.getCity());

		test3(person, city);

		System.out.println("当前 address 的 city    属性为： " + address2.getCity());
	}

	private static void test1(Person person, Address address) {
		test(person, address, Person::setAddress);
	}

	private static void test2(Person person, Address address) {
		test(person, address, (a, b) -> a.setAddress(b));
	}

	private static void test3(Person person, String city) {
		test(person, city, (aa, bb) -> aa.getAddress().setCity(bb));
	}

	private static <T, P> void test(T data, P param,  BiConsumer<T, P> function) {
		function.accept(data, param);
	}
}
