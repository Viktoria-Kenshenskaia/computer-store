package com.computer.store.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Represents the form factor of a desktop computer.
 */
public enum DesktopFormFactor {

    /**
     * Standard desktop form factor.
     */
    DESKTOP,
    /**
     * Nettop form factor.
     */
    NETTOP,
    /**
     * Monoblock form factor.
     */
    MONOBLOCK;

    /**
     * Creates a DesktopFormFactor enum value from the provided string value.
     *
     * @param value the string value representing the desktop form factor
     * @return the corresponding DesktopFormFactor enum value
     * @throws IllegalArgumentException if the provided value is not a valid desktop form factor
     */
    @JsonCreator
    public static DesktopFormFactor fromString(String value) {
        for (DesktopFormFactor formFactor : DesktopFormFactor.values()) {
            if (formFactor.name().equalsIgnoreCase(value)) {
                return formFactor;
            }
        }
        throw new IllegalArgumentException("Invalid Desktop Form Factor: " + value);
    }
}
