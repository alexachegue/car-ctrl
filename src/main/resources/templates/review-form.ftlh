<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CarCtrl Home</title>
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

<header class="header">CarCtrl Review</header>
<div class="d-flex">
    <nav class="nav flex-column p-3 vh-100 text-white" style="width: 250px; position: fixed; height: 100%;">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo" class="nav-logo mb-4" style="max-width: 100%;">
        <a class="nav-link text-white" href="/users/home-page"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link text-white" href="/services/explore"><i class="bi bi-search"></i> Explore</a>
        <a class="nav-link text-white" href="/users/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i>
            Service History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>
<div class="container mt-5">
    <h2 class="mb-4">Leave a Review</h2>
    <form action="/reviews/submit" method="post" class="p-4 border rounded">
        <input type="hidden" name="user.userId" value="${review.user.userId?c}">
        <input type="hidden" name="provider.providerId" value="${review.provider.providerId}">
        <input type="hidden" name="appointment.appointmentId" value="${review.appointment.appointmentId?c}">

        <div class="mb-3">
            <label class="form-label"><strong>Rating:</strong></label>
            <div class="star-rating">
                <input type="radio" id="star5" name="rating" value="5" required/>
                <label for="star5"><i class="bi bi-star-fill"></i></label>

                <input type="radio" id="star4" name="rating" value="4"/>
                <label for="star4"><i class="bi bi-star-fill"></i></label>

                <input type="radio" id="star3" name="rating" value="3"/>
                <label for="star3"><i class="bi bi-star-fill"></i></label>

                <input type="radio" id="star2" name="rating" value="2"/>
                <label for="star2"><i class="bi bi-star-fill"></i></label>

                <input type="radio" id="star1" name="rating" value="1"/>
                <label for="star1"><i class="bi bi-star-fill"></i></label>
            </div>
        </div>

        <div class="form-group mb-3">
            <label for="description">Review</label>
            <textarea class="form-control" id="description" name="description" rows="4"
                      placeholder="Tell us about your experience..." required></textarea>
        </div>

        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Submit Review</button>
        </div>
    </form>
</div>

<a href="/appointments/service-history-page" class="btn btn-outline-dark mt-2"> Back </a>

</body>
</html>
