package com.SpringJPADemo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.SpringJPADemo.Model.ResponseEntity;
import com.SpringJPADemo.Model.UserEntity;
import com.SpringJPADemo.Repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@RequestMapping(value = "/saveUser",method = RequestMethod.POST)
	public ResponseEntity<UserEntity> SaveUser(@RequestBody UserEntity user) {
		
		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		System.out.println(user);
		user.setAuthToken("");
		user.setRoleId(2);
		repo.save(user);
		
		res.setStatus(200);
		res.setMessage("User has saved.");
		res.setData(user);
		
		return res;
	}
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		ResponseEntity<List<UserEntity>> res = new ResponseEntity<>();
		
		List<UserEntity> userlist = repo.findAll();
		
		if(userlist.size()>0) {
			res.setStatus(200);
			res.setMessage("All users are retrieved.");
			res.setData(userlist);
		}else {
			res.setStatus(-1);
			res.setMessage("Invalid User!!!");
			res.setData(null);
		}
		
		return res;
	}
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserEntity> getUserById(@PathVariable("userId") int userId){
		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		
		UserEntity user = repo.findById(userId);
		System.out.println(user);
		
		if(user!= null) {
			
			res.setStatus(200);
			res.setMessage("The user is retrieved.");
			res.setData(user);
			
		}else{
			res.setStatus(-1);
			res.setMessage("Invalid User!!!");
			res.setData(null);
		}
		
		return res;
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserEntity> DeleteUser(@PathVariable("userId") int userId){
		
		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		
		UserEntity user = repo.findById(userId);
		System.out.println(user);
		
		if(user!= null) {
			
			repo.delete(user);
			
			res.setStatus(200);
			res.setMessage("The user is removed.");
			res.setData(user);
			
		}else{
			res.setStatus(-1);
			res.setMessage("Invalid User!!!");
			res.setData(null);
		}
		
		return res;	
	}
	@PutMapping("/editUser/{userId}")
	public ResponseEntity<UserEntity> EditUser(@PathVariable("userId") int userId,@RequestBody UserEntity updated_user){
		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		
		UserEntity Existing_user = repo.findById(userId);
		System.out.println(Existing_user);
		System.out.println(updated_user);
		if(Existing_user != null) {
			
			Existing_user.setFname(updated_user.getFname());
			Existing_user.setLname(updated_user.getLname());
			Existing_user.setAge(updated_user.getAge());
			Existing_user.setEmail(updated_user.getEmail());
			Existing_user.setPassword(updated_user.getPassword());
			
			repo.save(Existing_user);
			
			res.setStatus(200);
			res.setMessage("The user is updated.");
			res.setData(Existing_user);
			
		}else {
			res.setStatus(-1);
			res.setMessage("Invalid User!!!");
			res.setData(null);
		}
		
		return res;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<UserEntity> Authenticate(@RequestBody UserEntity user){
		ResponseEntity<UserEntity> res = new ResponseEntity<>();
		
		String email = user.getEmail();
		String password = user.getPassword();
		
		UserEntity emailresult = repo.findByEmail(email);
		UserEntity passwordresult = repo.findByPassword(password);
		
		if(emailresult != null && passwordresult != null) {
			res.setStatus(200);
			res.setMessage("The user has verified.");
			res.setData(emailresult);
		}else {
			res.setStatus(-1);
			res.setMessage("Invalid User!!!");
			res.setData(null);
		}
		
		return res;
	}
	
}
