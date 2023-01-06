package com.patterns.solid;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

interface Search {
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements Search {
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(r -> Objects.equals(r.getValue0().name, name)
                        && (r.getValue1() == Relationship.PARENT))
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

class Research {
//    public com.patterns.solid.Research(com.patterns.solid.Relationships relationships) {
//        List<Triplet<com.patterns.solid.Person, com.patterns.solid.Relationship, com.patterns.solid.Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(r -> r.getValue0().name.equals("John")
//                        && r.getValue1() == com.patterns.solid.Relationship.PARENT)
//                .forEach(child -> System.out.println("John has a child called " + child.getValue2().name));
//    }

    public Research(Search search) {
        List<Person> children = search.findAllChildrenOf("John");

        for (Person child : children) {
            System.out.println("John has a child called " + child.name);
        }
    }
}

class DIPDemo {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Sarah");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}
