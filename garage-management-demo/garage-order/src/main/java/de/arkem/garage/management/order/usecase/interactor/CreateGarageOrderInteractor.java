package java.de.arkem.garage.management.order.usecase.interactor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.de.arkem.garage.management.order.entities.aggregate.GarageOrder;
import java.de.arkem.garage.management.order.entities.aggregate.OrderItem;
import java.de.arkem.garage.management.order.usecase.in.CreateGarageOrder;
import java.de.arkem.garage.management.order.usecase.out.SaveGarageOrder;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateGarageOrderInteractor implements CreateGarageOrder {

    private final SaveGarageOrder saveGarageOrder;

    @Override
    public GarageOrder create() {
        GarageOrder order = new GarageOrder(null, null);
        order.getOrderItems().add(null);
        return saveGarageOrder.save();
    }

    @Override
    public GarageOrder createWithOrderItems(List<OrderItem> orderItems) {
        return saveGarageOrder.save();
    }
}
