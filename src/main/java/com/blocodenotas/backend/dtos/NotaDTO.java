package com.blocodenotas.backend.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class NotaDTO {
    private Long id;
    private String titulo;
    private String conteudo;
    private Long usuarioId;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public NotaDTO() {}

    public NotaDTO(Long id, String titulo, String conteudo, Long usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuarioId = usuarioId;
    }

    public NotaDTO(Long id, String titulo, String conteudo, Long usuarioId,
                   LocalDateTime dataCriacao, LocalDateTime dataModificacao) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.usuarioId = usuarioId;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
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
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaDTO notaDTO = (NotaDTO) o;
        return Objects.equals(id, notaDTO.id) &&
                Objects.equals(titulo, notaDTO.titulo) &&
                Objects.equals(conteudo, notaDTO.conteudo) &&
                Objects.equals(usuarioId, notaDTO.usuarioId) &&
                Objects.equals(dataCriacao, notaDTO.dataCriacao) &&
                Objects.equals(dataModificacao, notaDTO.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, conteudo, usuarioId, dataCriacao, dataModificacao);
    }

    @Override
    public String toString() {
        return "NotaDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", conteudo.length=" + (conteudo != null ? conteudo.length() : 0) +
                ", usuarioId=" + usuarioId +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}