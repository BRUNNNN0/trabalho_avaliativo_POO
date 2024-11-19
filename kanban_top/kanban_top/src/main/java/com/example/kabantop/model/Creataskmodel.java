package com.example.kabantop.model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;



import java.util.Date;

public class Creataskmodel {

    private String titulo;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Prioridademodel prioridade;
    private Date datalimitec;

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

    public Date getDatalimitec() {
        return datalimitec;
    }

    public void setDatalimitec(Date datalimitec) {
        this.datalimitec = datalimitec;
    }
}


