package vn.edu.fpt.dao;

import vn.edu.fpt.entity.UserEntity;

import java.util.List;

public interface UserDao {
    List<UserEntity> findAll();
}
