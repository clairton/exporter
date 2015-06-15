package br.eti.clairton.exporter;

import java.util.Date;

public class Person {
	private final String name;
	private final Date birthdate;

	public Person(String name, Date birthdate) {
		this.name = name;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public Date getBirthdate() {
		return birthdate;
	}
}
