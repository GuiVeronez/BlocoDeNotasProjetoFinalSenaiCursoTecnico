package com.blocodenotas.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    // Suporte para conteúdo muito longo (praticamente infinito)
    @Lob
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String conteudo;

    // Data de criação
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    // Data da última modificação
    @Column(name = "data_modificacao", nullable = false)
    private LocalDateTime dataModificacao;

    // Relacionamento com o usuário
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Construtores
    public Nota() {
        this.dataCriacao = LocalDateTime.now();
        this.dataModificacao = LocalDateTime.now();
    }

    public Nota(String titulo, String conteudo, Usuario usuario) {
        this();
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
    }

    public Nota(Long id, String titulo, String conteudo, Usuario usuario) {
        this();
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuario = usuario;
    }

    public Nota(Long id, String titulo, String conteudo, LocalDateTime dataCriacao, LocalDateTime dataModificacao, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
        this.usuario = usuario;
    }

    // Getters e Setters
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
        this.atualizarDataModificacao();
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
        this.atualizarDataModificacao();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.atualizarDataModificacao();
    }

    // Métodos auxiliares
    public void atualizarDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public String getPreview() {
        if (conteudo == null || conteudo.isEmpty()) {
            return "Sem conteúdo";
        }
        return conteudo.length() > 100 ? conteudo.substring(0, 100) + "..." : conteudo;
    }

    public int getTamanhoConteudo() {
        return conteudo != null ? conteudo.length() : 0;
    }

    public boolean isVazia() {
        return (titulo == null || titulo.trim().isEmpty()) &&
                (conteudo == null || conteudo.trim().isEmpty());
    }

    // Métodos padrão
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) &&
                Objects.equals(titulo, nota.titulo) &&
                Objects.equals(conteudo, nota.conteudo) &&
                Objects.equals(dataCriacao, nota.dataCriacao) &&
                Objects.equals(dataModificacao, nota.dataModificacao) &&
                Objects.equals(usuario, nota.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, conteudo, dataCriacao, dataModificacao, usuario);
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", conteudo.length=" + (conteudo != null ? conteudo.length() : 0) +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                ", usuarioId=" + (usuario != null ? usuario.getId() : "null") +
                '}';
    }

    // Método para atualização em lote
    public void atualizarDados(String novoTitulo, String novoConteudo) {
        this.titulo = novoTitulo;
        this.conteudo = novoConteudo;
        this.atualizarDataModificacao();
    }
}