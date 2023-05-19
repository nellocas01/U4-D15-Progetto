package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Rivista;

public class RivistaDAO {

	private EntityManagerFactory emf;

	public void RivistaDao() {
		emf = Persistence.createEntityManagerFactory("nome-unita-di-persistenza");
	}

	public void aggiungiRivista(Rivista rivista) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(rivista);
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
