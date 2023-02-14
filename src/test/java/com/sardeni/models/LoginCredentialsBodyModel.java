package com.sardeni.models;

import lombok.Data;

public @Data class LoginCredentialsBodyModel {

    private String email, password;
}
