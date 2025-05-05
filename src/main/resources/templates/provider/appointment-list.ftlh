<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Appointments</title>
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <!-- Sidebar -->
    <nav class="sidebar">
        <a class="nav-link" href="/providers/home?providerId=${providerId}"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile/${providerId}"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link active" href="/appointments?providerId=${providerId}"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services/${providerId}"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts?providerId=${providerId}"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage?providerId=${providerId}"><i class="bi bi-chat-left-text"></i> Reviews</a>
    </nav>

    <!-- Main Content -->
    <div class="main-content px-4">
        <h2 class="mb-4 text-white">Your Appointments</h2>

        <#if appointments?size == 0>
        <p class="text-muted">No appointments found.</p>
        <#else>
        <div class="card">
            <div class="card-body table-responsive">
                <table class="table table-dark table-striped table-hover align-middle">
                    <thead>
                    <tr>
                        <th>Customer</th>
                        <th>Car</th>
                        <th>Service</th>
                        <th>Description</th>
                        <th>Date</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list appointments as appointment>
                    <tr>
                        <td>${appointment.user.username!"Unknown"}</td>
                        <td>${appointment.car.make} ${appointment.car.model}</td>
                        <td>${appointment.service.name}</td>
                        <td>${appointment.description!"-"}</td>
                        <td>${appointment.appointmentDate?string("yyyy-MM-dd HH:mm")}</td>
                        <td>${appointment.status}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </#if>
</div>
</div>
</body>
</html>
