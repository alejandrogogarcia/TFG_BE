package es.udc.tfg.app.service.clientservice;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfg.app.model.client.Client;
import es.udc.tfg.app.model.client.ClientDao;
import es.udc.tfg.app.model.user.User;
import es.udc.tfg.app.model.user.UserDao;
import es.udc.tfg.app.util.conversors.TaxConversor;
import es.udc.tfg.app.util.enums.Taxes;
import es.udc.tfg.app.util.exceptions.DuplicateInstanceException;
import es.udc.tfg.app.util.exceptions.InputValidationException;
import es.udc.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.tfg.app.util.validator.ValidatorProperties;

@Transactional
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Client createClient(ClientData clientData, Long creatorID)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		User creator = userDao.find(creatorID);

		String dni = clientData.getDni();
		ValidatorProperties.validateDni(dni);

		try {
			clientDao.findByDni(dni);
			throw new DuplicateInstanceException(dni, Client.class.getName());

		} catch (InstanceNotFoundException e) {
			String firstName = clientData.getFirstName();
			ValidatorProperties.validateString(firstName);
			String lastName = clientData.getLastName();
			ValidatorProperties.validateString(lastName);
			String address = clientData.getAddress();
			ValidatorProperties.validateString(address);
			String city = clientData.getCity();
			ValidatorProperties.validateString(city);
			Long postCode = clientData.getPostCode();
			ValidatorProperties.validatePostCode(postCode);
			String province = clientData.getProvince();
			ValidatorProperties.validateString(province);
			String email = clientData.getEmail();
			ValidatorProperties.validateEmail(email);
			Long phoneNumber = clientData.getPhoneNumber();
			ValidatorProperties.validatePhoneNumbre(phoneNumber);
			Taxes tax = TaxConversor.stringToTax(clientData.getTax());

			Client client = new Client(firstName, lastName, dni, address, city, postCode, province, email, phoneNumber,
					creator, tax);
			clientDao.save(client);
			creator.addClient(client);
			return client;
		}

	}

	@Override
	public void updateClient(Long clientId, ClientData clientData)
			throws InputValidationException, DuplicateInstanceException, InstanceNotFoundException {

		Client client = clientDao.find(clientId);

		String dni = clientData.getDni();
		if (client.getDni() != dni) {
			ValidatorProperties.validateDni(dni);
			try {
				clientDao.findByDni(dni);
				throw new DuplicateInstanceException(dni, Client.class.getName());
			} catch (InstanceNotFoundException e) {
			}
		} else {
			dni = client.getDni();
		}

		String firstName = clientData.getFirstName();
		if (client.getFirstName() != firstName) {
			ValidatorProperties.validateString(firstName);
		} else {
			firstName= client.getFirstName();
		}

		String lastName = clientData.getLastName();
		if (client.getLastName() != lastName) {
			ValidatorProperties.validateString(lastName);
		} else {
			lastName= client.getLastName();
		}

		String address = clientData.getAddress();
		if (client.getAddress() != address) {
			ValidatorProperties.validateString(address);
		} else {
			address= client.getAddress();
		}

		String city = clientData.getCity();
		if (client.getCity() != city) {
			ValidatorProperties.validateString(city);
		} else {
			city= client.getCity();
		}

		Long postCode = clientData.getPostCode();
		if (client.getPostCode() != postCode) {
			ValidatorProperties.validatePostCode(postCode);
		} else {
			postCode = client.getPostCode();
		}

		String province = clientData.getProvince();
		if (client.getProvince() != province) {
			ValidatorProperties.validateString(province);
		} else {
			province = client.getProvince();
		}

		String email = clientData.getEmail();
		if (client.getEmail() != email) {
			ValidatorProperties.validateEmail(email);
		} else {
			email = client.getEmail();
		}

		Long phoneNumber = clientData.getPhoneNumber();
		if (client.getPhoneNumber() != phoneNumber) {
			ValidatorProperties.validatePhoneNumbre(phoneNumber);
		} else {
			phoneNumber= client.getPhoneNumber();
		}

		Taxes tax = TaxConversor.stringToTax(clientData.getTax());
		if (client.getTax() == tax) {
			tax = client.getTax();
		}

		client.setDni(dni);
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setAddress(address);
		client.setCity(city);
		client.setPostCode(postCode);
		client.setProvince(province);
		client.setEmail(email);
		client.setPhoneNumber(phoneNumber);
		client.setTax(tax);
		client.setModifyDate(Calendar.getInstance());

	}

	@Override
	public Client findClientById(Long clientId) throws InstanceNotFoundException {
		return clientDao.find(clientId);
	}

	@Override
	public List<Client> findClientByFirstName(String firstName) {
		return clientDao.findByFirstName(firstName);
	}

	@Override
	public List<Client> findClientByLastName(String lastName) {
		return clientDao.findByLastName(lastName);
	}

	@Override
	public Client findClientByDni(String dni) throws InstanceNotFoundException {
		return clientDao.findByDni(dni);
	}

	@Override
	public List<Client> findClientByCity(String city) {
		return clientDao.findByCity(city);
	}

	@Override
	public List<Client> findClientByCreatorId(Long creatorId) {
		return clientDao.findByCreatorId(creatorId);
	}

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

}
