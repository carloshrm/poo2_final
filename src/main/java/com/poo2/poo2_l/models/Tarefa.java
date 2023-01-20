package com.poo2.poo2_l.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Tarefa")
public class Tarefa implements IEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Projeto projeto;

    public Tarefa() {
        // utilizado pelo hibernate
    }

    public Tarefa(String titulo, Date dataLimite, String descricao) {
        this.descricao = descricao;
        this.dataCriada = new Date();
        this.dataLimite = dataLimite;
        this.titulo = titulo;
    }

    @Override
    public Long getId() {
    return id;
}

    public void setId(Long id) { this.id = id; }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
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
        return "Tarefa{" + "id=" + id + ", descricao='" + descricao + '\'' + ", titulo='" + titulo + '\'' + ", dataCriada=" + dataCriada + ", dataLimite=" + dataLimite + ", idProjeto=" + projeto + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Tarefa) {
            return this.id == ((Tarefa) obj).getId();
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projeto);
    }
}
