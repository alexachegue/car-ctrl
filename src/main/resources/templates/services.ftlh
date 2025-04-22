<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CarCtrl Explore</title>
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
            <a class="nav-link text-white" href="/users/alerts-page"><i class="bi bi-bell"></i> Alerts</a>
            <a class="nav-link text-white" href="/users/service-history-page"><i class="bi bi-journal-check"></i> Service
                History</a>
            <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
        </nav>
    </div>
<div class = "main-content">
    <h1>All Services</h1>

    <#if selectedCategory??>
    <h2>Category: ${selectedCategory?capitalize}</h2>
    </#if>

<#if services?? && (services?size > 0)>
<div class="card-container">
    <#list services as service>
    <div class="card">
        <div class="card-title">${service.name} </div>
        <div class="card-text">${service.description} </div>

        <p><strong>Find Providers</strong> </p>
    </div>
</#list>
</div>
<#else>
<p>No services found.</p>
</#if>

</div>
</body>
</html>