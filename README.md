# Design Patterns in Java 

This repo provides an overview of all the Gang of Four (GoF) design patterns as outlined in their seminal book, 
together with modern-day variations, adjustments, discussions of intrinsic use of patterns in the language.

* *SOLID Design Principles*: Single Responsibility Principle, Open-Closed Principle, Liskov Substitution Principle, 
Interface Segregation Principle and Dependency Inversion Principle

* *Creational Design Patterns*: Builder, Factories (Factory Method and Abstract Factory), Prototype and Singleton

* *Structrural Design Patterns*: Adapter, Bridge, Composite, Decorator, Façade, Flyweight and Proxy

* *Behavioral Design Patterns*: Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Null Object, 
Observer, State, Strategy, Template Method and Visitor

### What are Design Patterns?

Design Patterns are reusable solutions to common programming problems. They were popularised with the 1994 book Design 
Patterns: Elements of Reusable Object-Oriented Software by Erich Gamma, John Vlissides, Ralph Johnson and Richard Helm 
(who are commonly known as a Gang of Four, hence the GoF acronym).

The original book was written using C++ and Smalltalk as examples, but since then, design patterns have been adapted 
to every programming language imaginable: C#, Java, PHP and even programming languages that aren't strictly 
object-oriented, such as JavaScript.

The appeal of design patterns is immortal: we see them in libraries, some of them are intrinsic in programming 
languages, and you probably use them on a daily basis even if you don't realise they are there.

### SOLID Principles

* *Single responsibility principle*: A class should only have a single responsibility, that is, only changes to one part 
of the software's specification should be able to affect the specification of the class.
* *Open–closed principle*: Software entities should be open for extension, but closed for modification.
* *Liskov substitution principle*: Objects in a program should be replaceable with instances of their subtypes without 
altering the correctness of that program. See also design by contract.
* *Interface segregation principle*: Many client-specific interfaces are better than one general-purpose interface.
* *Dependency inversion principle*: One should "depend upon abstractions, not concretions."

### Style and requirements

The code is presented as a series of demonstrations that are normally given as a a single-file, so you can download 
the related file and run/compile it in IntelliJ, Eclipse or another IDE of your choice.

**Are there any requirements or prerequisites?**
* A good understanding of Java
* Familiarity with latest Java features
* Good understanding of object-oriented design principles