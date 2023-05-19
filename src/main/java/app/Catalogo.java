package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.ElementoCatalogo;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Catalogo {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {


		EntityManager em = emf.createEntityManager();
	

	public aggiungiElementoCatalogo(ElementoCatalogo elemento) {
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

	public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ElementoCatalogo> query = em.createQuery(
				"SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
		query.setParameter("anno", annoPubblicazione);
		List<ElementoCatalogo> risultati = query.getResultList();
		em.close();
		return risultati;
	}

	public List<ElementoCatalogo> ricercaPerAutore(String autore) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ElementoCatalogo> query = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.autore = :autore",
				ElementoCatalogo.class);
		query.setParameter("autore", autore);
		List<ElementoCatalogo> risultati = query.getResultList();
		em.close();
		return risultati;
	}

	public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ElementoCatalogo> query = em
				.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class);
		query.setParameter("titolo", "%" + titolo + "%");
		List<ElementoCatalogo> risultati = query.getResultList();
		em.close();
		return risultati;
	}

	public Query ricercaElementiInPrestito(String numeroTesseraUtente) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND p.dataRestituzioneEffettiva IS");
		return query;

	}
	
}
}
