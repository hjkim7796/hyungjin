package com.hyungjin.mvc.control;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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

	@RequestMapping(method = RequestMethod.GET)
	@Transactional
    public ModelAndView findAll()
    {
		return findAllOrderBy("ifIndex");
    }
	
	@RequestMapping(value = "/sort/{orderby}", method = RequestMethod.GET)
	@Transactional
    public ModelAndView findAllOrderBy(@PathVariable("orderby") String orderByName)
    {
		StopWatch w = new StopWatch();
		w.start("/sort");
		
		Collection<SnmpIfEntry> snmpIfEntryList = this.snmpIfEntryRepository.findAll(sortByName(orderByName));
        ModelAndView mav = new ModelAndView("snmpIfEntry-list");
        mav.addObject("snmpIfEntryList", snmpIfEntryList);
        
		w.stop();
        logger.info(w.prettyPrint());
        return mav;
    }
	
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
    public ModelAndView findById(@PathVariable("id") Long id)
    {
		SnmpIfEntry snmpIfEntry = this.snmpIfEntryRepository.findOne(id);
        
        ModelAndView mav = new ModelAndView("snmpIfEntry-detail");
        mav.addObject("snmpIfEntry", snmpIfEntry);
        return mav;
    }*/
	
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
    public SnmpIfEntry findById(@PathVariable("id") Long id)
    {
		return this.snmpIfEntryRepository.findOne(id);
    }*/
	
	private Sort sortByName(String name) {
        return new Sort(Sort.Direction.ASC, name);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView findOne(@PathVariable("id") Long id) {
		SnmpIfEntry snmpIfEntry = this.snmpIfEntryRepository.findOne(id);
		
		ModelAndView mav = new ModelAndView("snmpIfEntry-edit");
		mav.addObject("snmpIfEntry", snmpIfEntry);
        return mav;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute @Validated SnmpIfEntry snmpIfEntry,
			BindingResult result, @PathVariable Long id,
			final RedirectAttributes redirectAttributes) throws SnmpIfEntryNotFound {

		if (result.hasErrors())
			return new ModelAndView("snmpIfEntry-edit");

		SnmpIfEntry _snmpIfEntry = this.snmpIfEntryRepository.findOne(id);
		_snmpIfEntry.setIfAdminStatus(snmpIfEntry.getIfAdminStatus());
		this.snmpIfEntryRepository.save( _snmpIfEntry );
		cacheManager.getCache("SnmpIfEntry").clear();
		
		ModelAndView mav = new ModelAndView("redirect:/snmpIfEntry");
		
		return mav;
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView deleteAll(final RedirectAttributes redirectAttributes) throws SnmpIfEntryNotFound {
		this.snmpIfEntryRepository.deleteAll();
		cacheManager.getCache("SnmpIfEntry").clear();
		return new ModelAndView("redirect:/snmpIfEntry");	
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long id,
			final RedirectAttributes redirectAttributes) throws SnmpIfEntryNotFound {
		
		this.snmpIfEntryRepository.delete(id);
		cacheManager.getCache("SnmpIfEntry").clear();
		
		return new ModelAndView("redirect:/snmpIfEntry");		
	}
		
	@Autowired
	SnmpIfEntryRepository snmpIfEntryRepository;
	
	@Autowired
	CacheManager cacheManager;
}