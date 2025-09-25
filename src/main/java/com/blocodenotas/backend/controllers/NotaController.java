package com.blocodenotas.backend.controllers;

import com.blocodenotas.backend.dtos.NotaDTO;
import com.blocodenotas.backend.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    // Criar nota
    @PostMapping
    public ResponseEntity<NotaDTO> criarNota(@RequestBody NotaDTO notaDTO) {
        return ResponseEntity.ok(notaService.salvarNota(notaDTO));
    }

    // Atualizar nota
    @PutMapping("/{id}")
    public ResponseEntity<NotaDTO> atualizarNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        notaDTO.setId(id);
        return ResponseEntity.ok(notaService.atualizarNota(notaDTO));
    }

    // Deletar nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        notaService.deletarNota(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todas as notas (debug/admin)
    @GetMapping
    public ResponseEntity<List<NotaDTO>> buscarTodasNotas() {
        List<NotaDTO> notas = notaService.buscarTodasNotasDTO();
        return ResponseEntity.ok(notas);
    }
}
