package com.rocky.ad.service.impl;

import com.rocky.ad.dao.CreativeRepository;
import com.rocky.ad.entity.Creative;
import com.rocky.ad.service.ICreativeService;
import com.rocky.ad.vo.CreativeRequest;
import com.rocky.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }
    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
