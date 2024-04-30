# Design patterns

### Major objectives of design patterns are:

- Program to an interface not an implementation 
- Favor object composition over inheritance

Design patterns are used to represent some of the best practices adapted by experienced object-oriented software developers. They are like pre-made blueprints that you can customize to solve a recurring design problem in your code.

- **Reusable solutions to the problems:** In software engineering, a design pattern is a general reusable solution to commonly occurring problems in software design.

- **Interaction between the objects:** Object-oriented design patterns typically show relationships and interactions between classes or objects, without specifying final application classes or objects that are involved.

- **Template, not a solution:** It is not a finished design that can be transferred directly into code.

- **Language independent**

### Pros of Design Patterns

- **Add consistency to designs:** Design patterns solve similar problems in the same way, independent of language, adding consistency to designs.
  
- **Enhance clarity:** They enable a common vocabulary and improve design communication, making it easier for developers to understand and collaborate on projects.
  
- **Improve time to solution:** Design patterns provide templates that serve as foundations for good design, speeding up the development process by offering proven solutions to common problems.
  
- **Enhance reuse:** Through composition, design patterns promote reuse of design components, reducing redundancy and enhancing maintainability.

### Cons of Design Patterns

- **Negative consequences:** Some patterns may lead to negative consequences such as object proliferation, performance hits, or introducing unnecessary layers.
  
- **Subjective consequences:** The consequences of using patterns can be subjective, depending on concrete scenarios and project requirements.
  
- **Misinterpretations:** Patterns are subject to misinterpretations and different philosophies, leading to potential issues in implementation.
  
- **Overuse and abuse:** Patterns can be overused or abused, leading to the emergence of anti-patterns or suboptimal solutions.


# CREATIONAL DESIGN PATTERNS

- Creational design patterns provide various object creation mechanisms, which increase flexibility and reuse of existing code. 
- The basic form of object creation could result in design problems or added complexity to the design.  
- Creational design patterns solve this problem by somehow controlling this object creation.

**Recurring themes:** 
- Encapsulate knowledge about which concrete classes the system uses (so we can change them easily later) 
- Hide how instances of these classes are created and put together (so we can change it easily later)

An object is created using `new` keyword.

The `new` operator is often considered harmful as it scatters objects all over the application. Over time it can become challenging to change an implementation because classes become tightly coupled.


# Singleton Pattern 
### (Violates SRP- Single Responsibility Principle)

The  Singleton is part of the Creational Design Pattern Family .Singleton Design Pattern aims to keep a check on initialization of objects of a particular class by ensuring that only one instance of the object exists throughout the Java Virtual Machine.

A Singleton class also provides one unique global access point to the object so that each subsequent call to the access point returns only that particular object.

Definition: The singleton pattern is a design pattern that restricts the instantiation of a class to one object. 

**Why Singleton DP?**
1. We can be sure that a class has only a single instance. 
2. We gain a global access point to that instance. 
3. The Singleton Object is initialized only when it's requested for the first time. 

### Problem Solved by Singleton:

1. Ensures a class has only one instance.
2. Provides a global access point to that instance.
3. Causes Violation of Single Responsibility Principle.

### Solution Steps:
1. Make the default constructor private to prevent other objects from using the new operator with the Singleton class.
2. Create a static creation method that acts as a constructor, calling the private constructor to create an object and saving it in a static field. Subsequent calls to this method return the cached object.

### Implementation Example:
```java
public class Printer {
    private static Printer printer;
    private int nrOfPages;

    private Printer() {}

    public static Printer getInstance() {
        return printer == null ?
                printer = new Printer() :
                printer;
    }

    public void print(String text) {
        System.out.println(text +
                "\nPages printed today: " + ++nrOfPages +
                "\n---------");
    }
}
```

### Applicability:
Use the Singleton pattern when:
- There must be exactly one instance of a class, accessible from a well-known access point.
- The sole instance should be extensible by subclassing, and clients should be able to use an extended instance without modifying their code.

### Summary:
It improves consistency, clarity, and reuse in designs, although it may introduce negative consequences such as object proliferation or performance hits if overused.


## Participants

- **Singleton**: Defines an Instance operation that lets clients access its unique instance. Instance is a class operation (that is, a class method and a static member function). It may be responsible for creating its own unique instance.
- **Client**: Accesses a Singleton instance solely through Singleton's Instance operation.

## Collaborations
- Clients access a Singleton instance solely through Singleton's Instance operation.


## Implementation
### Java Singleton Pattern Implementation
1. Add a private static field to the class for storing the singleton instance.
2. Declare a public static creation method for getting the singleton instance.
3. Implement "lazy initialization" inside the static method.
4. Make the constructor of the class private.
5. Replace all direct calls to the singleton’s constructor with calls to its static creation method.

### Various Design Options for Implementing Singleton:
- **Method 1: Lazy Instantiation**
```java
// Classic Java implementation of singleton design pattern
class Singleton {
    private static Singleton obj;
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (obj == null)
            obj = new Singleton();
        return obj;
    }
}
```
Sure, here are the code implementations for the mentioned methods:

- **Method 2: Make `getInstance()` synchronized**

```java
class Singleton {
    private static Singleton obj;
    private Singleton() {}
    
    public static synchronized Singleton getInstance() {
        if (obj == null)
            obj = new Singleton();
        return obj;
    }
}
```

- **Method 3: Eager Instantiation**

```java
class Singleton {
    private static Singleton obj = new Singleton();
    private Singleton() {}
    
    public static Singleton getInstance() {
        return obj;
    }
}
```

- **Method 4 (Best): Use "Double Checked Locking"**

```java
class Singleton {
    private static volatile Singleton obj = null;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (obj == null) {
            synchronized (Singleton.class) {
                if (obj == null)
                    obj = new Singleton();
            }
        }
        return obj;
    }
}
```

These implementations demonstrate different ways to achieve thread-safe singleton instantiation in Java. Method 4, using double-checked locking, is often considered the best practice due to its efficiency and thread safety.


**Pros:**
- Ensures only a single instance of a class.
- Provides a global access point to that instance.
- Lazily initializes the singleton object when first requested.

**Cons:**
- Violates the Single Responsibility Principle.
- Requires special treatment in a multithreaded environment.
- May be difficult to unit test client code.

# Factory Pattern

The Factory Design Pattern, also known as the Factory Method Design Pattern, is widely used in Java and is defined by the Gang of Four (GoF) as a pattern that "defines an interface for creating an object, but let subclasses decide which class to instantiate."

### Introduction:
- The Factory Design Pattern delegates the responsibility of object creation from the client to a factory class, which creates objects based on a common interface, thereby hiding the implementation details.
- It allows subclasses to determine the type of objects that will be created.

### Motivation:
- Addresses the problem of creating objects without specifying their exact class.
- Helps in avoiding complex processes in object creation, which may not be suitable to include within a composing object.
- Provides a level of abstraction and separation of concerns.

### Intent:
- Provides an interface for creating objects in a superclass, allowing subclasses to change the type of objects created.

### Solution:
- The Factory pattern defines a superclass with a factory method that subclasses override to specify the type of objects to create.
- Clients interact with the factory through the factory method, and the factory decides which concrete class to instantiate.

### Applicability:
- Use the Factory pattern when the class can't anticipate the class of objects it must create.
- Use it when classes want their subclasses to specify the objects they create.
- Use it when classes delegate responsibility to one of several helper subclasses.

### Structure:
- Participants include Product, ConcreteProduct, Creator, and ConcreteCreator.
- Creator declares the factory method, which ConcreteCreator overrides to return an instance of ConcreteProduct.

### Consequence:
- Provides hooks for subclasses to extend or customize the object creation process.
- Connects parallel class hierarchies, allowing different types of objects to be created based on different criteria.

### Pros and Cons:
- Pros include flexibility in creating objects and connecting parallel class hierarchies.
- Cons include variations in implementation and potential language-specific issues.

### Issues to consider:
- Variations in the implementation, such as abstract vs. concrete Creator classes and parameterized factory methods.
- Language-specific variants and issues, such as different approaches in languages like Smalltalk.

The Factory Design Pattern provides a flexible and reusable solution for object creation, allowing for easy extension and customization in Java applications.

Before Factory Design Pattern:
```java
// Without Factory Design Pattern
public class CarRentalService {
    public Car rentCar(String customerType, String carGrade) {
        Car car = null;
        if (customerType.equals("Regular")) {
            if (carGrade.equals("A")) {
                car = new RegularGradeACar();
            } else if (carGrade.equals("B")) {
                car = new RegularGradeBCar();
            }
        } else if (customerType.equals("Company")) {
            if (carGrade.equals("A")) {
                car = new CompanyGradeACar();
            } else if (carGrade.equals("B")) {
                car = new CompanyGradeBCar();
            }
        }
        // Perform additional procedures like checking, cleaning, and fueling the car
        return car;
    }
}
```
For any addition, we violate OCP.


After Factory Design Pattern:
```java
// With Factory Design Pattern
public abstract class CarRentalService {
    public abstract Car rentCar(String carGrade);
}

public class RegularCustomerService extends CarRentalService {
    @Override
    public Car rentCar(String carGrade) {
        if (carGrade.equals("A")) {
            return new RegularGradeACar();
        } else if (carGrade.equals("B")) {
            return new RegularGradeBCar();
        }
        return null;
    }
}

public class CompanyCustomerService extends CarRentalService {
    @Override
    public Car rentCar(String carGrade) {
        if (carGrade.equals("A")) {
            return new CompanyGradeACar();
        } else if (carGrade.equals("B")) {
            return new CompanyGradeBCar();
        }
        return null;
    }
}
```

In the above code:

- Before Factory Design Pattern: The responsibility of object creation is directly handled by the `CarRentalService` class, leading to tight coupling and potential modification issues.
- After Factory Design Pattern: The object creation is delegated to specific factory classes (`RegularCustomerService` and `CompanyCustomerService`), adhering to the Factory Design Pattern. This results in a more flexible and maintainable codebase, as each factory class is responsible for creating objects of a specific type.

Before Factory Design Pattern:
```java
// Without Factory Design Pattern
public class Vehicle {
    private String type;

    public Vehicle(String type) {
        this.type = type;
    }

    public void start() {
        if (type.equals("Car")) {
            System.out.println("Starting the car...");
        } else if (type.equals("Motorcycle")) {
            System.out.println("Starting the motorcycle...");
        } else {
            System.out.println("Unknown vehicle type");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Vehicle("Car");
        car.start();

        Vehicle motorcycle = new Vehicle("Motorcycle");
        motorcycle.start();
    }
}
```

After Factory Design Pattern:
```java
// With Factory Design Pattern
public interface Vehicle {
    void start();
}

public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Starting the car...");
    }
}

public class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Starting the motorcycle...");
    }
}

// only responsible for creating vehicle child objects
public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        if (type.equals("Car")) {
            return new Car();
        } else if (type.equals("Motorcycle")) {
            return new Motorcycle();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("Car");
        car.start();

        Vehicle motorcycle = VehicleFactory.createVehicle("Motorcycle");
        motorcycle.start();
    }
}
```

In the above code:

- Before Factory Design Pattern: The responsibility of object creation and initialization is directly handled in the `Vehicle` class, leading to tight coupling and potential modification issues.
- After Factory Design Pattern: The object creation is delegated to the `VehicleFactory` class, adhering to the Factory Design Pattern. This results in a more flexible and maintainable codebase, as the factory class is responsible for creating objects based on the provided type.

```java
// Abstract Product Class
abstract class Product {
	public abstract void display();
}

// Concrete Products
class ConcreteProductA extends Product {
	@Override
	public void display() {
		System.out.println("This is Concrete Product A.");
	}
}

class ConcreteProductB extends Product {
	@Override
	public void display() {
		System.out.println("This is Concrete Product B.");
	}
}

// Creator Abstract Class
abstract class Creator {
	public abstract Product factoryMethod();
}

// Concrete Creators
class ConcreteCreatorA extends Creator {
	@Override
	public Product factoryMethod() {
		return new ConcreteProductA();
	}
}

class ConcreteCreatorB extends Creator {
	@Override
	public Product factoryMethod() {
		return new ConcreteProductB();
	}
}

// Client Code
public class FactoryMethodExample {
	public static void main(String[] args) {
		Creator creatorA = new ConcreteCreatorA();
		Product productA = creatorA.factoryMethod();
		productA.display();

		Creator creatorB = new ConcreteCreatorB();
		Product productB = creatorB.factoryMethod();
		productB.display();
	}
}
```

# Builder Pattern

## Introduction

- The Builder Design Pattern is designed to deal with the construction of comparatively complex objects.
- It delegates the construction of an object to a specialized class aware of the complex process and logic required.
- Helps in creating Single Responsibility classes for complex object creation while ensuring separation of object creation from business logic.
- Leads to code re-usability, reducing the code base, and improving SOLID compliance.

## Definition

- The Builder Design Pattern separates the construction of a complex object from its representation.
- When the complexity of creating an object increases, the Builder pattern can separate out the instantiation process by using another object (a builder) to construct the object.
- This builder can then be used to create many other similar representations using a simple step-by-step approach.

## Motivation

- A reader for the RTF (Rich Text Format) document exchange format should be able to convert RTF to many text formats.
- The problem is that the number of possible conversions is open-ended, so adding a new conversion should be easy without modifying the reader.
- A solution is to configure the RTFReader class with a TextConverter object that converts RTF to another textual representation.
- The Builder pattern captures all these relationships, where each converter class is called a builder, and the reader is called the director.

## Intent

- Separate the construction of a complex object from its representation.
- Allow the same construction process to create different representations of the object.

## Implementation

- **Builder**: Abstract interface for creating objects (product).
- **ConcreteBuilder**: Provides implementation for Builder. Constructs and assembles parts to build the objects.
- **Director**: Constructs an object using the Builder interface.
- **Product**: Represents the complex object under construction.


## Applicability

Use the Builder pattern when:

- The algorithm for creating a complex object should be independent of the parts that make up the object and how they're assembled.
- The construction process must allow different representations for the object that's constructed.
- You want to get rid of a “telescoping constructor” or create different representations of some product.

## Structure

- Builder
- ConcreteBuilder
- Director
- Product

## Participants

- **Builder**: Specifies an abstract interface for creating parts of a Product object.
- **ConcreteBuilder**: Provides implementation for Builder. Constructs and assembles parts of the product by implementing the Builder interface.
- **Director**: Constructs an object using the Builder interface.
- **Product**: Represents the complex object under construction.

## Collaboration

- Client creates Director object and configures it with a Builder.
- Director notifies Builder to build each part of the product.
- Builder handles requests from Director and adds parts to the product.
- Client retrieves the product from the Builder.

## Consequence

- Lets you vary a product’s internal representation by using different Builders.
- Isolates code for construction and representation.
- Gives finer-grain control over the construction process.

## How to Implement

1. Define common construction steps for building all available product representations.
2. Declare these steps in the base builder interface.
3. Create a concrete builder class for each product representation and implement their construction steps.
4. Optionally, create a director class to encapsulate various ways to construct a product using the same builder object.
5. Client code creates both the builder and director objects, passing the builder object to the director before construction starts.
6. Retrieve the construction result from the director or the builder.

## Pros and Cons

**Pros:**
- Construct objects step-by-step, defer construction steps, or run steps recursively.
- Reuse the same construction code for various representations of products.
- Single Responsibility Principle: Isolate complex construction code from the business logic of the product.

**Cons:**
- Increases overall code complexity since the pattern requires multiple new classes.



Before Builder Design Pattern:

```java
// Without Builder Design Pattern
public class Burger {
    private String bun;
    private String meat;
    private String salad;
    private String cheese;
    private String sauce;

    public Burger() {}

    public void setBun(String bun) {
        this.bun = bun;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void print() {
        System.out.println("Burger Details:");
        System.out.println("Bun: " + bun);
        System.out.println("Meat: " + meat);
        System.out.println("Salad: " + salad);
        System.out.println("Cheese: " + cheese);
        System.out.println("Sauce: " + sauce);
    }
}

public class Main {
    public static void main(String[] args) {
        Burger cheeseBurger = new Burger();
        cheeseBurger.setBun("White Bread");
        cheeseBurger.setMeat("Beef");
        cheeseBurger.setSalad("Iceberg");
        cheeseBurger.setCheese("American Cheese");
        cheeseBurger.setSauce("Secret Sauce");
        cheeseBurger.print();
    }
}
```

After Builder Design Pattern:

```java
// With Builder Design Pattern
public class Burger {
    private String bun;
    private String meat;
    private String salad;
    private String cheese;
    private String sauce;

    public Burger(String bun, String meat, String salad, String cheese, String sauce) {
        this.bun = bun;
        this.meat = meat;
        this.salad = salad;
        this.cheese = cheese;
        this.sauce = sauce;
    }

    public void print() {
        System.out.println("Burger Details:");
        System.out.println("Bun: " + bun);
        System.out.println("Meat: " + meat);
        System.out.println("Salad: " + salad);
        System.out.println("Cheese: " + cheese);
        System.out.println("Sauce: " + sauce);
    }
}

public class BurgerBuilder {
    private String bun;
    private String meat;
    private String salad;
    private String cheese;
    private String sauce;

    public BurgerBuilder setBun(String bun) {
        this.bun = bun;
        return this;
    }

    public BurgerBuilder setMeat(String meat) {
        this.meat = meat;
        return this;
    }

    public BurgerBuilder setSalad(String salad) {
        this.salad = salad;
        return this;
    }

    public BurgerBuilder setCheese(String cheese) {
        this.cheese = cheese;
        return this;
    }

    public BurgerBuilder setSauce(String sauce) {
        this.sauce = sauce;
        return this;
    }

    public Burger build() {
        return new Burger(bun, meat, salad, cheese, sauce);
    }
}

public class Main {
    public static void main(String[] args) {
        Burger cheeseBurger = new BurgerBuilder()
                                    .setBun("White Bread")
                                    .setMeat("Beef")
                                    .setSalad("Iceberg")
                                    .setCheese("American Cheese")
                                    .setSauce("Secret Sauce")
                                    .build();
        cheeseBurger.print();
    }
}
```

In the above code:

- Before Builder Design Pattern: The `Burger` class is responsible for both object construction and setting its properties, leading to a complex constructor and tight coupling.
- After Builder Design Pattern: The object construction is delegated to the `BurgerBuilder` class, adhering to the Builder Design Pattern. This results in a more flexible and readable codebase, as the builder class is responsible for creating and configuring the object step-by-step.

# Prototype Pattern

## Motivation

- The Prototype Pattern specifies creating objects using a prototypical instance and creating new objects by copying this prototype.
- It is useful when creating an instance of an object is expensive or complicated, and objects are alike or differ only in terms of their state.
- The pattern allows making new instances without knowing the specific class being instantiated.

## Intent

- Prototype is a creational design pattern that lets you copy existing objects without making your code dependent on their classes.
- In Java, this is typically achieved using the `clone()` method or de-serialization for deep copies.

## Implementation

- **Prototype**: Abstract interface for creating objects with a cloning method.
- **ConcretePrototype**: Implements the cloning method to create new objects by copying existing ones.
- **Client**: Requests a prototype to clone itself to create new instances.

## Example

Problem Statement: Producing exact copies of geometric objects without coupling the code to their classes.

## Applicability

Use the Prototype pattern when:
- A system should be independent of how its products are created, composed, and represented.
- The classes to instantiate are specified at runtime or to avoid building a class hierarchy of factories that parallels the class hierarchy of products.
- Instances of a class can have only a few different combinations of state, and it may be more convenient to clone prototypes rather than instantiate them manually.

## Collaboration

- A client asks a prototype to clone itself to create new instances.

## Consequence

Additional benefits of the Prototype pattern include:
- Adding and removing products at runtime.
- Specifying new objects by varying values or structure.
- Reduced subclassing.
- Configuring an application with classes dynamically.

## Issues to Consider

Consider the following issues when implementing prototypes:
- Using a prototype manager: Keep a registry of available prototypes when the number of prototypes in a system isn't fixed.
- Implementing the Clone operation: Correctly implementing the Clone operation, especially with object structures containing circular references.
- Initializing clones: Some clients may want to initialize the internal state of clones, which can be tricky to handle uniformly.

## Pros and Cons

**Pros:**
- Enables copying of existing objects without knowledge of their specific classes.
- Reduces the need for subclassing.
- Allows adding and removing products at runtime.

**Cons:**
- Tricky implementation of the Clone operation, especially with complex object structures.
- Handling initialization of clones uniformly can be challenging.


### Before Implementation:

```java
// Suppose we have a class representing a geometric shape
class Shape {
    private String type;
    private int width;
    private int height;
    
    // Constructor
    public Shape(String type, int width, int height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }

    // Getters and setters
    // ...

    // Method to create a deep copy of the shape
    public Shape clone() {
        return new Shape(this.type, this.width, this.height);
    }
}
```

### After Implementation:

```java
// Interface representing the prototype
interface Prototype {
    Prototype clone();
}

// Concrete implementation of the prototype
class Shape implements Prototype {
    private String type;
    private int width;
    private int height;

    // Constructor
    public Shape(String type, int width, int height) {
        this.type = type;
        this.width = width;
        this.height = height;
    }

    // Getters and setters
    // ...

    // Method to create a deep copy of the shape
    @Override
    public Prototype clone() {
        return new Shape(this.type, this.width, this.height);
    }
}

// Client class
class Client {
    private Prototype shapePrototype;

    public Client(Prototype shapePrototype) {
        this.shapePrototype = shapePrototype;
    }

    // Method to create a new shape instance by cloning the prototype
    public Prototype createShape() {
        return shapePrototype.clone();
    }
}

// Usage example
public class Main {
    public static void main(String[] args) {
        // Create a prototype instance
        Prototype circlePrototype = new Shape("Circle", 10, 10);

        // Create a client with the prototype
        Client client = new Client(circlePrototype);

        // Create a new shape instance using the prototype
        Prototype newCircle = client.createShape();
        System.out.println("New Circle: " + newCircle);

        // Similarly, you can create more shapes using the same prototype
    }
}
```

In the "Before Implementation" section, we have a basic class representing a geometric shape with a method to create a deep copy of the shape. In the "After Implementation" section, we have refactored the code to follow the Prototype Design Pattern, where the `Shape` class implements the `Prototype` interface and provides a method to clone itself. The `Client` class is responsible for creating new instances by cloning the prototype.
