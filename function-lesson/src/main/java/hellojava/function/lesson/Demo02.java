package hellojava.function.lesson;

import java.util.function.BiConsumer;

public class Demo02 {
	/**
	 * 最下面 test 方法，以往实现这样的效果，就得定义一个接口。
	 *
	 * test1、test2、test3以往就是接口的 3 个子类，相比函数式，代码多多了
	 *
	 * 当然，这种简化是牺牲了可读性和业务含义的。
	 *
	 * 所以在业务主体逻辑里，还是不能有 lambda 的，
	 *
	 * 它应该被封装在具有业务含义的方法中然后参与到业务中使用，且此方法最好只有这一行。
	 *
	 * @param args
	 */
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

	/**
	 * 相当于一个接口的实现类
	 * @param person
	 * @param address
	 */
	private static void test1(Person person, Address address) {
		test(person, address, Person::setAddress);
	}

	/**
	 * 相当于一个接口的实现类
	 * @param person
	 * @param address
	 */
	private static void test2(Person person, Address address) {
		test(person, address, (a, b) -> a.setAddress(b));
	}

	/**
	 * 相当于一个接口的实现类
	 * @param person
	 * @param city
	 */
	private static void test3(Person person, String city) {
		test(person, city, (aa, bb) -> aa.getAddress().setCity(bb));
	}

	/**
	 * 相当于一个接口
	 * @param data
	 * @param param
	 * @param function
	 * @param <T>
	 * @param <P>
	 */
	private static <T, P> void test(T data, P param,  BiConsumer<T, P> function) {
		function.accept(data, param);
	}
}
