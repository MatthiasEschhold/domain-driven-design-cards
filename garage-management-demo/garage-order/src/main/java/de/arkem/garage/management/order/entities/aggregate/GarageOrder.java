package java.de.arkem.garage.management.order.entities.aggregate;

import lombok.Getter;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.de.arkem.garage.management.order.adapter.OrderNumber2;
import java.util.List;

@AggregateRoot
@Getter
public class GarageOrder {
    private final OrderNumber2 orderNumber;
    private List<OrderItem> orderItems;

    public GarageOrder(de.arkem.garage.management.order.domain.model.OrderNumber orderNumber, List<OrderItem> orderItems) {
        if(orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("order must have at least one order item");
        }
        this.orderNumber = orderNumber;
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return java.util.Collections.unmodifiableList(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
}
