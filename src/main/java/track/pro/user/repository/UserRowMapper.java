package track.pro.user.repository;
import  track.pro.user.entites.User;
import track.pro.utils.Utils;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;




public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("user_id");
		String fullname = rs.getString("full_name");
		String user_name = rs.getString("user_name");
		String gender = rs.getString("gender");
		String mobile = rs.getString("mobile");
		String email = rs.getString("email");
		Blob blobprofile_image = rs.getBlob("profile_image");
		Blob blobprofile = rs.getBlob("profile");
		String pwd_salt = rs.getString("pwd_salt");
		String pwd_hash = rs.getString("pwd_hash");
		int roleId = rs.getInt("role_id");
		boolean isAuthorized = rs.getBoolean("is_authorized");
		
	     
	
		//Blob to MultiPartfile
		MultipartFile profile_image=blobprofile_image !=null ?Utils.convertToMultipart(blobprofile_image):null;
		MultipartFile profile=blobprofile != null ?Utils.convertToMultipart(blobprofile):null;
       
        return new User(id, fullname, user_name, gender, mobile, email, profile_image, profile, email, pwd_salt, pwd_hash, roleId, isAuthorized);
        		}

}
