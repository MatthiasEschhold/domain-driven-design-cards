package java.de.arkem.garage.management.order.usecase.in;


import java.de.arkem.garage.management.order.entities.aggregate.GarageOrder;
import java.de.arkem.garage.management.order.entities.aggregate.OrderItem;
import java.util.List;


public interface CreateGarageOrder {
    GarageOrder create();
    GarageOrder createWithOrderItems(List<OrderItem> orderItems);
}
