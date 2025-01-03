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
	        return jdbcTemplate.queryForObject(sql, new Object[]{user_name}, new ProfileRowMapper());
	    }
	}


