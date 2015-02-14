package com.hyungjin.mvc.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hyungjin.mvc.model.SnmpIfEntry;


@Component
public class SnmpIfEntryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SnmpIfEntry.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SnmpIfEntry snmpIfEntry = (SnmpIfEntry) target;
		
		Integer ifAdminStatus = snmpIfEntry.getIfAdminStatus();
		
		ValidationUtils.rejectIfEmpty(errors, "name", "snmpIfEntry.ifAdminStatus.empty");
		ValidationUtils.rejectIfEmpty(errors, "", "shop.emplNumber.empty");
		
		if (ifAdminStatus != null && ifAdminStatus < 1 && ifAdminStatus > 3)
			errors.rejectValue("ifAdminStatus", "snmpIfEntry.ifAdminStatus.lessThenOne");

	}

}
