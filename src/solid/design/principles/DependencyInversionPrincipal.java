package solid.design.principles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * High-level modules should not depend on low-level modules. Both should depend on abstractions
 * Abstractions should not depend on details. Depends should depend on abstractions
 */
public class DependencyInversionPrincipal {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Kat");
        Person child2 = new Person("Tom");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}

enum Relationship {
    PARENT,
    CHILDREN,
    SIBLING
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Relation {
    private Person firstPerson;
    private Person secondPerson;
    private Relationship relationship;

    public Relation(Person firstPerson, Person secondPerson, Relationship relationship) {
        this.firstPerson = firstPerson;
        this.secondPerson = secondPerson;
        this.relationship = relationship;
    }

    public Person getFirstPerson() {
        return firstPerson;
    }

    public Person getSecondPerson() {
        return secondPerson;
    }

    public Relationship getRelationship() {
        return relationship;
    }
}

//Low-level module: not implement logic, just store data, access data
//Low-level depend on abstraction (interface)
class Relationships implements RelationshipBrowser {
    private List<Relation> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Relation(parent, child, Relationship.PARENT));
        relations.add(new Relation(child, parent, Relationship.CHILDREN));
    }

    public List<Relation> getRelations() {
        return relations;
    }

    @Override
    public List<Person> findAllChildendOf(String name) {
        return relations.stream().filter(x -> x.getFirstPerson().getName().equals("John") && x.getRelationship().equals(Relationship.PARENT))
                .map(x -> x.getSecondPerson())
                .collect(Collectors.toList());
    }
}

//High-level module: implement logic code
class Research {
    //This implementation violate Dependency Inversion Principal because high level module depend on low-level module (Relationships)
    //hence, when low-level module change, we have to change high-level module
//    public Research(Relationships relationships) {
//        relationships.getRelations().stream().filter(x -> x.getFirstPerson().getName().equals("John") && x.getRelationship().equals(Relationship.PARENT))
//                .forEach(x -> System.out.println("John has a child called " + x.getSecondPerson().getName()));
//    }

    //High-level not depend on low-level but depend on abstraction
    public Research(RelationshipBrowser relationshipBrowser) {
        relationshipBrowser.findAllChildendOf("John").stream().forEach(x -> System.out.println("John has a child called " + x.getName()));
    }
}


//Abstraction not depend on detail
interface RelationshipBrowser {
    List<Person> findAllChildendOf(String name);
}