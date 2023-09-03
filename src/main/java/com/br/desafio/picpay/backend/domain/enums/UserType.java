package com.br.desafio.picpay.backend.domain.enums;

public enum UserType {
    PESSOA_FISICA("PESSOA_FISICA"),
    PESSOA_JURIDICA("PESSOA_JURIDICA");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public UserType fromValue(String type) {
        for (UserType userType : UserType.values()) {
            if (userType.getValue().equals(type)) {
                return userType;
            }
        }
        return null;
    }
}
