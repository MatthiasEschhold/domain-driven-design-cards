package de.arkem.garage.management.order.adapter.in.api;

import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.InputAdapter;
import de.arkem.garage.management.order.adapter.in.api.resource.GarageOrderResource;
import de.arkem.garage.management.order.adapter.in.api.resource.OrderItemResource;
import de.arkem.garage.management.order.domain.model.GarageOrder;
import de.arkem.garage.management.order.usecase.in.CreateGarageOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AdapterRing
@InputAdapter
@RestController
@RequestMapping("/api/garage-orders")
@RequiredArgsConstructor
public class GarageOrderController {

    private final CreateGarageOrder createGarageOrder;
    private final GarageOrderToResourceMapper garageOrderMapper;
    private final OrderItemToResourceMapper orderItemMapper;

    @GetMapping
    ResponseEntity<GarageOrderResource> getGarageOrder() {
        return ResponseEntity.ok(new GarageOrderResource());
    }

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
