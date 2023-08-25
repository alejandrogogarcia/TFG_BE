package es.udc.tfg.fapptura.util.conversors;

import es.udc.tfg.fapptura.util.enums.UserRole;
import es.udc.tfg.fapptura.util.exceptions.InputValidationException;

public class RoleConversor {

	public static String roleToString(UserRole role) throws InputValidationException {

		if (role == null) {
			throw new InputValidationException("role", "It must not be null");
		}

		return role.toString();

	}

	public static UserRole stringToRole(String strRole) throws InputValidationException {

		UserRole role = null;
		System.out.println("+++++++++++++++++++++++++++" + strRole);
		try {
			role = UserRole.valueOf(strRole);
		} catch (IllegalArgumentException e) {
			throw new InputValidationException("strRole", "It must be: EMPLOYEE, CLERK OR ADMIN");
		}

		return role;

	}

}
