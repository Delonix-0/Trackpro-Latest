package track.pro.user.entites;

public class User {
	private int userId;
	private String Name;
	private String gender;
	private String mobile;
	private String email;
	private String username;
	private String password;
	private String pwd_salt;
	private String pwd_hash;
	private int  roleId;
	private boolean authorizationStatus;
	public User() {
		super();
	}
	public User(int userId, String name, String gender, String mobile, String email, String username, String password,
			int roleId, boolean authorizationStatus) {
		super();
		this.userId = userId;
		Name = name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.authorizationStatus = authorizationStatus;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPwd_salt() {
		return pwd_salt;
	}
	public void setPwd_salt(String pwd_salt) {
		this.pwd_salt = pwd_salt;
	}
	public String getPwd_hash() {
		return pwd_hash;
	}
	public void setPwd_hash(String pwd_hash) {
		this.pwd_hash = pwd_hash;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public boolean isAuthorizationStatus() {
		return authorizationStatus;
	}
	public void setAuthorizationStatus(boolean authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}
	@Override
	public String toString() {
		return "/nUser [userId=" + userId + ", Name=" + Name + ", gender=" + gender + ", mobile=" + mobile + ", email="
				+ email + ",/n username=" + username + ", password=" + password + ", pwd_salt=" + pwd_salt + ", pwd_hash="
				+ pwd_hash + ",/n roleId=" + roleId + ", authorizationStatus=" + authorizationStatus + "]";
	}
	

}
