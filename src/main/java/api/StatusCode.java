package api;

/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */

public enum StatusCode {
    SC_200("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT");

    private String name;

    StatusCode(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}