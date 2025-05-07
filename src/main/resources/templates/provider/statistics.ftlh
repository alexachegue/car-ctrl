<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Statistics</title>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <!-- Sidebar -->
    <nav class="sidebar">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="display: block; margin: 0 auto; max-width: 80px; height: auto;">
        <a class="nav-link" href="/providers/home"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
        <a class="nav-link active" href="/providers/stats"><i class="bi bi-graph-up"></i> Statistics</a>
    </nav>

    <!-- Main Content -->
    <div class="main-content p-4">
        <h4 class="text-light">Your Service Statistics</h4>

        <#if stats?? && stats?size == 0>
        <p class="text-muted">No service statistics available yet.</p>
        <#elseif stats??>
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <#list stats as stat>
            <div class="col">
                <div class="card h-100 shadow-sm border-0">
                    <div class="card-body text-center">
                        <i class="bi bi-graph-up" style="font-size: 2rem; color: #C91B00;"></i>
                        <h5 class="card-title mt-2">${stat.serviceName}</h5>
                        <p class="card-text mb-1"><strong>Total Requests:</strong> ${stat.totalRequests}</p>
                        <p class="card-text mb-1"><strong>Average Rating:</strong> ${stat.averageRating?string("0.0")}</p>
                </div>
            </div>
        </div>
    </#list>
</div>
</#if>
</div>
</div>
</body>
</html>