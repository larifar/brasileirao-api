package br.com.phc.brasileiraoapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.EquipeDto;
import br.com.phc.brasileiraoapi.dto.EquipeResponseDto;
import br.com.phc.brasileiraoapi.entity.Equipe;
import br.com.phc.brasileiraoapi.exception.BadRequestException;
import br.com.phc.brasileiraoapi.exception.NotFoundException;
import br.com.phc.brasileiraoapi.repository.EquipeRepository;

@Service
public class EquipeService {

	@Autowired
	private EquipeRepository equipeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Equipe buscarEquipeId(long id) {
		return equipeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o id informado: " + id));
	}
	
	public Equipe buscarEquipeNome(String nomeEquipe) {
		return equipeRepository.findByNomeEquipe(nomeEquipe)
				.orElseThrow(() -> new NotFoundException("Nenhuma equipe encontrada com o nome informado: " + nomeEquipe));
	}

	public EquipeResponseDto listarEquipes() {
		EquipeResponseDto equipes = new EquipeResponseDto();
		equipes.setEquipes(equipeRepository.findAll());
		return equipes;
	}

	public Equipe inserirEquipe(EquipeDto dto) {
		boolean exists = equipeRepository.existsByNomeEquipe(dto.getNomeEquipe());
		if (exists) {
			throw new BadRequestException("Já existe uma equipe cadastrada com o nome informado");
		}
		Equipe equipe = mapper.map(dto, Equipe.class);
		return equipeRepository.save(equipe);
	}

	public void alterarEquipe(Long id, EquipeDto dto) {
		boolean exists = equipeRepository.existsById(id);
		if(!exists) {
			throw new BadRequestException("Não foi possivel alterar a equipe: ID - " + id + " inexistente");
		}
		Equipe equipe = mapper.map(dto, Equipe.class);
		equipe.setId(id);
		equipeRepository.save(equipe);
	}

}
