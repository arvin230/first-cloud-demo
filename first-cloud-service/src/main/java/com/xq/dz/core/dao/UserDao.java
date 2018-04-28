package com.xq.dz.core.dao;

import org.springframework.stereotype.Repository;

import com.xq.dz.core.model.User;

public interface UserDao<T extends User> extends BaseDao<User> {

}
