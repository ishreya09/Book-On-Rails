# GRASP principles

Responsibility - Responsibility in object-oriented design refers to a class or object's obligation or duty to perform specific tasks or provide certain functionalities

Responsibilities in object-oriented design can be categorized into two main types:

- Doing: Involves actions such as creating objects, initiating actions in other objects, and controlling or coordinating activities in other objects. 
- Knowing: Involves knowledge about private encapsulated data, related objects, or information that it can derive or calculate. a) private and public object data b) related objects references c) things it can derive

The GRASP (General Responsibility Assignment Software Patterns) principles are a set of guidelines for assigning responsibilities to classes and objects in object-oriented design. These principles help designers make informed decisions about how to distribute responsibilities among classes and objects, leading to designs that are maintainable, flexible, and scalable.

GRASP principles provide a common vocabulary and framework for discussing and reasoning about design decisions. They help designers identify which classes should be responsible for which tasks, leading to more cohesive and loosely coupled systems.

The primary goals of GRASP principles include:

- Encouraging high cohesion within classes: Ensuring that each class has a clear and well-defined purpose, with related responsibilities grouped together.
- Promoting low coupling between classes: Reducing dependencies between classes to make the system more flexible and easier to maintain.
- Supporting the Single Responsibility Principle (SRP): Ensuring that each class has only one reason to change, thus minimizing the impact of changes in one part of the system on other parts.
- Enabling the creation of reusable and extensible designs: Designing classes and objects in a way that allows them to be easily reused and extended without significant modification.

1. Information Expert
2. Creator
3. Controller
4. Low Coupling
5. High Cohesion
6. Indirection
7. Polymorphism
8. Pure Fabrication
9. Protected Variations


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

### Low Coupling: 

A measure of how strongly one element is connected to, 
has knowledge of, or relies on other elements . Deals with how much 
information the component knows about other components 

Minimizes the dependency hence making system maintainable,  
efficient and code reusable 
- Example: Inheritance, Composition, aggregation, association 


Design classes with minimal dependencies on other classes. Low coupling increases the flexibility and maintainability of the system by reducing the impact of changes in one class on other classes.

A class with high coupling leads to problems: 
- Changes in related classes force local changes 
- Harder to understand in isolation 
- Harder to reuse

INHERITANCE CREATES HIGH COUPLING BETWEEN THE TWO CLASSES
- Makes it challenging to change one class without affecting its subclasses

Consider a point of sale (POS) system where we have classes representing Sale, Payment, and Register. The task is to create a Payment and associate it with a Sale.

```java
// High Coupling Method (Register creates Payment)
public class Register {
    public void processPayment(Sale sale) {
        // Create a Payment
        Payment payment = new Payment();
        // Associate Payment with Sale
        sale.setPayment(payment);
    }
}

// Register class is responsible for creating a Payment and associating it with a Sale. 
// This creates a high coupling between the Register class and the Payment class because the 
// Register class directly depends on the Payment class.


// Low Coupling Approach (Sale creates Payment):
public class Sale {
    public void processPayment() {
        // Create a Payment
        Payment payment = new Payment();
        // Associate Payment with Sale
        setPayment(payment);
    }
}

public class Register {
    public void processPayment(Sale sale) {
        // Request Sale to process Payment
        sale.processPayment();
    }
}

//  the responsibility for creating a Payment and associating it with a Sale is delegated to the Sale class itself. 
// The Register class simply requests the Sale to process the payment. This results in lower coupling between 
// the Register class and the Payment class because the Register class does not directly instantiate the Payment class.

```
**High Coupling Example:**

```java
public class RentVideo {
    private VideoStore videoStore;

    public RentVideo() {
        this.videoStore = new VideoStore();
    }

    public void rentVideo(String videoTitle) {
        Video video = videoStore.getVideo(videoTitle);
        // Rent the video
    }
}

public class VideoStore {
    private List<Video> videos;

    public VideoStore() {
        // Initialize videos
    }

    public Video getVideo(String title) {
        // Find and return the video by title
        return null;
    }
}

public class Video {
    private String title;
    private String genre;

    // Constructor, getters, setters
}
```

In this example:
- `RentVideo` knows about both `VideoStore` and `Video` objects. It depends on both classes.
- `RentVideo` directly creates an instance of `VideoStore` within its constructor and calls methods on it.

**Low Coupling Example:**

```java
public class RentVideo {
    private VideoStore videoStore;

    public RentVideo(VideoStore videoStore) {
        this.videoStore = videoStore;
    }

    public void rentVideo(String videoTitle) {
        Video video = videoStore.getVideo(videoTitle);
        // Rent the video
    }
}

public class VideoStore {
    private List<Video> videos;

    public VideoStore() {
        // Initialize videos
    }

    public Video getVideo(String title) {
        // Find and return the video by title
        return null;
    }
}

public class Video {
    private String title;
    private String genre;

    // Constructor, getters, setters
}
```

In this example:
- `RentVideo` depends on `VideoStore`, but it's now passed as a parameter to `RentVideo`'s constructor, rather than being created directly within the class.
- `RentVideo` only knows about `VideoStore` and relies on it to retrieve videos, but it doesn't directly interact with `Video` objects.

### Explanation:

- In the high coupling example, `RentVideo` directly depends on both `VideoStore` and `Video`, creating a tight coupling between these classes.
- In the low coupling example, `RentVideo` only depends on `VideoStore`, and `VideoStore` encapsulates the logic related to `Video` objects. This reduces the dependency between `RentVideo` and `Video`, leading to lower coupling.
- By reducing coupling, the low coupling example provides greater flexibility and maintainability. Changes to `Video` won't directly impact `RentVideo`, making the codebase easier to modify and extend.

### Controller: 

- Deals with how to delegate the request from the UI layer 
objects to domain layer objects. 
- Helps in minimizing the dependency between GUI components 
and the system operation classes 
- When a request comes from UI layer object, Controller as a pattern 
helps us in determining which is the first object that should receive 
the message from the UI layer objects. – Controller Object 
- Delegates the work to other class and coordinates all activities. 
- Benefits - Can reuse the controller class. Can use to maintain the 
state of the use case. Can control the sequence of the activities

Assign the responsibility for handling system events to a controller class. Controllers act as intermediaries between the user interface and the system, facilitating the flow of information and coordinating actions.

**Facade Controller**: A class representing the overall system, integrating subsystems.

**Use case Controller**:  A class that represent a use case, whereby performs handling particular system operations Often uses same controller class for all system events of one-use case so that one can maintain state information, e.g., events must occur in a certain order

**Bloated Controller**: Single class handling numerous system events, performing multiple tasks without delegation.
- Single class receiving all system events and there are many of them 
- Controller performs many tasks rather than delegating them 
- Controller has many attributes and maintains significant information about system which should have been distributed among other objects 

```java

// The UserController encapsulates the logic for user registration and 
// login, ensuring that these operations are handled consistently and 
// centrally within the system

public class UserController {
    private UserDatabase database;

    public UserController() {
        this.database = new UserDatabase();
    }

    // Method to handle user registration
    public void registerUser(String username, String password) {
        // Validate input
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Create a new user object
            User user = new User(username, password);
            // Save user to the database
            database.saveUser(user);
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // Method to handle user login
    public void loginUser(String username, String password) {
        // Validate input
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Check if the user exists in the database
            if (database.isUserExists(username, password)) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid username or password.");
            }
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}

public class UserDatabase {
    private List<User> users;

    public UserDatabase() {
        this.users = new ArrayList<>();
    }

    // Method to save user to the database
    public void saveUser(User user) {
        users.add(user);
    }

    // Method to check if user exists in the database
    public boolean isUserExists(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
}

public class Main {
    public static void main(String[] args) {
        // Create UserController instance
        UserController userController = new UserController();

        // Register and login users
        userController.registerUser("john", "password123");
        userController.loginUser("john", "password123");
    }
}
```

### High Cohesion: 

Cohesion  - A measure of how strongly related and focused the responsibilities of an element (class, subsystem, etc.)

Ensure that classes have a single, well-defined purpose. High cohesion within classes leads to more modular and understandable designs.

The ShoppingCart class has high cohesion because it contains methods (addItem and calculateTotalPrice) that are related to the shopping cart's functionality.

The addItem method adds an item to the shopping cart, and the calculateTotalPrice method calculates the total price of items in the cart. Both methods are focused on the shopping cart's core responsibility.

```java

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // Method to add an item to the shopping cart
    public void addItem(Item item) {
        items.add(item);
    }

    // Method to calculate the total price of items in the shopping cart
    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}

public class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add items to the shopping cart
        cart.addItem(new Item("Laptop", 1000.0));
        cart.addItem(new Item("Mouse", 20.0));
        cart.addItem(new Item("Keyboard", 50.0));

        // Calculate total price of items in the shopping cart
        double totalPrice = cart.calculateTotalPrice();
        System.out.println("Total Price: $" + totalPrice);
    }
}

```

### Polymorphism: 

Use polymorphism to reduce dependencies between classes. By programming to interfaces rather than concrete implementations, you can create more flexible and extensible systems.

Deals with How to act different depending on object’s class
Guides us in deciding which  object is responsible for handling 
those  varying elements.

**Types:** 
• Adhoc - Overloading 
• Parametric – Early binding 
• Inclusion – Sub typing 
• Coercion - Casting

```java

abstract class Shape {
    abstract double getArea();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    double getArea() {
        return 0.5 * base * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Triangle triangle = new Triangle(4, 3);

        System.out.println("Area of Circle: " + circle.getArea());
        System.out.println("Area of Triangle: " + triangle.getArea());
    }
}

```

This design allows each subclass to implement the getArea() method according to its specific shape, demonstrating polymorphism and the delegation of responsibilities to the appropriate subclasses.

### Indirection: 

- Problem: How to assign responsibilities in order to avoid direct coupling between two components and keep ability for reuse. 
- Solution: Assign responsibility to intermediate class for providing linking between objects not linking directly 

Introduce an intermediary to decouple classes and reduce dependencies. Indirection allows for more flexible and maintainable designs by providing a layer of abstraction between components.

Let's consider a scenario where we have two classes, Client and Server, that need to communicate with each other. However, we want to avoid direct coupling between these classes to enhance flexibility and maintainability. We can introduce an intermediary class, such as a MessageBroker, to facilitate communication between them. Here's a Java code example:

```java

// Intermediary class
class MessageBroker {
    public void sendMessageToServer(String message) {
        // Logic to send message to the server
        System.out.println("Message sent to server: " + message);
    }

    public void sendMessageToClient(String message) {
        // Logic to send message to the client
        System.out.println("Message sent to client: " + message);
    }
}

// Client class
class Client {
    private MessageBroker messageBroker;

    public Client(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void sendMessageToServer(String message) {
        // Client sends message to server via the message broker
        messageBroker.sendMessageToServer(message);
    }
}

// Server class
class Server {
    private MessageBroker messageBroker;

    public Server(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void sendMessageToClient(String message) {
        // Server sends message to client via the message broker
        messageBroker.sendMessageToClient(message);
    }
}

// Main class to demonstrate the usage
public class Main {
    public static void main(String[] args) {
        // Create a message broker
        MessageBroker messageBroker = new MessageBroker();

        // Create client and server instances with the message broker
        Client client = new Client(messageBroker);
        Server server = new Server(messageBroker);

        // Client sends a message to the server
        client.sendMessageToServer("Hello from client!");

        // Server sends a message to the client
        server.sendMessageToClient("Hello from server!");
    }
}

```
The MessageBroker class serves as an intermediary between the Client and Server classes. It provides methods for sending messages to both the client and server.

Indirection is used in software design when there is a need to decouple components and reduce direct dependencies between them.

### Pure Fabrication: 

Introduce additional classes or objects to improve cohesion and reduce coupling. Pure fabrication involves creating classes solely to support the design and does not directly correspond to real-world entities.

Problem: What object should have responsibility when you do not want to violate High Cohesion and Low Coupling, or other goals, but solutions offered by Expert (for example) are not appropriate? Sometimes assigning responsibilities only to domain layer software classes leads to problems like poor cohesion or coupling, or low reuse potential.  
Solution: Assign a highly cohesive set of responsibilities to an artificial or convenience class that does not represent a domain concept 

Let's consider a scenario where we need to handle logging functionality in a system. We want to avoid directly coupling logging logic with domain classes to maintain high cohesion and low coupling. Instead, we can create a separate logging class as a pure fabrication. Here's a Java code example:

```java
// Pure Fabrication class for logging
class Logger {
    public static void log(String message) {
        // Logic to log the message (e.g., write to a file, console, etc.)
        System.out.println("Logging: " + message);
    }
}

// Example domain class
class ProductService {
    public void addProduct(String productName) {
        // Add product logic
        // Logging the action
        Logger.log("Product '" + productName + "' added.");
    }
}

// Main class to demonstrate the usage
public class Main {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        // Add a product and log the action
        productService.addProduct("Laptop");
    }
}
```

In this example:

- The `Logger` class is a pure fabrication class solely responsible for logging messages.
- Instead of directly incorporating logging logic into the `ProductService`, we delegate the logging responsibility to the `Logger` class. This maintains high cohesion within the `ProductService` class and avoids coupling it directly with logging concerns.

By using a pure fabrication class like `Logger`, we improve the maintainability, reusability, and flexibility of the system. It allows us to keep domain classes focused on their core responsibilities while handling auxiliary functionalities separately.

### Controlled / Protected Variations: 

Design classes to protect against variations or changes in other parts of the system. This principle helps to isolate the impact of changes and promote reuse and maintainability.

Problem: How to design objects, subsystems, and systems so that the variations or instability in these elements does not have an undesirable impact on other elements?. 
Solution: Identify points of predicted variation or instability; assign responsibilities to create a stable interface around them. 

Protected variation pattern applying for both variation and Evolution points

let's consider a scenario where we need to design a system that handles different payment methods (e.g., credit card, PayPal) while protecting against variations or changes in these payment methods. We'll use the Protected Variations principle to isolate the impact of changes and promote reuse and maintainability.
```java
// Interface representing a payment method
interface PaymentMethod {
    void processPayment(double amount);
}

// Concrete implementation for Credit Card payment
class CreditCardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        // Logic to process credit card payment
        System.out.println("Processing credit card payment for amount: $" + amount);
    }
}

// Concrete implementation for PayPal payment
class PayPalPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        // Logic to process PayPal payment
        System.out.println("Processing PayPal payment for amount: $" + amount);
    }
}

// Payment processing service
class PaymentService {
    private PaymentMethod paymentMethod;

    public PaymentService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void processPayment(double amount) {
        paymentMethod.processPayment(amount);
    }
}

// Main class to demonstrate the usage
public class Main {
    public static void main(String[] args) {
        // Use Credit Card payment method
        PaymentMethod creditCardPayment = new CreditCardPayment();
        PaymentService paymentService = new PaymentService(creditCardPayment);
        paymentService.processPayment(100.0); // Process payment using Credit Card

        // Use PayPal payment method
        PaymentMethod paypalPayment = new PayPalPayment();
        paymentService = new PaymentService(paypalPayment);
        paymentService.processPayment(50.0); // Process payment using PayPal
    }
}

```

By using the Protected Variations principle, we isolate the impact of changes in payment methods. If there are changes or variations in the way payments are processed (e.g., adding a new payment method), we can easily create a new implementation of the PaymentMethod interface without affecting other parts of the system.

