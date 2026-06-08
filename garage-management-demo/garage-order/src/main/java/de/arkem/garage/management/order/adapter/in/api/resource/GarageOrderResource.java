package de.arkem.garage.management.order.adapter.in.api.resource;

import com.fasterxml.jackson.annotation.JsonRootName;
import de.arkem.camoleculs.AdapterModel;
import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.InputAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AdapterRing
@InputAdapter
@AdapterModel
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonRootName("GarageOrder")
public class GarageOrderResource {
    private long orderNumber;
    private String status;
    private String customerRef;
    private List<OrderItemResource> orderItems;

}
