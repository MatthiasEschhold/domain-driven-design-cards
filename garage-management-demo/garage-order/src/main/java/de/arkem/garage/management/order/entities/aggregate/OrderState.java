package de.arkem.garage.management.order.domain.model;

import org.jmolecules.architecture.onion.classical.DomainModelRing;
import org.jmolecules.architecture.onion.simplified.DomainRing;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * OrderState represented as an enum with explicit allowed transitions.
 *
 * Domain invariant: transitions between states are restricted. Use {@link #transitionTo(OrderState)}
 * to perform a transition (it enforces the invariant and throws when the transition is invalid).
 */
@DomainRing
@DomainModelRing
@ValueObject
public enum OrderState {
    CREATED,
    STARTED,
    CANCELED,
    FINISHED;

    private static final Map<OrderState, Set<OrderState>> ALLOWED_TRANSITIONS;

    static {
        Map<OrderState, Set<OrderState>> m = new EnumMap<>(OrderState.class);
        m.put(CREATED, Set.of(STARTED, CANCELED));
        m.put(STARTED, Set.of(FINISHED, CANCELED));
        m.put(CANCELED, Set.of()); // terminal
        m.put(FINISHED, Set.of()); // terminal
        ALLOWED_TRANSITIONS = Collections.unmodifiableMap(m);
    }

    /**
     * Returns the target state if the transition is allowed, otherwise throws IllegalArgumentException.
     * Self-transitions (from == to) return the current instance.
     */
    public OrderState transitionTo(OrderState target) {
        Objects.requireNonNull(target, "target state must not be null");
        if (this == target) return this;
        if (canTransitionTo(target)) return target;
        throw new IllegalArgumentException("Invalid order state transition from " + this + " to " + target);
    }

    /**
     * Returns whether a transition from this state to {@code target} is permitted by the domain rules.
     */
    private boolean canTransitionTo(OrderState target) {
        Objects.requireNonNull(target, "target state must not be null");
        return ALLOWED_TRANSITIONS.get(this).contains(target);
    }

     /**
     * Parse a textual representation (case-insensitive) of a state.
     */
    public static OrderState parse(String s) {
        Objects.requireNonNull(s, "state string must not be null");
        try {
            return OrderState.valueOf(s.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown order state: " + s, e);
        }
    }
}
