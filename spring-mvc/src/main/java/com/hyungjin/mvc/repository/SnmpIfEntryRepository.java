package com.hyungjin.mvc.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hyungjin.mvc.model.SnmpIfEntry;

public interface SnmpIfEntryRepository extends JpaRepository<SnmpIfEntry, Long> {
	//@Override
	@Cacheable(value = "SnmpIfEntry")
	List<SnmpIfEntry> findAll(Sort sort);

	//@Override
	@Cacheable(value = "SnmpIfEntry")
	SnmpIfEntry findOne(Long id);
}
