<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Search Result</h1>
    <table class="table">
        <thead>
            <tr>
                <th>Link</th>
                <th>Clicks</th>
            </tr>
        </thead>
        <tbody>
            <%
                out.println(request.getAttribute("answers"));
            %>
        </tbody>
    <table>
</div>
</body>
</html>