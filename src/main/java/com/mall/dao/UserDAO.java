package com.mall.dao;

import com.mall.model.User;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 회원가입 메서드
    public void signUp(User user) {
        String sql = "INSERT INTO user (user_id, username, password, email, phone_number, role, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getAddress());
    }

    // 사용자 이름으로 유저 조회 메서드
    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setRole(rs.getString("role"));
                user.setAddress(rs.getString("address"));
                return user;
            });
        } catch (EmptyResultDataAccessException e) {
            // 결과가 없으면 null 반환
            return null;
        }
    }

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public User findById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
