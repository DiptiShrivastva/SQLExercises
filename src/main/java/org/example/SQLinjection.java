package org.example;

import java.sql.*;
import java.util.Scanner;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class SQLinjection {

    public static void CreatLogginDatabase()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            Statement stmt = conn.createStatement();

            // Create a new table
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "username TEXT NOT NULL ,"
                    + "id INTEGER PRIMARY KEY,"
                    + "password TEXT NOT NULL )";
            stmt.execute(sql);
            stmt.close();
            conn.close();
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean register(String username, String password)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            return pstmt.execute();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean loggin(String username, String password)
    {

        try
        {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            String sql = "SELECT id FROM users WHERE username = ? and password = ?";
            PreparedStatement preparedStatement  = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs= preparedStatement.executeQuery();
            conn.close();
            rs.close();
            return rs.next();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }



    public static void ShowRegisteredUsers() {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String username = rs.getString(1);
                Integer id = rs.getInt(2);

                System.out.println(id +" : " +username);
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        CreatLogginDatabase();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("(1) Login");
            System.out.println("(2) Register");
            System.out.println("(3) Show Users");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("____Login____");
                    System.out.println("Username : ");
                    String username = scanner.nextLine();
                    System.out.println("Password : ");
                    String password = scanner.nextLine();
                    if (loggin(username, password)) {
                        System.out.println("Logged in");
                    } else {
                        System.out.println("Login failed");
                    }
                    break;
                case "2":
                    System.out.println("____Register____");
                    System.out.println("Username : ");
                    String username1 = scanner.nextLine();
                    System.out.println("Password : ");
                    String password1 = scanner.nextLine();
                    if (register(username1, password1)) {
                        System.out.println("Registered user");
                    } else {
                        System.out.println("Registration failed");
                    }
                    break;
                case "3":
                    System.out.println("Below are the registered users.");
                    ShowRegisteredUsers();
                    break;
            }
        }
    }
}
