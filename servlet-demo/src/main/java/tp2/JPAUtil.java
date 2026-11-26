package tp2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("tp2PU"); // même nom que dans persistence.xml

    public static EntityManagerFactory getEmf() { return emf; }
}