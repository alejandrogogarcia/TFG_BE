package es.udc.tfg.app.util.conversors;

import es.udc.tfg.app.util.enums.Taxes;
import es.udc.tfg.app.util.exceptions.InputValidationException;

public class TaxConversor {

	public static String taxToString(Taxes tax) throws InputValidationException {

		if (tax == null) {
			throw new InputValidationException("tax", "It must not be null");
		}

		return tax.toString();

	}

	public static Taxes stringToTax(String strTax) throws InputValidationException {

		Taxes tax = null;
		try {
			tax = Taxes.valueOf(strTax);
		} catch (IllegalArgumentException e) {
			throw new InputValidationException("strTax", "It must be: VAT, REDUCED OR EXEMPT");
		}

		return tax;

	}

}
