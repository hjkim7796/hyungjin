package exam.spring3.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.minieum.tcp.UERegInfo;
 
@Controller
public class UERegInfoController_MEID {
	private static final Logger logger = Logger.getLogger(UERegInfoController_MEID.class);
			
	@RequestMapping("/ueRegInfo_meid")
	public ModelAndView ueRegInfo(HttpServletRequest request) {
		final String userIpAddress = request.getRemoteAddr();
		final String userAgent = request.getHeader("user-agent");
		logger.info("RemoteAddress : " + userIpAddress);
		logger.info("Controller : Get Data order by MEID");
		logger.info("Header : " + userAgent);
 
		final int index = UERegInfo.INDEX_MEID;
		List<UERegInfo> data = UERegInfoUtil.getUERegInfoList(index);
        String message = 
        		"<h1>Minieum Data</h1>" + 
        				UERegInfoUtil.linkMessage(index) +
        		"<p />" +
        		"<div>" +
					"<table border=\"1\">" +
						"<thead>" +
							UERegInfoUtil.theadMessage() +
						"</thead>" +
						"<tbody>" +
							UERegInfoUtil.trMessages(data) +
						"</tbody>" +
					"</table>" +
				"</div>";
  				
        return new ModelAndView("ueRegInfo", "message", message);
    }
	
}


