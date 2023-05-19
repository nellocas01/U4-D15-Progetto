package entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Prestito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	@ManyToOne
	private Utente utente;

	@ManyToOne
	private ElementoCatalogo elementoPrestato;

	@Temporal(TemporalType.DATE)
	private Date dataInizioPrestito;

	@Temporal(TemporalType.DATE)
	private Date dataRestituzionePrevista;

	@Temporal(TemporalType.DATE)
	private Date dataRestituzioneEffettiva;

	public Prestito(UUID id, Utente utente, ElementoCatalogo elementoPrestato, Date dataInizioPrestito,
			Date dataRestituzionePrevista, Date dataRestituzioneEffettiva) {
		super();
		this.id = id;
		this.utente = utente;
		this.elementoPrestato = elementoPrestato;
		this.dataInizioPrestito = dataInizioPrestito;
		this.dataRestituzionePrevista = dataRestituzionePrevista;
		this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
	}

	@Override
	public String toString() {
		return "Prestito [id=" + id + ", utente=" + utente + ", elementoPrestato=" + elementoPrestato
				+ ", dataInizioPrestito=" + dataInizioPrestito + ", dataRestituzionePrevista="
				+ dataRestituzionePrevista + ", dataRestituzioneEffettiva=" + dataRestituzioneEffettiva + "]";
	}

}
