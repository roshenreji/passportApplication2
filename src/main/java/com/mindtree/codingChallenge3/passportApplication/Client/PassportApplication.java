package com.mindtree.codingChallenge3.passportApplication.Client;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.mindtree.codingChallenge3.passportApplication.Entity.Passport;
import com.mindtree.codingChallenge3.passportApplication.Entity.Person;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.ServiceException.PassportApplicationServiceException;
import com.mindtree.codingChallenge3.passportApplication.Service.PassportApplicationSevice;
import com.mindtree.codingChallenge3.passportApplication.Service.Impl.PassportApplicationServiceImpl;

public class PassportApplication {

	static Scanner sc = new Scanner(System.in);
	private static PassportApplicationSevice service = new PassportApplicationServiceImpl();

	public static void displayMessages() {
		System.out.println();
		System.out.println("These are the choices: ");
		System.out.println("1. Insert Person Details to the Database");
		System.out.println("2. Insert Passport Details to the Database");
		System.out.println("3. Get Person Details by passing person id as input");
		System.out.println("4. Sort the person Birthplace and siplay the corresponding person Name");
		System.out.println("5. Exit ");
	}

	public static Person getPersonDetails(Person person) {
		System.out.println("Enter person id: ");

		int id = sc.nextInt();
		try {
			service.checkPersonIdPresentOrNot(id);
			System.out.println("Enter Person Name: ");

			sc.nextLine();
			String name = sc.nextLine();
			System.out.println("Enter Person's BirthPlace");
			String birthPlace = sc.next();
			System.out.println("Enter the Age of the person: ");
			int age = sc.nextInt();

			person = new Person(id, name, birthPlace, age);
		} catch (PassportApplicationServiceException e) {
			System.out.println(e.getMessage());
		}

		return person;
	}

	public static Passport getPassportDetailsDetails(Passport passport, Person person) {

		System.out.println("Enter Passport Id: ");
		int passportId = sc.nextInt();
		try {
			service.checkPassportIdPresentOrNot(passportId);
			System.out.println("Enter Passport Number");
			String passportNumber = sc.next();

			int personId = person.getId();

			passport = new Passport(passportId, passportNumber, person);

		} catch (PassportApplicationServiceException e) {
			System.out.println(e.getMessage());
		}
		
		return passport;
	}

	public static void displayPerson(List<Person> person) {
		Iterator<Person> it = person.iterator();
		int count = 1;
		Person temp;
		while (it.hasNext()) {
			temp = it.next();
			System.out.println();
			System.out.println(count + "\t Person Name" + temp.getName());
			System.out.println("\t BirthPlace: " + temp.getBirthPlace());
			count++;
		}
	}

	public static void main(String[] args) {
		boolean isValid = true;
		Person person = null;
		Passport passport = null;

		do {
			displayMessages();
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				boolean value = false;
				person = getPersonDetails(person);
				try {
					value = service.addPersonToDB(person);
					if (value == true) {
						System.out.println("Inserted Successfully");
					}
				} catch (PassportApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				boolean newValue = false;
				if (person != null) {
					passport = getPassportDetailsDetails(passport, person);
					try {
						newValue = service.addPassportToDB(passport);
						if (newValue == true) {
							System.out.println("Inserted Successfully");
						}
					} catch (PassportApplicationServiceException e) {
						System.out.println(e.getMessage());
					}
				}

				else
					System.out.println("Insert Person Details First");

				break;
			case 3:
				System.out.println("Enter the person's Id: ");
				int id = sc.nextInt();

				try {
					List<Person> personList = service.getPersonInformation(person, passport, id);
				} catch (PassportApplicationServiceException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 4:
				List<Person> sortedPerson;
				try {
					sortedPerson = service.sortBirthplace(person);
					displayPerson(sortedPerson);
				} catch (PassportApplicationServiceException e) {
					System.out.println(e.getMessage());
				}

			}
		} while (isValid);

	}

}
