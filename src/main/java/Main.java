import DAO.StudentsDAO;
import Model.Students;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        StudentsDAO studentsDAO = new StudentsDAO();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            menu();
            System.out.println("Nhập lựa chọn: ");
            choice = sc.nextInt();
            choose(choice, studentsDAO);
        } while (choice != 0);
//        showAllStudents(studentsDAO);
    }



    public static void menu() {
        System.out.println("1. Danh sách sinh viên");
        System.out.println("2. Nhập 1 sinh viên mới");
        System.out.println("3. Xoá sinh viên theo mã");
        System.out.println("4. Cập nhật thông tin sinh viên");
        System.out.println("5. Tìm sinh viên theo họ hoặc mã");
        System.out.println("6. Sắp xếp sinh viên theo GPA tăng dần");
        System.out.println("7. In ra sinh viên ở HN có GPA cao hơn 2.5");
        System.out.println("8. Sắp xếp sinh viên theo họ tên");
    }

    public static void choose(int choice, StudentsDAO studentsDAO) {
        switch (choice) {
            case 1:
                showAllStudents(studentsDAO);
                break;
            case 2:
                try {
                    if (addStudent(studentsDAO)) {
                        System.out.println("Thêm sinh viên thành công");
                    } else {
                        System.out.println("Thêm sinh viên thất bại");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Lỗi nhập liệu");
                    break;
                }
            case 3:
                if (deleteStudentByID(studentsDAO)) {
                    System.out.println("Xoá sinh viên thành công");
                } else {
                    System.out.println("Xoá sinh viên thất bại");
                }
                break;

            case 4:
//                System.out.println("Nhập mã sinh viên cần cập nhật: ");
//                Scanner sc1 = new Scanner(System.in);
//                String id1 = sc1.nextLine();
                try {
                    if (updateStudentByID(studentsDAO)) {
                        System.out.println("Cập nhật sinh viên thành công");
                    } else {
                        System.out.println("Cập nhật sinh viên thất bại");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Lỗi nhập liệu");
                    break;
                }
            case 5:
               List<Students> students = filterStudentByNameOrID(studentsDAO);
                for (Students std: students
                     ) {
                    System.out.println(std);
                }
               break;
            case 6:
                List<Students> students1 = sortStudentByGPA(studentsDAO);
                for (Students std: students1
                ) {
                    System.out.println(std);
                }
                break;
            case 7:
                List<Students> students2 = filterStudentIsFemaleAndGPAAndAddress(studentsDAO);
                for (Students std: students2
                ) {
                    System.out.println(std);
                }
                break;
            case 8:
                List<Students> students3 = sortStudentByFullName(studentsDAO);
                if(students3 == null){
                    System.out.println("Danh sách rỗng");
                }
                else {
                    for (Students std : students3
                    ) {
                        System.out.println(std);
                    }
                }
                break;
        }
    }


    public static void showAllStudents(StudentsDAO studentsDAO) {
        List<Students> students = studentsDAO.getAllStudents();
        System.out.printf("%-10s %-20s %-10s %-10s", "Mã SV", "Họ tên", "Giới tính", "Địa chỉ");
        for (int i = 0; i < students.size(); i++) {
            System.out.println();
            System.out.printf("%-10s %-20s %-10s %-10s", students.get(i).getId(), students.get(i).getFull_name(), students.get(i).getGender(), students.get(i).getAddress());
        }
    }

    public static boolean addStudent(StudentsDAO studentsDAO) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sinh viên: ");
        String studentsCode = sc.nextLine();
        if (studentsDAO.getStudentsByID(studentsCode) != null) {
            System.out.println("Mã sinh viên đã tồn tại");
            return false;
        }
        System.out.println("Nhập họ tên sinh viên: ");
        String studentsName = sc.nextLine();
        System.out.println("Nhập giới tính sinh viên: ");
        int studentsGender = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập ngày sinh sinh viên (YYYY - MM - DD): ");
        String studentsBirthday = sc.nextLine();
        System.out.println("Nhập địa chỉ sinh viên: ");
        String studentsAddress = sc.nextLine();
        System.out.println("Nhập số điện thoại sinh viên: ");
        String studentsPhoneNumber = sc.nextLine();
        System.out.println("Nhập email sinh viên: ");
        String studentsEmail = sc.nextLine();
        System.out.println("Nhập điểm GPA sinh viên: ");
        double studentsGPA = sc.nextDouble();
        sc.nextLine();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(studentsBirthday);
        Students students = new Students(studentsCode, studentsName, studentsGender, date, studentsAddress, studentsPhoneNumber, studentsEmail, studentsGPA);
        return studentsDAO.addStudent(students);
    }

    public static boolean deleteStudentByID(StudentsDAO studentsDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sinh viên cần xoá: ");
        String id = sc.nextLine();
        return studentsDAO.deleteStudentByID(id);
    }

    public static boolean updateStudentByID(StudentsDAO studentsDAO) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sinh viên cần cập nhật: ");
        String id = sc.nextLine();

        if (studentsDAO.getStudentsByID(id) == null) {
            System.out.println("Mã sinh viên không tồn tại");
            return false;
        }
        System.out.println("Nhập họ tên sinh viên: ");
        String studentsName = sc.nextLine();
        System.out.println("Nhập giới tính sinh viên: ");
        int studentsGender = sc.nextInt();
        sc.nextLine();
        System.out.println("Nhập ngày sinh sinh viên (YYYY - MM - DD): ");
        String studentsBirthday = sc.nextLine();
        System.out.println("Nhập địa chỉ sinh viên: ");
        String studentsAddress = sc.nextLine();
        System.out.println("Nhập số điện thoại sinh viên: ");
        String studentsPhoneNumber = sc.nextLine();
        System.out.println("Nhập email sinh viên: ");
        String studentsEmail = sc.nextLine();
        System.out.println("Nhập điểm GPA sinh viên: ");
        double studentsGPA = sc.nextDouble();
        sc.nextLine();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(studentsBirthday);
        Students students = new Students(id, studentsName, studentsGender, date, studentsAddress, studentsPhoneNumber, studentsEmail, studentsGPA);
        return studentsDAO.updateStudentByID(students, id);
    }

    public static List<Students> filterStudentByNameOrID(StudentsDAO studentsDAO) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập họ tên hoặc mã sinh viên cần tìm: ");
        String name = sc.nextLine();
        return studentsDAO.filterStudentByFullNameOrId(name);
    }

    public static List<Students> sortStudentByGPA(StudentsDAO studentsDAO) {
        return studentsDAO.sortedStudentsByGPAASC();
    }

    public static List<Students> filterStudentIsFemaleAndGPAAndAddress(StudentsDAO studentsDAO) {
        List<Students> students = studentsDAO.getAllStudents();
        if (students == null) {
            return null;
        }
        return studentsDAO.getAllSttudentWithAddressInHanoiAndGPA();
    }

    public static List<Students> sortStudentByFullName(StudentsDAO studentsDAO) {
        List<Students> students = studentsDAO.getAllStudents();
        if (students == null) {
            return null;
        }
        return students.stream().sorted((o1, o2) -> o1.getFull_name().compareTo(o2.getFull_name())).collect(Collectors.toList());
    }

    public static Students getStudentByID(String id, StudentsDAO studentsDAO) {
        return studentsDAO.getStudentsByID(id);
    }
}
