package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.dto.VoteDTO;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.VotoRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    private final Converter<VoteDTO, Voto> voteDTOVotoConverter;
    private final VotoRepository votoRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public VoteService(Converter<VoteDTO, Voto> voteDTOVotoConverter,
                       VotoRepository votoRepository,
                       EmprendimientoRepository emprendimientoRepository) {
        this.voteDTOVotoConverter = voteDTOVotoConverter;
        this.votoRepository = votoRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    public boolean createVote(VoteDTO voteDTO) {
        Voto voto = voteDTOVotoConverter.convert(voteDTO);
        Emprendimiento emprendimiento = emprendimientoRepository.getById(voto.getEmprendimientoId());
        emprendimiento.setVotesCount(emprendimiento.getVotesCount()+1);
        emprendimientoRepository.save(emprendimiento);
        votoRepository.save(voto);
        return true;
    }
}
