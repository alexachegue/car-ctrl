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

<header class="header">CarCtrl</header>
<div class="d-flex">
    <nav class="nav flex-column p-3 vh-100 text-white" style="width: 250px; position: fixed; height: 100%;">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link text-white" href="/users/home-page"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link text-white" href="/services/explore"><i class="bi bi-search"></i> Explore</a>
        <a class="nav-link text-white" href="/alerts/user"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i> Service History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>

<div class="container mt-5" style="margin-left: 260px;">
    <h1 class="mb-4">Service History</h1>

    <#if appointments?size == 0>
        <p>No appointments found.</p>
    <#else>
        <div class="row">
            <#list appointments as a>
                <div class="col-md-6 mb-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">${a.service.name}</h5>
                            <p><strong>Date:</strong> ${a.appointmentDate?string("yyyy-MM-dd HH:mm")}</p>
                            <p><strong>Status:</strong> ${a.status}</p>
                            <p><strong>Car:</strong> ${a.car.make} ${a.car.model} (${a.car.year})</p>
                            <p><strong>Description:</strong> ${a.description!""}</p>

                            <#if a.appointmentDate?long < .now?long>
                            <a href="/reviews/new?appointmentId=${a.appointmentId}" class="btn btn-sm btn-outline-secondary me-2">Leave Review</a>
                            <a href="/reviews/view/appointment/${a.appointmentId}" class="btn btn-sm btn-outline-primary">View Review</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</div>
</body>
</html>
