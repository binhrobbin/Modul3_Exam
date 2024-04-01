package vn.codegym.repository;

import vn.codegym.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository<Student>{


    public static final String SELECT_ALL_STUDENT = "select * from student";
    public static final String SELECT_FROM_STUDENT_WHERE_ID = "select * from student where id=?";
    public static final String UPDATE_STUDENT_BY_ID = "update student set name=?,email=?,dateOfBirth=?,address=?,phoneNumber=?,classroom=?  where id=?";
    public static final String DELETE_ROW_STUDENT_WHERE_ID = "delete from student where id=?";

    @Override
    public List<Student> findAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null ;
        List<Student> studentList = new ArrayList<>();
        if (connection != null){
            try {
                statement = connection.prepareStatement(SELECT_ALL_STUDENT);
                resultSet = statement.executeQuery();

                Student student = null;
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String dateOfBirth = resultSet.getString("dateOfBirth");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phoneNumber");
                    String classroom = resultSet.getString("classroom");
                    student = new Student(id,name,email,dateOfBirth,address,phoneNumber,classroom);
                    studentList.add(student);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                DBConnection.close();
            }
        }
        return studentList;
    }

    @Override
    public void save(Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null; // nạp lệnh SQL
        if (connection != null){
            try {
                statement = connection.prepareStatement("insert into student(name,email,dateOfBirth,address,phoneNumber,classroom) values " +
                        "(?, ?, ?, ?, ?,?)");
                statement.setString (1,student.getName());
                statement.setString (2,student.getEmail());
                statement.setString (3,student.getDateOfBirth());
                statement.setString(4,student.getAddress());
                statement.setString(5,student.getPhoneNumber());
                statement.setString(6,student.getClassroom());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public void update(int id, Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        if (connection != null){
            try {
//                update student set name=?,email=?,dateOfBirth=?,address=?,phoneNumber=?,classroom=?  where id=?
                statement = connection.prepareStatement(UPDATE_STUDENT_BY_ID);
                statement.setString(1,student.getName());
                statement.setString(2,student.getEmail());
                statement.setString(3,student.getDateOfBirth());
                statement.setString(4,student.getAddress());
                statement.setString(5,student.getPhoneNumber());
                statement.setString(6,student.getClassroom());
                statement.setInt(7,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public void remove(int id, Student student) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        if (connection != null){
            try {
                statement = connection.prepareStatement(DELETE_ROW_STUDENT_WHERE_ID);
                statement.setInt(1,id);
                statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                DBConnection.close();
            }
        }
    }

    @Override
    public Student findById(int id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Student student = null;
        if (connection != null){
            try {
                statement = connection.prepareStatement(SELECT_FROM_STUDENT_WHERE_ID);
                statement.setInt(1,id);
                resultSet = statement.executeQuery();
                resultSet.next();
//                name,email,dateOfBirth,address,phoneNumber,classroom
                String name = resultSet.getString("name");
                String email= resultSet.getString("email");
                String dateOfBirth = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                String classroom = resultSet.getString("classroom");
                student = new Student(id,name,email,dateOfBirth,address,phoneNumber,classroom);
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                DBConnection.close();
            }
        }
        return student;
    }
}
