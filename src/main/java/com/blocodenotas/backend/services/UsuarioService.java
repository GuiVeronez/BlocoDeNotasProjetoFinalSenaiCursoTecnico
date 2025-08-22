package com.blocodenotas.backend.services;

import com.blocodenotas.backend.dtos.UsuarioDTO;
import com.blocodenotas.backend.models.Usuario;
import com.blocodenotas.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Salvar usuário (cadastro)
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = converterDTOParaUsuario(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return converterUsuarioParaDTO(usuario);
    }

    // Autenticar usuário (login)
    public UsuarioDTO autenticarUsuario(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        return usuario.map(this::converterUsuarioParaDTO).orElse(null);
    }

    // Buscar todos os usuários
    public List<UsuarioDTO> buscarTodosUsuariosDTO() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterUsuarioParaDTO)
                .collect(Collectors.toList());
    }

    // Converte de entidade para DTO
    public UsuarioDTO converterUsuarioParaDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        return dto;
    }

    // Converte de DTO para entidade
    public Usuario converterDTOParaUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        return usuario;
    }
}
