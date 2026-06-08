package java.de.arkem.garage.management.order.entities.aggregate;

import org.jmolecules.architecture.onion.classical.DomainModelRing;
import org.jmolecules.architecture.onion.simplified.DomainRing;
import org.jmolecules.ddd.annotation.ValueObject;
import java.util.Objects;

@DomainRing
@DomainModelRing
@ValueObject
public record OrderNumber(long value) {

    // canonical constructor: enforce invariants at creation time
    public OrderNumber {
        requireValid(value);
    }

    private void requireValid(long value) {
        ensurePositive(value);
    }

    private void ensurePositive(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("order number must be greater than zero");
        }
    }

    // parse from a numeric string (trimmed) and convert to OrderNumber
    public static OrderNumber parse(String orderNumberAsStringValue) {
        Objects.requireNonNull(orderNumberAsStringValue, "order number string must not be null");
        String trimmedValue = orderNumberAsStringValue.trim();
        requireNotEmpty(trimmedValue);
        try {
            return new OrderNumber(Long.parseLong(trimmedValue));
        } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("invalid order number: " + trimmedValue, e);
        }
    }

    private static void requireNotEmpty(String orderNumberAsStringValue) {
        if (orderNumberAsStringValue.isEmpty()) {
            throw new IllegalArgumentException("order number string is empty");
        }
    }
}
