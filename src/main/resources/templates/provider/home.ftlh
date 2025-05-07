<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Provider Dashboard</title>
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
        <a class="nav-link active" href="/providers/home"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/services/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
    </nav>

    <!-- Main Content -->
    <div class="main-content">
        <h2>Welcome to Your Dashboard</h2>
        <p>Here’s a quick overview of your account and activities.</p>

        <!-- Profile Completion -->
        <div class="card mb-4">
            <div class="card-body text-center">
                <h5 class="card-title">Profile Status</h5>
                <i class="bi bi-person" style="font-size: 2rem; color: #C91B00;"></i>

                <#if profileCompletion < 100>
                <p>Your profile is ${profileCompletion}% complete.</p>
                <a href="/providers/profile" class="btn btn-primary">Complete your profile now</a>
                <#else>
                <p>
                    <i class="bi bi-check-square-fill"></i> Fully completed!
                </p>
                <a href="/providers/profile" class="btn btn-primary">View your profile</a>
            </#if>
            <!-- add a progress bar(not sure) -->

            <!--                <div class="progress mt-2">-->
            <!--                    <div class="progress-bar bg-success" role="progressbar"-->
            <!--                         style="width: ${profileCompletion}%" aria-valuenow="${profileCompletion}" aria-valuemin="0" aria-valuemax="100">-->
            <!--                        ${profileCompletion}%-->
            <!--                    </div>-->
            <!--                </div>-->
        </div>
    </div>

        <!-- Quick Links -->
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <div class="col">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="bi bi-tools" style="font-size: 2rem; color: #C91B00;"></i>
                        <h5 class="card-title mt-2">Manage Services</h5>
                        <a href="/services/services" class="btn btn-primary">Go to Services</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="bi bi-calendar-check" style="font-size: 2rem; color: #C91B00;"></i>
                        <h5 class="card-title mt-2">Manage Appointments</h5>
                        <a href="/appointments" class="btn btn-primary">Go to Appointments</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="bi bi-bell" style="font-size: 2rem; color: #C91B00;"></i>
                        <h5 class="card-title mt-2">View Notifications</h5>
                        <a href="/alerts" class="btn btn-primary">Go to Alerts</a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body text-center">
                        <i class="bi bi-chat-left-text" style="font-size: 2rem; color: #C91B00;"></i>
                        <h5 class="card-title mt-2">Manage Reviews</h5>
                        <a href="/reviews/manage" class="btn btn-primary">Go to Reviews</a>
                    </div>
                </div>
            </div>
            <form action="/providers/logout" method="post">
                <button type="submit" class="btn btn-dark mt-3"><i class="bi bi-box-arrow-left"></i> Logout</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
