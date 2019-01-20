package com.spz.tools.hibernate.entity;
// Generated 2019-1-17 14:55:37 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name="student"
    ,catalog="myschool"
    , uniqueConstraints = @UniqueConstraint(columnNames="identityCard") 
)
public class Student  implements java.io.Serializable {


     private int studentNo;
     private Grade grade;
     private String loginPwd;
     private String studentName;
     private String sex;
     private String phone;
     private String address;
     private Date bornDate;
     private String email;
     private String identityCard;
     private Set teachers = new HashSet(0);
     private Set results = new HashSet(0);
     private Set results_1 = new HashSet(0);
     private Set teachers_1 = new HashSet(0);

    public Student() {
    }

	
    public Student(int studentNo, String loginPwd, String studentName, String sex) {
        this.studentNo = studentNo;
        this.loginPwd = loginPwd;
        this.studentName = studentName;
        this.sex = sex;
    }
    public Student(int studentNo, Grade grade, String loginPwd, String studentName, String sex, String phone, String address, Date bornDate, String email, String identityCard, Set teachers, Set results, Set results_1, Set teachers_1) {
       this.studentNo = studentNo;
       this.grade = grade;
       this.loginPwd = loginPwd;
       this.studentName = studentName;
       this.sex = sex;
       this.phone = phone;
       this.address = address;
       this.bornDate = bornDate;
       this.email = email;
       this.identityCard = identityCard;
       this.teachers = teachers;
       this.results = results;
       this.results_1 = results_1;
       this.teachers_1 = teachers_1;
    }
   
     @Id 
    
    @Column(name="studentNo", unique=true, nullable=false)
    public int getStudentNo() {
        return this.studentNo;
    }
    
    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="gradeId")
    public Grade getGrade() {
        return this.grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    
    @Column(name="loginPwd", nullable=false, length=20)
    public String getLoginPwd() {
        return this.loginPwd;
    }
    
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
    
    @Column(name="studentName", nullable=false, length=50)
    public String getStudentName() {
        return this.studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    @Column(name="sex", nullable=false, length=2)
    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="phone", length=50)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="address")
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="bornDate", length=10)
    public Date getBornDate() {
        return this.bornDate;
    }
    
    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
    
    @Column(name="email", length=50)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="identityCard", unique=true, length=18)
    public String getIdentityCard() {
        return this.identityCard;
    }
    
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="students")
    public Set getTeachers() {
        return this.teachers;
    }
    
    public void setTeachers(Set teachers) {
        this.teachers = teachers;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="student")
    public Set getResults() {
        return this.results;
    }
    
    public void setResults(Set results) {
        this.results = results;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="student")
    public Set getResults_1() {
        return this.results_1;
    }
    
    public void setResults_1(Set results_1) {
        this.results_1 = results_1;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="students")
    public Set getTeachers_1() {
        return this.teachers_1;
    }
    
    public void setTeachers_1(Set teachers_1) {
        this.teachers_1 = teachers_1;
    }




}

