<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alerts</title>
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
        <a class="nav-link" href="/appointments?providerId=${providerId}"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services/${providerId}"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link active" href="/alerts?providerId=${providerId}"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage?providerId=${providerId}"><i class="bi bi-chat-left-text"></i> Reviews</a>

    </nav>

    <!-- Main Content -->
    <div class="main-content">
        <h2>${title}</h2>

        <!-- Filter -->
        <form method="get" action="/alerts">
            <div class="mb-3">
                <label for="type" class="form-label">Filter by Type:</label>
                <select id="type" name="type" class="form-select" onchange="this.form.submit()">
<!--                    <option value="" <#if filter?has_content && filter == "">selected</#if>>All Notifications</option>-->
                    <option value="" <#if filter == "All">selected</#if>>All Notifications</option>
                    <option value="New Appointment" <#if filter == "New Appointment">selected</#if>>Appointments</option>
                    <option value="Reminder" <#if filter == "Reminder">selected</#if>>Reminders</option>
                    <option value="Review" <#if filter == "Review">selected</#if>>Reviews</option>
                </select>
            </div>
        </form>


<!-- Alert Cards -->
        <#if alertList?size == 0>
        <p>No alerts to display.</p>
        <#else>
        <div class="alerts-container">
            <#list alertList as alert>
            <div class="alert alert-dark d-flex justify-content-between align-items-center" role="alert" data-type="${alert.alertType}">
                <div>
                    <h5 class="alert-heading">${alert.alertType}</h5>
                    <p><strong>Message:</strong> ${alert.message}</p>
                    <p><strong>Appointment ID:</strong> ${alert.appointmentId}</p>
                    <p><strong>Provider ID:</strong> ${alert.providerId}</p>
                    <p><strong>User ID:</strong> ${alert.userId}</p>
                    <p><strong>Created:</strong> ${alert.createdAt?string("yyyy-MM-dd")}</p>
                </div>
<!--                <form method="post" action="/alerts/delete/${alert.alertId}" style="margin-left: 10px;">-->
<!--                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this alert?')">Delete</button>-->

<!--                </form>-->
                <div class="d-flex gap-2">
                    <form method="post" action="/alerts/delete/${alert.alertId}">
                        <button type="submit" class="btn btn-danger btn-sm"
                                onclick="return confirm('Are you sure you want to delete this alert?');">
                            Delete
                        </button>
                    </form>
                </div>
            </div>
        </#list>
        </div>
    </#if>
    </div>
</div>
</body>
</html>

