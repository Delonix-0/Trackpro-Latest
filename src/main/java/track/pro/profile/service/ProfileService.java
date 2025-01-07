package track.pro.profile.service;

import track.pro.profile.entites.Profile;

public interface ProfileService {
	Profile getUserByUsername(String user_name);
    String convertBlobToBase64(byte[] blob);
	void updateProfile(Profile profile);

}
