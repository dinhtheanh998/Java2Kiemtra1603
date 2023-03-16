package Model;

import java.util.Date;

public class Students implements Comparable<Students> {
    @Override
    public int compareTo(Students o) {
        if(this.gpa > o.gpa) return -1;
        else if(this.gpa < o.gpa) return 1;
        else return 0;
    }

    private String id;
    private String full_name;

    private int gender;

    private Date birthday;
    private String address;
    private String phone_number;
    private String email;
    private Double gpa;

    public Students() {
    }

    public Students(String id, String full_name, int gender, Date birthday, String address, String phone_number, String email, Double gpa) {
        this.id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id='" + id + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
