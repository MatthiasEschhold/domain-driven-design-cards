package de.arkem.garage.management.order.adapter.out.db.entity;

import de.arkem.camoleculs.AdapterModel;
import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.OutputAdapter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AdapterRing
@OutputAdapter
@AdapterModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDbEntity {
    @Id @GeneratedValue
    private long orderItemNumber;
    private String description;
    private double quantity;
}
