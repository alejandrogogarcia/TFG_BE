package es.udc.tfg.fapptura.rest.security;

public interface JwtGenerator {

	String generate(JwtInfo info);
	
	JwtInfo getInfo(String token);
}
