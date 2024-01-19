package br.com.phc.brasileiraoapi.dto;

import java.io.Serializable;

import br.com.phc.brasileiraoapi.util.StatusPartida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaGoogleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private StatusPartida statusPartida;
	private String tempoPartida;
	// info equipe da casa
	private String nomeEquipeCasa;
	private String urlLogoEquipeCasa;
	private Integer placarEquipeCasa;
	private String golsEquipeCasa;
	private Integer placarEstendidoEquipeCasa;
	// info equipe da visitante
	private String nomeEquipeVisitante;
	private String urlLogoEquipeVisitante;
	private Integer placarEquipeVisitante;
	private String golsEquipeVisitante;
	private Integer placarEstendidoEquipeVisitante;
	
	

}
