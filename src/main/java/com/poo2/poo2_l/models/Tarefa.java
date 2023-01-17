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

//create table Tarefa(id serial primary key, titulo text, descricao text, data_criada date, data_limite date);

@Entity
@Table(name = "Tarefa")
public class Tarefa {
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "titulo")
    private String titulo;
    private Date dataCriada;
    private Date dataLimite;

    public Tarefa() {
        //
    }

    public Tarefa(String desc, Date dl, String titulo) {
        this.descricao = desc;
        this.dataCriada = new Date();
        this.dataLimite = dl;
        this.titulo = titulo;
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datacriada")
    public void setDataCriada(Date dataCriada) {
        this.dataCriada = dataCriada;
    }
    public Date getDataCriada() {
        return dataCriada;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datalimite")
    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }
    public Date getDataLimite() {
        return dataLimite;
    }

    @Column(name = "descricao")
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Column(name = "titulo")
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataCriada=" + dataCriada +
                ", dataLimite=" + dataLimite +
                '}';
    }
}
