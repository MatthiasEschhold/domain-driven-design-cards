package java.de.arkem.garage.management.order.usecase.out;


import java.de.arkem.garage.management.order.entities.aggregate.GarageOrder;

public interface SaveGarageOrder {
    GarageOrder save(GarageOrder garageOrder);
}
