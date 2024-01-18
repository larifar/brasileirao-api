package br.com.phc.brasileiraoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.EquipeResponseDto;
import br.com.phc.brasileiraoapi.entity.Equipe;
import br.com.phc.brasileiraoapi.exception.NotFoundException;
import br.com.phc.brasileiraoapi.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository equipeRepository;
	
	public Equipe buscarEquipeId(long id) {
		return equipeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o id informado: " + id));
	}

	public EquipeResponseDto listarEquipes() {
		EquipeResponseDto equipes = new EquipeResponseDto();
		equipes.setEquipes(equipeRepository.findAll());
		return equipes;
	}

}
