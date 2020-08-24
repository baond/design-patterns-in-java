package builder.pattern;

/**
 * with class has many properties which some is mandatory and other is optional, we can using builder to create instance part by part.
 * Hence, we don't need declare more constructor with optional properties, and don't need setter to guarantee immutable of class
 */
public class FluentBuilder {
    public static void main(String[] args) {
        Person employee = new EmployeeCorrectExtendBuilder().withName("My name").workAs("Developer").build();
        System.out.println("I'am " + employee.getName() + " work as " + employee.getPosition());
    }

}


class Person {
    protected String name;
    protected String position;

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}

//This cannot extends
class PersonBuilder {
    private Person person = new Person();

    public PersonBuilder withName(String name) {
        person.name = name;
        return this;
    }

    public Person build() {
        return this.person;
    }
}

class EmployeeBuilder extends PersonBuilder {
    private Person person = new Person();

    //Error if using withName before cause withName return PersonBuilder not EmployeeBuilder
    public EmployeeBuilder workAs(String position) {
        person.position = position;
        return this;
    }

    public Person build() {
        return this.person;
    }
}

//Correct way
class ExtendablePersionBuilder<SELF extends ExtendablePersionBuilder<SELF>> {
    protected Person person = new Person();

    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    public Person build() {
        return this.person;
    }

    protected SELF self() {
        return (SELF) this;
    }
}

//Correct extend
class EmployeeCorrectExtendBuilder extends ExtendablePersionBuilder<EmployeeCorrectExtendBuilder> {
    public EmployeeCorrectExtendBuilder workAs(String position) {
        person.position = position;
        return this;
    }

    @Override
    protected EmployeeCorrectExtendBuilder self() {
        return this;
    }
}

