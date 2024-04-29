# SOLID Principles

Leads to more flexible and stable software architecture that’s easier to maintain and extend, and less likely to break 

- SOLID principles are software design coding standards 
- Helps us to obtain good software with low coupling and high cohesion   
- Applied to reduce dependencies - changes in one part of software will not be impacting others.

SOLID principles are software design coding standards that lead to more flexible and stable software architecture that’s easier to maintain and extend, and less likely to break. They help us to obtain good software with low coupling and high cohesion, applied to reduce dependencies so that changes in one part of the software will not impact others.

## Definitions of SOLID Principles:

### 1. Single Responsibility Principle (SRP):
States that every module or class should have responsibility over a single part of the functionality provided by the software.

A class should have one, and only one, reason to change, meaning that a class should have only one job or responsibility. If a class has more than one responsibility, it becomes more difficult to change and maintain.

Ensures low coupling, easier understanding and maintainability, organized classes.

```java
// VIOLATION OF SRP
class Book {
    String title;
    String author;

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    void searchBook() {
        // Logic to search for the book in the inventory
    }
}

// FOLLOWS SRP
class Book {
    String title;
    String author;

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }
}

// InventoryView Class: A new class called InventoryView is created to handle the responsibility of checking the inventory. 
// The searchBook() method is moved to this class, and it references the Book class in its constructor to perform the search operation.
class InventoryView {
    Book book;

    InventoryView(Book book) {
        this.book = book;
    }

    void searchBook() {
        // Logic to search for the book in the inventory
    }
}


```

### 2. Open/Closed Principle (OCP):
Software entities should be open for extension but closed for modification, meaning that classes should be designed in a way that allows new functionality to be added without altering existing code.

- A software entity must be easily extensible with new features without having to modify its existing code in use. 
- We should be able to extend the existing code using OOP features like **inheritance via subclasses and interfaces**.
- Never modify classes, interfaces, and other code units that already exist, as it can lead to unexpected behavior.  
- While adding  a new feature extend the code rather than modifying it, so that the risk of failure is minimized

```java

// This class is open for extension but closed for modification.
class CookbookDiscount { 
    String getCookbookDiscount() { 
        String discount = "30% between Dec 1 and 24"; 
        return discount; 
    } 
} 
// SRP principle
class DiscountManager { 
    void processCookbookDiscount(CookbookDiscount 
discount) {...} 
}
// The discount mannager works fine until the a new Discount like BiographyDiscount comes as well
// The DiscountManager class will have to be modified to accommodate the new discount type.
// This violates the Open/Closed Principle, as the class is not closed for modification.

// To fix this, we can create an interface called Discountable that defines the method getDiscount().
interface Discountable { 
    String getDiscount(); 
}

// CookbookDiscount and BiographyDiscount classes implement the Discountable interface and provide their own implementation of the getDiscount() method.
class CookbookDiscount implements Discountable { 
    @Override 
    public String getDiscount() { 
        return "30% between Dec 1 and 24"; 
    } 
} 
  
class BiographyDiscount extends CookbookDiscount { 
    @Override 
    public String getDiscount() { 
        return "20% discount for your birthday"; 
    } 
}

// The DiscountManager class is modified
class DiscountManager { 
    void processDiscount(Discountable discount) { 
        String discountValue = discount.getDiscount(); 
        // Process the discount value 
    } 
}

// DiscountManager can refer to the Discountable interface instead of the concrete classes. 
// When the processDiscount() method is called, pass both CookbookDiscount and BiographyDiscount as an argument, 
// as both are the implementation of the Discountable interface. 
```

If OCP is not followed:
- End up testing the entire functionality.
- QA Team needs to test the entire flow, leading to a costly process for the organization.
- Breaks the SRP, resulting in increased maintenance overhead on the classes.

This situation can lead to inefficiencies in testing, increased costs, and decreased maintainability of the codebase.


### 3. Liskov Substitution Principle (LSP):
Objects of a superclass should be replaceable with objects of its subclasses without affecting the correctness of the program.

“Let S be a subtype of T, then for each object o1 of type S there is an object o2 of type T such that for all programs P defined in terms of T, the behaviour of P is unchanged when o1 is substituted for o2”. 

- If a class is a subclass of another class, it should be able to replace that class without affecting the behavior of the program.

- A child class should never change the characteristics of its parent class 
- Derived classes should never do less than their base class. 
- Applies to inheritance(is – a relationship) hierarchies and avoids overuse/misuse

```java

class BookDelivery {
    String title;
    int userID;

    void getDeliveryLocations() {
        // Logic to get delivery locations
    }
}

class HardcoverDelivery extends BookDelivery {
    @Override
    void getDeliveryLocations() {
        // Override to provide specific delivery locations for hardcover books
    }
}

class AudiobookDelivery extends BookDelivery {
    @Override
    void getDeliveryLocations() {
        // Audiobooks cannot be delivered to physical locations,
        // so this method cannot be implemented appropriately
    }
}
// AudiobookDelivery subclass cannot appropriately implement the getDeliveryLocations() method 
// because audiobooks cannot be physically delivered. This leads to a violation of the Liskov Substitution Principle.

```
**Problem:**
- Initially, we have a `BookDelivery` class with a `getDeliveryLocations()` method.
- We create a `HardcoverDelivery` subclass that extends `BookDelivery` and overrides the `getDeliveryLocations()` method to handle physical delivery locations.
- Later, we create an `AudiobookDelivery` subclass also extending `BookDelivery`. However, audiobooks cannot be physically delivered, so the `getDeliveryLocations()` method cannot be implemented for audiobook delivery.

**Violation of LSP:**
- The `AudiobookDelivery` subclass cannot fulfill the contract of its superclass (`BookDelivery`) because it cannot implement the `getDeliveryLocations()` method as expected. This violates the Liskov Substitution Principle.

**Solution:**
- To solve this problem, we need to refactor the inheritance hierarchy to better differentiate between book delivery types.
- Introduce an extra layer of abstraction by creating `OfflineDelivery` and `OnlineDelivery` classes to split up the responsibilities of `BookDelivery`.
- Move the `getDeliveryLocations()` method to `OfflineDelivery` and create a new `getSoftwareOptions()` method for `OnlineDelivery` (more suitable for digital deliveries).
- This refactoring provides a clearer and more flexible design where each subclass has appropriate methods to override based on its specific functionality.

```java
// FOLLOWS LSP
class BookDelivery {
    String title;
    int userID;
}

class OfflineDelivery extends BookDelivery {
    void getDeliveryLocations() {
        // Logic to get physical delivery locations
    }
}

class OnlineDelivery extends BookDelivery {
    void getSoftwareOptions() {
        // Logic to get software delivery options
    }
}

class HardcoverDelivery extends OfflineDelivery {
    @Override
    void getDeliveryLocations() {
        // Override to provide specific delivery locations for hardcover books
    }
}

class AudiobookDelivery extends OnlineDelivery {
    @Override
    void getSoftwareOptions() {
        // Override to provide specific software options for audiobooks
    }
}
```

**Explanation:**
- In the refactored design, we have separate classes (`OfflineDelivery` and `OnlineDelivery`) to represent different types of book deliveries, each with its own responsibilities.
- Subclasses (`HardcoverDelivery` and `AudiobookDelivery`) extend the appropriate delivery type class and override methods as needed for their specific functionality.
- This design adheres to the Liskov Substitution Principle by allowing subclasses to fulfill the contracts of their superclasses without violating the expected behavior.


### 4. Interface Segregation Principle (ISP):
Clients should not be forced to depend on interfaces they do not use. Instead of one large interface, it’s better to have smaller, more specific interfaces.

Make fine grained interfaces that are client-specific. Clients should not be forced to implement interfaces they do not use
Instead, start by building a new interface and then let your class implement multiple interfaces as needed.
Create smaller interfaces that you can implement more flexibly
Failure to comply with this principle means that in our implementations we will have dependencies on methods that we do not need but that we are obliged to define.

```java
// VIOLATION OF ISP
public interface BookAction { 
    void seeReviews(); 
    void searchSecondhand(); 
    void listenSample(); 
} 

// Both classes depend on methods they don’t use, so it violates the Interface Segregation Principle
class HardcoverUI implements BookAction { 
    @Override 
    public void seeReviews() 
        {...} 
    @Override 
    public void searchSecondhand() 
        {...} 
    @Override 
    public void listenSample() 
        {...} 
}

class AudiobookUI implements BookAction { 
    @Override 
    public void seeReviews() 
        {...} 
    @Override 
    public void searchSecondhand() 
        {...} 
    @Override 
    public void listenSample() 
        {...} 
} 

```

```java
// FIXED WITH ISP

// Extend the code with two more specific sub-interfaces: HardcoverAction and AudioAction. 
interface BookAction {
    void seeReviews();
}

interface HardcoverAction extends BookAction {
    void flipPages();
}

interface AudioAction extends BookAction {
    void listenSample();
}

class AudiobookUI implements AudioAction {
    @Override
    public void seeReviews() {
        // Implementation to see reviews for audiobooks
    }

    @Override
    public void listenSample() {
        // Implementation to listen to a sample of the audiobook
    }
}

class HardcoverUI implements HardcoverAction {
    @Override
    public void seeReviews() {
        // Implementation to see reviews for hardcover books
    }

    @Override
    public void flipPages() {
        // Implementation to flip pages of the hardcover book
    }
}


```

### 5. Dependency Inversion Principle (DIP):
High-level modules should not depend on low-level modules. Both should depend on abstractions. 
Abstractions should not depend on details; rather, details should depend on abstractions.

The goal is to avoid tightly coupled code, as it easily breaks the application.  
- Decouple high-level and low-level classes. The interaction between them must be thought of as an abstract interaction 
- According to Robert C Martin,  Dependency Inversion Principle is a specific combination of the Open-Closed and Liskov Substitution Principles.

**Before:**

```java
class Book {
    void seeReviews() {
        // Logic to see reviews of the book
    }

    void readSample() {
        // Logic to read a sample of the book
    }
}

class Shelf {
    Book book;

    void addBook(Book book) {
        // Logic to add a book to the shelf
    }

    void customizeShelf() {
        // Logic to customize the shelf
    }
}
```

In this code:
- We have a `Book` class with methods to see reviews and read a sample of the book.
- The `Shelf` class has a dependency on the `Book` class, allowing users to add books to their shelf and customize the shelf.

If we add DVD, we will need to modify the Shelf class to accommodate the new product type, violating the Open/Closed Principle.

**After:**

```java
public interface Product {
    void seeReviews();
    void getSample();
}

class Book implements Product {
    @Override
    public void seeReviews() {
        // Implementation to see reviews of the book
    }

    @Override
    public void getSample() {
        // Implementation to read a sample of the book
    }
}

class DVD implements Product {
    @Override
    public void seeReviews() {
        // Implementation to see reviews of the DVD
    }

    @Override
    public void getSample() {
        // Implementation to watch a sample of the DVD
    }
}

class Shelf {
    Product product;

    void addProduct(Product product) {
        // Logic to add a product to the shelf
    }

    void customizeShelf() {
        // Logic to customize the shelf
    }
}

// Code follows the Liskov Substitution Principle, as 
// the Product type can be substituted with both of its 
// subtypes (Book and DVD) without breaking the program.  
// At the same time, we have also implemented the 
// Dependency Inversion Principle, as high-level classes 
// don’t depend on low-level classes

```

In this refactored code:
- We introduce the `Product` interface to abstract the common behavior of products.
- Both `Book` and `DVD` classes implement the `Product` interface, providing implementations for `seeReviews()` and `getSample()` methods.
- The `Shelf` class references the `Product` interface instead of specific implementations (e.g., `Book` or `DVD`), allowing it to accept any product type.
- This design follows the Dependency Inversion Principle, as high-level classes (`Shelf`) depend on abstractions (`Product`) rather than concrete implementations (`Book` or `DVD`).


## Examples in Java:

### Single Responsibility Principle (SRP):
```java
class Printer {
    public void print(String content) {
        System.out.println("Printing: " + content);
    }
}

class Logger {
    public void log(String message) {
        System.out.println("Logging: " + message);
    }
}
```

### Open/Closed Principle (OCP):
```java
interface Shape {
    double area();
}

class Rectangle implements Shape {
    private double width;
    private double height;

    @Override
    public double area() {
        return width * height;
    }
}

class Circle implements Shape {
    private double radius;

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
```

### Liskov Substitution Principle (LSP):
```java
class Bird {
    public void fly() {
        System.out.println("Flying...");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        // Ostrich cannot fly
        throw new UnsupportedOperationException("Ostrich cannot fly.");
    }
}
```

### Interface Segregation Principle (ISP):
```java
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class MultifunctionPrinter implements Printer, Scanner {
    @Override
    public void print() {
        System.out.println("Printing...");
    }

    @Override
    public void scan() {
        System.out.println("Scanning...");
    }
}
```

### Dependency Inversion Principle (DIP):
```java
interface MessageSender {
    void sendMessage(String message);
}

class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        // Send message via email
    }
}

class SMS implements MessageSender {
    @Override
    public void sendMessage(String message) {
        // Send message via SMS
    }
}
```
