package com.blocodenotas.backend.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String conteudo;

    // Relacionamento com o usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    public Nota() {}

    public Nota(Long id, String titulo, String conteudo, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
    }

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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(titulo, nota.titulo) && Objects.equals(conteudo, nota.conteudo) && Objects.equals(usuario, nota.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, conteudo, usuario);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    //    @ManyToMany
//    @JoinTable(name = "nota_pasta",
//            joinColumns = @JoinColumn(name = "nota_id"),
//            inverseJoinColumns = @JoinColumn(name = "pasta_id"))
//    private List<Pasta> pasta = new ArrayList<>();;
}
