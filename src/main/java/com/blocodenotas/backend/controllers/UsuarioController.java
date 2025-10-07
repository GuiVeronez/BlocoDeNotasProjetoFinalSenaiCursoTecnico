package com.blocodenotas.backend.controllers;

import com.blocodenotas.backend.dtos.NotaDTO;
import com.blocodenotas.backend.dtos.UsuarioDTO;
import com.blocodenotas.backend.services.NotaService;
import com.blocodenotas.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342/")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotaService notaService;

    // Criar usuário (cadastro)
    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioCriado = usuarioService.salvarUsuario(usuarioDTO);
            return ResponseEntity.ok(usuarioCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"Erro interno do servidor\"}");
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> loginUsuario(@RequestBody UsuarioDTO loginDTO) {
        UsuarioDTO usuarioDTO = usuarioService.autenticarUsuario(loginDTO.getEmail(), loginDTO.getSenha());
        if (usuarioDTO != null) {
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Buscar todos usuários
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> buscarTodosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.buscarTodosUsuariosDTO();
        return ResponseEntity.ok(usuarios);
    }

    // Listar notas de um usuário
    @GetMapping("/{id}/notas")
    public ResponseEntity<List<NotaDTO>> listarNotasPorUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(notaService.buscarNotasPorUsuarioId(id));
    }
}