package com.spz.tools.hibernate.entity;
// Generated 2019-1-17 14:55:37 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ResultId generated by hbm2java
 */
@Embeddable
public class ResultId  implements java.io.Serializable {


     private int studentNo;
     private int subjectNo;
     private Date examDate;

    public ResultId() {
    }

    public ResultId(int studentNo, int subjectNo, Date examDate) {
       this.studentNo = studentNo;
       this.subjectNo = subjectNo;
       this.examDate = examDate;
    }
   

    @Column(name="studentNo", nullable=false)
    public int getStudentNo() {
        return this.studentNo;
    }
    
    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    @Column(name="subjectNo", nullable=false)
    public int getSubjectNo() {
        return this.subjectNo;
    }
    
    public void setSubjectNo(int subjectNo) {
        this.subjectNo = subjectNo;
    }

    @Column(name="examDate", nullable=false, length=19)
    public Date getExamDate() {
        return this.examDate;
    }
    
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ResultId) ) return false;
		 ResultId castOther = ( ResultId ) other; 
         
		 return (this.getStudentNo()==castOther.getStudentNo())
 && (this.getSubjectNo()==castOther.getSubjectNo())
 && ( (this.getExamDate()==castOther.getExamDate()) || ( this.getExamDate()!=null && castOther.getExamDate()!=null && this.getExamDate().equals(castOther.getExamDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStudentNo();
         result = 37 * result + this.getSubjectNo();
         result = 37 * result + ( getExamDate() == null ? 0 : this.getExamDate().hashCode() );
         return result;
   }   


}

