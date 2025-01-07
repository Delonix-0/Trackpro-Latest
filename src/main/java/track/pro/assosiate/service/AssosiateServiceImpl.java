package track.pro.assosiate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.assosiate.repository.AssosiateRepository;
import track.pro.user.entites.User;

@Service
public class AssosiateServiceImpl implements AssosiateService {

	@Autowired
	AssosiateRepository assosiateRepository;

	@Override
	public List<User> getAllUser() {
		return assosiateRepository.fetchAllAssosiate();
	}

}
