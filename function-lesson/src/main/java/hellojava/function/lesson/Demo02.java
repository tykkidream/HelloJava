package hellojava.function.lesson;

import java.util.function.BiConsumer;

public class Demo02 {
	public static void main(String[] args) {
		Person person = new Person();

		Address address = new Address();

		String city = "太原";



		// 测试简单直接使用类上的函数
		System.out.println("当前 person  的 address 属性为： " + person.getAddress());

		test(person, address, Person::setAddress);

		System.out.println("处理 person  的 address 属性为： " + person.getAddress());


		// 测试级联使用类的属性上的函数
		System.out.println("当前 address 的 city    属性为： " + address.getCity());

		test(person, city, (aa, bb) -> aa.getAddress().setCity(bb));

		System.out.println("当前 address 的 city    属性为： " + address.getCity());
	}

	private static <T, P> void test(T data, P param,  BiConsumer<T, P> function) {
		function.accept(data, param);
	}
}
