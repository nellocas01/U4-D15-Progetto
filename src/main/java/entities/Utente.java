package entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	private String nome;
	private String cognome;
	private Date dataNascita;
	private String numeroTessera;

	public Utente(UUID id, String nome, String cognome, Date dataNascita, String numeroTessera) {
		super();
		this.id = id;
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
