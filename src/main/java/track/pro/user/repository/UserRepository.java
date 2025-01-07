package track.pro.user.repository;

import java.util.List;
import java.util.Optional;

import track.pro.tasks.entites.Task;
import track.pro.user.entites.Role;
import track.pro.user.entites.User;

public interface UserRepository {
   
   int insertUser(User user);
   List<Role> fetchAllRoles();
   Optional<User> getUserBy(String user_name);
   int toggleAuthority(int user_id);
   int findUserIdByUserName(String user_name);
   Optional<User> getUserByEmail(String email);
   Optional<User> getUserByMobile(String mobile);
   void save(User user);
   
}
