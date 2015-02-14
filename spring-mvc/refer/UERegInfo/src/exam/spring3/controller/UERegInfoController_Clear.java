package exam.spring3.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class UERegInfoController_Clear {
	private static final Logger logger = Logger.getLogger(UERegInfoController_Clear.class);
	
	@RequestMapping("/ueRegInfo_clear")
	public ModelAndView ueRegInfo(HttpServletRequest request) {
		final String userIpAddress = request.getRemoteAddr();
		final String userAgent = request.getHeader("user-agent");
		logger.info("RemoteAddress : " + userIpAddress);
		logger.info("Controller : Get Data order by CLEAR");
		logger.info("Header : " + userAgent);
		logger.warn("*** All Data was removed by " + userIpAddress);
		
		final int index = UERegInfoUtil.INDEX_CLEAR;
		
		//List<UERegInfo> data = UERegInfoUtil.received("127.0.0.1", 32008, index);
		
		UERegInfoUtil.clear("127.0.0.1", 32008);
		
        String message = 
        		"<h1>Minieum Data</h1>" + 
        				UERegInfoUtil.linkMessage(index) +
        		"<p />" +
      		  	"<h1>Cleared...</h1>";
  				
        return new ModelAndView("ueRegInfo", "message", message);
	}
	
    
}


