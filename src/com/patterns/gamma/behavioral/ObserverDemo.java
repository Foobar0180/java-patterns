package com.patterns.gamma.behavioral;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
    private int count = 0;
    private Map<java.lang.Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int index = count;
        handlers.put(count++, handler);
        return new Subscription(this, index);
    }

    public void handle(TArgs args) {
        for (Consumer<TArgs> handler : handlers.values()) {
            handler.accept(args);
        }
    }

    public class Subscription implements AutoCloseable {
        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() {
            event.handlers.remove(id);
        }
    }
}

class PropertyChangedEventArgs {
    public Object source;
    public String propertyName;

    public PropertyChangedEventArgs(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }
}

class Person {
    public Event<PropertyChangedEventArgs> propertyChanged = new Event<>();

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return;
        this.age = age;
        propertyChanged.handle(new PropertyChangedEventArgs(
                this, "age"
        ));
    }
}


class ObserverDemo {
    public static void main(String[] args) {
        Person person = new Person();
        Event<PropertyChangedEventArgs>.Subscription subscription
                = person.propertyChanged.addHandler(p -> {
                    System.out.println("Person's " + p.propertyName + " has changed");
        });
        person.setAge(17);
        person.setAge(18);
        subscription.close();
        person.setAge(19);
    }
}


//
//import java.util.ArrayList;
//import java.util.List;
//
//class PropertyChangedEventArgs<T> {
//    public T source;
//    public String propertyName;
//    public Object newValue;
//
//    public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
//        this.source = source;
//        this.propertyName = propertyName;
//        this.newValue = newValue;
//    }
//}
//
// interface Observer<T> {
//    void handle(PropertyChangedEventArgs<T> args);
// }
//
//class Observable<T> {
//    private List<Observer<T>> observers = new ArrayList<>();
//
//    public void subscribe(Observer<T> observer) {
//        observers.add(observer);
//    }
//
//    protected void propertyChanged(T source, String propertyName, Object newValue) {
//        for (Observer<T> observer : observers) {
//            observer.handle(new PropertyChangedEventArgs<T>(source, propertyName, newValue));
//        }
//    }
//}
//
//class Person extends Observable<Person> {
//    private int age;
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        if (this.age == age) return;
//
//        this.age = age;
//        propertyChanged(this, "age", age);
//    }
//}
//
///*
//* An observer is an object that wishes to be informed/notified when an event occurs. The entity generating
//* the events is an observable.
//* */
//
//class ObserverDemo implements Observer<Person> {
//    public static void main(String[] args) {
//        new ObserverDemo();
//    }
//
//    public ObserverDemo() {
//        Person person = new Person();
//        person.subscribe(this);
//        for (int i = 20; i < 24; ++i) {
//            person.setAge(i);
//        }
//    }
//
//    @Override
//    public void handle(PropertyChangedEventArgs<Person> args) {
//        System.out.println("Person's " + args.propertyName + " has changed to " + args.newValue);
//    }
//}
