package java.de.arkem.garage.management.order.entities.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jmolecules.architecture.onion.classical.DomainModelRing;
import org.jmolecules.architecture.onion.simplified.DomainRing;
import org.jmolecules.ddd.annotation.Entity;
import org.jmolecules.ddd.annotation.Identity;

@DomainRing
@DomainModelRing
@Entity
@Getter
@AllArgsConstructor
public class OrderItem {
    @Identity
    private final long orderItemNumber;
    private final String description;
    private double quantity;

    public void changeQuantity(double newQuantity) {
        this.quantity = newQuantity;
    }
}
