package org.example;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

public class SQLExample {
    public static void main(String[] args) {
        // CreateDataBase();
        //InsertData("Lilly", 23);
        printStudents();
        UpdatestData("Dipti", 35, 1);
        deletestudents("vini");
    }
    public static void CreateDataBase() {
        try {
            Connection conn=DriverManager.getConnection("jdbc:sqlite:univercity.db");
            Statement smt=conn.createStatement();
            String sql="CREATE TABLE IF NOT EXISTS students("
                    +"id INTEGER PRIMARY KEY,"
                    +"age INTEGER NOT NULL,"
                    +"name TEXT NOT NULL)";
            smt.execute(sql);
            smt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    public static void InsertData(String name,int age){
        try {
            Connection conn=DriverManager.getConnection("jdbc:sqlite:univercity.db");
            String sql="INSERT INTO students(name,age) VALUES(?,?)";
            PreparedStatement psmt=conn.prepareStatement(sql);
            psmt.setString(1,"dipti");

            psmt.setInt(2,40);

            psmt.executeUpdate();
            psmt.close();
            conn.close();
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
    public static void printStudents(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:univercity.db");
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name, age FROM students";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");
                System.out.println("id : "+id+" age : "+age+" Name : "+name);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void UpdatestData(String name,int age, int id){
        try {
            Connection conn=DriverManager.getConnection("jdbc:sqlite:univercity.db");
            String sql="UPDATE  students SET age= ? WHERE id= ?  AND name = ?";
            PreparedStatement psmt=conn.prepareStatement(sql);
            psmt.setInt(1,age);
            psmt.setString(3,name);
            psmt.setInt(2,id);

            psmt.executeUpdate();
            psmt.close();
            conn.close();
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
    public static void deletestudents(String name){
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:school.db");
            String sql = "DELETE FROM students WHERE name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(sql);
            pstmt.setString(1,name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}






