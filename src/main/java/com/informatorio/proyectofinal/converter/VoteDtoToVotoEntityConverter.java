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

    private final EmprendimientoRepository emprendimientoRepository;
    private final UserRepository userRepository;

    public VoteDtoToVotoEntityConverter(EmprendimientoRepository emprendimientoRepository,
                                        UserRepository userRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Voto convert(VoteDTO voteDto){
        Voto voto = new Voto();
        voto.setUserId(voteDto.getUserId());
        voto.setEmpresaId(voteDto.getEmprendimientoId());
        voto.setDate(LocalDateTime.now());
        return voto;
    }
}
