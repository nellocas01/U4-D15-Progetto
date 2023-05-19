package entities;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class ElementoCatalogo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID isbn;

	private String titolo;
	private int annoPubblicazione;
	private int numeroPagine;

	public ElementoCatalogo(String titolo, int annoPubblicazione, int numeroPagine) {
		super();
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numeroPagine = numeroPagine;
	}

	@Override
	public String toString() {
		return "ElementoCatalogo [isbn=" + isbn + ", titolo=" + titolo + ", annoPubblicazione=" + annoPubblicazione
				+ ", numeroPagine=" + numeroPagine + "]";
	}

}
