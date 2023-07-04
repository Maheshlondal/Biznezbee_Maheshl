package com.example.demo.entity;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should only contain letters and spaces")
    private String name;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]+$", message = "Mobile number should only contain digits")
    @Length(max = 15, message = "Mobile number should not exceed {max} characters")
    private String mobile;

    @Column(name = "age")
    @NotBlank(message = "age  is required")
    private Integer age;
    
    @NotBlank(message = "User Type is required")
    @Pattern(regexp = "^(Developer|HR|Manager)$", message = "User Type should be either Developer, HR, or Manager")
    private String userType;

    public User() {
        // Default constructor
    }


    public User(Long id,
			@NotBlank(message = "Name is required") @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should only contain letters and spaces") String name,
			@NotBlank(message = "Date of Birth is required") String dateOfBirth,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,
			@NotBlank(message = "Mobile number is required") @Pattern(regexp = "^[0-9]+$", message = "Mobile number should only contain digits") @Length(max = 15, message = "Mobile number should not exceed {max} characters") String mobile,
			@NotBlank(message = "age  is required") Integer age,
			@NotBlank(message = "User Type is required") @Pattern(regexp = "^(Developer|HR|Manager)$", message = "User Type should be either Developer, HR, or Manager") String userType) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.mobile = mobile;
		this.age = age;
		this.userType = userType;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", mobile="
				+ mobile + ", age=" + age + ", userType=" + userType + "]";
	}


	public void setStatus(String string) {
        // TODO Auto-generated method stub
    }
}
