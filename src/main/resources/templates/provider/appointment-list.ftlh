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
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo"
             style="display: block; margin: 0 auto 20px auto; max-width: 80px; height: auto;">
        <a class="nav-link" href="/providers/home"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link active" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
        <a class="nav-link" href="/providers/stats"><i class="bi bi-graph-up"></i> View Statistics</a>

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
                        <td>
                            ${appointment.status}
                            <#if appointment.status == "Pending">
                            <form action="/appointments/accept" method="post" style="display:inline;">
                                <input type="hidden" name="appointmentId" value="${appointment.appointmentId}">
                                <button type="submit" class="btn btn-success btn-sm ms-2">Accept</button>
                            </form>
                        </#if>
                        </td>

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
