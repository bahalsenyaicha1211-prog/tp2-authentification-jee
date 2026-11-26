package tp2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/inscrire")
public class InscriptionServlet extends HttpServlet {

    // Si quelqu'un tape /inscrire dans la barre d'adresse (GET) : retour au formulaire
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect("inscription.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Lire les champs (trim() enlève les espaces au début et à la fin)
        String nom = request.getParameter("nom").trim();
        String prenom = request.getParameter("prenom").trim();
        String email = request.getParameter("email").trim();
        String mdp = request.getParameter("mdp");
        String mdp2 = request.getParameter("mdp2");

        // 2. Vérifier que les deux mots de passe sont identiques
        if (!mdp.equals(mdp2)) {
            renvoyerErreur(request, response, "Les mots de passe ne correspondent pas.");
            return;
        }

        EntityManager em = JPAUtil.getEmf().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            // 3. Vérifier que l'email n'est pas déjà utilisé
            Long deja = em.createQuery(
                            "SELECT COUNT(u) FROM Utilisateur u WHERE u.email = :email", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (deja > 0) {
                renvoyerErreur(request, response, "Cet email est déjà utilisé.");
                return;
            }

            // 4. Créer l'objet et le HACHER avant de l'enregistrer
            Utilisateur u = new Utilisateur();
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setMotDePasse(Hachage.sha256(mdp));

            // 5. Enregistrer : une écriture doit être dans une transaction
            tx.begin();
            em.persist(u);          // équivalent d'un INSERT
            tx.commit();            // valide définitivement

            // 6. Succès : on va vers la page de connexion avec un petit indicateur
            response.sendRedirect("index.jsp?inscrit=1");

        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();   // en cas de problème, on annule tout
            renvoyerErreur(request, response, "Erreur lors de l'inscription.");
        } finally {
            em.close();
        }
    }

    // Réaffiche le formulaire avec un message
    private void renvoyerErreur(HttpServletRequest request, HttpServletResponse response,
                                String message) throws ServletException, IOException {
        request.setAttribute("erreur", message);
        request.getRequestDispatcher("inscription.jsp").forward(request, response);
    }
}