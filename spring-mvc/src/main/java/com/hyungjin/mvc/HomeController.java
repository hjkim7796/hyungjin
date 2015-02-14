package com.hyungjin.mvc;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hyungjin.mvc.model.SnmpIfEntry;
import com.hyungjin.mvc.repository.SnmpIfEntryRepository;

/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView index() {
		
		SnmpIfEntryRepository bean = appContext.getBean( SnmpIfEntryRepository.class );
		
    	//bean.deleteAll();
    	//
		Integer ifIndex;
		String ifDescr;
		Integer ifSpeed;
		String ifPhysAddress;
		Integer ifAdminStatus;
		Integer ifOperStatus;
		Long ifInOctets;
		Long ifInDiscards;
		Long ifInErrors;
		Long ifOutOctets;
		Long ifOutDiscards;
		Long ifOutErrors;
		Random r = new Random();
    	for(int i=1;i <=1000;i++) {
    		try {
    			ifIndex = i;
    			ifDescr = "Interface #" + i;
 				ifSpeed = 1000000;
				ifPhysAddress="aa:bb:cc:11:" + (r.nextInt(88) + 10)  + ":" + (r.nextInt(88)) + 10;
				ifAdminStatus=r.nextInt(3)+1;
				ifOperStatus=r.nextInt(7)+1;
				ifInOctets=(long)r.nextInt(100000);
				ifInDiscards = (long)r.nextInt(10);
				ifInErrors = (long)r.nextInt(10);
				ifOutOctets=(long)r.nextInt(100000);
				ifOutDiscards = (long)r.nextInt(100);
				ifOutErrors = (long)r.nextInt(10);
    			
				bean.save( new SnmpIfEntry(ifIndex, ifDescr,
    					ifSpeed, ifPhysAddress, ifAdminStatus,
    					ifOperStatus, ifInOctets, ifInDiscards,
    					ifInErrors, ifOutOctets, ifOutDiscards,	ifOutErrors));
				
				logger.debug("Inserted " + ifIndex);
    				
    		} catch(Exception e){
    			break;
    		}
    	}
    	
		return new ModelAndView("index");
	}

	@Autowired
	private ApplicationContext appContext;
}