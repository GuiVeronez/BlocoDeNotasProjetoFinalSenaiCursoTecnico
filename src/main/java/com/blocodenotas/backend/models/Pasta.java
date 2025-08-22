//package com.blocodenotas.backend.models;
//
//import jakarta.persistence.*;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//@Entity
//@Table(name = "pastas")
//public class Pasta {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String nome;
//
//    public Pasta() {
//        notas = new HashSet<>();
//    }
//
//    public Pasta(Long id, String nome) {
//        this.id = id;
//        this.nome = nome;
//        notas = new HashSet<>();
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null || getClass() != o.getClass()) return false;
//        Pasta pasta = (Pasta) o;
//        return Objects.equals(id, pasta.id) && Objects.equals(nome, pasta.nome);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, nome);
//    }
//
//    @Override
//    public String toString() {
//        return "Pasta{" +
//                "id=" + id +
//                ", nome='" + nome + '\'' +
//                '}';
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuarioPasta;
//
//    @ManyToMany(mappedBy = "pasta")
//    private Set<Nota> notas;
//}
