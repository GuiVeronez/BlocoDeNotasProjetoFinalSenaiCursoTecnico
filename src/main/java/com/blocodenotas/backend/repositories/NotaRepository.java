package com.blocodenotas.backend.repositories;

import com.blocodenotas.backend.models.Nota;
import com.blocodenotas.backend.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}