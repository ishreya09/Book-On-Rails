# GRASP principles

Responsibility - Responsibility in object-oriented design refers to a class or object's obligation or duty to perform specific tasks or provide certain functionalities

Responsibilities in object-oriented design can be categorized into two main types:

- Doing: Involves actions such as creating objects, initiating actions in other objects, and controlling or coordinating activities in other objects.
- Knowing: Involves knowledge about private encapsulated data, related objects, or information that it can derive or calculate.

The GRASP (General Responsibility Assignment Software Patterns) principles are a set of guidelines for assigning responsibilities to classes and objects in object-oriented design. These principles help designers make informed decisions about how to distribute responsibilities among classes and objects, leading to designs that are maintainable, flexible, and scalable.

GRASP principles provide a common vocabulary and framework for discussing and reasoning about design decisions. They help designers identify which classes should be responsible for which tasks, leading to more cohesive and loosely coupled systems.

The primary goals of GRASP principles include:

- Encouraging high cohesion within classes: Ensuring that each class has a clear and well-defined purpose, with related responsibilities grouped together.
- Promoting low coupling between classes: Reducing dependencies between classes to make the system more flexible and easier to maintain.
- Supporting the Single Responsibility Principle (SRP): Ensuring that each class has only one reason to change, thus minimizing the impact of changes in one part of the system on other parts.
- Enabling the creation of reusable and extensible designs: Designing classes and objects in a way that allows them to be easily reused and extended without significant modification.

## Types of GRASP Principles:


### Creator:

DECIDE WHO CAN BE CREATOR BASED ON OBJECT'S ASSOCIATION AND THEIR INTERACTION

Assign the responsibility for creating an instance of a class to the class itself or another class that aggregates or contains the class in question. This helps to ensure that objects are created in a way that maintains consistency and encapsulation.

```java

// According to the Creator principle, the responsibility for creating 
// instances of the Car class should be assigned to a class that aggregates 
// or contains the Car class.

// Car class
public class Car {
    private String model;
    private String color;

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    // Getters and setters
    // ...
}

// CarFactory class responsible for creating Car instances
public class CarFactory {
    public Car createCar(String model, String color) {
        // Here, CarFactory is responsible for creating instances of Car
        return new Car(model, color);
    }
}

// Main class to demonstrate how the Creator principle works
public class Main {
    public static void main(String[] args) {
        // Create a Car instance using CarFactory
        CarFactory carFactory = new CarFactory();
        Car car = carFactory.createCar("Toyota", "Red");

        // Output car details
        System.out.println("Model: " + car.getModel());
        System.out.println("Color: " + car.getColor());
    }
}

```
```java
import java.util.ArrayList;
import java.util.List;

// Sale contains SalesLineItem objects, it's suggested that the Sale class should be responsible for creating 
// instances of SalesLineItem objects according to the Creator pattern.

public class Sale {
    private List<SalesLineItem> salesLineItems;

    public Sale() {
        this.salesLineItems = new ArrayList<>();
    }

    // Method responsible for adding SalesLineItem to the Sale
    public void addSalesLineItem(Product product, int quantity) {
        SalesLineItem salesLineItem = createSalesLineItem(product, quantity);
        salesLineItems.add(salesLineItem);
    }

    // Creator method responsible for creating SalesLineItem instances
    private SalesLineItem createSalesLineItem(Product product, int quantity) {
        return new SalesLineItem(product, quantity);
    }
}

public class SalesLineItem {
    private Product product;
    private int quantity;

    public SalesLineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and setters
}

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
}

public class Main {
    public static void main(String[] args) {
        // Creating a Product
        Product product = new Product("Laptop", 1000.0);
        
        // Creating a Sale and adding SalesLineItem
        Sale sale = new Sale();
        sale.addSalesLineItem(product, 2);
    }
}

```


### Information Expert: 

OBJECTS DO THINGS RELATED TO INFORMATION THEY HAVE

Assign responsibility to the class that has the most information required to fulfill it. This principle emphasizes placing methods in the class with the most knowledge to ensure high cohesion and low coupling.

```java
// This implementation adheres to the Information Expert principle by assigning responsibilities 
// to the classes that have the necessary information to perform operations

import java.util.ArrayList;
import java.util.List;


// In the Sale class, the method calculateTotal is responsible for calculating the total sale amount. 
// It iterates through each SalesLineItem in the salesLineItems list and calculates the subtotal for each line item 
// using the calculateSubtotal method of the SalesLineItem class.
// The SalesLineItem class is responsible for calculating the subtotal for each line item. 
// It uses the quantity attribute and the getPrice method of the associated Product object to calculate the subtotal.
// The Product class is responsible for providing information about the product, such as its name and price.


public class Sale {
    private List<SalesLineItem> salesLineItems;

    public Sale() {
        this.salesLineItems = new ArrayList<>();
    }

    // Method to add sales line items to the sale
    public void addSalesLineItem(Product product, int quantity) {
        SalesLineItem salesLineItem = new SalesLineItem(product, quantity);
        salesLineItems.add(salesLineItem);
    }

    // Method to calculate the total sale amount
    public double calculateTotal() {
        double total = 0.0;
        for (SalesLineItem lineItem : salesLineItems) {
            total += lineItem.calculateSubtotal();
        }
        return total;
    }
}

public class SalesLineItem {
    private Product product;
    private int quantity;

    public SalesLineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Method to calculate subtotal for the line item
    public double calculateSubtotal() {
        return product.getPrice() * quantity;
    }
}

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating products
        Product product1 = new Product("Laptop", 1000.0);
        Product product2 = new Product("Mouse", 20.0);

        // Creating a sale
        Sale sale = new Sale();
        sale.addSalesLineItem(product1, 2);
        sale.addSalesLineItem(product2, 1);

        // Calculating total sale amount
        double total = sale.calculateTotal();
        System.out.println("Total Sale Amount: $" + total);
    }
}


```

### Controller: 

Assign the responsibility for handling system events to a controller class. Controllers act as intermediaries between the user interface and the system, facilitating the flow of information and coordinating actions.

### Low Coupling: 

Design classes with minimal dependencies on other classes. Low coupling increases the flexibility and maintainability of the system by reducing the impact of changes in one class on other classes.

### High Cohesion: 

Ensure that classes have a single, well-defined purpose. High cohesion within classes leads to more modular and understandable designs.

### Polymorphism: 

Use polymorphism to reduce dependencies between classes. By programming to interfaces rather than concrete implementations, you can create more flexible and extensible systems.
### Pure Fabrication: 

Introduce additional classes or objects to improve cohesion and reduce coupling. Pure fabrication involves creating classes solely to support the design and does not directly correspond to real-world entities.

### Indirection: 

Introduce an intermediary to decouple classes and reduce dependencies. Indirection allows for more flexible and maintainable designs by providing a layer of abstraction between components.


### Protected Variations: 

Design classes to protect against variations or changes in other parts of the system. This principle helps to isolate the impact of changes and promote reuse and maintainability.

