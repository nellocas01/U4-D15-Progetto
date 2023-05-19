package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Utente;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtenteDAO {

	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiUtente(Utente u) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(u);
			tx.commit();
			log.info("utente salvato");

		} finally

		{
			em.close();
		}
	}
}
