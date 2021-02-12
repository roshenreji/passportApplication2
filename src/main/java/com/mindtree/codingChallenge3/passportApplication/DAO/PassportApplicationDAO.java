package com.mindtree.codingChallenge3.passportApplication.DAO;

import java.util.List;

import com.mindtree.codingChallenge3.passportApplication.Entity.Passport;
import com.mindtree.codingChallenge3.passportApplication.Entity.Person;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.PassportApplicationDAOException;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.PersonAbsentException;

public interface PassportApplicationDAO {

	public boolean addPersonToDB(Person person)throws PassportApplicationDAOException;
	public boolean addPassportToDB(Passport passport)throws PassportApplicationDAOException;
	public List<Person> getPersonInformation(Person person,Passport passport, int id)throws PassportApplicationDAOException;
	public List<Person> sortBirthplace(Person person)throws PassportApplicationDAOException;
	public boolean checkPersonIdPresentOrNot(int id) throws PersonAbsentException;
	public boolean checkPassportIdPresentOrNot(int id) throws PersonAbsentException;
}
