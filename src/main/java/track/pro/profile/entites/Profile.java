package track.pro.profile.entites;

import java.util.Arrays;

public class Profile {
	private int user_id;
    private String full_name;
    private String user_name;
    private String gender;
    private String mobile;
    private String email;
    private byte[] profileimg; // BLOB for profile image
    private byte[] profileresume; // BLOB for profile resume
    private String password;
    private String pwd_salt;
    private String pwd_hash;
    private int role_id;
    private boolean is_authorized;

    // Getters and Setters
    // ... (same as provided earlier)

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

	public byte[] getProfileimg() {
		return profileimg;
	}

	public void setProfileimg(byte[] profileimg) {
		this.profileimg = profileimg;
	}

	public byte[] getProfileresume() {
		return profileresume;
	}

	public void setProfileresume(byte[] profileresume) {
		this.profileresume = profileresume;
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
		return "Profile [user_id=" + user_id + ", full_name=" + full_name + ", user_name=" + user_name + ", gender="
				+ gender + ", mobile=" + mobile + ", email=" + email + ", profileimg=" + Arrays.toString(profileimg)
				+ ", profileresume=" + Arrays.toString(profileresume) + ", password=" + password + ", pwd_salt="
				+ pwd_salt + ", pwd_hash=" + pwd_hash + ", role_id=" + role_id + ", is_authorized=" + is_authorized
				+ "]";
	}
    }

	


