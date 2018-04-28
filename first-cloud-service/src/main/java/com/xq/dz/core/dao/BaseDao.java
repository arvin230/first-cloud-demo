package com.xq.dz.core.dao;

import com.xq.dz.core.model.BaseBean;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseDao<T extends BaseBean> extends Mapper<T>, MySqlMapper<T> {

}
