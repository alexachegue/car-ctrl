<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CarCtrl</title>
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

<div class="header">CarCtrl</div>
<div class="d-flex">
    <nav class="nav flex-column p-3 vh-100 text-white" style="width: 250px; position: fixed; height: 100%;">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link text-white" href="/users/home-page"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link text-white" href="/services/explore"><i class="bi bi-search"></i> Explore</a>
        <a class="nav-link text-white" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i>
            Service
            History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>

<div class="main-content">
    <h1>Providers Offering: ${service.name}</h1>
    <div class="row">
        <#list providers as provider>
        <div class="col-md-4 mb-4">
            <div class="card h-100 shadow rounded-3">
                <div class="card-body">
                    <h5 class="card-title">${provider.providername}</h5>
                    <p class="card-text">
                        <strong>Email:</strong> ${provider.email}<br>
                        <strong>Phone:</strong> ${provider.phone}<br>
                        <strong>Address:</strong> ${provider.address}<br>
                        <strong>Hours:</strong> ${provider.operatingHours}<br>
                        <strong>Bio:</strong> ${provider.bio}
                    </p>
                    <p class="card-text">
                        <small class="text-muted">Category: ${service.serviceCategory}</small>
                    </p>
                    <a href="/appointments/form?providerId=${provider.providerId}&serviceId=${serviceId}"
                       class="btn btn-primary w-100 mt-2">Book Appointment</a>
                </div>
            </div>
        </div>
        </#list>
    </div>
<a href="/services/service-category/${category}" class="btn btn-outline-dark mt-2"> Back </a>
</div>
</body>
</html>
