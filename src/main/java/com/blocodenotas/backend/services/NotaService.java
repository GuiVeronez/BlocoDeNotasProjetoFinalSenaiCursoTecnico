package com.blocodenotas.backend.services;
import com.blocodenotas.backend.dtos.NotaDTO;
import com.blocodenotas.backend.models.Nota;
import com.blocodenotas.backend.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public NotaDTO salvarNota(NotaDTO notaDTO) {
        Nota nota = converterNotaDTOParaNota(notaDTO);
        nota = notaRepository.save(nota);
        return converterNotaParaNotaDTO(nota);
    }

    public NotaDTO converterNotaParaNotaDTO(Nota nota) {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setId(nota.getId());
        notaDTO.setTitulo(nota.getTitulo());
        notaDTO.setConteudo(nota.getConteudo());
        return notaDTO;
    }

    public Nota converterNotaDTOParaNota(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setId(notaDTO.getId());
        nota.setTitulo(notaDTO.getTitulo());
        nota.setConteudo(notaDTO.getConteudo());
        return nota;
    }

    public List<NotaDTO> buscarTodasNotasDTO() {
        return notaRepository.findAll()
                .stream()
                .map(nota -> converterNotaParaNotaDTO(nota))
                .toList();
    }

    public NotaDTO atualizarNota(NotaDTO notaDTO) {
        if (isNull(notaDTO.getId())) {
            throw new IllegalArgumentException("campo Id não informado");
        }
        Nota nota = notaRepository.findById(notaDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Nota não encontrado"));

        nota = converterNotaDTOParaNota(notaDTO);
        nota = notaRepository.save(nota);
        return converterNotaParaNotaDTO(nota);
    }

    public void deletarNota(Long id) {
        notaRepository.deleteById(id);
    }
}
