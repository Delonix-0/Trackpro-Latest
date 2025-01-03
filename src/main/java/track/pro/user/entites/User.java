package track.pro.user.entites;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String full_name;
	private String user_name;
	private String gender;
	private String mobile;
	private String email;
	@Transient
	private MultipartFile profile_image;
	@Transient
	private MultipartFile profile; 
	private String password;
	private String pwd_salt;
	private String pwd_hash;
	private int role_id;
	private boolean is_authorized;

	public User() {

	}

	public User(int user_id, String full_name) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
	}

	public User(String full_name, String user_name, String gender, String mobile, String email,
			MultipartFile profile_image, MultipartFile profile, String password, int role_id, boolean is_authorized) {
		super();
		this.full_name = full_name;
		this.user_name = user_name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.profile_image = profile_image;
		this.profile = profile;
		this.password = password;
		this.role_id = role_id;
		this.is_authorized = is_authorized;
	}

	public User(int user_id, String full_name, String user_name, String gender, String mobile, String email,
			MultipartFile profile_image, MultipartFile profile, String password, String pwd_salt, String pwd_hash,
			int role_id, boolean is_authorized) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.user_name = user_name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.profile_image = profile_image;
		this.profile = profile;
		this.password = password;
		this.pwd_salt = pwd_salt;
		this.pwd_hash = pwd_hash;
		this.role_id = role_id;
		this.is_authorized = is_authorized;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public MultipartFile getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(MultipartFile profile_image) {
		this.profile_image = profile_image;
	}

	public MultipartFile getProfile() {
		return profile;
	}

	public void setProfile(MultipartFile profile) {
		this.profile = profile;
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

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public boolean isIs_authorized() {
		return is_authorized;
	}

	public void setIs_authorized(boolean is_authorized) {
		this.is_authorized = is_authorized;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", full_name=" + full_name + ", user_name=" + user_name + ", gender="
				+ gender + ", mobile=" + mobile + ", email=" + email + ", profile_image=" + profile_image + ", profile="
				+ profile + ", password=" + password + ", pwd_salt=" + pwd_salt + ", pwd_hash=" + pwd_hash
				+ ", role_id=" + role_id + ", is_authorized=" + is_authorized + "]";
	}
}