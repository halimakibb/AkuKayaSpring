package main.java.akuKaya.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tblM_Role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleID")
	private int roleID;
	
	@Column(name = "RoleName", nullable = false, length = 50, unique = true)
	private String roleName;
	
	@Column(name = "IsActive", nullable = false)
	private boolean isActive;
	
	@Column(name = "CreatedBy", nullable = false)
	private String createdBy;
	
	@Column(name = "CreatedDate", nullable = false)
	private Date createdDate;
	
	@Column(name = "UpdatedBy", nullable = false)
	private String updatedBy;
	
	@Column(name = "UpdatedDate", nullable = false)
	private Date updatedDate;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getRoleID() {
		return roleID;
	}

	public Role() {
		
	}
	
	public Role(String roleName, boolean isActive, String createdBy, Date createdDate, String updatedBy,
			Date updatedDate) {
		this.roleName = roleName;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		
	}
	
	
}
