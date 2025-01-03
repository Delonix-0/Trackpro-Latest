package track.pro.profile.repository;

import track.pro.profile.entites.Profile;


public interface ProfileRepository {
	Profile findUserByUsername(String user_name);

}
