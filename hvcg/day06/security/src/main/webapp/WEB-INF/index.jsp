<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome/title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>Welcome to our course!!!!</h2>
    <div class="container">
        <form action="/search" method="post">
            <div class="mb-3">
                <label for="fname">First name:</label><br>
                <input type="text" id="fname" name="fname" value="" placeholder="input your first name">
            </div>
            <div class="mb-3">
                <label for="lname">Last name:</label><br>
                <input type="text" id="lname" name="lname" value="" placeholder="input your last name">
            </div>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
</body>
</html>