package com.rocky.ad.dao;

import com.rocky.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository  extends JpaRepository<AdUser, Long> {
}
