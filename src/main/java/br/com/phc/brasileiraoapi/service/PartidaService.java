package br.com.phc.brasileiraoapi.service;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.EquipeDto;
import br.com.phc.brasileiraoapi.dto.PartidaDto;
import br.com.phc.brasileiraoapi.dto.PartidaGoogleDto;
import br.com.phc.brasileiraoapi.dto.PartidaResponseDto;
import br.com.phc.brasileiraoapi.entity.Equipe;
import br.com.phc.brasileiraoapi.entity.Partida;
import br.com.phc.brasileiraoapi.exception.BadRequestException;
import br.com.phc.brasileiraoapi.exception.NotFoundException;
import br.com.phc.brasileiraoapi.repository.PartidaRepository;

@Service
public class PartidaService {
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EquipeService equipeService;

	public Partida buscarPartidaPorId(long id) {
		return partidaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma partida encontrada com o id informado: " + id));
	}

	public PartidaResponseDto listarPartidas() {
		PartidaResponseDto partidas = new PartidaResponseDto();
		partidas.setPartidas(partidaRepository.findAll());
		return partidas;
	}

	public Partida inserirPartida(PartidaDto dto) {
		Partida partida = mapper.map(dto, Partida.class);
		partida.setEquipeCasa(equipeService.buscarEquipeNome(dto.getNomeEquipeCasa()));
		partida.setEquipeVisitante(equipeService.buscarEquipeNome(dto.getNomeEquipeVisitante()));
		
		return salvarPartida(partida);
	}
	
	public Partida salvarPartida(Partida partida) {
		return partidaRepository.save(partida);
	}

	public void alterarPartida(Long id, PartidaDto dto) {
		boolean exists = partidaRepository.existsById(id);
		if(!exists) {
			throw new NotFoundException("Não foi possivel alterar a partida: ID - " + id + " inexistente");
		}
		Partida partida = buscarPartidaPorId(id);
		partida.setEquipeCasa(equipeService.buscarEquipeNome(dto.getNomeEquipeCasa()));
		partida.setEquipeVisitante(equipeService.buscarEquipeNome(dto.getNomeEquipeVisitante()));
		partida.setDataHoraPartida(dto.getDataHoraPartida());
		partida.setLocalPartida(dto.getLocalPartida());
		
		salvarPartida(partida);
	}

	public void atualizaPartida(Partida partida, PartidaGoogleDto partidaGoogle) {
		partida.setPlacarQuipeCasa(partidaGoogle.getPlacarEquipeCasa());
		partida.setPlacarQuipeVisitante(partidaGoogle.getPlacarEquipeVisitante());
		partida.setGolsEquipeCasa(partidaGoogle.getGolsEquipeCasa());
		partida.setGolsEquipeVisitante(partidaGoogle.getGolsEquipeVisitante());
		partida.setPlacarEstendidoEquipeCasa(partidaGoogle.getPlacarEstendidoEquipeCasa());
		partida.setPlacarEstendidoEquipeVisitante(partidaGoogle.getPlacarEstendidoEquipeVisitante());
		partida.setTempoPartida(partidaGoogle.getTempoPartida());
		
		salvarPartida(partida);
		
	}

	public List<Partida> listarPartidasPeriodo() {
		return partidaRepository.listarPartidasPeriodo();
	}

	public Integer buscarQuantidadePartidasPeriodo() {
		return partidaRepository.buscarQuantidadePartidasPeriodo();
	}

}
