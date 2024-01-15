package br.com.phc.brasileiraoapi.util;

import br.com.phc.brasileiraoapi.dto.PartidaGoogleDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScrappingUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrappingUtil.class);
	private static final String BASE_URL_GOOGLE = "https://www.google.com/search?q=" ;
	private static final String COMPLEMENTO_URL_GOOGLE = "&hl=pt-BR" ;
	
	private static final String DIV_PARTIDA_ENCERRADA = "span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]";
	private static final String DIV_TEMPO_PARTIDA = "div[class=imso_mh__lv-m-stts-cont]";
	private static final String DIV_DADOS_CASA = "div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String DIV_DADOS_VISITANTE = "div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String ITEM_LOGO = "img[class=imso_btl__mh-logo]";
	private static final String DIV_PLACAR_VISITANTE = "div[class=imso_mh__r-tm-sc imso_mh__scr-it imso-light-font]";
	private static final String DIV_PLACAR_CASA = "div[class=imso_mh__l-tm-sc imso_mh__scr-it imso-light-font]";
	private static final String DIV_GOLS = "div[class=imso_gs__tgs imso_gs__";
	private static final String DIV_PENALIDADES = "div[class=imso_mh_s__psn-sc]";
	private static final String ITEM_GOL = "div[class=imso_gs__gs-r]";
	private static final String CASA = "casa";
	private static final String VISITANTE = "visitante";

	public static void main(String[] args) {
		String url = BASE_URL_GOOGLE + "corinthians+x+palmeiras+08/08/2020" + COMPLEMENTO_URL_GOOGLE ;
		
		ScrappingUtil scrapping = new ScrappingUtil();
		scrapping.obtemInfoPartida(url);
		

	}
	
	public PartidaGoogleDto obtemInfoPartida(String url) {
		PartidaGoogleDto partida = new PartidaGoogleDto();
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			String title = document.title();
			LOGGER.info("Titulo da página: {}", title);
			StatusPartida statusPartida = obtemStatusPartida(document);
			LOGGER.info("Status da partida: {}", statusPartida);
			
			if(statusPartida != StatusPartida.PARTIDA_NAO_INICIADA) {
				String tempoPartida = obtemTempoPartida(document);
				LOGGER.info("Tempo da partida: {}",tempoPartida);
				
				Integer placarEquipeCasa = recuperaPlacarEquipe(document, DIV_PLACAR_CASA);
				LOGGER.info("Placar equipe casa: {}", placarEquipeCasa);
				
				Integer placarEquipeVisitante = recuperaPlacarEquipe(document, DIV_PLACAR_VISITANTE);
				LOGGER.info("Placar equipe Visitante: {}", placarEquipeVisitante);
				
				String golsEquipeCasa = recuperaGolsEquipe(document, CASA);
				LOGGER.info("Gols da casa: {}", golsEquipeCasa);
				
				String golsEquipeVisitante = recuperaGolsEquipe(document, VISITANTE);
				LOGGER.info("Gols do visitante: {}", golsEquipeVisitante);
				
				Integer placarEstendidoEquipeCasa = buscaPenalidades(document, CASA);
				LOGGER.info("Placar estendido da casa: {}", placarEstendidoEquipeCasa);
				Integer placarEstendidoEquipeVisitante = buscaPenalidades(document, VISITANTE);
				LOGGER.info("Placar estendido do visitante: {}", placarEstendidoEquipeVisitante);
				
			}
			
			String nomeEquipeCasa = recuperaNomeEquipe(document, DIV_DADOS_CASA);
			LOGGER.info("Nome da equipe casa: {}", nomeEquipeCasa);
			
			String nomeEquipeVisitante = recuperaNomeEquipe(document, DIV_DADOS_VISITANTE);
			LOGGER.info("Nome da equipe visitante: {}", nomeEquipeVisitante);
			
			String urlLogoEquipeCasa = recuperaLogoEquipe(document, DIV_DADOS_CASA);
			LOGGER.info("URL da equipe casa: {}", urlLogoEquipeCasa);
			
			String urlLogoEquipeVisitante = recuperaLogoEquipe(document, DIV_DADOS_VISITANTE);
			LOGGER.info("URL da equipe visitante: {}", urlLogoEquipeVisitante);
			
		} catch (IOException e) {
			LOGGER.error("ERRO AO TENTAR CONECTAR NO GOOGLE COM JSOUP -> {}", e.getMessage());
		}
		
		return partida;
	}
	
	public StatusPartida obtemStatusPartida(Document document) {
		StatusPartida statusPartida = StatusPartida.PARTIDA_NAO_INICIADA;
		
		boolean isTempoPartida = document.select(DIV_TEMPO_PARTIDA).isEmpty();
		if(!isTempoPartida) {
			String tempoPartida = document.select(DIV_TEMPO_PARTIDA).first().text();
			statusPartida = StatusPartida.PARTIDA_EM_ANDAMENTO;
			if(tempoPartida.contains("Pênaltis")) {
				statusPartida = StatusPartida.PARTIDA_PENALTIS;
			}
			
		}
		isTempoPartida = document.select(DIV_PARTIDA_ENCERRADA).isEmpty();
		if(!isTempoPartida) {
			statusPartida = StatusPartida.PARTIDA_ENCERRADA;
		}
		
		return statusPartida;
	}
	
	public String obtemTempoPartida(Document document) {
		String tempoPartida = null;
		boolean isTempoPartida = document.select(DIV_TEMPO_PARTIDA).isEmpty();
		if(!isTempoPartida) {
			tempoPartida = document.select(DIV_TEMPO_PARTIDA).first().text();
		}
		
		isTempoPartida = document.select(DIV_PARTIDA_ENCERRADA).isEmpty();
		if(!isTempoPartida) {
			tempoPartida = document.select(DIV_PARTIDA_ENCERRADA).first().text();
		}
		
		return corrigeTempoPartida(tempoPartida);
	}
	
	public String corrigeTempoPartida(String tempo) {
		 if(tempo.contains("'")) {
			 return tempo.replace("'", " min");
		 } else {
			 return tempo;
		 }
	}
	
	public String recuperaNomeEquipe(Document document, String itemHtml) {
		Element elemento = document.selectFirst(itemHtml);
		return elemento.select("span").text();
	}
	
	public String recuperaLogoEquipe(Document document, String itemHtml) {
		Element elemento = document.selectFirst(itemHtml);
		return elemento.select(ITEM_LOGO).attr("src");
	}
	
	public Integer recuperaPlacarEquipe(Document document, String itemHtml) {
		String placarEquipe = document.selectFirst(itemHtml).text();
		return formataPlacarStringInteger(placarEquipe);
	}
	
	public String recuperaGolsEquipe(Document document, String tipoEquipe) {
		List<String> golsEquipe = new ArrayList<>();
		String time = tipoEquipe.equals(CASA) ? "left-team]" : "right-team]";
		
		Elements elementos = document.select(DIV_GOLS + time).select(ITEM_GOL);
		for (Element e: elementos) {
			String infoGol = e.select(ITEM_GOL).text();
			golsEquipe.add(infoGol);
		}
		
		return String.join(", ", golsEquipe);
	}
	
	public Integer buscaPenalidades(Document document, String tipoEquipe) {
		boolean isPenalidade = document.select(DIV_PENALIDADES).isEmpty();
		if(!isPenalidade) {
			String penalidades = document.select(DIV_PENALIDADES).text();
			String penalidadesCompleta = penalidades.substring(0, 5).replace(" ", "");
			
			String[] divisao = penalidadesCompleta.split("-");
			
			return tipoEquipe.equals(CASA) ? formataPlacarStringInteger(divisao[0]) : formataPlacarStringInteger(divisao[1]);
		}
		
		return null;
	}
	
	public Integer formataPlacarStringInteger(String placar) {
		Integer valor;
		try {
			valor = Integer.parseInt(placar);
		}catch (Exception e) {
			valor = 0;
		}
		
		return valor;
	}

}
