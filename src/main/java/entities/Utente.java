package entities;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	private String nome;
	private String cognome;
	private String dataNascita;
	private String numeroTessera;

	public Utente(String nome, String cognome, String dataNascita, String numeroTessera) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.numeroTessera = numeroTessera;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", numeroTessera=" + numeroTessera + "]";
	}

}
