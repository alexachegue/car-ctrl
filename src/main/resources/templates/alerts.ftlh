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
        <a class="nav-link text-white" href="/appointments/service-history-page"><i class="bi bi-journal-check"></i>
            Service History</a>
        <a class="nav-link text-white" href="/users/profile-page"><i class="bi bi-person"></i> Profile</a>
    </nav>
</div>

<--! ONLY PAGE LEFT TO FINISH ALERTS IS INCOMPLETE FOR NOW !-->

<div class="main-content container mt-4 vh-100 d-flex">
    <div class="w-75 pe-3">
        <h2>Notifications & Alerts</h2>
        <#list alerts as alert>
        <div class="alert alert-${alert.type} <#if alert.dismissible?? && alert.dismissible>alert-dismissible fade show</#if>"
             role="alert">
            <i class="bi ${alert.icon}"></i> ${alert.message}
            <#if alert.dismissible?? && alert.dismissible>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </#if>
        </div>
        </#list>
    </div>

<div class="w-25">
    <div class="card">
        <div class="card-header"><i class="bi bi-chat-dots"></i> Messages</div>
        <div class="card-body" style="max-height: 300px; overflow-y: auto;">
            <#list messages as msg>
            <div class="d-flex <#if msg.sender == 'you'>justify-content-end</#if> mb-3">
                <div class="alert alert-${msg.sender == 'you'?then('primary', 'secondary')} w-100">
                    ${msg.text} <#if msg.sender == 'you'>- You<#else>- ${msg.sender}
                </
                #if>
            </div>
        </div>
    </
    #list>
</div>
<div class="card-footer">
    <div class="input-group">
        <input type="text" class="form-control" placeholder="Type a message..." disabled>
        <button class="btn btn-primary" type="button" disabled><i class="bi bi-send"></i> Send</button>
    </div>
</div>
</div>
</div>
</div>

</body>