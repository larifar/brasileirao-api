package br.com.phc.brasileiraoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.PartidaGoogleDto;
import br.com.phc.brasileiraoapi.entity.Partida;
import br.com.phc.brasileiraoapi.util.ScrappingUtil;

@Service
public class ScrapingService {
	
	@Autowired
	private ScrappingUtil scrappingUtil;
	
	@Autowired
	private PartidaService partidaService;
	
	public void verificaPartidasPeriodo() {
		Integer quantidadePartida = partidaService.buscarQuantidadePartidasPeriodo();
		
		if(quantidadePartida > 0) {
			List<Partida> partidas = partidaService.listarPartidasPeriodo();
			
			partidas.forEach(partida -> {
				String urlPartida = scrappingUtil.montaUrlGoogle(
						partida.getEquipeCasa().getNomeEquipe(), 
						partida.getEquipeVisitante().getNomeEquipe());
				
				PartidaGoogleDto partidaGoogle = scrappingUtil.obtemInfoPartida(urlPartida);
				
				partidaService.atualizaPartida(partida, partidaGoogle);
			});
			
			
		}
	}

}
