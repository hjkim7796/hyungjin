package com.hyungjin.mvc.control;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyungjin.mvc.exception.SnmpIfEntryNotFound;
import com.hyungjin.mvc.model.SnmpIfEntry;
import com.hyungjin.mvc.repository.SnmpIfEntryRepository;


@RestController
@RequestMapping("/snmpIfEntry")
public class SnmpIfEntryControl {
	
	static Logger logger = LoggerFactory.getLogger(SnmpIfEntryControl.class);
	final StopWatch w = new StopWatch();

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Transactional
    public ModelAndView findAll()
    {
		Collection<SnmpIfEntry> snmpIfEntryList = this.snmpIfEntryRepository.findAll();
        
        ModelAndView mav = new ModelAndView("snmpIfEntry-list");
        mav.addObject("snmpIfEntryList", snmpIfEntryList);
        return mav;
    }
	
	@RequestMapping(value = "/list/{orderby}", method = RequestMethod.GET)
	@Transactional
    public ModelAndView findAllOrderBy(@PathVariable("orderby") String orderByName)
    {
		Collection<SnmpIfEntry> snmpIfEntryList = this.snmpIfEntryRepository.findAll(sortByName(orderByName));
        
        ModelAndView mav = new ModelAndView("snmpIfEntry-list");
        mav.addObject("snmpIfEntryList", snmpIfEntryList);
        return mav;
    }
	
	private Sort sortByName(String name) {
        return new Sort(Sort.Direction.ASC, name);
    }
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView editSnmpIfEntry(@PathVariable("id") Long id) {
		SnmpIfEntry snmpIfEntry = this.snmpIfEntryRepository.findOne(id);
		
		ModelAndView mav = new ModelAndView("snmpIfEntry-edit");
		mav.addObject("snmpIfEntry", snmpIfEntry);
        return mav;

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editSnmpIfEntry(@ModelAttribute @Validated SnmpIfEntry snmpIfEntry,
			BindingResult result, @PathVariable Long id,
			final RedirectAttributes redirectAttributes) throws SnmpIfEntryNotFound {

		if (result.hasErrors())
			return new ModelAndView("snmpIfEntry-edit");

		SnmpIfEntry _snmpIfEntry = this.snmpIfEntryRepository.findOne(id);
		_snmpIfEntry.setIfAdminStatus(snmpIfEntry.getIfAdminStatus());
		this.snmpIfEntryRepository.save( _snmpIfEntry );
		
		ModelAndView mav = new ModelAndView("redirect:/snmpIfEntry/list.html");
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public ModelAndView deleteAll(final RedirectAttributes redirectAttributes) throws SnmpIfEntryNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/snmpIfEntry/list.html");		
		this.snmpIfEntryRepository.deleteAll();
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteSnmpIfEntry(@PathVariable Long id,
			final RedirectAttributes redirectAttributes) throws SnmpIfEntryNotFound {
		
		ModelAndView mav = new ModelAndView("redirect:/snmpIfEntry/list.html");		
		this.snmpIfEntryRepository.delete(id);
		return mav;
	}
		
	@Autowired
	SnmpIfEntryRepository snmpIfEntryRepository;
}