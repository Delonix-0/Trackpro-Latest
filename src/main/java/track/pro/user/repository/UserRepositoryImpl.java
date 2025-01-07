package track.pro.user.repository;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import track.pro.user.entites.Role;
import track.pro.user.entites.User;
import track.pro.utils.Utils;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(User user) {

		// convert Multipartfile to Blob

		Blob profile_image = Utils.convertToBlob(user.getProfile_image());
		Blob profile = Utils.convertToBlob(user.getProfile());

		final String INSERT_USER = "INSERT INTO \r\n"
				+ "users (`full_name`, `user_name`, `gender`, `mobile`, `email`, `profile_image`, `profile`, `pwd_salt`, `pwd_hash`, `role_id`, `is_authorized`) \r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return jdbcTemplate.update(INSERT_USER, user.getFull_name(), user.getUser_name(), user.getGender(),
				user.getMobile(), user.getEmail(), profile_image, profile, user.getPwd_salt(), user.getPwd_hash(),
				user.getRole_id(), user.isIs_authorized());
	}

	private Object getprofile_image() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> fetchAllRoles() {
		final String GET_ALL_ROLES = "SELECT * FROM roles WHERE role_id !=1";
		return jdbcTemplate.query(GET_ALL_ROLES, (rs, rowNum) -> {

			int roleId = rs.getInt("role_id");
			String roleName = rs.getString("role_name");

			return new Role(roleId, roleName);
		});
	}

	@Override
	public Optional<User> getUserBy(String user_name) {
		User user = null;
		final String FETCH_ALL_USERS = "SELECT * FROM users WHERE user_name = ?";

		try {
			user = jdbcTemplate.queryForObject(FETCH_ALL_USERS, new UserRowMapper(), user_name);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

	@Override
	public int toggleAuthority(int user_id) {
		final String UPDATE_AUTHORITY = "UPDATE `trackpro`.`users` SET `is_authorized` = !is_authorized WHERE user_id =? ";
		return jdbcTemplate.update(UPDATE_AUTHORITY, user_id);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		User user = null;
		final String FETCH_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
		try {
			user = jdbcTemplate.queryForObject(FETCH_USER_BY_EMAIL, new UserRowMapper(), email);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(user);

	}

	@Override
	public Optional<User> getUserByMobile(String mobile) {

		User user = null;
		final String FETCH_USER_BY_MOBILE = "SELECT * FROM users WHERE mobile = ?";
		try {
			user = jdbcTemplate.queryForObject(FETCH_USER_BY_MOBILE, new UserRowMapper(), mobile);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

	@Override
	public int findUserIdByUserName(String user_name) {
		String sql = "SELECT user_id FROM users WHERE user_name= ? ";
		return jdbcTemplate.queryForObject(sql, Integer.class, user_name);
	}

	@Override
	@Transactional
	public void save(User user) {
		if (user.getUser_id() == 0) {
			entityManager.persist(user);
		} else {
			entityManager.merge(user);
		}
	}

}
