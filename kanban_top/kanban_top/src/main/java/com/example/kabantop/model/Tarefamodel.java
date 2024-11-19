package com.example.kabantop.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;

import java.util.Date;

@Entity
public class Tarefamodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String titulo;
    private String descricao;

    private Date datalimite;
    @Enumerated(EnumType.STRING)
    private  Prioridademodel prioridade;

    @Enumerated(EnumType.STRING)
    private Statusmodel status;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date Datacriacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getDatalimite() {
        return datalimite;
    }

    public void setDatalimite(Date datalimite) {
        this.datalimite = datalimite;
    }

    public Prioridademodel getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridademodel prioridade) {
        this.prioridade = prioridade;
    }

    public Statusmodel getStatus() {
        return status;
    }

    public void setStatus(Statusmodel status) {
        this.status = status;
    }

    public Date getDatacriacao() {
        return Datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        Datacriacao = datacriacao;
    }
}
