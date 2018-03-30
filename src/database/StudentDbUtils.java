package database;

import domain.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDbUtils {

    PreparedStatement preparedStatement;
    ResultSet resultSet;

    ObservableList<Student> listOfStudents = FXCollections.observableArrayList();
    Connection connection = DatabaseConnector.databaseConnector();

    public boolean createStudent(Student student) {
        if (connection != null) {
            String query = "INSERT INTO student (name, address,roll,dob,gender,role,username,password) VALUES (?,?,?,?,?,?,?,?)";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getAddress());
                preparedStatement.setInt(3, student.getRoll());
                preparedStatement.setString(4, student.getDateOfBirth());
                preparedStatement.setString(5, student.getGender());
                preparedStatement.setString(6, student.getRole());
                preparedStatement.setString(7, student.getUsername());
                preparedStatement.setString(8, student.getPassword());

                preparedStatement.execute();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public boolean updateStudent(Student student) {
        if (connection != null) {
            String query = "UPDATE student SET name=?, address=?,roll=?,dob=?,gender=?,role=?,username=?,password=? WHERE id =?";
            try {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getAddress());
                preparedStatement.setInt(3, student.getRoll());
                preparedStatement.setString(4, student.getDateOfBirth());
                preparedStatement.setString(5, student.getGender());
                preparedStatement.setString(6, student.getRole());
                preparedStatement.setString(7, student.getUsername());
                preparedStatement.setString(8, student.getPassword());
                preparedStatement.setInt(9, student.getId());

                preparedStatement.execute();
                connection.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /*
    
    public ObservableList<Admin> fetchData() {
        try {
            String query = "SELECT * FROM admin";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                
                
                Integer id= resultSet.getInt("Admin_ID");
                String adminName= resultSet.getString("Admin_Name");
                 String password = resultSet.getString("Password");
                 
                String securityQuestion= resultSet.getString("security_question");
                String securityAnswer= resultSet.getString("Answer");
                
                Admin admin=new Admin(id,adminName,password,securityQuestion,securityAnswer);
                listOfAdmin.add(admin);

            }
            return listOfAdmin;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
     */
    public ObservableList<Student> fetchData() {
        String query = "SELECT * FROM student";
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    int roll = resultSet.getInt("roll");
                    String dateOfBirth = resultSet.getString("dob");
                    String gender = resultSet.getString("gender");
                    String role = resultSet.getString("role");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");

                    Student student = new Student(id, name, address, roll, dateOfBirth, gender, role, username, password);

//                    System.out.println("student : "+student.getId());
                    listOfStudents.add(student);

                }
                return listOfStudents;

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return null;

    }

    public Boolean deleteStudent(int id) {
        String query = "DELETE FROM student where id=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean loginAction(String username, String password) throws SQLException {
        String query = "SELECT * FROM student Where username=? and password=?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        resultSet=preparedStatement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        return false;

    }
}
