package com.example.kabantop.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public class updataskmodel {
    private String titulo;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Prioridademodel prioridade;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridademodel getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridademodel prioridade) {
        this.prioridade = prioridade;
    }
}




