package pattern.prototype;

public class CopyConstructor {
    public static void main(String[] args) {
        Address a = new Address("No 1, Thai Ha Street", "Hanoi", "Vietnam");
        Employee john = new Employee("John", a);
        Employee paul = new Employee(john);
        paul.name = "Paul";
        System.out.println(john);
        System.out.println(paul);
    }
}

class Address {
    public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public Address(Address other) {
        this(other.streetAddress, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='"+  streetAddress + "', " +
                "city='" + city + "', " +
                "country='" + country + "'}";
    }
}

class Employee {
    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other) {
        this(other.name, new Address(other.address));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
