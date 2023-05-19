package entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rivista extends ElementoCatalogo {
	@Enumerated(EnumType.STRING)
	private Periodicita periodicita;

	public enum Periodicita {
		SETTIMANALE, MENSILE, SEMESTRALE
	}

	public Rivista(String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
		super(titolo, annoPubblicazione, numeroPagine);
		this.periodicita = periodicita;
	}

	@Override
	public String toString() {
		return "Rivista [periodicita=" + periodicita + "]";
	}

}
