package vn.edu.fpt.service;

import vn.edu.fpt.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();
}
