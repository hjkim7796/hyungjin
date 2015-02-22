package com.hyungjin.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SnmpIfEntry")
//@Cache(usage=CacheConcurrencyStrategy.READ_WRITE,region="SnmpIfEntry")
public class SnmpIfEntry {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, length = 10)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private Integer ifIndex;
	
	@Column(length = 100)
	private String ifDescr;
	private Integer ifSpeed;
	
	@Column(length = 20)
	private String ifPhysAddress;
	private Integer ifAdminStatus;
	private Integer ifOperStatus;
	private Long ifInOctets;
	private Long ifInDiscards;
	private Long ifInErrors;
	private Long ifOutOctets;
	private Long ifOutDiscards;
	private Long ifOutErrors;
	
	public SnmpIfEntry() {
	}
	
	public SnmpIfEntry(Integer ifIndex, String ifDescr,
			Integer ifSpeed, String ifPhysAddress, Integer ifAdminStatus,
			Integer ifOperStatus, Long ifInOctets, Long ifInDiscards,
			Long ifInErrors, Long ifOutOctets, Long ifOutDiscards,
			Long ifOutErrors) {
		super();
		this.ifIndex = ifIndex;
		this.ifDescr = ifDescr;
		this.ifSpeed = ifSpeed;
		this.ifPhysAddress = ifPhysAddress;
		this.ifAdminStatus = ifAdminStatus;
		this.ifOperStatus = ifOperStatus;
		this.ifInOctets = ifInOctets;
		this.ifInDiscards = ifInDiscards;
		this.ifInErrors = ifInErrors;
		this.ifOutOctets = ifOutOctets;
		this.ifOutDiscards = ifOutDiscards;
		this.ifOutErrors = ifOutErrors;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIfIndex() {
		return ifIndex;
	}
	public void setIfIndex(Integer ifIndex) {
		this.ifIndex = ifIndex;
	}
	public String getIfDescr() {
		return ifDescr;
	}
	public void setIfDescr(String ifDescr) {
		this.ifDescr = ifDescr;
	}
	public Integer getIfSpeed() {
		return ifSpeed;
	}
	public void setIfSpeed(Integer ifSpeed) {
		this.ifSpeed = ifSpeed;
	}
	public String getIfPhysAddress() {
		return ifPhysAddress;
	}
	public void setIfPhysAddress(String ifPhysAddress) {
		this.ifPhysAddress = ifPhysAddress;
	}
	public Integer getIfAdminStatus() {
		return ifAdminStatus;
	}
	public void setIfAdminStatus(Integer ifAdminStatus) {
		this.ifAdminStatus = ifAdminStatus;
	}
	public Integer getIfOperStatus() {
		return ifOperStatus;
	}
	public void setIfOperStatus(Integer ifOperStatus) {
		this.ifOperStatus = ifOperStatus;
	}
	public Long getIfInOctets() {
		return ifInOctets;
	}
	public void setIfInOctets(Long ifInOctets) {
		this.ifInOctets = ifInOctets;
	}
	public Long getIfInDiscards() {
		return ifInDiscards;
	}
	public void setIfInDiscards(Long ifInDiscards) {
		this.ifInDiscards = ifInDiscards;
	}
	public Long getIfInErrors() {
		return ifInErrors;
	}
	public void setIfInErrors(Long ifInErrors) {
		this.ifInErrors = ifInErrors;
	}
	public Long getIfOutOctets() {
		return ifOutOctets;
	}
	public void setIfOutOctets(Long ifOutOctets) {
		this.ifOutOctets = ifOutOctets;
	}
	public Long getIfOutDiscards() {
		return ifOutDiscards;
	}
	public void setIfOutDiscards(Long ifOutDiscards) {
		this.ifOutDiscards = ifOutDiscards;
	}
	public Long getIfOutErrors() {
		return ifOutErrors;
	}
	public void setIfOutErrors(Long ifOutErrors) {
		this.ifOutErrors = ifOutErrors;
	}

	@Override
	public String toString() {
		return "SnmpIfEntry [id=" + id + ", ifIndex=" + ifIndex + ", ifDescr="
				+ ifDescr + ", ifSpeed=" + ifSpeed + ", ifPhysAddress="
				+ ifPhysAddress + ", ifAdminStatus=" + ifAdminStatus
				+ ", ifOperStatus=" + ifOperStatus + ", ifInOctets="
				+ ifInOctets + ", ifInDiscards=" + ifInDiscards
				+ ", ifInErrors=" + ifInErrors + ", ifOutOctets=" + ifOutOctets
				+ ", ifOutDiscards=" + ifOutDiscards + ", ifOutErrors="
				+ ifOutErrors + "]";
	}
}