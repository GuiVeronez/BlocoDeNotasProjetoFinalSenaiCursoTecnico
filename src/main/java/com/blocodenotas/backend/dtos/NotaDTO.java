package com.blocodenotas.backend.dtos;

import com.blocodenotas.backend.models.Pasta;
import com.blocodenotas.backend.models.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotaDTO {
    private Long id;
    private String titulo;
    private String conteudo;

    public NotaDTO() {}

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
