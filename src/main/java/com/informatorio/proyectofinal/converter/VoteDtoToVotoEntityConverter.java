package com.informatorio.proyectofinal.converter;

import com.informatorio.proyectofinal.dto.VoteDTO;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VoteDtoToVotoEntityConverter implements Converter<VoteDTO, Voto> {

    public VoteDtoToVotoEntityConverter(EmprendimientoRepository emprendimientoRepository,
                                        UserRepository userRepository) {
    }

    @Override
    public Voto convert(VoteDTO voteDto){
        Voto voto = new Voto();
        voto.setUserId(voteDto.getUserId());
        voto.setEmprendimientoId(voteDto.getEmprendimientoId());
        voto.setDate(LocalDateTime.now());
        return voto;
    }
}
