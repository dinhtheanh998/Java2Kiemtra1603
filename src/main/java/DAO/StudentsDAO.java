package DAO;

import Model.Students;
import Connection.MyConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO {
    public List<Students> getAllStudents() {
        List<Students> students = new ArrayList<>();
        Connection connection = null;
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM students";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getString("id"));
                student.setFull_name(resultSet.getString("full_name"));
                student.setBirthday(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone_number(resultSet.getString("phone_number"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(Double.parseDouble(resultSet.getString("gpa")));
                students.add(student);
            }
            //close connection
            connection.close();
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    public boolean deleteStudentByID(String id) {
        Connection connection = null;
        try {
            connection = MyConnection.getConnection();
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean updateStudentByID(Students st, String id) {
        Connection connection = null;
        try {
            connection = MyConnection.getConnection();
            String sql = "UPDATE students SET full_name = ?, gender = ? ,date_of_birth = ?, address = ?, phone_number = ?, email = ?, gpa = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, st.getFull_name());
            preparedStatement.setInt(2, st.getGender());
            java.sql.Date sqlDate = new java.sql.Date(st.getBirthday().getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, st.getAddress());
            preparedStatement.setString(5, st.getPhone_number());
            preparedStatement.setString(6, st.getEmail());
            preparedStatement.setDouble(7, st.getGpa());
            preparedStatement.setString(8, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Students> filterStudentByFullNameOrId(String keyword) {
        Connection connection = null;
        try {

            List<Students> students = new ArrayList<>();
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM students WHERE full_name LIKE ? OR id LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getString("id"));
                student.setFull_name(resultSet.getString("full_name"));
                student.setBirthday(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone_number(resultSet.getString("phone_number"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(Double.parseDouble(resultSet.getString("gpa")));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Students> sortedStudentsByGPAASC() {
        Connection connection = null;
        try {
            List<Students> students = new ArrayList<>();
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM students ORDER BY gpa ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getString("id"));
                student.setFull_name(resultSet.getString("full_name"));
                student.setBirthday(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone_number(resultSet.getString("phone_number"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(Double.parseDouble(resultSet.getString("gpa")));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //    get all student with address in Hanoi and GPA > 2.5
    public List<Students> getAllSttudentWithAddressInHanoiAndGPA() {
        Connection connection = null;
        try {
            List<Students> students = new ArrayList<>();
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM students WHERE address = 'Hanoi' AND gpa > 2.5 AND gender = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getString("id"));
                student.setFull_name(resultSet.getString("full_name"));
                student.setBirthday(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone_number(resultSet.getString("phone_number"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(Double.parseDouble(resultSet.getString("gpa")));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Students> sortedStudentsWithFullName() {
        Connection connection = null;
        try {
            List<Students> students = new ArrayList<>();
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM students ORDER BY full_name ASC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getString("id"));
                student.setFull_name(resultSet.getString("full_name"));
                student.setBirthday(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone_number(resultSet.getString("phone_number"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(Double.parseDouble(resultSet.getString("gpa")));
                students.add(student);
            }
            return students;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean addStudent(Students st) {
        Connection connection = null;
        try {
            connection = MyConnection.getConnection();
            String sql = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, st.getId());
            preparedStatement.setString(2, st.getFull_name());
            preparedStatement.setInt(3, st.getGender());
//            parse Java Date to SQL Date

            java.sql.Date sqlDate = new java.sql.Date(st.getBirthday().getTime());


            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, st.getAddress());
            preparedStatement.setString(6, st.getPhone_number());
            preparedStatement.setString(7, st.getEmail());
            preparedStatement.setDouble(8, st.getGpa());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Students getStudentsByID(String id) {
        Connection connection = null;
        try {
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Students student = new Students();
                student.setId(resultSet.getString("id"));
                student.setFull_name(resultSet.getString("full_name"));
                student.setBirthday(resultSet.getDate("date_of_birth"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone_number(resultSet.getString("phone_number"));
                student.setEmail(resultSet.getString("email"));
                student.setGpa(Double.parseDouble(resultSet.getString("gpa")));
                return student;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
