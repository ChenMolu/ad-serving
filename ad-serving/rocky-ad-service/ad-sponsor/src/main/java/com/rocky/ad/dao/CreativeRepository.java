package com.rocky.ad.dao;

import com.rocky.ad.entity.Creative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreativeRepository extends JpaRepository<Creative, Long> {
}
