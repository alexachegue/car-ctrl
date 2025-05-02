<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-center">${title}</h2>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <#list adminList as admin>
            <tr>
                <td>${admin.id}</td>
                <td>${admin.username}</td>
                <td>${admin.email}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>
