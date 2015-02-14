package com.hyungjin.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyungjin.mvc.model.SnmpIfEntry;

public interface SnmpIfEntryRepository extends JpaRepository<SnmpIfEntry, Long> {
	SnmpIfEntry findByIfIndex(Integer ifIndex);

}
