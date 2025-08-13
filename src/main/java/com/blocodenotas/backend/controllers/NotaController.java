package com.blocodenotas.backend.controllers;
import com.blocodenotas.backend.dtos.NotaDTO;
import com.blocodenotas.backend.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping()
    public ResponseEntity<NotaDTO> criarNota(@RequestBody NotaDTO notaDTO) {
        return ResponseEntity.ok(notaService.salvarNota(notaDTO));
    }

    @PutMapping()
    public ResponseEntity<NotaDTO> atualizarNota(@RequestBody NotaDTO notaDTO) {
        return ResponseEntity.ok(notaService.atualizarNota(notaDTO));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarNota(@RequestBody NotaDTO notaDTO)  {
        notaService.deletarNota(notaDTO.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/todos")
    public ResponseEntity<List<NotaDTO>> buscarTodasNotas() {
        List<NotaDTO> notas = notaService.buscarTodasNotasDTO();
        return ResponseEntity.ok(notas);
    }
}
