package track.pro.profile.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import track.pro.profile.entites.Profile;
import track.pro.user.entites.User;

public class ProfileRowMapper implements RowMapper<Profile> {
	@Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setUser_id(rs.getInt("user_id"));
        profile.setFull_name(rs.getString("full_name"));
        profile.setUser_name(rs.getString("user_name"));
        profile.setGender(rs.getString("gender"));
        profile.setMobile(rs.getString("mobile"));
        profile.setEmail(rs.getString("email"));
        profile.setProfileimg(rs.getBytes("profile_image"));
        profile.setProfileresume(rs.getBytes("profile"));
        profile.setPwd_salt(rs.getString("pwd_salt"));
        profile.setPwd_hash(rs.getString("pwd_hash"));
        profile.setRole_id(rs.getInt("role_id"));
        profile.setIs_authorized(rs.getBoolean("is_authorized"));
        return profile;
}
}


