package com.mall.service;

import com.mall.dao.UserDAO;
import com.mall.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    // 사용자 정보 저장
    public void signUpUser(User user) {
        // 이미 존재하는 사용자 확인
        User existingUser = userDAO.findByUsername(user.getUsername());
        if (existingUser != null) {
            // 사용자 이름이 이미 존재하면 예외 발생
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }
        // 사용자 이름이 중복되지 않으면 회원가입 처리
        userDAO.signUp(user);
    }

    // 사용자 이름으로 사용자 정보 찾기
    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    // 사용자 정보 수정
    public void updateUser(User user) {
        // 수정 로직
        userDAO.updateUser(user);
    }

    // 사용자 ID로 사용자 정보 찾기
    public User getUserById(int userId) {
        return userDAO.findById(userId);
    }
}