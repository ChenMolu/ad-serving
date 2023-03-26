package com.rocky.ad.service;

import com.rocky.ad.exception.AdException;
import com.rocky.ad.vo.CreateUserRequest;
import com.rocky.ad.vo.CreateUserResponse;

/**
 * 用户账户服务功能
 */
public interface IUserService  {

    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException;

}
