package track.pro.user.services;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.user.entites.Role;
import track.pro.user.entites.User;
import track.pro.user.repository.UserRepository;
import track.pro.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public int registerUser(User user) {

		System.out.println("\n registerUser");
		// password Encryption
		String pwdFormUI = user.getPassword();
		String pwdSalt = Utils.generateSalt();
		String modifiedPwd = pwdFormUI + pwdSalt;
		String pwdHash = Utils.generateHash(modifiedPwd);
		user.setPwd_salt(pwdSalt);
		;
		user.setPwd_hash(pwdHash);

		if (user.getRole_id() == 1)
			user.setIs_authorized(true);

		return userRepository.insertUser(user);
	}

	@Override
	public List<Role> getAllRoles() {
		System.out.println(userRepository.fetchAllRoles());
		return userRepository.fetchAllRoles();
	}

	@Override
	public Optional<User> fetchUserBy(String user_name) {

		return userRepository.getUserBy(user_name);
	}

	@Override
	public boolean matchPassword(String password, User user) {

		String modifiedPwd = password + user.getPwd_salt();
		String newHash = Utils.generateHash(modifiedPwd);
		String oldHash = user.getPwd_hash();

		return newHash.equals(oldHash);
	}

	@Override
	public int updateAuthority(int user_id) {

		return userRepository.toggleAuthority(user_id);
	}

	@Override
	public Optional<User> fetchUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	@Override
	public Optional<User> fetchUserByMobile(String mobile) {
		return userRepository.getUserByMobile(mobile);
	}

	@Override
	public int getUserIdByUserName(String user_name) {

		return userRepository.findUserIdByUserName(user_name);
	}

	@Override
    public void updatePassword(User user, String newPassword) {
        String salt = Utils.generateSalt();
        String hashedPassword = Utils.generateHash(newPassword + salt);
        user.setPwd_salt(salt);
        user.setPwd_hash(hashedPassword);
        userRepository.save(user);
    }

	

}