package tp2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/authentifier")
public class AuthentifierServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");              // accents
        // 1. Lire les champs du formulaire (via leur attribut name)
        String email = request.getParameter("email");
        String mdpHache = Hachage.sha256(request.getParameter("mdp")); // on hache avant de comparer

        // 2. Chercher dans la BD
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            TypedQuery<Utilisateur> q = em.createQuery(
                    "SELECT u FROM Utilisateur u WHERE u.email = :email AND u.motDePasse = :mdp", Utilisateur.class);
            q.setParameter("email", email);
            q.setParameter("mdp", mdpHache);
            List<Utilisateur> resultat = q.getResultList();

            if (!resultat.isEmpty()) {
                // 3a. Succès : on mémorise l'utilisateur dans la SESSION
                HttpSession session = request.getSession();
                session.setAttribute("utilisateur", resultat.get(0));
                session.setMaxInactiveInterval(600);        // 10 minutes
                response.sendRedirect("bienvenue.jsp");
            } else {
                // 3b. Échec
                response.sendRedirect("erreur.jsp");
            }
        } finally {
            em.close();                                     // toujours libérer la connexion
        }
    }
}