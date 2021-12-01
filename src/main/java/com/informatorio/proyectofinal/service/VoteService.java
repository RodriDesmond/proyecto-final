package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.dto.VoteDTO;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.repository.VotoRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final Converter<VoteDTO, Voto> voteDTOVotoConverter;
    private final VotoRepository votoRepository;

    public VoteService(Converter<VoteDTO, Voto> voteDTOVotoConverter,
                       VotoRepository votoRepository) {
        this.voteDTOVotoConverter = voteDTOVotoConverter;
        this.votoRepository = votoRepository;
    }

    public boolean createVote(VoteDTO voteDTO) {
        Voto voto = voteDTOVotoConverter.convert(voteDTO);
        votoRepository.save(voto);
        return true;
    }
}
