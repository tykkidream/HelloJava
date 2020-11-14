package hellojava.function.lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

public class Demo01 {

	/**
	 * 本 Demo 是为了对比原生与函数的性能，所以挑选了原生set和BiConsumer进行对比。
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> data = testData();

		for (int i = 0; i < 10; i++) {
			test1(data);

			test2(data);

			System.out.println("===============");
		}
	}

	public static List<String> testData() {
		int size = 1000_0000;

		List<String> data = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			data.add(UUID.randomUUID().toString());
		}

		return data;
	}


	private static void test1(List<String> data) {
		int size = data.size();

		List<Person> persons = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			persons.add(new Person());
		}

		long begin = System.currentTimeMillis();

		for (int i = 0; i < size; i++) {
			Person person = persons.get(i);
			String name = data.get(i);

			person.setName(name);
		}

		long end = System.currentTimeMillis();

		System.out.println("原生使用时间： " + (end - begin));
	}

	private static void test2(List<String> data) {

		int size = data.size();

		List<Person> persons = new ArrayList<>(size);

		for (int i = 0; i < size; i++) {
			persons.add(new Person());
		}

		BiConsumer<Person, String> function = Person::setName;

		long begin = System.currentTimeMillis();

		for (int i = 0; i < size; i++) {
			Person person = persons.get(i);
			String name = data.get(i);

			function.accept(person, name);
		}

		long end = System.currentTimeMillis();

		System.out.println("函数使用时间： " + (end - begin));
	}

}
