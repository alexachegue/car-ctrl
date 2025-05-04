<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Review Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

<header class="header">CarCtrl</header>

<div class="d-flex">
    <nav class="nav flex-column p-3 vh-100 text-white" style="width: 250px; position: fixed; height: 100%;">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link text-white" href="/users/home-page"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link text-white" href="/services/explore"><i class="bi bi-search"></i> Explore</a>
        <a class="nav-link text-white" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i>
            Service History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>

<main class="container mt-4 main-content">
    <h1 class="mb-4">Review Details</h1>

    <div class="card mb-4">
        <div class="card-body">
            <p class="card-text"><strong>Reviewed by:</strong> ${review.user.username}</p>
            <p class="card-text"><strong>Provider:</strong> ${review.provider.providername}</p>
            <p class="card-text"><strong>Rating:</strong> ${review.rating}/5</p>
            <p class="card-text"><strong>Description:</strong><br>${review.description}</p>
            <p class="card-text"><strong>Provider Response:</strong><br>
                <span class="border p-2 d-block">${review.providerResponse!"(No response yet)"}</span>
            </p>
        </div>
    </div>

    <a href="/appointments/service-history-page" class="btn btn-secondary">Back to History</a>
</main>
</body>
</html>
