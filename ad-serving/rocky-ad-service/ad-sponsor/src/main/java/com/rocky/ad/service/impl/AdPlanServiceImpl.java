package com.rocky.ad.service.impl;

import com.rocky.ad.constant.CommonStatus;
import com.rocky.ad.constant.Constants;
import com.rocky.ad.dao.AdPlanRepository;
import com.rocky.ad.dao.AdUserRepository;
import com.rocky.ad.entity.AdPlan;
import com.rocky.ad.entity.AdUser;
import com.rocky.ad.exception.AdException;
import com.rocky.ad.service.IAdPlanService;
import com.rocky.ad.utils.CommonUtils;
import com.rocky.ad.vo.AdPlanGetRequest;
import com.rocky.ad.vo.AdPlanRequest;
import com.rocky.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Override
    @Transactional
    public AdPlanResponse createPlan(AdPlanRequest planRequestVO) throws AdException {
        if(!planRequestVO.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        // 校验是否存在发布推广的用户
        Optional<AdUser> adUser = adUserRepository.findById(planRequestVO.getUserId());
        if(!adUser.isPresent()){
            throw  new AdException(Constants.ErrorMsg.USER_NOT_EXIST);
        }

        // 校验是痘已经存在推广计划
        AdPlan adPlan = adPlanRepository.findByUserIdAndPlanName
                (planRequestVO.getUserId(), planRequestVO.getPlanName());
        if(adPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        // 新建推广计划
        AdPlan insertedPlan = adPlanRepository.save(
                new AdPlan(
                        planRequestVO.getUserId(),
                        planRequestVO.getPlanName(),
                        CommonUtils.parseStringDate(planRequestVO.getStartDate()),
                        CommonUtils.parseStringDate(planRequestVO.getEndDate()))
        );

        return new AdPlanResponse(insertedPlan.getId(), insertedPlan.getPlanName());
    }

    @Override
    @Transactional
    public List<AdPlan> getAdPlanByPlanIds(AdPlanGetRequest planGetRequestVO) throws AdException {
        if(!planGetRequestVO.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdPlan> adPlanList = adPlanRepository.findAllByIdInAndUserId
                (planGetRequestVO.getPlanIds(), planGetRequestVO.getUserId());
        return adPlanList;
    }

    @Override
    @Transactional
    public AdPlanResponse updatePlan(AdPlanRequest adPlanRequest) throws AdException {
        if(!adPlanRequest.updateValidate()) {
            throw  new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan oldPlan = adPlanRepository.findByUserIdAndPlanName(
                adPlanRequest.getUserId(), adPlanRequest.getPlanName());
        if(oldPlan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (null != adPlanRequest.getPlanName()) {
            oldPlan.setPlanName(adPlanRequest.getPlanName());
        }
        if (null != adPlanRequest.getStartDate()) {
            oldPlan.setStartDate(CommonUtils.parseStringDate(adPlanRequest.getStartDate()));
        }
        if (null != adPlanRequest.getEndDate()) {
            oldPlan.setEndDate(CommonUtils.parseStringDate(adPlanRequest.getEndDate()));
        }
        oldPlan.setUpdateTime(new Date());

        AdPlan plan = adPlanRepository.save(oldPlan);
        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deletePlan(AdPlanRequest adPlanRequest) throws AdException {
        if (! adPlanRequest.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> oldPlan = adPlanRepository.findById(adPlanRequest.getId());
        if (!oldPlan.isPresent()) {
            throw new AdException("找不到当前推广计划，删除失败！");
        }

        AdPlan plan = oldPlan.get();
        //虚拟删除
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);

//        planRepository.deleteById(planRequestVO.getPlanId());
    }
}
