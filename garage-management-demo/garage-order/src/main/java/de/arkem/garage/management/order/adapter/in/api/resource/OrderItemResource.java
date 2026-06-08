package de.arkem.garage.management.order.adapter.in.api.resource;

import de.arkem.camoleculs.AdapterModel;
import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.InputAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AdapterRing
@InputAdapter
@AdapterModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemResource {
    private long orderItemNumber;
    private String description;
    private double quantity;
}
