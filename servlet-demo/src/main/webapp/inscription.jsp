<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Inscription</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div class="carte">
    <h2>Inscription</h2>

    <%
        String erreur = (String) request.getAttribute("erreur");
        if (erreur != null) {
    %>
      <div class="message erreur"><%= erreur %></div>
    <% } %>

    <form action="inscrire" method="post">
      <input type="text" name="nom" placeholder="Nom" required>
      <input type="text" name="prenom" placeholder="Prénom" required>
      <input type="email" name="email" placeholder="Email" required>
      <input type="password" name="mdp" placeholder="Mot de passe" required>
      <input type="password" name="mdp2" placeholder="Confirmer le mot de passe" required>
      <input type="submit" value="S'inscrire">
    </form>

    <p class="lien">Déjà inscrit ? <a href="index.jsp">Se connecter</a></p>
  </div>
</body>
</html>