<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CarCtrl – Provider Alerts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>

<header class="header">CarCtrl – Provider</header>
<div class="d-flex">
    <nav class="nav flex-column p-3 vh-100 text-white" style="width: 250px; position: fixed;">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link" href="/providers/home"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link active" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
        <a class="nav-link" href="/providers/stats"><i class="bi bi-graph-up"></i> View Statistics</a>
    </nav>

    <div class="container mt-4" style="margin-left: 270px;">
        <h1 class="mb-4">Your Alerts</h1>
        <#if alertList?size == 0>
        <div class="alert alert-secondary">No alerts to show right now.</div>
        <#else>
        <#list alertList as alert>
        <div class="alert alert-${alert.alertType?lower_case} d-flex justify-content-between align-items-center">
            <div>
                <strong>[${alert.alertType}]</strong> ${alert.message}
                <br>
                <small class="text-muted">Created: ${alert.createdAt?string("yyyy-MM-dd HH:mm")}</small>
            </div>
            <form method="post" action="/alerts/delete/${alert.alertId}">
                <button type="submit" class="btn-close" aria-label="Close"></button>
            </form>
        </div>
    </#list>
</#if>
</div>
</div>
</body>
</html>