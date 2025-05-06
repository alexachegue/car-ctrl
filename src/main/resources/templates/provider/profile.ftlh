<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Provider Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        <a class="nav-link active" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
    </nav>

    <!-- Main Content -->
    <div class="main-content">
        <nav class="navbar">
            <div class="social-links">
                <a href="#"><i class="bi bi-twitter"></i></a>
                <a href="#"><i class="bi bi-facebook"></i></a>
                <a href="#"><i class="bi bi-instagram"></i></a>
            </div>
        </nav>


        <div class="page-header" data-parallax="true"></div>

        <!-- Profile Card -->
        <div class="profile-card">
            <div class="avatar">
                <img src="/images/logo.png" alt="Provider Avatar">
            </div>
            <h3 class="title">${provider.providername}</h3>
            <h6>Auto Shop</h6>
            <p>${provider.bio!"No bio available"}</p>
        </div>

        <!-- Contact Info -->
        <div class="card">
            <div class="card-header">Contact Information</div>
            <div class="card-body text-center">
                <p><strong>Address:</strong> ${provider.address!"No address provided"}</p>
                <p><strong>Email:</strong> ${provider.email!"No email provided"}</p>
                <p><strong>Phone:</strong> ${provider.phone!"No phone provided"}</p>
                <p><strong>Operating Hours:</strong> ${provider.operatingHours!"Not specified"}</p>
                <p><strong>Status:</strong> ${provider.status!"Unknown"}</p>
                <p><strong>Active Since:</strong> ${provider.activeDate?string("yyyy-MM-dd")}</p>
                <a href="/providers/edit/${provider.providerId}" class="btn btn-primary mt-2">Edit Profile</a>
            </div>
        </div>

        <!-- Services Grid Section -->
        <div class="container text-center mt-4">
            <h3 class="mb-4">Services You Provide</h3>

            <#if providerServices?size == 0>
            <p class="text-muted">You haven't selected any services yet.</p>
            <#else>
            <div class="row row-cols-1 row-cols-md-2 g-4">
                <#list providerServices as service>
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${service.name}</h5>
                            <p class="card-text">${service.description}</p>
                            <p class="text-muted"><em>Category: ${service.category}</em></p>
                            <a href="/bookings/book/${service.serviceId}" class="btn btn-primary">Book Now</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        </#if>

    <!-- Add Service Card -->
    <div class="card mt-4">
        <div class="card-body text-center">
            <i class="bi bi-plus-circle" style="font-size: 2rem; color: #C91B00;"></i>
            <h5 class="card-title mt-3">Manage Your Services</h5>
            <p class="card-text">Add or remove services from your profile.</p>
            <a href="/service/services" class="btn btn-primary">Manage Services</a>
        </div>
    </div>
</div>
    </div>

    </div>
</div>
</body>
</html>
