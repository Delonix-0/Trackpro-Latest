package track.pro.profile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.profile.entites.Profile;
import track.pro.user.entites.User;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Profile findUserByUsername(String user_name) {
		String sql = "SELECT * FROM users WHERE user_name = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { user_name }, new ProfileRowMapper());
	}

	@Override
	public void updateProfile(Profile profile) {
		String sql = "UPDATE users SET mobile = ?, email = ?, profile_image = ?, profile = ? WHERE user_name = ?";
		jdbcTemplate.update(sql, profile.getMobile(), profile.getEmail(), profile.getProfileimg(),
				profile.getProfileresume(), profile.getUser_name());
	}
}
