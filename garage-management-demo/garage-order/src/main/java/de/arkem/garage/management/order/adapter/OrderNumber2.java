package java.de.arkem.garage.management.order.adapter;

import lombok.Getter;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
@Getter
public class OrderNumber2 {
    private long value;

    public OrderNumber2(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("order number must be greater than zero");
        }
        this.value = value;
    }
}
