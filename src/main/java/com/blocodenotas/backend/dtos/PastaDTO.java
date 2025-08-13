package com.blocodenotas.backend.dtos;

import com.blocodenotas.backend.models.Usuario;
import jakarta.persistence.*;

import java.util.Objects;

public class PastaDTO {
    private Long id;
    private String nome;

    public PastaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
