package com.rocky.ad.service;

import com.rocky.ad.exception.AdException;
import com.rocky.ad.vo.CreateUserRequest;
import com.rocky.ad.vo.CreateUserResponse;

public interface IUserService  {

    CreateUserResponse createUser(CreateUserRequest createUserRequest) throws AdException;
}
