<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h2>Register to our course!!!!</h2>
    <div class="container">
        <form action="/register" method="post">
            <div class="mb-3">
                <label for="username">Username:</label><br>
                <input type="text" id="username" name="username" value="" placeholder="input your username">
            </div>
            <div class="mb-3">
                <label for="Password">Password</label><br>
                <input type="password" id="password" name="password" value="" placeholder="input your password">
            </div>
            <input type="hidden"
            	name="${_csrf.parameterName}"
            	value="${_csrf.token}"/>
            <input type="submit" value="Submit">
        </form>
    </div>
</div>
</body>
</html>