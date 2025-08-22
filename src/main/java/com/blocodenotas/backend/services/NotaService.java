package com.blocodenotas.backend.services;

import com.blocodenotas.backend.dtos.NotaDTO;
import com.blocodenotas.backend.models.Nota;
import com.blocodenotas.backend.models.Usuario;
import com.blocodenotas.backend.repositories.NotaRepository;
import com.blocodenotas.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public NotaDTO salvarNota(NotaDTO notaDTO) {
        Nota nota = converterNotaDTOParaNota(notaDTO);
        // Relacionar nota ao usuário
        if (notaDTO.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(notaDTO.getUsuario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            nota.setUsuario(usuario);
        }
        nota = notaRepository.save(nota);
        return converterNotaParaNotaDTO(nota);
    }

    public NotaDTO converterNotaParaNotaDTO(Nota nota) {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setId(nota.getId());
        notaDTO.setTitulo(nota.getTitulo());
        notaDTO.setConteudo(nota.getConteudo());
        if (nota.getUsuario().getId() != null) {
            notaDTO.setUsuario(nota.getUsuario());
        }
        return notaDTO;
    }

    public Nota converterNotaDTOParaNota(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setId(notaDTO.getId());
        nota.setTitulo(notaDTO.getTitulo());
        nota.setConteudo(notaDTO.getConteudo());

        if (notaDTO.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(notaDTO.getUsuario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            nota.setUsuario(usuario);
        }

        return nota;
    }

    public List<NotaDTO> buscarTodasNotasDTO() {
        return notaRepository.findAll()
                .stream()
                .map(this::converterNotaParaNotaDTO)
                .collect(Collectors.toList());
    }

    public List<NotaDTO> buscarNotasPorUsuarioId(Long usuarioId) {
        return notaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::converterNotaParaNotaDTO)
                .collect(Collectors.toList());
    }

    public NotaDTO atualizarNota(NotaDTO notaDTO) {
        if (isNull(notaDTO.getId())) {
            throw new IllegalArgumentException("campo Id não informado");
        }

        Nota nota = notaRepository.findById(notaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        nota.setTitulo(notaDTO.getTitulo());
        nota.setConteudo(notaDTO.getConteudo());

        if (notaDTO.getUsuario().getId() != null) {
            Usuario usuario = usuarioRepository.findById(notaDTO.getUsuario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            nota.setUsuario(usuario);
        }

        nota = notaRepository.save(nota);
        return converterNotaParaNotaDTO(nota);
    }

    public void deletarNota(Long id) {
        notaRepository.deleteById(id);
    }
}
