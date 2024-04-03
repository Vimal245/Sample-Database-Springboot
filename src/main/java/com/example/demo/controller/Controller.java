package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;

@RestController
@RequestMapping("/mypath")
public class Controller {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/value")
	List<User>get(){
		return userRepository.findAll();
	}
	
	@PostMapping("/postvalue")
	User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updatemethod(@PathVariable long id,@RequestBody User userData)
	{
		Optional<User> userupdate = userRepository.findById(id);
		if(userupdate.isPresent())
		{
			User user = userupdate.get();
			user.setName(userData.getName());
			user.setSalary(userData.getSalary());
			
			userRepository.save(user);
			return new ResponseEntity<>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/delete/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userRepository.deleteById((long) id);
	}

}
