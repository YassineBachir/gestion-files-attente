<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des Tickets</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            text-align: center;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #007bff;
        }

        div {
            background: white;
            padding: 20px;
            margin: 20px auto;
            width: 50%;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        p {
            font-size: 18px;
            color: #333;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 5px;
            transition: background 0.3s;
            cursor: pointer;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        form {
            display: inline-block;
        }
    </style>
</head>
<body>
<h1>Gestion des Tickets - Agent: ${nomAgent} - Localisation: ${nomLocalisation}</h1>

<div>
    <h3>Ticket Actuel</h3>
    <p><strong>Numéro de ticket :</strong> ${ticket.ticketNumber}</p>
    <p><strong>Position dans la file :</strong> ${ticket.position}</p>
    <p><strong>Nombre de personnes devant :</strong> ${ticket.peopleAhead}</p>
    <p><strong>Numéro en cours de traitement :</strong> ${ticket.currentProcessing}</p>
</div>

<!-- Boutons pour passer au client suivant/précédent -->
<form action="/agent/${localisationId}/next" method="post">
    <button type="submit" class="btn">Client suivant</button>
</form>

<form action="/agent/${localisationId}/previous" method="post">
    <button type="submit" class="btn">Client précédent</button>
</form>

</body>
</html>
