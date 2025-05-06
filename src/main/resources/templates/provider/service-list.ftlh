<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <!-- Sidebar -->
    <nav class="sidebar">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link" href="/providers/home"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link active" href="/service/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
    </nav>

    <!-- Main Content -->
    <div class="main-content p-4">
        <h2 class="mb-4">${title}</h2>

        <!-- Current Services -->
        <h4>Services You Provide</h4>
        <#if providerServices?size == 0>
        <p class="text-muted">You haven't selected any services yet.</p>
        <#else>
        <div class="row row-cols-1 row-cols-md-2 g-4 mb-5">
            <#list providerServices as service>
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">${service.name}</h5>
                        <p class="card-text"><strong>Category:</strong> ${service.category}</p>
                        <p class="card-text">${service.description}</p>
                        <form method="post" action="/service/unassign">
                            <input type="hidden" name="serviceId" value="${service.serviceId}">
                            <button class="btn btn-primary btn-sm mt-2" onclick="return confirm('Remove this service?');">Remove</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#if>

<!-- Available Services to Add -->
<h4 class="text-light">Other Available Services</h4>
<#if unassignedServices?size == 0>
<p class="text-muted">All services have been assigned.</p>
<#else>
<div class="row row-cols-1 row-cols-md-2 g-4">
    <#list unassignedServices as service>
    <div class="col">
        <div class="card h-100">
            <div class="card-body">
                <h5 class="card-title">${service.name}</h5>
                <p class="card-text"><strong>Category:</strong> ${service.category}</p>
                <p class="card-text">${service.description}</p>
                <form method="post" action="/service/assign">
                    <input type="hidden" name="serviceId" value="${service.serviceId}">
                    <input type="hidden" name="providerId" value="${provider.providerId}">
                    <button class="btn btn-primary btn-sm mt-2">Add This Service</button>
                </form>
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
