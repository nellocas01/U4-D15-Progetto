package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Rivista;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RivistaDAO {

	private final EntityManager em;

	public RivistaDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiRivista(Rivista r) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(r);
			tx.commit();
			log.info("libro salvato");

		} finally

		{
			em.close();
		}
	}
}
