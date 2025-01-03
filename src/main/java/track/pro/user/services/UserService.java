package track.pro.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

import track.pro.user.entites.Role;
import track.pro.user.entites.User;


public interface UserService {
	int registerUser(User user);
	 List<Role> getAllRoles() ; 
	 Optional <User> fetchUserBy(String user_name);
 boolean matchPassword(String password,User user);
 int updateAuthority(int user_id);
 int getUserIdByUserName(String user_name);
 Optional<User> fetchUserByEmail(String email);
 Optional<User> fetchUserByMobile(String mobile);
void updatePassword(User user, String newPassword);

 
 
}
