package com.hyungjin.mvc.service;

import java.util.List;

import com.hyungjin.mvc.exception.SnmpIfEntryNotFound;
import com.hyungjin.mvc.model.SnmpIfEntry;

public interface SnmpIfEntryService {
	
	public SnmpIfEntry create(SnmpIfEntry snmpIfEntry);
	public SnmpIfEntry delete(long id) throws SnmpIfEntryNotFound;
	public List<SnmpIfEntry> findAll();
	public SnmpIfEntry update(SnmpIfEntry snmpIfEntry) throws SnmpIfEntryNotFound;
	public SnmpIfEntry findById(long id);
	public SnmpIfEntry findByIfIndex(int ifIndex);

}
