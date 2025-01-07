package track.pro.superadmin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.user.entites.User;
import track.pro.user.repository.UserRowMapper;

@Repository
public class SuperAdminRepositoryImpl implements SuperAdminRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> fetchAllManagers() {
		final String GET_AllMANAGERS = "SELECT *FROM users WHERE role_id=2";
		return jdbcTemplate.query(GET_AllMANAGERS, new UserRowMapper());
	}

}