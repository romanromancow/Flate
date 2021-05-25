package Jdbc1;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // CREATE DATABASE mydb;
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb11?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "RomaOla2011!";

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initDB();

                while (true) {
                    System.out.println("1: add flate");
                    System.out.println("2: view flate");
                    System.out.println("3: select flate");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlate(sc);
                            break;
                        case "2":
                            viewFlats();
                            break;
                        case "3":
                            selectFlats();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute ("DROP TABLE IF EXISTS Flats");
            st.execute("CREATE TABLE Flats (id INT NOT NULL " +
                    "AUTO_INCREMENT PRIMARY KEY, district VARCHAR(20) " +
                    "NOT NULL, street VARCHAR (20)"+
                    "NOT NULL, area INT "+
                    "NOT NULL, rooms INT "+
                    "NOT NULL, price INT)");
        } finally {
            st.close();
        }
    }

    private static void addFlate(Scanner sc) throws SQLException {
        System.out.print("Enter flate district: ");
        String district = sc.nextLine();
        System.out.print("Enter flate street: ");
        String street = sc.nextLine();
        System.out.print("Enter flate area: ");
        String sArea = sc.nextLine();
        int area = Integer.parseInt(sArea);
        System.out.print("Enter flate rooms: ");
        String sRooms = sc.nextLine();
        int rooms = Integer.parseInt(sRooms);
        System.out.print("Enter flate price: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);


        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Flats (district, street, area, rooms, price) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setString(1, district);
            ps.setString(2, street);
            ps.setInt(3, area);
            ps.setInt(4, rooms);
            ps.setInt(5, price);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE

        } finally {
            ps.close();
        }
    }


    private static void viewFlats() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Flats");
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();

            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
    private static void selectFlats () throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("4: Select flats by price");
        System.out.println("5: Select flats by area");
        System.out.println("6: Select flats by rooms");
        System.out.println("7: Select flats by street");
        System.out.println("-->");

        String s = scanner.nextLine();
        switch (s) {
            case "4":
                selectPice();
                break;
            case "5":
                selectArea();
                break;
            case "6":
                selectRooms();
                break;
            case "7":
                selectStreet();
                break;
            default:
                return;
        }
    }
    private static void selectPice() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Flats ORDER BY price DESC");
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();

            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
    private static void selectArea() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Flats ORDER BY area DESC");
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();

            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
    private static void selectRooms() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Flats ORDER BY rooms DESC");
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();

            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
    private static void selectStreet() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Flats ORDER BY street DESC");
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();

            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
}
