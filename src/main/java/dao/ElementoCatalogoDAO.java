package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.ElementoCatalogo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElementoCatalogoDAO {

	private EntityManager em;

	public ElementoCatalogoDAO(EntityManager em) {
		this.em = em;
	}

	public void aggiungiElementoCatalogo(ElementoCatalogo elemento) {
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
		ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
		em.close();
		return elemento;
	}
}
