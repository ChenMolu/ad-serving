package com.rocky.ad.service;


import com.rocky.ad.vo.CreativeRequest;
import com.rocky.ad.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
