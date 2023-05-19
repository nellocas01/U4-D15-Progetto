package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dao.ElementoCatalogoDAO;
import dao.LibroDAO;
import dao.RivistaDAO;
import dao.UtenteDAO;
import entities.ElementoCatalogo;
import entities.Libro;
import entities.Rivista;
import entities.Rivista.Periodicita;
import entities.Utente;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j
public class Catalogo {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		LibroDAO ld = new LibroDAO(em);
		RivistaDAO rd = new RivistaDAO(em);
		UtenteDAO ud = new UtenteDAO(em);
		ElementoCatalogoDAO ecd = new ElementoCatalogoDAO(em);

		Libro l = new Libro("Hunger Games", 1990, 40, "Pippo", "Fantasy");
		Rivista r = new Rivista("Corriere dello sport", 1950, 40, Periodicita.SETTIMANALE);
		Utente x = new Utente("Nello", "Casolla", "25/03/1990", "fz890");

		try {
			ecd.aggiungiElementoCatalogo(l);
			// ecd.aggiungiElementoCatalogo(r);
			// ecd.rimuoviElementoCatalogo("78144db5-c5e2-4035-bc3f-56cbe99fe35a");
		} finally {
			em.close();
		}
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
			log.info("elemento aggiunto");
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
			log.info("elemento rimosso");
			em.close();
		}
	}

	public ElementoCatalogo ricercaPerISBN(String isbn) {
		EntityManager em = emf.createEntityManager();
		ElementoCatalogo elemento = em.find(ElementoCatalogo.class, isbn);
		log.info("elemento ricercato");
		em.close();
		return elemento;
	}

	public List<ElementoCatalogo> ricercaPerAnnoPubblicazione(int annoPubblicazione) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ElementoCatalogo> query = em.createQuery(
				"SELECT e FROM ElementoCatalogo e WHERE e.annoPubblicazione = :anno", ElementoCatalogo.class);
		query.setParameter("anno", annoPubblicazione);
		List<ElementoCatalogo> risultati = query.getResultList();
		log.info("anno ricercato");
		em.close();
		return risultati;
	}

	public List<ElementoCatalogo> ricercaPerAutore(String autore) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ElementoCatalogo> query = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.autore = :autore",
				ElementoCatalogo.class);
		query.setParameter("autore", autore);
		List<ElementoCatalogo> risultati = query.getResultList();
		log.info("autore trovato");
		em.close();
		return risultati;
	}

	public List<ElementoCatalogo> ricercaPerTitolo(String titolo) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<ElementoCatalogo> query = em
				.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.titolo LIKE :titolo", ElementoCatalogo.class);
		query.setParameter("titolo", "%" + titolo + "%");
		List<ElementoCatalogo> risultati = query.getResultList();
		log.info("titolo trovato");
		em.close();
		return risultati;
	}

	public Query ricercaElementiInPrestito(String numeroTesseraUtente) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("SELECT p.elementoPrestato FROM Prestito p WHERE p.utente.numero");
		log.info("numero tessera trovato");
		return query;
	}
}
