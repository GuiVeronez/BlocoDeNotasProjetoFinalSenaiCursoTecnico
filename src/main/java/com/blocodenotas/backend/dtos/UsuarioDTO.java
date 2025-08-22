package com.blocodenotas.backend.dtos;
import com.blocodenotas.backend.models.Nota;
//import com.blocodenotas.backend.models.Pasta;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UsuarioDTO() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}