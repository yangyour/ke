package cn.dblearn.dbblog.common.core;

import cn.dblearn.dbblog.common.base.BaseUser;

public interface IUserService {

	BaseUser findById(Long id);
}
