package de.arkem.garage.management.order.adapter.out.db;

import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.Mapper;
import de.arkem.camoleculs.OutputAdapter;
import de.arkem.garage.management.order.adapter.out.db.entity.GarageOrderDbEntity;
import de.arkem.garage.management.order.adapter.out.db.entity.OrderItemDbEntity;
import de.arkem.garage.management.order.domain.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
