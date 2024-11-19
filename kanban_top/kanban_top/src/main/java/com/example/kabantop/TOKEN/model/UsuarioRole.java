package com.example.kabantop.TOKEN.model;

// Enum para representar os papéis de usuário
public enum UsuarioRole {
    ADMIN("admin"),
    USUARIO("user");

    private final String role;

    // Construtor do enum
    UsuarioRole(String role) {
        this.role = role;
    }

    // Getter para o valor associado ao enum
    public String getRole() {
        return role;
    }
}