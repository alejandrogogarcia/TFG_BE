package es.udc.tfg.fapptura.rest.security;

import java.util.List;
import java.util.stream.Collectors;

import es.udc.tfg.fapptura.model.user.User;
import es.udc.tfg.fapptura.rest.dtos.AuthenticatedUserDto;
import es.udc.tfg.fapptura.rest.dtos.UserDto;
import es.udc.tfg.fapptura.util.conversors.CalendarConversor;

public class UserConversor {
	
	
	public static UserDto toUserDto(User user) {
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
			user.getDni().toString(), user.getLanguage().toString(), CalendarConversor.calendarToString(user.getBirthDate()),user.getRole().toString());
	}
	
	public static List<UserDto> toUserDtoList(List<User> userList) {
		
		return userList.stream().map(u -> toUserDto(u)).collect(Collectors.toList());
	}
	
//	public final static User toUser(UserDto userDto) {
//		
//		return new User(userDto., firstName, lastName, dni, birthDate, email, language, role, isActive);
//	}
	
	public static AuthenticatedUserDto toAuthenticatedUserDto(String serviceToken, User user) {
		
		return new AuthenticatedUserDto(serviceToken, toUserDto(user));
		
	}

}
