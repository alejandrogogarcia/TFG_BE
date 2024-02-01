package es.udc.tfg.app.rest.security;

public interface JwtGenerator {

	String generate(JwtInfo info);
	
	JwtInfo getInfo(String token);
}
