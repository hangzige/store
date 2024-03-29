package com.springboot.entity;

public class User extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 258918078223569423L;
	
	private Integer uid;
	private String username;
	private String password;
	private String salt;
	private Integer isDelete;
	private String phone;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	private String email;
	private Integer gender;
	private String avatar;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer uid, String username, String password, String salt, Integer isDelete, String phone,
			String email, Integer gender, String avatar) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.isDelete = isDelete;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.avatar = avatar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", isDelete=" + isDelete + ", phone=" + phone + ", email=" + email + ", gender=" + gender
				+ ", avatar=" + avatar + ", getCreatedUser()=" + getCreatedUser() + ", getCreatedTime()="
				+ getCreatedTime() + ", getModifiedUser()=" + getModifiedUser() + ", getModifiedTime()="
				+ getModifiedTime() + "]";
	}
	
}
