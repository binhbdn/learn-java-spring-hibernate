<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <h1>Hello from Index.jsp</h1>
    <form action="/search" method="post"">
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
</body>
</html>