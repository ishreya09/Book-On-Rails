# STRUCTURAL DESIGN PATTERNS

- Structural design patterns provide different ways to create a class structure, for example using inheritance and composition to create a large object from small objects.

- Structural design patterns are concerned with how classes and objects can be composed to form larger structures.
- These patterns simplify the structure by identifying the relationships and focus on how classes inherit from each other and how they are composed from other classes.
- Structural class patterns use inheritance to compose interfaces or implementations.
- They explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient.

# Adapter
- The Adapter pattern is a structural design pattern that allows objects with incompatible interfaces to collaborate. It acts as a bridge between two incompatible interfaces.

### Adapter Pattern

The Adapter pattern serves as a bridge between two incompatible interfaces, allowing them to work together seamlessly. It's like using an adapter to connect two different types of plugs in the real world.

#### Motivation
- **Interface Adaptation:** Adapter pattern adapts between classes and objects, providing a bridge between them.
- **Reusability:** It allows previously incompatible objects to interact and promotes reusability of existing functionality.

#### Intent
- **Enable Collaboration:** Adapter allows classes with incompatible interfaces to collaborate.
- **Interface Conversion:** It converts the interface of a class into another interface that a client requires.
- **Wrapper Design:** Adapter pattern is also known as the Wrapper pattern.

#### Structure
- **Target:** Defines the domain-specific interface that the client uses.
- **Adapter:** Adapts the interface of the Adaptee to the Target interface.
- **Adaptee:** Defines an existing interface that needs adapting.
- **Client:** Collaborates with objects conforming to the Target interface.

#### Implementation Example
Consider a scenario where we have an `AudioPlayer` class that plays only mp3 format files. We want to extend its functionality to play other formats like `vlc` and `mp4`. We can achieve this using the Adapter pattern by creating a `MediaAdapter` class that adapts the `AdvancedMediaPlayer` interface to the `MediaPlayer` interface.

```java
// Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee interface
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Concrete Adapter
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;
    
    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}

// Concrete Adaptee
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

// Concrete Adaptee
class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}
```

#### Applicability
- When you want to use an existing class, but its interface does not match the one you need.
- When you want to create a reusable class that can work with unrelated or unforeseen classes.
- When you need to use several existing subclasses but adapting their interfaces via subclassing is impractical.

#### Consequences
- **Improved Flexibility:** Adapter pattern allows for improved code flexibility by enabling interaction between incompatible interfaces.
- **Encapsulation:** It encapsulates the complexities of interface conversion, making the code more modular and maintainable.
- **Performance Overhead:** There might be a slight performance overhead due to additional method calls and object creations involved in the adaptation process.

#### Issues to Consider
- **Determining Adapter Responsibilities**: Assess how much functionality the adapter should encapsulate. If the Target and Adaptee interfaces are similar, the adapter can simply delegate requests from the Target to the Adaptee. However, if the interfaces are not similar, the adapter may need to perform additional tasks such as data structure conversion or implementing operations required by the Target but not implemented by the Adaptee.
- **Two-Way Adapters for Transparency:** Adapters may not always be transparent to all clients. When an object is adapted, it no longer fully conforms to the Adaptee interface, which can lead to compatibility issues. Two-way adapters address this problem by providing transparency. These adapters allow an adapted object to be used wherever an Adaptee object can be used, ensuring compatibility across different clients. They are particularly useful when two different clients need to view an object differently.

In summary, the Adapter pattern facilitates collaboration between incompatible interfaces and promotes code reusability and flexibility. It's a valuable tool for integrating diverse systems and components.

| Feature                       | Object Adapter                                    | Class Adapter                                        |
|-------------------------------|---------------------------------------------------|------------------------------------------------------|
| Composition vs. Inheritance   | Uses composition                                  | Uses inheritance                                     |
| Subclass Adaptation           | Adapts not only the Adaptee, but also its subclasses | Adapts the specific Adaptee class (may not adapt subclasses) |
| Handling New Methods         | Requires modification or extension to expose new methods | Automatically inherits new methods from Adaptee class |
| Delegation Code              | Requires writing code for delegating requests to the Adaptee | No explicit delegation code needed                   |
| Flexibility                   | Less flexible as it requires modification for new methods | More flexible as it automatically inherits new methods |
| Interface Adaptation (If Target is an Interface) | Can implement multiple interfaces to adapt to different Targets | Can only adapt a single interface (Adaptee's)         |

This table summarizes the differences between Object Adapter and Class Adapter patterns, highlighting their respective features, advantages, and disadvantages.


# Facade Pattern
- The Facade pattern is a structural design pattern that provides a simplified interface to a complex system of classes, interfaces, and objects. It hides the complexities of the system and provides an interface to the client using which the client can access the system.

- An object that provides a simplified interface to a larger body of code, a library, a framework, or any other complex set of classes.
- The main intent of Facade is to provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.
- Example: `fopen` is an interface to open and read system commands.
- A structural design pattern, which wraps a complicated subsystem with a simpler interface.
- If the Facade is the only access point for the subsystem, it will limit the features and flexibility that "power users" may need.

## Why Facade?

- Structuring a system into subsystems helps **reduce complexity**.
- A common design goal is to **minimize the communication and dependencies between subsystems**. One way to achieve this goal is to introduce a facade object that provides a single, simplified interface to the more general facilities of a subsystem.

## Applicability

- To provide a simple interface to a complex subsystem. A facade can provide a simple default view of the subsystem that is good enough for most clients.
- Introduce a facade to decouple the subsystem from clients and other subsystems, thereby promoting subsystem independence and portability.
- Use a facade to define an entry point to each subsystem level.
- To simplify the dependencies between subsystems by making them communicate with each other solely through their facades.

## Advantages

- It shields clients from subsystem components, thereby reducing the number of objects that clients deal with and making the subsystem easier to use.
- It promotes weak coupling between the subsystem and its clients. Often the components in a subsystem are strongly coupled.
- This can eliminate complex or circular dependencies. This can be an important consequence when the client and the subsystem are implemented independently.
- Reducing compilation dependencies with facades can limit the recompilation needed for a small change in an important subsystem.

## Known uses

- In the ET++ application framework, an application that can have built-in browsing tools for inspecting its objects at run-time. These browsing tools are implemented in a separate subsystem that includes a facade class called "ProgrammingEnvironment." This facade defines operations such as InspectObject and InspectClass for accessing the browsers.
- The Choices operating system uses facade to compose many frameworks into one. The key abstractions in Choices are processes, storage, and address spaces. For each of these abstractions, there is a corresponding subsystem, implemented as a framework, that supports porting Choices to a variety of different hardware platforms.

## Implementation

- **Reduce client-subsystem coupling.** This can be done by simply creating a "Facade Abstract Class" and concrete subclasses for the implementation of the subsystem. Now the client class can communicate with the subsystem through the "Abstract Facade Class“.
- An alternative approach is to configure a facade object with different subsystem objects.
- Public versus Private Interfaces. Classes and Subsystems are similar. Both encapsulate something. Both have private and public interfaces. The public interface consists of classes accessible by all Clients whereas the private interface is only for subsystem extenders. Facade is part of the public interface


Consider a scenario where a computer system is composed of various subsystems such as CPU, Memory, and Hard Drive. The Facade pattern provides a simplified interface to the client for performing complex operations involving these subsystems.

```java
// Subsystem classes
class CPU {
    public void processData() {
        System.out.println("Processing data...");
    }
}

class Memory {
    public void load() {
        System.out.println("Loading data into memory...");
    }
}

class HardDrive {
    public void readData() {
        System.out.println("Reading data from hard drive...");
    }
}

// Facade class
class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        cpu.processData();
        memory.load();
        hardDrive.readData();
        System.out.println("Computer started successfully!");
    }
}

// Client class
public class Main {
    public static void main(String[] args) {
        // Client uses the Facade to start the computer
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();
    }
}
```

In this example:
- We have three subsystem classes: `CPU`, `Memory`, and `HardDrive`, each responsible for a specific operation.
- The `ComputerFacade` class provides a simplified interface for the client to start the computer. It hides the complexity of interacting with the subsystems.
- The client class `Main` interacts with the `ComputerFacade` to start the computer without needing to know the details of how each subsystem works.

This example demonstrates how the Facade pattern simplifies the usage of a complex system by providing a unified interface.

# Proxy Pattern
- The Proxy pattern is a structural design pattern that provides a surrogate or placeholder object to control access to another object. It allows you to create a class that represents the functionality of another class.

The Proxy design pattern provides a surrogate or placeholder for another object to control access to it. It allows you to perform operations either before or after the request reaches the original object.

#### Intent

- **Lazy Loading:** Proxy is heavily used to implement lazy loading scenarios where the full object creation is deferred until it is actually needed.
- **Provide Functionality:** Using the proxy pattern, a class represents the functionality of another class.

#### Design Participants

- **Subject:** Interface exposing the functionality available to clients.
- **Real Subject:** Concrete implementation of the Subject interface that needs to be hidden behind a proxy.
- **Proxy:** Hides the real object by extending it, and clients communicate with the real object via this proxy.

#### Applicability

The Proxy Pattern can be used when:

- Data can be cached to improve responsiveness.
- The Real Subject is in a remote location and requires wrapping and unwrapping of requests for making remote calls.
- The Real Subject should be protected from unauthorized access.
- A Virtual Proxy is needed for lazy initialization.

#### Advantages

- The proxy works even if the service object isn’t ready or available.
- New proxies can be introduced without changing the service or clients, following the Open/Closed Principle.

#### Disadvantages

- Introduces an additional layer between the client and the Real Subject, increasing code complexity.
- Response from the service might get delayed.
- Additional request forwarding is introduced between the client and the Real Subject.

#### Usage Examples

1. Image viewer program that lists and displays high-resolution photos.
2. Document editor that embeds graphical objects in a document.
3. Protective proxy acting as an authorization layer.
4. Adding a thread-safe feature to an existing class without changing its code.

#### Example Code

Here's a simple example demonstrating the Proxy pattern in Java:

```java
// Subject Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk(filename);
    }

    private void loadFromDisk(String filename) {
        System.out.println("Loading " + filename);
    }

    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Image1 will be loaded from disk
        image1.display();
        // Image1 will not be loaded again, already loaded from disk
        image1.display();
        // Image2 will be loaded from disk
        image2.display();
    }
}
```

This example demonstrates how the Proxy pattern can help with lazy initialization and caching to improve performance.

```java
// Subject Interface
interface Account {
    void withdraw(double amount);
}

// Real Subject
class BankAccount implements Account {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + ". Remaining balance: $" + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}

// Proxy
class Check implements Account {
    private BankAccount bankAccount;

    public Check(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void withdraw(double amount) {
        System.out.println("Processing check for $" + amount);
        bankAccount.withdraw(amount);
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(1000.0);
        Account check = new Check(bankAccount);

        // Using the check to withdraw funds
        check.withdraw(500.0);
        check.withdraw(800.0); // This will result in insufficient funds
    }
}
```

In this example, the `BankAccount` class represents the real subject, which holds the actual funds. The `Check` class acts as a proxy, allowing withdrawals to be made from the bank account indirectly. The client interacts with the `Check` proxy object, which internally delegates the `withdraw` operation to the `BankAccount` object.

# Flyweight Design Pattern
- The Flyweight pattern is a structural design pattern that minimizes memory usage or computational expenses by sharing as much as possible with related objects. It is used to reduce the number of objects created and to decrease memory footprint and increase performance.

The Flyweight pattern is used to reduce the number of objects created and to decrease memory footprint and increase performance. It achieves this by sharing as much as possible with related objects, rather than creating new instances.

### Flyweight Design Pattern - Introduction

The flyweight pattern is for sharing objects, where each instance does not contain its own state but stores it externally. This allows efficient sharing of objects to save space when there are many instances but a few different types.

### Flyweight Design Pattern - Intent

- Use sharing to support a large number of fine-grained objects efficiently.
- Flyweight is a structural design pattern that lets you fit more objects into the available amount of RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.

### Flyweight Pattern - Motivation

- An application uses many objects.
- A flyweight is a shared object that can be used in multiple contexts simultaneously.
- It acts as an individual object in each context.
- The key concept is the distinction between intrinsic and extrinsic state.

### Problem

Placing the same information in many different places:
1. Mistakes can be made in copying the information.
2. If the data changes, all occurrences of the information must be located and changed. This makes maintenance of the document difficult.
3. Documents can become quite large if the same information is repeated over and over again.
4. RAM overhead associated with each instance.
5. CPU overhead associated with memory management.

### Solution

Create Flyweight Objects for Intrinsic state:
- **Intrinsic State**: Information independent of the object’s context, sharable (e.g., state might include name, postal abbreviation, time zone, etc.). This is included in the flyweight and stored only once instead of storing the same information multiple times for multiple objects.
- **Extrinsic State**: Information dependent on the object’s context, unshareable, stateless, having no stored values, but values that can be calculated on the spot (e.g., state might need access to region). This is excluded from the Flyweight.

### Flyweight Participants

- **Flyweight**: Declares an interface through which flyweights can receive and act on extrinsic state.
- **ConcreteFlyweight**: Implements the Flyweight interface and adds storage for intrinsic state, if any.
- **UnsharedConcreteFlyweight**: Not all Flyweight subclasses need to be shared.
- **FlyweightFactory**: Creates and manages flyweight objects.
- **Client**: Maintains a reference to flyweight(s) and computes or stores the extrinsic state of flyweight(s).

### Applicability

- An application uses a large number of objects.
- Storage costs are high because of the sheer quantity of objects.
- Most object state can be made extrinsic.
- Many groups of objects may be replaced by relatively few shared objects once extrinsic state is removed.
- The application doesn't depend on object identity.

### Consequences

- **Disadvantage**: Flyweights may introduce run-time costs associated with transferring, finding, and/or computing extrinsic state, especially if it was formerly stored as intrinsic state.
- **Advantage**: Storage savings are a function of several factors.

### Implementation

- Removing extrinsic state.
- Managing shared objects.

### Example

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface Character {
    void draw();
}

// ConcreteFlyweight class representing a character
class ConcreteCharacter implements Character {
    private final char symbol;

    public ConcreteCharacter(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void draw() {
        System.out.print(symbol);
    }
}

// Flyweight factory class to manage flyweight objects
class CharacterFactory {
    private static final Map<Character, ConcreteCharacter> characters = new HashMap<>();

    public static Character getCharacter(char symbol) {
        ConcreteCharacter character = characters.get(symbol);
        if (character == null) {
            character = new ConcreteCharacter(symbol);
            characters.put(symbol, character);
        }
        return character;
    }
}

// Client code
public class DocumentEditor {
    public static void main(String[] args) {
        String documentText = "Hello, world!";
        for (char ch : documentText.toCharArray()) {
            Character character = CharacterFactory.getCharacter(ch);
            character.draw();
        }
    }
}
```

In this example, we have an OO Document Editor that uses the Flyweight pattern to represent each character in the document as a flyweight object. The `Character` interface defines the flyweight behavior, and the `ConcreteCharacter` class implements it. The `CharacterFactory` class manages the flyweight objects by creating and reusing them as needed. Finally, the `DocumentEditor` class demonstrates how to use the flyweight objects to draw characters in a document.

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight interface
interface Pen {
    void draw();
}

// ConcreteFlyweight representing a pen with refill
class ConcretePenWithRefill implements Pen {
    private final String refillColor;

    public ConcretePenWithRefill(String refillColor) {
        this.refillColor = refillColor;
    }

    @Override
    public void draw() {
        System.out.println("Drawing with " + refillColor + " pen");
    }
}

// ConcreteFlyweight representing a pen without refill
class ConcretePenWithoutRefill implements Pen {
    @Override
    public void draw() {
        System.out.println("Drawing with a pen without refill");
    }
}

// Flyweight factory class
class PenFactory {
    private static final Map<String, Pen> pens = new HashMap<>();

    public static Pen getPen(String refillColor) {
        if (refillColor != null) {
            return pens.computeIfAbsent(refillColor, ConcretePenWithRefill::new);
        } else {
            return new ConcretePenWithoutRefill();
        }
    }
}

// Client code
public class FlyweightExample {
    public static void main(String[] args) {
        Pen redPen1 = PenFactory.getPen("red");
        redPen1.draw();

        Pen redPen2 = PenFactory.getPen("red");
        redPen2.draw();

        Pen bluePen = PenFactory.getPen("blue");
        bluePen.draw();

        Pen penWithoutRefill = PenFactory.getPen(null);
        penWithoutRefill.draw();
    }
}
```

In this example:
- We have a `Pen` interface representing the flyweight behavior.
- `ConcretePenWithRefill` represents a concrete flyweight object for a pen with refill, and `ConcretePenWithoutRefill` represents a pen without refill.
- The `PenFactory` class manages the creation and retrieval of flyweight pen objects, reusing existing instances whenever possible.
- The client code demonstrates how to use the flyweight pattern to create and draw pens with different refill colors, ensuring that only one instance is created for each color.
