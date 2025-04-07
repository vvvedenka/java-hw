package edu.phystech.hw2.contact;

public final class InvalidContactFieldException extends RuntimeException {
    private final String invalidField;

    public InvalidContactFieldException(String field) {
        super("Invalid field: " + field);
        this.invalidField = field;
    }

    public String getFieldName() {
        return this.invalidField;
    }
}