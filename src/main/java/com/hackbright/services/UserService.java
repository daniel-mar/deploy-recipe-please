package com.hackbright.services;

import com.hackbright.models.LoginUser;
import com.hackbright.models.User;
import com.hackbright.repositories.UserRepository;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service
public class UserService {
	
	// Using dependency injection rather than @Autowire
    private UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
    	// TO-DO - Reject values or register if no errors:
        
        // Reject if email is taken (present in database)
    	if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
    		result.rejectValue("email", "Unique", "You cannot email with this email, must use dif email!");
    	}
    	
        // Reject if password doesn't match confirmation
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
        	result.rejectValue("confirm", "Matches", "Your password and confirm password must matchy matchy!!");
        }
        
        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        }
        
        // Hash and set password using BCrypt, save user to database
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        
        return userRepo.save(newUser);
    }
    
    
    public User login(LoginUser newLogin, BindingResult result) {
    	// TO-DO - Reject values:
        
    	// Find user in the DB by email
        // Reject if NOT present
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
        	result.rejectValue("email", "Unique", "We don't know who you are! Email does not exist in the Database!");
        	return null;
        }
        User user = potentialUser.get();
        // Reject if BCrypt password match fails
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
        	result.rejectValue("password", "Matches", "That password does not match the password for this email");
        	return null;
        }
        // Return null if result has errors
        if(result.hasErrors()) {
        	return null;
        } else  {
        	// Otherwise, return the user object
        	return user;
        }
    	
    }
    
	//=======================================================================
	//Find One User based on Id
	//=======================================================================
 
    public User getUserInfo(Long id) {
    	
    	// in our service, our getUserInfo is called back to our userRepo in Repository
    	Optional<User>optionalUser = userRepo.findById(id);
    	if(optionalUser.isPresent()) { //if we located something
    		return optionalUser.get(); //pull from DB and return a proper instance
    	}else {
    		return null;
    	}
    }
}