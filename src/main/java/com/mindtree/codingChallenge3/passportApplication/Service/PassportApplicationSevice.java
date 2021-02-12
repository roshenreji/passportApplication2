package com.mindtree.codingChallenge3.passportApplication.Service;

import java.util.List;

import com.mindtree.codingChallenge3.passportApplication.Entity.Passport;
import com.mindtree.codingChallenge3.passportApplication.Entity.Person;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.ServiceException.PassportApplicationServiceException;

public interface PassportApplicationSevice {

	public boolean addPersonToDB(Person person)throws PassportApplicationServiceException;
	public boolean addPassportToDB(Passport passport)throws PassportApplicationServiceException;
	public List<Person> getPersonInformation(Person person,Passport passport, int id)throws PassportApplicationServiceException;
	public List<Person> sortBirthplace(Person person)throws PassportApplicationServiceException;
	public boolean checkPersonIdPresentOrNot(int id) throws PassportApplicationServiceException;
	public boolean checkPassportIdPresentOrNot(int id) throws PassportApplicationServiceException;
}
