package com.springboot.propelrr.aptitude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rest")
public class Profile {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String fullname, email, mobile, gender, birthdate;
  private int age;

  public void setId(Integer id){
      this.id = id;
  }
  public String getFullname(){
      return this.fullname;
  }
  public void setFullname(String fullname){
      this.fullname = fullname;
  }

  public String getEmail() {
      return this.email;
  }

  public void setEmail(String email) {
      this.email = email;
  }

  public String getMobile() {
      return this.mobile;
  }

  public void setMobile(String mobile) {
      this.mobile = mobile;
  }

  public String getGender() {
      return this.gender;
  }

  public void setGender(String gender) {
      this.gender = gender;
  }

  public String getBirthDate() {
      return this.birthdate;
  }

  public void setBirthDate(String birthdate) {
      this.birthdate = birthdate;
  }

  public int getAge() {
      return this.age;
  }

  public void setAge(int age) {
      this.age = age;
  }


}
