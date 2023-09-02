<!DOCTYPE html>
<html>
<head>
    <title>Submit Data</title>
</head>
<body>

    <h1>User Registration</h1>
    <form action="/submit" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="mobile">Mobile Number:</label>
        <input type="text" id="mobile" name="mobile" required><br><br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
