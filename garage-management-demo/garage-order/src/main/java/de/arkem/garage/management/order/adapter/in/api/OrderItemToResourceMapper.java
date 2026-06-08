package de.arkem.garage.management.order.adapter.in.api;

import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.InputAdapter;
import de.arkem.camoleculs.Mapper;
import de.arkem.garage.management.order.adapter.in.api.resource.OrderItemResource;
import de.arkem.garage.management.order.domain.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
