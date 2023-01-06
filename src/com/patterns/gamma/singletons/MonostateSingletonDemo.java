package com.patterns.gamma.singletons;

class ChiefExecutiveOfficer {

    /*  By making the fields static, these values are shared between all instances of the object.
        This is a dangerous approach. It does not communicate to consumers that the object is in fact
        a singleton but rather appears as a normal object */

    private static String name;
    private static int numberOfShares;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ChiefExecutiveOfficer.name = name;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        ChiefExecutiveOfficer.numberOfShares = numberOfShares;
    }

    @Override
    public String toString() {
        return "ChiefExecutiveOfficer{" +
                "name=" + name + '\'' +
                ", numberOfShares=" + numberOfShares +
                "}";
    }
}

class MonostateSingletonDemo {

    public static void main(String[] args) {
        /* The object behaves like a Singleton for the purpose of data storage */

        ChiefExecutiveOfficer ceo1 = new ChiefExecutiveOfficer();
        ceo1.setName("Richard Hendriks");
        ceo1.setNumberOfShares(40);

        ChiefExecutiveOfficer ceo2 = new ChiefExecutiveOfficer();

        System.out.println(ceo1.toString());
        System.out.println(ceo2.toString());
    }
}
