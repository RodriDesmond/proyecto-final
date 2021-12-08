package com.informatorio.proyectofinal.service;

import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmprendimientoService {

	private final EmprendimientoRepository emprendimientoRepository;;

	public EmprendimientoService(EmprendimientoRepository emprendimientoRepository) {
		this.emprendimientoRepository = emprendimientoRepository;
	}

	public Emprendimiento removeEmprendimiento(Long id, Emprendimiento emprendimiento) {
		Emprendimiento inDB = emprendimientoRepository.getById(id);
		inDB.setActive(false);
		return emprendimientoRepository.save(inDB);
	}
}
