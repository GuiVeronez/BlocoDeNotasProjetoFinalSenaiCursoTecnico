package com.blocodenotas.backend.dtos;

//import com.blocodenotas.backend.models.Pasta;
import com.blocodenotas.backend.models.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotaDTO {
    private Long id;
    private String titulo;
    private String conteudo;
    private Usuario usuario;

    public NotaDTO() {}

    public NotaDTO(Long id, String titulo, String conteudo, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
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
}
