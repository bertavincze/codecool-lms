package com.codecool.model.user;

public enum UserRole {

    STUDENT("student"),
    MENTOR("mentor");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        String result;
        switch (this) {
            case STUDENT:
                result = "Student";
                break;
            case MENTOR:
                result = "Mentor";
                break;
            default:
                result = "You shouldn't see this";
                break;
        }
        return result;
    }

}
