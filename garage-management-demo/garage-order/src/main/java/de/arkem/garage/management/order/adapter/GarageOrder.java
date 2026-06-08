package java.de.arkem.garage.management.order.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jmolecules.architecture.onion.classical.DomainModelRing;
import org.jmolecules.architecture.onion.simplified.DomainRing;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DomainRing
@DomainModelRing
@AggregateRoot
@AllArgsConstructor
@Getter
public class GarageOrder {
    @Identity
    private OrderNumber orderNumber;
    private OrderState state;
    private List<OrderItem> items;
    private CustomerRef customerRef;

    /**
     * Adds an item to the order. Not allowed when the order is in a terminal state
     * (CANCELED or FINISHED).
     */
    public void addItem(OrderItem item) {
        Objects.requireNonNull(item, "item must not be null");
        if (state == OrderState.CANCELED || state == OrderState.FINISHED) {
            throw new IllegalStateException("Cannot add items to an order in state: " + state);
        }
        items.add(item);
    }

    // state transition helper operations
    public void start() {
        this.state = this.state.transitionTo(OrderState.STARTED);
    }

    public void finish() {
        this.state = this.state.transitionTo(OrderState.FINISHED);
    }

    public void cancel() {
        this.state = this.state.transitionTo(OrderState.CANCELED);
    }

    private GarageOrder(OrderState state, List<OrderItem> items) {
        this.state = state;
        this.items = items;
    }

    /**
     * Static factory that creates a new GarageOrder with the given OrderNumber
     * and sets the initial state to CREATED. Items are initialized to an empty list.
     */
    public static GarageOrder createEmptyOrder() {
        return new GarageOrder(OrderState.CREATED, new ArrayList<>());
    }

    /**
     * Static factory that creates a new GarageOrder with the given OrderNumber and a pre-filled list of items.
     * The provided list is defensively copied. Null elements are not allowed.
     */
    public static GarageOrder createOrderWithOrderItems(List<OrderItem> initialItems) {
        Objects.requireNonNull(initialItems, "initialItems must not be null");
        return new GarageOrder(OrderState.CREATED, createOrderItems(initialItems));
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
