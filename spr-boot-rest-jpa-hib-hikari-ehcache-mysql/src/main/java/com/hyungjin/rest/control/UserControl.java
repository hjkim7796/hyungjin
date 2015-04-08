package com.hyungjin.rest.control;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyungjin.rest.model.User;
import com.hyungjin.rest.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserControl {
	
	static Logger logger = LoggerFactory.getLogger(UserControl.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional
	Collection<User> findAll() {
		StopWatch w = new StopWatch();
		try {
			w.start("/list");
			return this.userRepository.findAll();
		} finally {
			w.stop();
	        logger.info(w.prettyPrint());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public User findById(@PathVariable("id") Long id) {
		StopWatch w = new StopWatch();
		try {
			w.start("/" + id);
			return this.userRepository.findOne(id);
		} finally {
			w.stop();
	        logger.info(w.prettyPrint());
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@Transactional
	public User update(@PathVariable("id") Long id, @RequestBody User user) {
		logger.info("ID: " + id.toString());
		logger.info("Input: " + user.toString());
		User _user = this.userRepository.findOne(id);
		if( _user != null && _user.getUserId().equals(user.getUserId()) ) {
			_user.setEmail(user.getEmail());
			_user.setPassword(user.getPassword());
			_user.setUserName(user.getUserName());
			return _user;
		}
		throw new RuntimeException();
	}

	@RequestMapping(value = "/", method=RequestMethod.POST)
	@Transactional
    public User add(@RequestBody User user) {
		logger.info(user.toString());
		User _user = this.userRepository.findByUserId(user.getUserId());
		if(_user == null) {
			return this.userRepository.save( user );
		}
		throw new RuntimeException("Already Exist...");
        
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @Transactional
    public String delete(@PathVariable Long id) throws Exception {
        this.userRepository.delete(id);
        return "The User was successfully deleted.";
    }
	@Autowired
	UserRepository userRepository;
}
