<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Edit Profile</title>
</head>
<body>
<div class="container-fluid">
    <!-- Sidebar -->
    <nav class="sidebar">
        <img src="/images/carcontrollogo.png" alt="CarCtrl Logo"
             style="display: block; margin: 0 auto 20px auto; max-width: 80px; height: auto;">
        <a class="nav-link" href="/providers/home"><i class="bi bi-house-door"></i> Home</a>
        <a class="nav-link" href="/providers/profile"><i class="bi bi-person"></i> Profile</a>
        <a class="nav-link" href="/appointments"><i class="bi bi-calendar-check"></i> Appointments</a>
        <a class="nav-link" href="/service/services"><i class="bi bi-tools"></i> Services</a>
        <a class="nav-link" href="/alerts"><i class="bi bi-bell"></i> Alerts</a>
        <a class="nav-link" href="/reviews/manage"><i class="bi bi-chat-left-text"></i> Reviews</a>
        <a class="nav-link" href="/providers/stats"><i class="bi bi-graph-up"></i> View Statistics</a>
    </nav>


    <!-- Main Content -->
    <div class="main-content">
        <h2>Edit Provider Profile</h2>

        <form action="/providers/update" method="post">
        <input type="hidden" name="providerId" value="${provider.providerId}">

            <div class="mb-3">
                <label for="providername">Name</label>
                <input type="text" class="form-control" id="providername" name="providername" value="${provider.providername}">
            </div>

            <div class="mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${provider.email}">
            </div>

            <div class="mb-3">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${provider.phone}">
            </div>

            <div class="mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" value="${provider.address!''}">
            </div>

            <div class="mb-3">
                <label for="operatingHours">Operating Hours</label>
                <input type="text" class="form-control" id="operatingHours" name="operatingHours" value="${provider.operatingHours!''}">
            </div>

            <div class="mb-3">
                <label for="bio">Bio</label>
                <textarea class="form-control" id="bio" name="bio">${provider.bio!''}</textarea>
            </div>

            <div class="mb-3">
                <label for="status">Status</label>
                <select class="form-select" id="status" name="status">
                    <option value="ACTIVE" <#if provider.status == "ACTIVE">selected</#if>>Active</option>
                    <option value="BUSY" <#if provider.status == "BUSY">selected</#if>>Busy</option>
                    <option value="OFFLINE" <#if provider.status == "OFFLINE">selected</#if>>Offline</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="activeDate">Active Since</label>
                <input type="date" class="form-control" id="activeDate" name="activeDate"
                value="${provider.activeDate?string("yyyy-MM-dd")!}">
            </div>

            <button type="submit" class="btn btn-primary">Save Changes</button>
            <a href="/providers/profile" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</div>
</body>
</html>
