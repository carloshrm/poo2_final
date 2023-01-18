package com.poo2.poo2_l.models;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Tarefa")
public class Tarefa {
    private Long id;
    private String descricao;
    private String titulo;
    private Date dataCriada;
    private Date dataLimite;

    public Tarefa() {
        // utilizado pelo hibernate
    }

    public Tarefa(String desc, Date dl, String titulo) {
        this.descricao = desc;
        this.dataCriada = new Date();
        this.dataLimite = dl;
        this.titulo = titulo;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriada() {
        return dataCriada;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacriada")
    public void setDataCriada(Date dataCriada) {
        this.dataCriada = dataCriada;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datalimite")
    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    @Column(name = "descricao")
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Column(name = "titulo")
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", titulo='" + titulo + '\'' +
                ", dataCriada=" + dataCriada +
                ", dataLimite=" + dataLimite +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tarefa) {
            return this.id == ((Tarefa) obj).getId();
        } else
            return false;
    }
}
