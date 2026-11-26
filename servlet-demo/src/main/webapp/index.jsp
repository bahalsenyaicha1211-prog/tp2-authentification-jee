<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
<title>Authentification</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
<div class="carte">
    <h2>Connexion</h2>
  <% if ("1".equals(request.getParameter("inscrit"))) { %>
      <p style="color:green">Inscription réussie, vous pouvez vous connecter.</p>
  <% } %>
  <!-- action SANS slash devant : sinon erreur 404 -->
  <form action="authentifier" method="post">
    <input type="email" name="email" placeholder="Email" required><br>
    <input type="password" name="mdp" placeholder="Mot de passe" required><br>
    <input type="submit" value="OK">
  </form>
  <p class="lien">Pas encore de compte ? <a href="inscription.jsp">S'inscrire</a></p>
  </div>
</body>
</html>