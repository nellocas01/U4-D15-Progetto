package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-D15-Progetto");

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

}