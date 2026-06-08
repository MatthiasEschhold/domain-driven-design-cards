package de.arkem.garage.management.order.adapter.out.db.entity;

import de.arkem.camoleculs.AdapterModel;
import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.OutputAdapter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
