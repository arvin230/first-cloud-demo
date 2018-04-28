package com.xq.dz.core.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xq.dz.core.dao.BaseDao;
import com.xq.dz.core.model.BaseBean;

import tk.mybatis.mapper.entity.Example;

public abstract class BaseService<T extends BaseBean> {

	protected BaseDao<T> dao;

	@Transactional(readOnly = true)
	public Integer selectCount() {
		return this.dao.selectCount(null);
	}

	@Transactional(readOnly = true)
	public Integer selectCountByWhere(T record) {
		return this.dao.selectCount(record);
	}

	@Transactional(readOnly = true)
	public T selectByPrimaryKey(Serializable id) {
		return this.dao.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("rawtypes")
	public List<T> selectAll() {
		RowBounds rowBounds = new RowBounds(1, 10000);
		Class clz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		Example example = new Example(clz);
		return this.dao.selectByExampleAndRowBounds(example, rowBounds);
	}

	@Transactional(readOnly = true)
	public T selectOne(T record) {
		return this.dao.selectOne(record);
	}

	@Transactional(readOnly = true)
	public List<T> selectListByWhere(T record) {
		return this.dao.select(record);
	}

	public Integer insert(T record) {
		return this.dao.insert(record);
	}

	public Integer insertSelective(T record) {
		return this.dao.insertSelective(record);
	}

	public Integer insertSelectiveWithOutTimeUpdate(T record) {
		return this.dao.insertSelective(record);
	}

	public Integer update(T record) {
		return this.dao.updateByPrimaryKey(record);
	}

	public Integer updateSelective(T record) {
		return this.dao.updateByPrimaryKeySelective(record);
	}

	public Integer deleteByPrimaryKey(Serializable id) {
		return this.dao.deleteByPrimaryKey(id);
	}

	public Integer deleteByWhere(T record) {
		return this.dao.delete(record);
	}

	@Transactional(readOnly = true)
	public List<T> selectByExample(Object example) {
		RowBounds rowBounds = new RowBounds(1, 10000);
		return this.dao.selectByExampleAndRowBounds(example, rowBounds);
	}

	@Transactional(readOnly = true)
	public Integer selectCountByExample(Object example) {
		return this.dao.selectCountByExample(example);
	}

	public Integer updateByExample(T record, Object example) {
		return this.dao.updateByExample(record, example);
	}

	public Integer updateByExampleSelective(T record, Object example) {
		return this.dao.updateByExampleSelective(record, example);
	}

	public Integer updateByExampleSelectiveWithOutTimeUpdate(T record, Object example) {
		return this.dao.updateByExampleSelective(record, example);
	}

	public Integer deleteByExample(Object example) {
		return this.dao.deleteByExample(example);
	}

	@Transactional(readOnly = true)
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		return this.dao.selectByRowBounds(record, rowBounds);
	}

	@Transactional(readOnly = true)
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		return this.dao.selectByExampleAndRowBounds(example, rowBounds);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertList(List<T> recordList) {
		return this.dao.insertList(recordList);
	}

	@Transactional(readOnly = true)
	public T selectOneByExample(Object example) {
		RowBounds rowBounds = new RowBounds(1, 1);
		List<T> tList = selectByExampleAndRowBounds(example, rowBounds);
		if (tList.size() > 0) {
			return tList.get(0);
		} else {
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertIfNotExistByExample(T record, Object example) {
		if (selectOneByExample(example) != null) {
			return 0;
		} else {
			return insertSelective(record);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertIfNotExistByExampleElseUpdate(T record, Object example) {
		if (selectOneByExample(example) != null) {
			return updateByExampleSelective(record, example);
		} else {
			return insertSelective(record);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer insertIfNotExistByExampleElseUpdateWithOutTimeUpdate(T record, Object example) {
		if (selectOneByExample(example) != null) {
			return updateByExampleSelectiveWithOutTimeUpdate(record, example);
		} else {
			return insertSelectiveWithOutTimeUpdate(record);
		}
	}
}
