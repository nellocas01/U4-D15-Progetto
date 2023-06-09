package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Libro extends ElementoCatalogo {

	private String autore;
	private String genere;

	public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
		super(titolo, annoPubblicazione, numeroPagine);
		this.autore = autore;
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Libro [autore=" + autore + ", genere=" + genere + "]";
	}

}
