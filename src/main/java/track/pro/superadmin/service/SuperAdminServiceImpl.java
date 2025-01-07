package track.pro.superadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import track.pro.superadmin.repository.SuperAdminRepository;
import track.pro.user.entites.User;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

	@Autowired

	SuperAdminRepository superadminRepository;

	@Override

	public List<User> getAllManager() {

		return superadminRepository.fetchAllManagers();

	}

}
