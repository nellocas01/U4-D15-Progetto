package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Prestito;

public class PrestitoDAO {

	private EntityManagerFactory emf;

	public void PrestitoDao() {
		emf = Persistence.createEntityManagerFactory("U4-D15-Progetto");
	}

	public void aggiungiPrestito(Prestito prestito) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(prestito);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public List<Prestito> ricercaPrestitiScadutiNonRestituiti() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Prestito> query = em.createQuery(
				"SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL",
				Prestito.class);
		query.setParameter("oggi", new Date());
		List<Prestito> risultati = query.getResultList();
		em.close();
		return risultati;
	}
}
