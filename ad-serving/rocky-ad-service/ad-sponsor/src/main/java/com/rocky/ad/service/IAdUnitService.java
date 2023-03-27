package com.rocky.ad.service;

import com.rocky.ad.exception.AdException;
import com.rocky.ad.vo.*;

public interface IAdUnitService {

    /**
     * 创建推广单元
     * @param request
     * @return
     */
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

}
