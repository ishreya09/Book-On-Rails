# BEHAVIORAL DESIGN PATTERNS

# Chain of Responsibility Pattern

The Chain of Responsibility pattern is a behavioral design pattern that allows an object to pass a request along a chain of handlers. Each handler decides either to process the request or to pass it along the chain. This pattern decouples senders and receivers of requests.

Command objects are handled or passed on to other objects by logic-containing processing objects

The Chain of Responsibility pattern establishes a chain within a system, so that a message can either be handled at the level where it is first received, or be directed to an object that can handle it. it is used to manage algorithms, relationships and responsibilities between objects

promotes loose coupling b/w sender of request and receiver  by giving more than one object an opportunity to handle the request

The receiving objects are chained and pass the request along the chain until one of the objects handles it.

#### Introduction
- The Chain of Responsibility design pattern allows a set of potential request handler objects to form a chain.
- The order in which these objects form the chain can be decided dynamically at runtime by the client.
- It reduces coupling by decoupling the sender of the request and its receivers.
- It simplifies objects by eliminating the need for them to know the chain’s structure.
- Allows for dynamic addition or removal of responsibilities by changing the members or the order of the chain.

#### Structure
- **Handler**: An interface that receives the request and dispatches it to a chain of handlers. It knows only about the first handler in the chain.
- **ConcreteHandler**: Implements the handler interface, handles requests it is responsible for, and can access its successor. If it cannot handle the request, it forwards it to its successor.
- **Client**: Initiates the request to a ConcreteHandler object on the chain.

#### Implementation:

In the main method of ChainOfResponsibility, concrete handler objects (Manager, VicePresident, CEO) are created and chained together based on the order of approval hierarchy
For example, Manager's successor is set to VicePresident, and VicePresident's successor is set to CEO. Transactions are then passed to the manager for approval.

```java
// Abstract class representing a transaction processor
abstract class TransactionProcessor {
    private TransactionProcessor transactionProcessor;
    
    abstract protected Double getApprovalLimit();
    abstract protected String getDesignation();

    public void setSuccessor(TransactionProcessor transactionProcessor) {
        this.transactionProcessor = transactionProcessor;
    }

    public void approve(Transaction transaction) {
        if (transaction.getAmount() <= 0.0) {
            System.out.println("Invalid Amount. Amount should be > 0");
            return;
        }
        if (transaction.getAmount() <= getApprovalLimit()) {
            System.out.println("Transaction for amount " + transaction.getAmount() + " approved by " + getDesignation());
        } else {
            if (transactionProcessor == null) {
                System.out.println("Invalid Amount. Amount should not exceed 25lacs!");
                return;
            }
            transactionProcessor.approve(transaction);
        }
    }
}

// Concrete class representing a Manager
class Manager extends TransactionProcessor {
    @Override
    protected Double getApprovalLimit() {
        return 100000.0;
    }

    @Override
    protected String getDesignation() {
        return "Manager";
    }
}

// Concrete class representing a Vice President
class VicePresident extends TransactionProcessor {
    @Override
    protected Double getApprovalLimit() {
        return 1000000.0;
    }

    @Override
    protected String getDesignation() {
        return "Vice President";
    }
}

// Concrete class representing a CEO
class CEO extends TransactionProcessor {
    @Override
    protected Double getApprovalLimit() {
        return 2500000.0;
    }

    @Override
    protected String getDesignation() {
        return "CEO";
    }
}

// Class representing a transaction
class Transaction {
    private Double amount;
    private String purpose;

    Transaction(Double amount, String purpose) {
        this.amount = amount;
        this.purpose = purpose;
    }

    public Double getAmount() {
        return amount;
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Manager manager = new Manager();
        VicePresident vicePresident = new VicePresident();
        CEO ceo = new CEO();
        
        manager.setSuccessor(vicePresident);
        vicePresident.setSuccessor(ceo);

        manager.approve(new Transaction(2600000.0, "General"));
        manager.approve(new Transaction(50000.0, "General"));
        manager.approve(new Transaction(120000.0, "General"));
        manager.approve(new Transaction(1500000.0, "General"));
        manager.approve(new Transaction(0.0, "General"));
    }
}
```

Let's consider a simplified example of a chain of responsibility pattern where we have a chain of email processors to handle different types of emails. The email processors will categorize emails as spam, complaints, fan mail, or requests for new features.

```java
// Step 1: Define the abstract handler interface
interface EmailHandler {
    void handleEmail(Email email);
    void setNextHandler(EmailHandler nextHandler);
}

// Step 2: Implement concrete email handler classes
class SpamHandler implements EmailHandler {
    private EmailHandler nextHandler;

    @Override
    public void handleEmail(Email email) {
        if (email.getType().equals("SPAM")) {
            System.out.println("Spam email handled");
        } else if (nextHandler != null) {
            nextHandler.handleEmail(email);
        }
    }

    @Override
    public void setNextHandler(EmailHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class ComplaintHandler implements EmailHandler {
    private EmailHandler nextHandler;

    @Override
    public void handleEmail(Email email) {
        if (email.getType().equals("COMPLAINT")) {
            System.out.println("Complaint email handled");
        } else if (nextHandler != null) {
            nextHandler.handleEmail(email);
        }
    }

    @Override
    public void setNextHandler(EmailHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class FanMailHandler implements EmailHandler {
    private EmailHandler nextHandler;

    @Override
    public void handleEmail(Email email) {
        if (email.getType().equals("FAN_MAIL")) {
            System.out.println("Fan mail email handled");
        } else if (nextHandler != null) {
            nextHandler.handleEmail(email);
        }
    }

    @Override
    public void setNextHandler(EmailHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class FeatureRequestHandler implements EmailHandler {
    private EmailHandler nextHandler;

    @Override
    public void handleEmail(Email email) {
        if (email.getType().equals("FEATURE_REQUEST")) {
            System.out.println("Feature request email handled");
        } else if (nextHandler != null) {
            nextHandler.handleEmail(email);
        }
    }

    @Override
    public void setNextHandler(EmailHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

// Step 3: Client code to set up the chain and process emails
public class EmailProcessor {
    private EmailHandler firstHandler;

    public EmailProcessor(EmailHandler firstHandler) {
        this.firstHandler = firstHandler;
    }

    public void processEmail(Email email) {
        firstHandler.handleEmail(email);
    }

    public static void main(String[] args) {
        // Creating the chain of responsibility
        EmailHandler spamHandler = new SpamHandler();
        EmailHandler complaintHandler = new ComplaintHandler();
        EmailHandler fanMailHandler = new FanMailHandler();
        EmailHandler featureRequestHandler = new FeatureRequestHandler();

        // Setting up the chain
        spamHandler.setNextHandler(complaintHandler);
        complaintHandler.setNextHandler(fanMailHandler);
        fanMailHandler.setNextHandler(featureRequestHandler);

        // Creating email processor with the first handler in the chain
        EmailProcessor processor = new EmailProcessor(spamHandler);

        // Processing different types of emails
        processor.processEmail(new Email("SPAM"));
        processor.processEmail(new Email("COMPLAINT"));
        processor.processEmail(new Email("FAN_MAIL"));
        processor.processEmail(new Email("FEATURE_REQUEST"));
    }
}

// Step 4: Define the Email class
class Email {
    private String type;

    public Email(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

```

In this example:

- We have different concrete email handler classes (`SpamHandler`, `ComplaintHandler`, `FanMailHandler`, `FeatureRequestHandler`) implementing the `EmailHandler` interface.
- Each handler checks the type of the email it receives. If it can handle the email type, it processes it; otherwise, it passes it to the next handler in the chain.
- The client code (`EmailProcessor`) sets up the chain of responsibility by linking the handlers together and processes different types of emails.
- The `Email` class represents an email with a type.

#### Benefits, Uses, and Drawbacks:
- **Benefits**:
  - Decouples sender and receivers of requests.
  - Simplifies objects by eliminating the need to know the chain’s structure.
  - Allows dynamic addition or removal of responsibilities.
- **Uses**:
  - Commonly used in systems to handle events like mouse clicks and keyboard events.
  - In Java, used in handling a chain of Exceptions.
- **Drawbacks**:
  - Execution of the request isn’t guaranteed.
  - Can be hard to observe the runtime characteristics and debug.



# Command Pattern

The Command pattern is a behavioral design pattern that turns a request into a stand-alone object that contains all the information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

Command pattern is a data-driven design pattern and falls under behavioral patterns.

A request is wrapped under an object as command and passed to an invoker object.

The Invoker then searches for the appropriate object to handle the Command and passes it to the corresponding object that can execute it.

The object, called Command, contains all the information needed to perform an action or trigger an event, such as the method name, the method owner, and the arguments for method parameters.

Certainly! Here's the Markdown representation along with the "before" and "after" code sections for the Command Pattern:

- **Intent:** 
    - Encapsulate a request in an object.
    - Allow the parameterization of clients with different requests.
    - Allow saving the requests in a queue.

- **Motivation:** 
    - Sometimes it's necessary to issue requests to objects without knowing anything about the operation being requested or the receiver of the request.
    - For example, user interface toolkits include objects like buttons and menus that carry out a request in response to user input.
    - But the toolkit can't implement the request explicitly in the button or menu because only applications that use the toolkit know what should be done on which object.

- **Structure:**
    - **Command:** Declares an interface for executing an operation.
    - **ConcreteCommand:**
        - Defines a binding between a Receiver object and an action.
        - Implements `execute()` by invoking a corresponding operation on Receiver.
    - **Client (Application):** Creates a Command object and sets its Receiver.
    - **Invoker:** Asks the Command to carry out a request.
    - **Receiver:** Knows how to perform the operation associated with a request. Can be any class.

- **Collaborations:** 
    - The Client creates a ConcreteCommand object and specifies its receiver.
    - An Invoker object stores the ConcreteCommand object.
    - The invoker issues a request by calling `execute()` on the command. When commands are undoable, ConcreteCommand stores state for undoing prior to invoking `execute()`.
    - The ConcreteCommand object invokes operations on its receiver to carry out the request.

### Implementation Example:

```java
// Define the command interface
public interface Order {
    void execute();
}

// Define the receiver
public class Stock {
    private String name = "ABC";
    private int quantity = 10;
    public void buy() {
        System.out.println("Stock [ Name: "+name+",  Quantity: " + quantity +" ] bought");
    }
    public void sell() {
        System.out.println("Stock [ Name: "+name+",  Quantity: " + quantity +" ] sold");
    }
}

// Concrete classes implementing the Order interface
public class BuyStock implements Order {
    private Stock abcStock;
    public BuyStock(Stock abcStock) {
        this.abcStock = abcStock;
    }
    public void execute() {
        abcStock.buy();
    }
}

public class SellStock implements Order {
    private Stock abcStock;
    public SellStock(Stock abcStock) {
        this.abcStock = abcStock;
    }
    public void execute() {
        abcStock.sell();
    }
}

// Command invoker class
import java.util.ArrayList;
import java.util.List;
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();
    public void takeOrder(Order order){
        orderList.add(order);
    }
    public void placeOrders(){
        for (Order order : orderList){
            order.execute();
        }
        orderList.clear();
    }
}

// Client class
public class CommandPatternDemo {
    public static void main(String[] args) {
        Stock abcStock = new Stock();
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        broker.placeOrders();
    }
}
```

This code demonstrates the Command Pattern where requests are encapsulated as objects allowing parameterization of clients with different requests. The `Broker` class acts as an invoker that stores and executes these commands.


# Interpreter Pattern

The Interpreter pattern is a behavioral design pattern that defines a grammar for interpreting a language and provides an interpreter to parse sentences in that language. It is useful when you need to interpret a language or a grammar.

The Interpreter pattern is a behavioral design pattern that defines a way to interpret a domain specific language or grammar. It allows you to define the grammar of a language and provide a way to evaluate sentences in that language

- **Intent:** 
    1. Provide a way to interpret and execute expressions in a language.
    2. Break down complex expressions into smaller components for easy interpretation.
    3. Create parsers and compilers for the defined language.
    4. Build scripting languages and other domain-specific languages.
    5. Provide a flexible and extensible way to define and interpret expressions.
    6. Support the implementation of rules governing the behavior of the language.
    7. Facilitate the creation of abstract syntax trees to represent expressions.
    8. Allow for the creation of interpreters handling different types of expressions and operators.

- **Motivation:** 
    - Define a domain-specific language tailored to specific application needs.
    - Interpret and execute expressions dynamically, especially for complex expressions.
    - Separate concerns between defining a language and interpreting expressions.
    - Build parsers and compilers for wide applicability.
    - Facilitate communication between components by defining a common language.

- **Design Solution – Structure:**
    - **AbstractExpression (Expression):** Interface for executing an operation.
    - **TerminalExpression:** Implements `Interpret` operation associated with terminal symbols.
    - **NonterminalExpression:** Implements `Interpret` for nonterminal symbols (not used in this example).
    - **Context:** Contains global information for the interpreter.
    - **Client (InterpreterApp):** Builds abstract syntax trees and invokes `Interpret` operation.

- **Design Solution – Participants:**
    - **AbstractExpression (Expression):** Interface for executing an operation.
    - **TerminalExpression:** Implements `Interpret` operation associated with terminal symbols.
    - **NonterminalExpression:** Implements `Interpret` for nonterminal symbols.
    - **Context:** Contains global information for the interpreter.
    - **Client (InterpreterApp):** Builds abstract syntax trees and invokes `Interpret` operation.

- **Example - Implementation:**
    - Model algebraic expressions like `2 + 3` using `ConstantExpression` and `AddExpression`.
    - Expressions are structured as `AddExpression(ConstantExpression(2), ConstantExpression(3))`.
    - Java code implementation follows this structure.

- **Applicability:** 
    - Used to interpret domain-specific languages or grammars like in compilers or scripting languages.
    - Implement rule-based systems with expressions defined in a domain-specific language.

- **Applications:** 
    - Regular Expressions
    - SQL Queries
    - Mathematical Expressions
    - Programming Languages

- **Advantages:**
    - Flexibility
    - Extensibility
    - Separation of Concerns

- **Disadvantages:**
    - Complexity
    - Performance
    - Maintenance

- **Consequence:**
    - Powerful tool for flexibility and extensibility with trade-offs in complexity, performance, and maintenance.

### Java Code Implementation:

```java
// Define the abstract expression interface
public interface Expression {
    void interpret(Context context);
}

// Define terminal expressions
public class ThousandExpression implements Expression {
    public void interpret(Context context) { /* Interpretation logic */ }
}

public class HundredExpression implements Expression {
    public void interpret(Context context) { /* Interpretation logic */ }
}

// Define non-terminal expressions if needed

// Define the context
public class Context {
    // Contains global information
}

// Define the client
public class InterpreterApp {
    public static void main(String[] args) {
        // Build and interpret abstract syntax trees
    }
}
```

This pattern helps interpret and execute expressions in a language, breaking down complex expressions for easy interpretation. It's commonly used for defining domain-specific languages and implementing parsers and compilers.

Let's create a simple example to understand the Interpreter pattern better. Suppose we want to create a simple language to represent arithmetic expressions like addition, subtraction, multiplication, and division. We'll implement a parser and interpreter for this language using the Interpreter pattern.

### Example: Arithmetic Expression Interpreter

```java
// 1. Define the abstract expression interface
interface Expression {
    int interpret();
}

// 2. Implement terminal expressions
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// 3. Implement non-terminal expressions
class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

// Similarly, implement other non-terminal expressions like SubtractExpression, MultiplyExpression, DivideExpression

// 4. Create a client to build and interpret expressions
public class InterpreterApp {
    public static void main(String[] args) {
        // Create expressions
        Expression expression = new AddExpression(
            new NumberExpression(10),
            new AddExpression(
                new NumberExpression(5),
                new NumberExpression(3)
            )
        );

        // Interpret the expression
        int result = expression.interpret();
        System.out.println("Result: " + result); // Output: 18
    }
}
```

In this example:
- We define the `Expression` interface with an `interpret()` method.
- `NumberExpression` represents a terminal expression that simply returns a number.
- `AddExpression`, `SubtractExpression`, `MultiplyExpression`, and `DivideExpression` represent non-terminal expressions that evaluate binary operations.
- In the `InterpreterApp`, we create an arithmetic expression (`10 + (5 + 3)`) using the defined expressions and interpret it to get the result (`18`).


# Iterator Pattern

The Iterator pattern is a behavioral design pattern that provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation. It allows you to traverse elements of a collection without exposing its underlying representation.

The Iterator design pattern is a behavioral pattern that provides a way to access the elements of an aggregate object sequentially, without exposing its underlying implementation.

In other words, it provides a uniform way to access the elements of a collection, such as a list, set, or map, without having to know how the collection is implemented

**Lazy Evaluation** - The iterator pattern makes it possible to traverse a collection of objects multiple times without having to reset the traversal state of the collection. The Iterator pattern supports lazy evaluation of collections, where the elements of the collection are computed or retrieved only when they are needed. This can be especially useful for working with large datasets or data streams.


#### Introduction
The Iterator design pattern is a behavioral pattern that provides a way to access the elements of an aggregate object sequentially, without exposing its underlying implementation. It offers a standardized way to traverse collections, making it easier to iterate over data structures like lists, sets, or maps.

#### Intent
The intent of using an iterator is to provide a standardized way of accessing and manipulating collections of data in a simple, efficient, and flexible manner. It is useful for implementing algorithms that require sequential access to data, such as sorting, searching, and filtering.

#### Motivation
- **Separation of concerns:** The Iterator pattern separates the concerns of managing a collection of objects from using the objects in the collection, making code modular and easier to maintain.
- **Lazy evaluation:** It supports lazy evaluation of collections, where elements are computed or retrieved only when needed.
- **Encapsulation:** By encapsulating the traversal logic in a separate object, the pattern hides the internal details of the collection from the client code.

#### Design Solution – Structure
- **Iterator (AbstractIterator):** Defines an interface for accessing and traversing elements.
- **ConcreteIterator (Iterator):** Implements the Iterator interface and keeps track of the current position in the traversal of the aggregate.
- **Aggregate (AbstractCollection):** Defines an interface for creating an Iterator object.
- **ConcreteAggregate (Collection):** Implements the Iterator creation interface to return an instance of the proper ConcreteIterator.

#### Example Code
```java
// Define the Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Implement ConcreteIterator
class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int currentIndex;

    public ArrayIterator(T[] array) {
        this.array = array;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[currentIndex++];
    }
}

// Define Aggregate interface
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// Implement ConcreteAggregate
class ArrayCollection<T> implements Aggregate<T> {
    private T[] array;

    public ArrayCollection(T[] array) {
        this.array = array;
    }

    @Override
    public Iterator<T> createIterator() {
        return new ArrayIterator<>(array);
    }
}

// Client code
public class IteratorDemo {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        Aggregate<Integer> aggregate = new ArrayCollection<>(numbers);
        Iterator<Integer> iterator = aggregate.createIterator();
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        // Output: 1 2 3 4 5
    }
}
```

#### Advantages
- Single Responsibility Principle
- Open/Closed Principle
- Parallel iteration support
- Delayed iteration support

#### Disadvantages
- Overkill for simple collections
- May be less efficient than direct element access in some cases

The Iterator pattern is valuable for simplifying iteration over collections and improving code flexibility and maintainability.





