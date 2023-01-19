package com.poo2.poo2_l.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Tarefa")
public class Tarefa implements IEntidade {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(name = "descricao", nullable = false, length = 180)
    private String descricao;
    @Column(name = "titulo", nullable = false, length = 40)
    private String titulo;
    @Temporal(TemporalType.DATE)
    @Column(name = "datacriada", nullable = false)
    private Date dataCriada;
    @Temporal(TemporalType.DATE)
    @Column(name = "datalimite", nullable = false)
    private Date dataLimite;
    @ManyToOne(targetEntity = Projeto.class)
    @JoinColumn(name = "projetoID", referencedColumnName = "id", nullable = true)
    private Long idProjeto;

    public Tarefa() {
        // utilizado pelo hibernate
    }

    public Tarefa(String descricao, Date dataLimite, String titulo) {
        this.descricao = descricao;
        this.dataCriada = new Date();
        this.dataLimite = dataLimite;
        this.titulo = titulo;
    }

    public Tarefa(String descricao, Date dataLimite, String titulo, Long idProjeto) {
        this(descricao, dataLimite, titulo);
        this.idProjeto = idProjeto;
    }

    @Override
    public Long getId() {
    return id;
}

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Date getDataCriada() {
        return dataCriada;
    }

    public void setDataCriada(Date dataCriada) {
        this.dataCriada = dataCriada;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        if (dataLimite.before(dataCriada)) this.dataLimite = new Date();
        else this.dataLimite = dataLimite;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo.isBlank()) this.titulo = "Sem Título.";
        else this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao.isBlank()) this.descricao = "Sem Descrição.";
        else this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Tarefa{" + "id=" + id + ", descricao='" + descricao + '\'' + ", titulo='" + titulo + '\'' + ", dataCriada=" + dataCriada + ", dataLimite=" + dataLimite + ", idProjeto=" + idProjeto + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tarefa) {
            return this.id == ((Tarefa) obj).getId();
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProjeto);
    }
}
