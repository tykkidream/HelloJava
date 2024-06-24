package hellojava.function.lesson;

public class Person {
	private String name;

	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		if (address != null) {
			this.address = address;
		}
	}
}
