<!DOCTYPE html>
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
        <a class="nav-link text-white" href="/alerts/user"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i> Service
            History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>

<div class="container mt-5">
    <div class="card shadow rounded-4">
        <div class="card-header">
            <h1 class="mb-0">Book Appointment</h1>
                <h2>${appointment.service.name}</h2>
                <h2>${appointment.provider.providername}</h2>
        </div>
        <div class="card-body">
            <form method="post" action="/appointments/create">
                <input type="hidden" name="providerId" value="${appointment.provider.providerId}">
                <input type="hidden" name="serviceId" value="${appointment.service.serviceId}">

                <div class="mb-3">
                    <label for="carId" class="form-label">Select Your Car</label>
                    <select class="form-select" name="carId" id="carId" required>
                        <#list cars as car>
                        <option value="${car.carId}">${car.make} ${car.model} (${car.year})</option>
                        </#list>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="appointmentDate" class="form-label">Select Date and Time</label>
                    <input type="datetime-local" class="form-control" id="appointmentDate" name="appointmentDate"
                           value="<#if appointment.appointmentDate??>${appointment.appointmentDate?string('yyyy-MM-dd\'T\'HH:mm')}<#else></#if>"
                           required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Notes (optional)</label>
                    <textarea class="form-control" name="description" id="description" rows="3"
                              placeholder="Describe any details or requests">${appointment.description!}</textarea>
                </div>

                <div class="text-end">
                    <button type="submit" class="btn btn-primary px-4">Confirm</button>
                </div>
            </form>
        </div>
    </div>
    <a href="/providers/by-service/${service.serviceId}" class="btn btn-primary">Back to Providers</a>
</div>
</body>
</html> 