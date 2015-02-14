package com.hyungjin.mvc.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyungjin.mvc.exception.SnmpIfEntryNotFound;
import com.hyungjin.mvc.model.SnmpIfEntry;
import com.hyungjin.mvc.repository.SnmpIfEntryRepository;


@Service
public class SnmpIfEntryServiceImpl implements SnmpIfEntryService {
	
	@Resource
	private SnmpIfEntryRepository snmpIfEntryRepository;

	@Override
	@Transactional
	public SnmpIfEntry create(SnmpIfEntry snmpIfEntry) {
		SnmpIfEntry createdSnmpIfEntry = snmpIfEntry;
		return snmpIfEntryRepository.save(createdSnmpIfEntry);
	}
	
	@Override
	@Transactional
	public SnmpIfEntry findById(long id) {
		return snmpIfEntryRepository.findOne(id);
	}
	
	@Override
	@Transactional
	public SnmpIfEntry findByIfIndex(int ifIndex) {
		return snmpIfEntryRepository.findByIfIndex(ifIndex);
	}

	@Override
	@Transactional(rollbackFor=SnmpIfEntryNotFound.class)
	public SnmpIfEntry delete(long id) throws SnmpIfEntryNotFound {
		SnmpIfEntry deletedSnmpIfEntry = snmpIfEntryRepository.findOne(id);
		
		if (deletedSnmpIfEntry == null)
			throw new SnmpIfEntryNotFound();
		
		snmpIfEntryRepository.delete(deletedSnmpIfEntry);
		return deletedSnmpIfEntry;
	}

	@Override
	@Transactional
	public List<SnmpIfEntry> findAll() {
		return snmpIfEntryRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=SnmpIfEntryNotFound.class)
	public SnmpIfEntry update(SnmpIfEntry snmpIfEntry) throws SnmpIfEntryNotFound {
		SnmpIfEntry updatedSnmpIfEntry = snmpIfEntryRepository.findOne(snmpIfEntry.getId());
		
		if (updatedSnmpIfEntry == null)
			throw new SnmpIfEntryNotFound();
		
		
		updatedSnmpIfEntry.setIfAdminStatus(snmpIfEntry.getIfAdminStatus());
		return updatedSnmpIfEntry;
	}

}
