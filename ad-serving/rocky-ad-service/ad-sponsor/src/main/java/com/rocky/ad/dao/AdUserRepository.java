package com.rocky.ad.dao;

import com.rocky.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdUserRepository  extends JpaRepository<AdUser, Long> {

    AdUser findByUserName(String username);

    List<AdUser> findAllByUserName(String userName);
}
