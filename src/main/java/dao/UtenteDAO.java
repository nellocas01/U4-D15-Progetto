package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Libro;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UtenteDAO {

	private final EntityManager em;

	public UtenteDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiUtente(Libro libro) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(libro);
			tx.commit();
			log.info("libro salvato");

		} finally

		{
			em.close();
		}
	}
}
