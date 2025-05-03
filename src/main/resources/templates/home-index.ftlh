<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CarCtrl Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

<header class="header">CarCtrl Navigation</header>
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
    <div class="row">
        <div class="col-md-4">
            <div class="card profile-card shadow-lg p-3 mb-4 text-center">
                <img src="/images/bmw328i.jpg" alt="User Profile" class="profile-image rounded-circle">
                <#if user??>
                <h2>Welcome, ${user.username}!</h2>
                <p>Car Enthusiast | Member Since ${user.dateJoined?string("MMMM dd, yyyy")}
                </p>
                <#else>
                <h2>Welcome to CarCtrl!</h2>
            </#if>
        </div>
    </div>

    <div class="col-md-8 d-flex flex-column">
        <div class="row equal height">
            <div class="col-md-6">
                <div class="card shadow-sm mb-3 text-center equal-height">
                    <div class="card-body">
                        <h5 class="card-title">Explore Services</h5>
                        <p class="card-text">Find the right service for your vehicle today.</p>
                        <a href="/services/explore" class="btn btn-primary">Explore</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card shadow-sm mb-3 text-center equal-height">
                    <div class="card-body">
                        <h5 class="card-title">Service History</h5>
                        <p class="card-text">Your digital record keeper.</p>
                        <a href="/users/service-history-page" class="btn btn-primary">View</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card shadow-sm mb-3 text-center equal-height">
                    <div class="card-body">
                        <h5 class="card-title">Alerts</h5>
                        <p class="card-text">Stay alert to upcoming services.</p>
                        <a href="/users/alerts-page" class="btn btn-primary">View</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card shadow-sm mb-3 text-center equal-height">
                    <div class="card-body">
                        <h5 class="card-title">Edit Information</h5>
                        <p class="card-text">Keep your vehicle details up-to-date.</p>
                        <a href="/users/profile-page" class="btn btn-primary">Edit</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</main>
</div>
</body>
</html>