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
	public Collection<User> findAll() {
		StopWatch w = new StopWatch();
		try {
			w.start("/list");
			return this.userRepository.findAll();
		} finally {
			w.stop();
	        logger.info(w.prettyPrint());
		}
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@Transactional
	public User findById(@PathVariable("userId") String userId) {
		StopWatch w = new StopWatch();
		try {
			w.start("/" + userId);
			return this.userRepository.findOne(userId);
		} finally {
			w.stop();
	        logger.info(w.prettyPrint());
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@Transactional
	public User update(@RequestBody User user) {
		logger.info("Input: " + user.toString());
		Collection<User> users = this.userRepository.findAll();
		for(User _user: users)
		{
			if(user.getEmail() != null)
				_user.setEmail(user.getEmail());
			if(user.getPassword() != null)
				_user.setPassword(user.getPassword());
			if(user.getUserName() != null)
				_user.setUserName(user.getUserName());
			
			this.userRepository.save( _user );
		}
		return user;
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@Transactional
	public User update(@PathVariable("userId") String userId, @RequestBody User user) {
		logger.info("ID: " + userId);
		logger.info("Input: " + user.toString());
		User _user = this.userRepository.findOne(userId);
		//update or create user
		if(_user != null || user.getUserId() != null) {
			user.setUserId(userId);
			return this.userRepository.save( user );
		}
		
		throw new RuntimeException();
	}

	@RequestMapping(value = "/", method=RequestMethod.POST)
	@Transactional
    public User add(@RequestBody User user) {
		logger.info(user.toString());
		User _user = this.userRepository.findOne(user.getUserId());
		if(_user == null) {
			return this.userRepository.save( user );
		}
		throw new RuntimeException("Already Exist...");
        
    }
	
    @RequestMapping(value="/", method=RequestMethod.DELETE)
    @Transactional
    public String delete() throws Exception {
        this.userRepository.deleteAll();
        return "The User was successfully deleted.";
    }
    
    @RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
    @Transactional
    public String delete(@PathVariable String userId) throws Exception {
        this.userRepository.delete(userId);
        return "The User was successfully deleted.";
    }
	@Autowired
	UserRepository userRepository;
}
