package com.rocky.ad.service.impl;

import com.rocky.ad.constant.Constants;
import com.rocky.ad.dao.AdUserRepository;
import com.rocky.ad.entity.AdUser;
import com.rocky.ad.exception.AdException;
import com.rocky.ad.service.IUserService;
import com.rocky.ad.utils.CommonUtils;
import com.rocky.ad.vo.CreateUserRequest;
import com.rocky.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository adUserRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository adUserRepository){
        this.adUserRepository = adUserRepository;
    }

    /**
     * 新建用户
     * @param createUserRequest
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest)
            throws AdException {

        if(!createUserRequest.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser = adUserRepository.findByUserName(createUserRequest.getUsername());
        if(oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = adUserRepository.save(new AdUser(createUserRequest.getUsername(),
                CommonUtils.md5(createUserRequest.getUsername())));
        log.info("new User:",newUser);

        return new CreateUserResponse(newUser.getId(), newUser.getUsername(),
                newUser.getToken(), newUser.getCreateTime(),newUser.getUpdateTime());
    }
}
