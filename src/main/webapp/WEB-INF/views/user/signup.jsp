<%--
  Created by IntelliJ IDEA.
  User: jmg13
  Date: 2023-06-27
  Time: 오전 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        h1 {
            text-align: center;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff; /* Change button color to blue */
            color: #ffffff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3; /* Change hover color to a darker shade of blue */
        }

        .login-link {
            text-align: center;
            margin-top: 10px;
        }

        .login-link a {
            text-decoration: none;
            color: #000000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>회원가입 페이지</h1>
    <form method="post" action="/user/signup">
        <label for="username">사용자명:</label>
        <input type="text" id="username" name="username" required>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required>
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required>
        <input type="submit" value="회원가입">
    </form>
    <div class="login-link">
        <a href="/user/login">이미 계정이 있으신가요? 로그인</a>
    </div>
</div>
</body>
</html>
