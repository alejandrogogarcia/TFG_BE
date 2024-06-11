package es.udc.tfg.app.util.enums;

public enum Taxes {
	VAT(21), REDUCED(10), EXEMPT(0);

	private final int value;

	Taxes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
