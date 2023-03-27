package com.rocky.ad.service;

import com.rocky.ad.exception.AdException;
import com.rocky.ad.vo.AdUnitItRequest;
import com.rocky.ad.vo.AdUnitRequest;
import com.rocky.ad.vo.AdUnitResponse;

public interface IAdUnitService {

    /**
     * 创建推广单元
     * @param request
     * @return
     */
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException;
}
