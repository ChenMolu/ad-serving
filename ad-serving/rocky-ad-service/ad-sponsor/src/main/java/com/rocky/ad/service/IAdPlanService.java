package com.rocky.ad.service;

import com.rocky.ad.entity.AdPlan;
import com.rocky.ad.exception.AdException;
import com.rocky.ad.vo.AdPlanGetRequest;
import com.rocky.ad.vo.AdPlanRequest;
import com.rocky.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * 推广计划服务功能
 */
public interface IAdPlanService {

    /**
     * 创建推广计划
     */
    AdPlanResponse createPlan(AdPlanRequest planRequestVO) throws AdException;

    /**
     * 获取推广计划
     */
    List<AdPlan> getAdPlanByPlanIds(AdPlanGetRequest planGetRequestVO) throws AdException;

    /**
     * 更新推广计划
     */
    AdPlanResponse updatePlan(AdPlanRequest adPlanRequest) throws AdException;

    /**
     * 删除推广计划
     */
    void deletePlan(AdPlanRequest adPlanRequest) throws AdException;
}
