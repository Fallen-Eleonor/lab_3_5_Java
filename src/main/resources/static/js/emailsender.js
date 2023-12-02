$(document).ready(function() {
    $("#emailForm").submit(function(event) {
        event.preventDefault();
        var formData = $(this).serialize();
        $.ajax({
            type: "POST",
            url: "/email/send",
            data: formData,
            success: function(response) {
                $("#result").text(response);
            },
            error: function(xhr, status, error) {
                $("#result").text("Error occurred while sending email.");
            }
        });
    });
});