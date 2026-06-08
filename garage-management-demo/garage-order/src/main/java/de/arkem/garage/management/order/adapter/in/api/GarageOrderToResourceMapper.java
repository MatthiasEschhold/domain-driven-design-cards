package de.arkem.garage.management.order.adapter.in.api;

import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.InputAdapter;
import de.arkem.camoleculs.Mapper;
import de.arkem.garage.management.order.adapter.in.api.resource.GarageOrderResource;
import de.arkem.garage.management.order.adapter.in.api.resource.OrderItemResource;
import de.arkem.garage.management.order.domain.model.GarageOrder;
import de.arkem.garage.management.order.domain.model.OrderItem;
import org.springframework.stereotype.Component;

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
