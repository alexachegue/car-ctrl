<!DOCTYPE html>
<html lang="en">
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


<header class="header">CarCtrl</header>
<div class="d-flex">
    <nav class="nav flex-column p-3 vh-100 text-white" style="width: 250px; position: fixed; height: 100%;">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link text-white" href="/users/home-page"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link text-white" href="/services/explore"><i class="bi bi-search"></i> Explore</a>
        <a class="nav-link text-white" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i> Service History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>
<main class="container mt-4 main-content">
    <h1>PROFILE</h1>
    <div class="row">
    <div class="card shadow rounded-4">
        <div class="card-header text-white text-center rounded-top-4">
            <h3>Welcome, ${user.username}!</h3>
        </div>
        <div class="card-body">
            <h5 class="mb-3">User Info</h5>
            <ul class="list-group mb-4">
                <#if user.profilePicture??>
                <img src="/uploads/profile_pictures/${user.profilePicture}"
                     alt="${user.username}'s Profile Picture"
                     width="150" height="150" style="border-radius: 50%; object-fit: cover;">
                <#else>
                <p>No profile picture uploaded.</p>
                </#if>
                <li class="list-group-item"><strong>Email:</strong> ${user.email}</li>
                <li class="list-group-item"><strong>Phone:</strong> ${user.phoneNumber}</li>
                <li class="list-group-item"><strong>Joined:</strong>${user.dateJoined?string("MMMM dd, yyyy")}</li>
            </ul>

            <h5>Your Registered Cars:</h5>
            <#if cars?? && cars?size gt 0>
            <#list cars as car>
            <div class="card my-3 p-3 border rounded-3">
                <h5>${car.make} ${car.model}</h5>
                <p class="mb-1 text-secondary">Year: ${car.year}</p>
                <p class="mb-0 text-secondary">Color: ${car.color}</p>
            </div>
            </#list>
            <#else>
            <div class="alert alert-warning mt-3">
            You haven't registered a car yet.
            </div>
            </#if>
        </div>

    <a href="/cars/register-car" class="btn btn-primary w-100 mt-2">Register A Car</a>
    <a href="/users/edit-profile" class="btn btn-dark mt-2">Edit Profile</a>


    <form action="/users/logout" method="post" class="mt-4">
        <button class="btn btn-dark mt-2">Logout</button>
    </form>
    </div>
    </div>
</main>
</body>
</html>