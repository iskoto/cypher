# Cypher

A Java desktop banking application (Swing GUI) built as a university team project. It provides secure user registration and login backed by a MySQL database.

## Features

- Login and registration dialogs built with Java Swing.
- MySQL/JDBC integration via `mysql-connector-j`.
- All database queries use parameterised `PreparedStatement`s to prevent SQL injection.
- Basic validation (empty fields, password confirmation match) before hitting the database.

## Structure

- `src/LoginCypher.java` – login dialog and authentication query.
- `src/RegistrationCypher.java` – registration dialog and insert query.
- `src/User.java` – simple user data model.

## Tech stack

Java, Swing, MySQL, JDBC.
