package track.pro.profile.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import track.pro.profile.entites.Profile;
import track.pro.profile.repository.ProfileRepository;

@Service
public class ProfileServicesImpl implements ProfileService {
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile getUserByUsername(String user_name) {
		return profileRepository.findUserByUsername(user_name);
	}

	@Override
	public String convertBlobToBase64(byte[] blob) {
		return Base64.getEncoder().encodeToString(blob);
	}

	@Override
	public void updateProfile(Profile profile) {
		profileRepository.updateProfile(profile);
	}

}
