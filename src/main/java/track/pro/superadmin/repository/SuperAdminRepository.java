package track.pro.superadmin.repository;

import java.util.List;

import track.pro.user.entites.User;


public interface SuperAdminRepository {
	List<User> fetchAllManagers();
}
