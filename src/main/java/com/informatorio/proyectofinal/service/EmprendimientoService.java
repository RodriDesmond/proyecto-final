package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.dto.EmprendimientoDto;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.TagsRepository;
import org.springframework.stereotype.Service;

@Service
public class EmprendimientoService {

	private final EmprendimientoRepository emprendimientoRepository;

	public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, TagsRepository tagsRepository) {
		this.emprendimientoRepository = emprendimientoRepository;
	}

	public Emprendimiento updateEmprendimiento(Long id, EmprendimientoDto emprendimientoDto) {

		Emprendimiento inDB = emprendimientoRepository.getById(id);

		if (emprendimientoDto.getName() != null) {
			inDB.setName(emprendimientoDto.getName());
		}
		if (emprendimientoDto.getContent() != null) {
			inDB.setContent(emprendimientoDto.getContent());
		}
		if (emprendimientoDto.getDescription() != null) {
			inDB.setDescription(emprendimientoDto.getDescription());
		}
		if (emprendimientoDto.getGoal() != 0) {
			inDB.setGoal(emprendimientoDto.getGoal());
		}

		inDB.getTags().addAll(emprendimientoDto.getTags());

		return emprendimientoRepository.save(inDB);
	}

	public Emprendimiento removeEmprendimiento(Long id, Emprendimiento emprendimiento) {
		Emprendimiento inDB = emprendimientoRepository.getById(id);
		inDB.setActive(false);
		return emprendimientoRepository.save(inDB);
	}
}
