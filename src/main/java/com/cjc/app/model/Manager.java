package com.cjc.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.DynamicUpdate;

@XmlRootElement
@Entity
@DynamicUpdate
@Table(name = "MANAGER_DTLS")
public class Manager {
   
	@Id
	@Column(name = "MANAGER_ID")
	private Integer id;
	
	@Column(name = "MANAGER_FIRSTNAME")
	private String firstname;
	
	@Column(name = "MANAGER_LASTNAME")
	private String lastname;
	
	@Column(name = "MANAGER_TYPE")
	private String type;
	
	@Column(name = "MANAGER_SALARY")
	private Double salary;
	
	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(Integer id, String firstname, String lastname, String type, Double salary) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
		this.salary = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", type=" + type
				+ ", salary=" + salary + "]";
	}
}
