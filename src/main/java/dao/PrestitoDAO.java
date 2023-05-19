package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Prestito;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrestitoDAO {
	private final EntityManager em;

	public PrestitoDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiPrestito(Prestito p) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(p);
			tx.commit();
			log.info("prestito salvato");

		} finally

		{
			em.close();
		}
	}

	public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
		TypedQuery<Prestito> query = em.createQuery(
				"SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL",
				Prestito.class);
		query.setParameter("oggi", new Date());
		List<Prestito> risultati = query.getResultList();
		em.close();
		return risultati;
	}
}
