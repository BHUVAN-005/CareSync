<!DOCTYPE html>
<html>
<head>
    <title>Upload File</title>
</head>
<body>
    <h2>Upload File</h2>
    <form method="post" action="FileUploadDBServlet" enctype="multipart/form-data">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" required><br>
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" required><br>
        <label for="category">Category:</label>
        <input type="text" name="category" required><br>
        <label for="photo">Upload File:</label>
        <input type="file" name="photo" required><br>
        <input type="submit" value="Upload">
    </form>
</body>
</html>
