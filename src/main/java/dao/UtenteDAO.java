package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.Utente;

public class UtenteDAO {

	private EntityManagerFactory emf;

	public void UtenteDao() {
		emf = Persistence.createEntityManagerFactory("nome-unita-di-persistenza");
	}

	public void aggiungiUtente(Utente utente) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(utente);
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
