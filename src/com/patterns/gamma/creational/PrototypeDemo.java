package com.patterns.gamma.creational;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;

class Address  { // implements Cloneable
    public String streetName, city, country;
    public int houseNumber;

    public Address(String streetName, int houseNumber, String city, String country) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.country = country;
    }

    // Copy constructor
    public Address(Address other) {
        this(other.streetName, other.houseNumber, other.city, other.country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return new Address(streetName, houseNumber, city, country);
//    }
}

class Person { // implements Cloneable
    public String [] names;
    public Address address;

    public Person(String [] names, Address address) {
        this.names = names;
        this.address = address;
    }

    public Person(Person other) {
        this.names = other.names;
        this.address = new Address(other.address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return new Person(
//                names.clone(),
//                (Address) address.clone());
//    }
}

class PiedPiper implements Serializable {
    public String tagLine;

    public PiedPiper(String tagLine) {
        this.tagLine = tagLine;
    }

    @Override
    public String toString() {
        return "PiedPiper{" +
                "tagLine='" + tagLine + '\'' +
                '}';
    }
}

public class PrototypeDemo {
    public static void main(String[] args) throws Exception {
//        Person erlich = new Person(
//                new String [] {"Erlich", "Bachman"},
//                new Address("Newell Road", 5230, "San Francisco", "USA"));
//
//        Person richard = new Person(erlich);
//        richard.names = new String [] {"Richard", "Hendriks"};

        PiedPiper piedPiper = new PiedPiper("The internet we deserve");

        // Save the original object to the serialization store and then read/deserialize the contents into
        // a new object
        PiedPiper hooli = SerializationUtils.roundtrip(piedPiper);

        hooli.tagLine = "Making the world a better place";

//        System.out.println(erlich.toString());
//        System.out.println(richard.toString());

        System.out.println(piedPiper.toString());
        System.out.println(hooli.toString());
    }
}
