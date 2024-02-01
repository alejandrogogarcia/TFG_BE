package es.udc.tfg.app.service.clientservice;

import java.util.List;

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;

public interface ClientService {
	
	public Client createClient(ClientData clientData, Long creatorID) throws InputValidationException, InstanceNotFoundException, DuplicateInstanceException;
	
	public void updateClient(Long clientId, ClientData clientData) throws InstanceNotFoundException, InputValidationException, DuplicateInstanceException; 
	
	public Client findClientById(Long clientId) throws InstanceNotFoundException;
	
	public List<Client> findClientByFirstName(String firstName);
	
	public List<Client> findClientByLastName(String lastName);
	
	public Client findClientByDni(String dni) throws InstanceNotFoundException;
	
	public List<Client> findClientByCity(String city);
	
	public List<Client> findClientByCreatorId(Long creatorId);
	
	public List<Client> findAll();

}
