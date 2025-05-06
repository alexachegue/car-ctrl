 <!DOCTYPE html>
 <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Manage Reviews</title>
        <link href="https://fonts.googleapis.com/css2?family=Manrope:wght@300;400;600;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
        <link href="/css/styles.css" rel="stylesheet">
    </head>
    <body>
    <div class="container-fluid">
        <nav class="sidebar">
            <a class="nav-link" href="/providers/home?providerId=${providerId}"><i class="bi bi-house-door"></i> Home</a>
            <a class="nav-link" href="/providers/profile/${providerId}"><i class="bi bi-person"></i> Profile</a>
            <a class="nav-link" href="/appointments?providerId=${providerId}"><i class="bi bi-calendar-check"></i> Appointments</a>
            <a class="nav-link" href="/service/services/${providerId}"><i class="bi bi-tools"></i> Services</a>
            <a class="nav-link" href="/alerts?providerId=${providerId}"><i class="bi bi-bell"></i> Alerts</a>
            <a class="nav-link active" href="/reviews/manage?providerId=${providerId}"><i class="bi bi-chat-left-text"></i> Reviews</a>
        </nav>

        <div class="main-content">
            <h2>Manage Reviews</h2>

            <div class="card mb-4">
                <div class="card-body">
                    <p><strong>Total Reviews:</strong> ${totalReviews}</p>
                    <p><strong>Average Rating:</strong> ${averageRating}/5</p>
                    <p><strong>Replied Reviews:</strong> ${repliedCount} (${(repliedCount * 100 / totalReviews)?int}%)</p>
                </div>
            </div>

            <div class="reviews-list">
                <#list reviews as review>
                <div class="card mb-3">
                    <div class="card-body">
                        <p><strong>Customer:</strong> ${review.user.username}</p>
                        <p><strong>Rating:</strong> ${review.rating}</p>
                        <p><strong>Review:</strong> ${review.description}</p>
                        <p><strong>Date:</strong> ${review.createdAt?string("MMM dd, yyyy HH:mm")}</p>

                        <#if review.reply??>
                        <p><em><strong>Reply:</strong> ${review.reply.message}</em></p>

                        <!-- Update/Delete Reply -->
                        <form action="/replies/create" method="post" class="mb-2">
                            <input type="hidden" name="reviewId" value="${review.reviewId}">
                            <textarea class="form-control mb-2" name="message" rows="2">${review.reply.message}</textarea>
                            <button type="submit" class="btn btn-warning btn-sm">Update Reply</button>
                        </form>
                        <form action="/replies/delete/${review.reply.replyId}" method="post" onsubmit="return confirm('Delete this reply?');">
                            <input type="hidden" name="_method" value="delete">
                            <button type="submit" class="btn btn-danger btn-sm">Delete Reply</button>
                        </form>
                        <#else>
                        <!-- New Reply -->
                        <form action="/replies/create" method="post">
                            <input type="hidden" name="reviewId" value="${review.reviewId}">
                            <textarea class="form-control mb-2" name="message" rows="2" placeholder="Write your reply..."></textarea>
                            <button type="submit" class="btn btn-primary btn-sm">Submit Reply</button>
                        </form>
                    </#if>
                </div>
            </div>
        </#list>
    </div>
    </div>
    </div>

    <!-- Method Spoofing Script for DELETE -->
    <script>
        document.querySelectorAll('form').forEach(form => {
            if (form.querySelector('input[name="_method"][value="delete"]')) {
                form.addEventListener('submit', function (e) {
                    e.preventDefault();
                    const confirmed = confirm("Are you sure you want to delete this reply?");
                    if (confirmed) {
                        fetch(form.action, {
                            method: 'DELETE'
                        }).then(() => window.location.reload());
                    }
                });
            }
        });
    </script>
    </body>
    </html>