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
public class Tarefa implements IEntidade {
    private Long id;
    private String descricao;
    private String titulo;
    private Date dataCriada;
    private Date dataLimite;

    public Tarefa() {
        // utilizado pelo hibernate
    }

    public Tarefa(String descricao, Date dataLimite, String titulo) {
        this.descricao = descricao;
        this.dataCriada = new Date();
        this.dataLimite = dataLimite;
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
    @Column(name = "datacriada", nullable = false)
    public void setDataCriada(Date dataCriada) {
        this.dataCriada = dataCriada;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "datalimite", nullable = false)
    public void setDataLimite(Date dataLimite) {
        if (dataLimite.before(dataCriada))
            this.dataLimite = new Date();
        else
            this.dataLimite = dataLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    @Column(name = "descricao", nullable = false, length = 180)
    public void setDescricao(String descricao) {
        if (descricao.isBlank())
            this.descricao = "Sem Descrição.";
        else
            this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    @Column(name = "titulo", nullable = false, length = 40)
    public void setTitulo(String titulo) {
        if (titulo.isBlank())
            this.titulo = "Sem Título.";
        else
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
