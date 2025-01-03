package track.pro.assosiate.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import track.pro.user.entites.User;
import track.pro.user.repository.UserRowMapper;

@Repository
public class AssosiateRepositoryImpl implements AssosiateRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<User> fetchAllAssosiate() {
		final String GET_ALL_ASSOSIATE = "SELECT * FROM trackpro.users where role_id = 3";
		return jdbcTemplate.query(GET_ALL_ASSOSIATE, new UserRowMapper());
	}
	
	

}
