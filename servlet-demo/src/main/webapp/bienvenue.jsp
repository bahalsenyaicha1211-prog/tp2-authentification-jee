<%@ page contentType="text/html;charset=UTF-8" %>
<%
    if (session.getAttribute("utilisateur") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Bienvenue</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <div class="carte bienvenue">
    <h2>Bienvenue ${sessionScope.utilisateur.prenom} ${sessionScope.utilisateur.nom} !</h2>
    <p>Vous êtes connecté.</p>
  </div>
</body>
</html>