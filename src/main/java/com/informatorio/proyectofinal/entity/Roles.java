package com.informatorio.proyectofinal.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    USUARIO,
    OWNER,
    COLABORADOR;

    public String getAuthority() {
        return name();
    }
}
