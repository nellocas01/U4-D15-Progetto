package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Libro;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LibroDAO {

	private final EntityManager em;

	public LibroDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiLibro(Libro ld) {
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(ld);
			tx.commit();
			log.info("libro salvato");

		} finally

		{
			em.close();
		}
	}
}
