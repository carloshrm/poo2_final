package com.poo2.poo2_l.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Projeto")
public class Projeto implements IEntidade, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 20)
    private String titulo;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataInicio")
    private Date dataInicio;

    public Projeto() {
    }

    public Projeto(String titulo, String descricao, Date dataInicio) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (descricao.isBlank()) this.descricao = "Sem descrição";
        else this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Projeto)
            return this.id == ((Projeto) obj).getId();
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
