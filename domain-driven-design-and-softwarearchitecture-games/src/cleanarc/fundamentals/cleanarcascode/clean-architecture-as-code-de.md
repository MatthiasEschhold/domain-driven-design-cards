# Implementierung eines Anwendungsfalls

Nach der theoretischen Einführung in die Clean Architecture betrachten wir nun ein Beispiel 
auf Code-Ebene. Erfahrungsgemäß lässt sich das Architekturmuster am besten anhand eines 
praktischen Anwendungsfalls nachvollziehen.

Dazu implementieren wir den Use Case `CreateGarageOrder` als ersten vollständigen Anwendungsfall 
des Werkstattverwaltungssystems – Ende-zu-Ende, vom REST-Controller bis zur Datenbank.

Die Codebeispiele nutzen die Bibliothek [jMolecules](https://j-molecules.org/)
mit dem Ziel die Architekturelemente von Domain-Driven Design mittels Annotationen
zum Ausdruck zu bringen. Zur Vermeidung von Boilerplate-Code wird die Bibliothek
[Lombok](https://projectlombok.org/) verwendet. 

Die Beispiele sind minimalistisch gehalten, um den Fokus auf das Verstehen der Architektur zu legen.
Abbildung der Clean Architecture im Code zu vertiefen. Die hier eingeführten Codebeispiele werden
in Folgebeiträgen weiter ausgebaut und verfeinert.

---

## 🧱 Der Anwendungsfall im vertikalen Modul

Der Ende-zu-Ende-Anwendungsfall `CreateGarageOrder` wird im Modul `garage.order` 
implementiert. Listing 1 zeigt die Paket- und Klassenstruktur des Moduls,
die im folgenden erläutert wird.

```
|-- de.arkem.javapro.vertical.cleanarc
    |-- garage.order
    |   |-- adapter
        |   |-- in
            |  |-- GarageOrderController.java
            |  |-- GarageOrderResource.java
            |  |-- GarageOrderToResourceMapper.java
        |   |-- out
            |  |-- GarageOrderRepository.java
            |  |-- GarageOrderDbEntity.java
            |  |-- GarageOrderToDbEntityMapper.java
    |   |-- usecase
        |   |-- in
            |   |-- CreateGarageOrder.java
        |   |-- interactor
            |   |-- CreateGarageOrderInteractor.java
        |   |-- out
            |   |-- SaveGarageOrder.java
    |   |-- domain
        |   |-- model
            |   |-- GarageOrder.java
            |   |-- OrderItem.java
            |   |-- OrderStatus.java
    |-- vehicle
    |   |-- ...
    |-- customer
    |   |-- ...
```
[Listing 1: Paket- und Klassenstruktur des Modul GarageOrder mit dem Anwendungsfall CreateGarageOrder]()

## Implementierung des Domänenmodells im Domain Ring

Das Domänenmodell ist konzeptionell bereits in Teil 2 eingeführt und beschrieben. Abb. 1
zeigt dem Domänenobjekt-Graph der Root Entity `GarageOrder`.

[Abb. 1: Root Entity Auftrag auf konzeptioneller Ebene]()

Abb. 1 führt die Value Objects `OrderNumber` und `OrderItem` auf. Beide Fachwerte 
müssen Regeln implementieren, die sicherstellen, dass die Fachwerte immer
korrekt initialisiert. Zur Erinnerung - Value Objects sind unveränderlich (immutable).
D.h. bei Veränderung des Fachwerts wird ein neues Objekt erzeugt.

Gemäß der Verantwortung eines Value Objects, wird die Regeln zur Sicherung der 
fachlichen Konsistenz (aka Invariante) im Value Object selbst implementiert. 

Listing 1 zeigt das einfache Value Object `OrderNumber`, bestehend aus dem 
Wert, implementiert als primitiver Datentyp, hier als `int`, und einer Invariante 
(`ensurePositive`), die sicherstellt, dass die `OrderNumber` nicht negativ sein kann. 

```java
package de.arkem.garage.management.order.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;
import java.util.Objects;

@ValueObject
public record OrderNumber(int value) {
    
    public OrderNumber {
        ensurePositive(value);
    }
    
    private void ensurePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("order number must be greater than zero");
        }
    }
}
```
[Listing 1: Die Implemtierung des Value Object OrderNumber]()

Listing 2 zeigt die Entity `OrderItem`, die eine `orderItemNumber`, eine `description`
und eine `quantity` besitzt. Im Gegensatz zu Value Objects sind Entities veränderlich. D.h. 
die `quantity` eines `OrderItem` kann über die Methode `changeQuantity` verändert werden.
Das `OrderItem` hat folglich einen eigenen Lebenszyklus, der allerdings nur 
innerhalb des Aggregate Root `GarageOrder` existiert und gültig ist. 
Die Methode `changeQuantity` ist ein simples Beispiel für eine domänenspezifische Verhalten,
die in den Domänenobjekten implementiert wird.

Die Entity `OrderItem` kapselt drei Value Objects, die primitiv implementiert werden. Dies
ist in diesem Fall zulässig, da die Fachwerte keine Invarianten besitzen, die eine eigene
Implementierung als Value Object erfordern würden. Über unterschiedliche Möglichkeiten der
Implementierung von Entitäten und Fachwerten wird in einem separaten Teil eingegangen.

```java
package de.arkem.garage.management.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@Entity
@Getter
@AllArgsConstructor
public class OrderItem {
    @Identity
    private final int orderItemNumber;
    private final String description;
    private double quantity;

    public void changeQuantity(double newQuantity) {
        this.quantity = newQuantity;
    }
}
```
[Listing 2: Das Value Object OrderItem]()

Listing 3 zeigt die Root Entity `GarageOrder`, die eine `OrderNumber` als Identität
besitzt und eine Liste von `OrderItem`-Objekten enthält. Ergänzend hierzu sind zwei statische
Fabrikmethoden implementiert, die die Erstellung eines leeren Auftrags (`createEmptyOrder`)
und die Erstellung eines Auftrags mit vorgegebenen Auftragspositionen (`createOrderWithOrderItems`)
ermöglichen.

Ist die Objekterzeugung über einen Konstruktor zu komplex oder verliert die Lesbarkeit
des Konstruktors an Klarheit, so sind statische Fabrikmethoden als Verhalten am 
Domänenobjekt eine gute Alternative.

Die statischen Fabrikmethoden in Listing 3 stellen sicher, dass die `GarageOrder`-Objekte
immer in einem konsistenten Zustand erzeugt werden. Beide Fabrikmethoden prüfen die
übergebenen Parameter auf `null` und werfen eine `NullPointerException`, falls ein Parameter
`null` ist. Die Methode `createOrderWithOrderItems` stellt zudem sicher, dass die übergebene Liste
von `OrderItem`-Objekten keine `null`-Elemente enthält.

In weiteren Teilen werden wir für Verhalten am Domänenobjekt sowie für Objekterzeugung 
unterschiedliche Implementierungsstrategien kennenlernen.

```java
package de.arkem.garage.management.order.domain.model;

import lombok.Getter;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AggregateRoot
public class GarageOrder {
    @Identity
    private OrderNumber orderNumber;
    private List<OrderItem> items;

    /**
     * Adds an item to the order. 
     */
    public void addItem(OrderItem item) {
        Objects.requireNonNull(item, "item must not be null");
        items.add(item);
    }
    
    private GarageOrder(OrderNumber orderNumber, List<OrderItem> items) {
        this.orderNumber = orderNumber;
        this.items = items;
    }

    /**
     * Static factory that creates a new GarageOrder with the given OrderNumber
     * and sets the initial state to CREATED. Items are initialized to an empty list.
     */
    public static GarageOrder createEmptyOrder(OrderNumber orderNumber) {
        Objects.requireNonNull(orderNumber, "orderNumber must not be null");
        return new GarageOrder(orderNumber, new ArrayList<>());
    }

    /**
     * Static factory that creates a new GarageOrder with the given OrderNumber and a pre-filled list of items.
     * The provided list is defensively copied. Null elements are not allowed.
     */
    public static GarageOrder createOrderWithOrderItems(OrderNumber orderNumber, List<OrderItem> initialItems) {
        Objects.requireNonNull(orderNumber, "orderNumber must not be null");
        Objects.requireNonNull(initialItems, "initialItems must not be null");
        return new GarageOrder(orderNumber, createOrderItems(initialItems));
    }

    private static List<OrderItem> createOrderItems(List<OrderItem> initialItems) {
        var items = new ArrayList<OrderItem>(initialItems.size());
        for (OrderItem it : initialItems) {
            Objects.requireNonNull(it, "order item must not be null");
            items.add(it);
        }
        return items;
    }
}
```
[Listing 3: Die Root Entity GarageOrder]()

## Implementierung des Use Case Ring

Auf den Use Case Ring sind der `Use Case Input Port`, der `Use Case Interactor` und der `Use Case Output Port` verortet.

Der `Use Case Input Port` definiert die Schnittstelle des Anwendungsfalls `CreateGarageOrder`. Listing 4 zeigt die Schnittstelle `CreateGarageOrder`,
die Methoden `create` und `createWithOrderItems` deklariert. Die Methode erwarten keinen Parameter bzw. eine Liste von
`OrderItems`. Beide Methoden geben die angelegte `GarageOrder` zurück.

```java
@UseCaseRing
@UseCaseInputPort
@BusinessUseCase
public interface CreateGarageOrder {
    GarageOrder create();
    GarageOrder createWithOrderItems(List<OrderItem> orderItems);
}
```
[Listing 4: Der Use Case Input Port CreateGarageOrder]()

```java
@UseCaseRing
@UseCaseOutputPort
public interface SaveGarageOrder {
    GarageOrder save(GarageOrder garageOrder);
}
```
[Listing 5: Der Use Case Output Port SaveGarageOrder]()

```java
@UseCaseRing
@UseCaseOutputPort
public interface SaveGarageOrder {
    GarageOrder save(GarageOrder garageOrder);
}
```
[Listing 6: Der Use Case Interactor CreateGarageOrder]()

## Implementierung des Interface Adapters Rings

Robert C. Martin nennt in den Ring in Clean Architecture auf dem die Adapter
verortet sind Interface Adapters Ring. Dieser unterteilt sich in einen ein- und ausgehende
Seite der Applikation. 

Martin drückt dies fokussiert auf Ebene der Use Case Input bzw. Use Case Output Ports aus. 
In der Hexagonal Architecture nach Alistar Cockburn, liegt der Fokus des Ausdrucks auf der
Ebene der Adapter. Dort werden diese als Driving oder Primary bzw. Driven oder Secondary Adapter bezeichnet.

Alle Konzepte folgend er gleichen Idee und beschreiben einen Flow durch die Ringe
von der ein- zu ausgehenden Seite als Basiskonzept der Architekturmuster. 
Dieses Basiskonzept wird in [Use Case Interactor im Spotlight: The Flowfather]() 

Zur Abgrenzung der ein- und ausgehenden Seite der Anwendung,
wird in der Clean Architecture die bestehende Begriffswelt wiederverwendet und
"input" und "output" auch für die Abgrenzung auf dem Interface Adapter
Ring eingesetzt. Als Beispiel wird ein REST-Controller als Input Adapter und ein Datenbank-Repository
als Output Adapter dargestellt.

In der Clean Architecture ist das Ziel eine klare Trennung zwischen der Fachlichkeit (Domäne) und der
Technik zu erreichen. Um die technologie-agnostische Domäne zu implementieren sind im Kern drei Konzepte notwendig.

1. **Adapter Model**: Adapter Modelle repräsentieren die Datenstrukturen, die von den Adaptern verwendet werden (z. B. REST-Ressourcen, Datenbank-Entitäten). Sie sind spezifisch für die jeweilige Technologie und sollten nicht direkt in der Domäne verwendet werden.
2. **Mapper**: Mapper sind verantwortlich für die Umwandlung zwischen Adapter- und Domänenmodellen. Sie isolieren die Domäne von den technischen Details der Adapter und ermöglichen eine klare Trennung der Verantwortlichkeiten.
3. **Adapter mit Anwendung des Dependency Inversion Principle**: Adapter sind die Komponenten, die die
   ein- und ausgehende Use Cases nutzen bzw. implementieren. Die Bildung dieser Beziehungsstrukturen, ermöglicht die
   Abhängigkeitsumkehr und strikte Ausrichtung der Abhängigkeiten zur Domäne hin.


### Input Adapter

Listing 7 und 8 zeigen die REST-Ressourcen `GarageOrderResource` und `OrderItemResource`, 
die als DTOs (Data Transfer Objects) für die Kommunikation über die REST-Schnittstelle dienen.
Das Ressourcenmodell ist bewusst einfach gehalten und bildet die Domänenobjekte
nicht 1:1 ab. In realen Anwendungen ist es üblich, dass das Ressourcenmodell
vom Domänenmodell abweicht, um den Anforderungen der API gerecht zu werden.

```java
@AdapterRing
@InputAdapter
@AdapterModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonRootName("GarageOrder")
public class GarageOrderResource {
    private long orderNumber;
    private String status;
    private String customerRef;
    private List<OrderItemResource> orderItems;

}
```
[Listing 7: Die REST-Ressource GarageOrderResource]()

```java
@AdapterRing
@InputAdapter
@AdapterModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemResource {
    private long orderItemNumber;
    private String description;
    private double quantity;
}
```
[Listing 8: Die REST-Ressource OrderItemResource]()

Listing 9 zeigt den benötigen Mapper des REST-Input-Adapters. Der Mapper ist verantwortlich
für die Umwandlung der `OrderItemResource`-Objekte in die entsprechenden Domänenobjekte `OrderItem`.
Listing 10 stellt den Mapper dar, der für die Umwandlung zwischen dem `GarageOrderResource`-Objekt 
und dem Domänenobjekt `GarageOrder` implementiert.

```java
@AdapterRing
@InputAdapter
@Mapper
@Component
public class OrderItemToResourceMapper {

    List<OrderItem> mapToDomain(List<OrderItemResource> resources) {
        return resources
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    OrderItem mapToDomain(OrderItemResource resource) {
        return new OrderItem(
                resource.getOrderItemNumber(),
                resource.getDescription(),
                resource.getQuantity());
    }
}
```
[Listing 9: Der Mapper OrderItemToResourceMapper]()

```java
@AdapterRing
@InputAdapter
@Mapper
@Component
public class GarageOrderToResourceMapper {

    GarageOrderResource mapToResource(GarageOrder garageOrder) {
        GarageOrderResource resource = new GarageOrderResource();
        resource.setOrderNumber(garageOrder.getOrderNumber().value());
        resource.setStatus(garageOrder.getState().name());
        resource.setCustomerRef(garageOrder.getCustomerRef().value());
        resource.setOrderItems(garageOrder.getItems().stream()
                .map(this::mapToOrderItemResource)
                .toList());
        return resource;
    }

    private OrderItemResource mapToOrderItemResource(OrderItem orderItem) {
        return new OrderItemResource(
                orderItem.getOrderItemNumber(),
                orderItem.getDescription(),
                orderItem.getQuantity());
    }
}
```
[Listing 10: Der Mapper GarageOrderToResourceMapper]()

Listing 11 zeigt den REST-Controller `GarageOrderController`, der als Input Adapter
für den Use Case `CreateGarageOrder` dient. Der Controller empfängt HTTP-POST-Anfragen
an die URL `/api/garage-orders` und nutzt den Use Case `CreateGarageOrder`, um eine neue
`GarageOrder` zu erstellen. Weiter steuer der Controller das Mapping, durch Delegation an die
Mapper.

```java
@AdapterRing
@InputAdapter
@RestController
@RequestMapping("/api/garage-orders")
@RequiredArgsConstructor
public class GarageOrderController {

    private final CreateGarageOrder createGarageOrder;
    private final GarageOrderToResourceMapper garageOrderMapper;
    private final OrderItemToResourceMapper orderItemMapper;
    
    @PostMapping
    ResponseEntity<GarageOrderResource> createGarageOrder(@RequestBody
                                                          List<OrderItemResource> orderItems) {
        GarageOrder garageOrder;
        if(null == orderItems) {
            garageOrder = createGarageOrder.create();
        } else {
            garageOrder = createGarageOrder.createWithOrderItems(
                    orderItemMapper.mapToDomain(orderItems));
        }
        return ResponseEntity.ok(garageOrderMapper.mapToResource(garageOrder));
    }
}

```
[Listing 11: Der Interface Adapter GarageOrderController]()

### Output Adapter

Listing 12 und 13 zeigen die Datenbank-Entitäten `GarageOrderDbEntity` und `OrderItemDbEntity`,
die als Adapter Model für die Persistierung in der Datenbank dienen. Die Entitäten
sind mit JPA-Annotationen versehen, um die Zuordnung zu den Datenbanktabellen zu definieren.

```java
@AdapterRing
@OutputAdapter
@AdapterModel
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GarageOrderDbEntity {
    @Id @GeneratedValue
    private long orderNumber;
    private String status;
    private String customerRef;
    @ElementCollection
    @CollectionTable(name = "garage_order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItemDbEntity> orderItems;
}
```
[Listing 12: Das Adapter Model GarageOrderDbEntity]()

```java
@AdapterRing
@OutputAdapter
@AdapterModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDbEntity {
    @Id @GeneratedValue
    private long orderItemNumber;
    private String description;
    private double quantity;
}
```
[Listing 13: Das Adapter Model OrderItemDbEntity]()

Listing 14 zeigt den Mapper des Output Adapters, der für die Umwandlung
zwischen den Datenbank-Entitäten und den Domänenobjekten verantwortlich ist.
Das Mapping, in die Richtung der Domänenobjekte, ist geprägt von Objekterzeugung, aufgrund
der Implementierung des Domänenmodells nach dem Rich Domain Model. Ein Bestandteil hiervon ist
die kontrollierte Objekterzeugung über konstruktoren und statische Fabrikmethoden. Alternativen zu
diesen Ansätzen, werden in weiteren Beiträgen vorgestellt. Persönlich favorisiere ich die Verwendung von
parametrisierten Konstruktoren im Adapter Modell, anstelle der Verwendung von Setter-Methoden.

```java
@AdapterRing
@OutputAdapter
@Mapper
@Component
public class GarageOrderToDbEntityMapper {

    GarageOrder mapToDomain(GarageOrderDbEntity dbEntity) {
        return new GarageOrder(
                new OrderNumber(dbEntity.getOrderNumber()),
                OrderState.parse(dbEntity.getStatus()),
                dbEntity.getOrderItems()
                        .stream()
                        .map(this::mapToOrderItem)
                        .collect(Collectors.toList()),
                new CustomerRef(dbEntity.getCustomerRef())
        );
    }

    private OrderItem mapToOrderItem(OrderItemDbEntity dbEntity){
        return new OrderItem(dbEntity.getOrderItemNumber(),
                dbEntity.getDescription(),
                dbEntity.getQuantity());
    }

    GarageOrderDbEntity mapToDbEntity(GarageOrder domainObject) {
        return new GarageOrderDbEntity(
                domainObject.getOrderNumber().value(),
                domainObject.getState().name(),
                domainObject.getCustomerRef().value(),
                domainObject.getItems()
                        .stream()
                        .map(this::mapToOrderItemDbEntity)
                        .collect(Collectors.toList()));
    }

    OrderItemDbEntity mapToOrderItemDbEntity(OrderItem orderItem){
        return new OrderItemDbEntity(orderItem.getOrderItemNumber(),
                orderItem.getDescription(),
                orderItem.getQuantity());
    }
}
```
[Listing 14: Der Mapper GarageOrderToDbEntityMapper]()

Listing 15 zeigt abschließend das Repository `GarageOrderRepository`, das als Output Adapter
für den Use Case `CreateGarageOrder` dient. Das Repository nutzt das JPA-Repository
`GarageOrderJpaRepository`, um die Datenbank-Entitäten in der Datenbank zu speichern.
Das Mapping zwischen den Domänenobjekten und den Datenbank-Entitäten wird an
den zuvor beschriebenen Mapper `GarageOrderToDbEntityMapper` delegiert.

```java
@AdapterRing
@OutputAdapter
@Repository
@Component
@RequiredArgsConstructor
public class GarageOrderRepository implements SaveGarageOrder {

    private final GarageOrderJpaRepository jpaRepository;
    private final GarageOrderToDbEntityMapper mapper;

    @Override
    public GarageOrder save(GarageOrder garageOrder) {
        var savedOrder = jpaRepository.save(
                mapper.mapToDbEntity(garageOrder));
        return mapper.mapToDomain(savedOrder);
    }
}
```
[Listing 15: Das Repository GarageOrderRepository]()

## Zusammenfassung

In diesem Beitrag haben wir den Ende-zu-Ende-Anwendungsfall `CreateGarageOrder`
im vertikalen Modul `garage.order` implementiert. Dabei wurde das Domänenmodell
im Domain Ring realisiert, der Use Case im Use Case Ring und die Adapter
im Interface Adapters Ring.
Die Implementierung ist bewusst minimalistisch gehalten, um den Fokus auf die
Architektur zu legen. In weiteren Beiträgen werden wir die Implementierung
weiter ausbauen und verfeinern.
