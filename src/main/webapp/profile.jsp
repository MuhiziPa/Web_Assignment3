<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Profile Page</title>
    <!-- Add your CSS styles here -->
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
      }
      .container {
        max-width: 400px;
        margin: 0 auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }
      h1 {
        text-align: center;
      }
    </style>
  </head>
  <body>
    <h1>Welcome to Your Profile</h1>
  <form action="UploadProfileServlet" method="post" enctype="multipart/form-data">
    <label for="profilePicture">Upload Profile Picture:</label>
    <input type="file" name="profilePicture" accept="image/*" />
    <br />
    <label for="diploma">Upload Diploma (PDF):</label>
    <input type="file" name="diploma" accept=".pdf" />
    <br />
    <input type="submit" value="Upload" />
</form>
  
</html>
