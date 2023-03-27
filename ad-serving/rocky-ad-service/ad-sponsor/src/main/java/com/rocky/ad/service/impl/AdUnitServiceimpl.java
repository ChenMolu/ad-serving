package com.rocky.ad.service.impl;

import com.rocky.ad.constant.Constants;
import com.rocky.ad.dao.AdPlanRepository;
import com.rocky.ad.dao.AdUnitRepository;
import com.rocky.ad.entity.AdPlan;
import com.rocky.ad.entity.AdUnit;
import com.rocky.ad.exception.AdException;
import com.rocky.ad.service.IAdUnitService;
import com.rocky.ad.vo.AdUnitRequest;
import com.rocky.ad.vo.AdUnitResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdUnitServiceimpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitRepository unitRepository;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if(!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if(!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdUnit oldAdUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if(oldAdUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        AdUnit newAdUnit = unitRepository.save(
                new AdUnit(request.getPlanId(), request.getUnitName(),
                        request.getPositionType(), request.getBudget())
        );
        return null;
    }
}
