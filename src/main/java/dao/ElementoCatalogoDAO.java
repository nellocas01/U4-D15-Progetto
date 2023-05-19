package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entities.ElementoCatalogo;

public class ElementoCatalogoDAO {

	private EntityManagerFactory emf;

	public void ElementoCatalogoDao() {
		emf = Persistence.createEntityManagerFactory("U4-D15-Progetto");
	}

	public void aggiungiElementoCatalogo(ElementoCatalogo elemento) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(elemento);
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

	public void rimuoviElementoCatalogo(String isbn) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
			if (elemento != null) {
				em.remove(elemento);
			}
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

	public ElementoCatalogo ricercaPerISBN(String isbn) {
		EntityManager em = emf.createEntityManager();
		ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
		em.close();
		return elemento;
	}
}
