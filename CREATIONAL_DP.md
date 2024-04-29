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

### Factory Pattern

### Builder Pattern

### Prototype 
Pattern


