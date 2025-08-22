package com.blocodenotas.backend.services;

import com.blocodenotas.backend.dtos.NotaDTO;
import com.blocodenotas.backend.models.Nota;
import com.blocodenotas.backend.models.Usuario;
import com.blocodenotas.backend.repositories.NotaRepository;
import com.blocodenotas.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Salvar nota
    public NotaDTO salvarNota(NotaDTO notaDTO) {
        Nota nota = converterNotaDTOParaNota(notaDTO);
        nota = notaRepository.save(nota);
        return converterNotaParaNotaDTO(nota);
    }

    // Converter Nota para DTO
    public NotaDTO converterNotaParaNotaDTO(Nota nota) {
        NotaDTO dto = new NotaDTO();
        dto.setId(nota.getId());
        dto.setTitulo(nota.getTitulo());
        dto.setConteudo(nota.getConteudo());
        dto.setUsuarioId(nota.getUsuario().getId()); // ✅ importante
        return dto;
    }

    // Converter DTO para Nota
    public Nota converterNotaDTOParaNota(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setId(notaDTO.getId());
        nota.setTitulo(notaDTO.getTitulo());
        nota.setConteudo(notaDTO.getConteudo());

        if (notaDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(notaDTO.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para a nota"));
            nota.setUsuario(usuario);
        } else {
            throw new IllegalArgumentException("UsuárioId da nota não informado");
        }

        return nota;
    }

    // Buscar todas as notas (admin/debug)
    public List<NotaDTO> buscarTodasNotasDTO() {
        return notaRepository.findAll()
                .stream()
                .map(this::converterNotaParaNotaDTO)
                .toList();
    }

    // Buscar notas por usuário
    public List<NotaDTO> buscarNotasPorUsuarioId(Long usuarioId) {
        return notaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::converterNotaParaNotaDTO)
                .toList();
    }

    // Atualizar nota
    public NotaDTO atualizarNota(NotaDTO notaDTO) {
        if (notaDTO.getId() == null) {
            throw new IllegalArgumentException("campo Id não informado");
        }

        Nota nota = notaRepository.findById(notaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        // Atualiza campos
        nota.setTitulo(notaDTO.getTitulo());
        nota.setConteudo(notaDTO.getConteudo());

        if (notaDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(notaDTO.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            nota.setUsuario(usuario);
        }

        nota = notaRepository.save(nota);
        return converterNotaParaNotaDTO(nota);
    }

    // Deletar nota
    public void deletarNota(Long id) {
        notaRepository.deleteById(id);
    }
}
