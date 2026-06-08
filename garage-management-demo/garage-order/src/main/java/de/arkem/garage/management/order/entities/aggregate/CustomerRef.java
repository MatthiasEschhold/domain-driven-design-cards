package de.arkem.garage.management.order.entities.aggregate;

import org.jmolecules.architecture.onion.classical.DomainModelRing;
import org.jmolecules.architecture.onion.simplified.DomainRing;
import org.jmolecules.ddd.annotation.ValueObject;

@DomainRing
@DomainModelRing
@ValueObject
public record CustomerRef(String value) {

    public CustomerRef {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CustomerRef cannot be null or blank");
        }
    }
}
