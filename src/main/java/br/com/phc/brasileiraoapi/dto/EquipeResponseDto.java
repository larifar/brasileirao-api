package br.com.phc.brasileiraoapi.dto;

import java.io.Serializable;
import java.util.List;

import br.com.phc.brasileiraoapi.entity.Equipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipeResponseDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Equipe> equipes;
}
