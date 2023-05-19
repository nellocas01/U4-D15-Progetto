package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Libro;

public class LibroDAO {

	private EntityManagerFactory emf;

	public void LibroDao() {
		emf = Persistence.createEntityManagerFactory("nome-unita-di-persistenza");
	}

	public void aggiungiLibro(Libro libro) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(libro);
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
}
