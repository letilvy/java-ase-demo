package com.sap.ase.exercises.decoupling;

public class ExceedsBetLimit extends IllegalArgumentException {
	private static final long serialVersionUID = -8410422610446605273L;

	public ExceedsBetLimit(String message) {
		super(message);
	}
}
