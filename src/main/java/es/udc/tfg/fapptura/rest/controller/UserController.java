package es.udc.tfg.fapptura.rest.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.udc.tfg.fapptura.model.user.User;
import es.udc.tfg.fapptura.rest.dtos.AuthenticatedUserDto;
import es.udc.tfg.fapptura.rest.dtos.UserDto;
import es.udc.tfg.fapptura.rest.security.JwtGenerator;
import es.udc.tfg.fapptura.rest.security.JwtInfo;
import es.udc.tfg.fapptura.rest.security.UserConversor;
import es.udc.tfg.fapptura.service.userservice.LoginData;
import es.udc.tfg.fapptura.service.userservice.PassChangeData;
import es.udc.tfg.fapptura.service.userservice.RegisterData;
import es.udc.tfg.fapptura.service.userservice.UserService;
import es.udc.tfg.fapptura.util.exceptions.ConfirmPasswordNotMatchException;
import es.udc.tfg.fapptura.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.fapptura.util.exceptions.IncorrectPasswordException;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;
import es.udc.tfg.fapptura.util.exceptions.InstanceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtGenerator jwtGenerator;

	@PostMapping("/login")
	public AuthenticatedUserDto login(@RequestBody LoginData params)
			throws InstanceNotFoundException, IncorrectPasswordException {

		User user = userService.loginUser(params);

		return UserConversor.toAuthenticatedUserDto(generateToken(user), user);

	}

	@PostMapping("/users/create")
	public ResponseEntity<AuthenticatedUserDto> signUp(@RequestBody RegisterData registerData)
			throws InputValidationException, ConfirmPasswordNotMatchException, DuplicateInstanceException
			 {

		User user = userService.registerUser(registerData);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").build().toUri();

		return ResponseEntity.created(location).body(UserConversor.toAuthenticatedUserDto(generateToken(user), user));

	}
	
	@PutMapping("/users/{id}/changePass")
	public AuthenticatedUserDto login(@PathVariable Long id, @RequestBody PassChangeData params)
			throws InstanceNotFoundException, IncorrectPasswordException, InputValidationException{

		userService.changeUserPassword(id, params.getOldPassword(), params.getNewPassword());
		
		User user = userService.findUserById(id);
		
		return UserConversor.toAuthenticatedUserDto(generateToken(user), user);

	}
	
	@GetMapping("/allUsers")
	public List<UserDto> findAllUsers(){
				
		return UserConversor.toUserDtoList(userService.findAllUsers());

	}

	private String generateToken(User user) {
		
		JwtInfo jwtInfo = new JwtInfo(user.getId(), user.getDni(), user.getRole().toString());
		
		return jwtGenerator.generate(jwtInfo);
		
	}

}
