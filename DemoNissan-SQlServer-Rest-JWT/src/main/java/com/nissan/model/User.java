package com.nissan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;


@Entity
@Table(name="userjwt")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	private String firstName;
	private String lastname;
	private Integer age;
	private String gender;
	private String address;
	private Integer phoneNo;
	private Integer loginId;
	
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdAt;
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime updatedAt;
	
	private Integer roleId;
	
	@ManyToOne
	@JoinColumn(name="roleId",insertable=false,updatable=false)
	private Role role;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String username, String emailId, String password, DateTime createdAt, DateTime updatedAt,
			Integer roleId, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.emailId = emailId;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roleId = roleId;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public DateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}
	public DateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@PrePersist
	public void onSave() {
		DateTime currentDateTime =new DateTime();
		this.createdAt=currentDateTime;
		this.updatedAt=currentDateTime; 

	}
	@PostPersist
	public void onUpdate() {
		DateTime currentDateTime =new DateTime();
		this.updatedAt=currentDateTime; 
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", emailId=" + emailId + ", password=" + password
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", roleId=" + roleId + ", role=" + role
				+ "]";
	}
	
	
}
