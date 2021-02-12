package com.mindtree.codingChallenge3.passportApplication.Service.Impl;

import java.util.List;

import com.mindtree.codingChallenge3.passportApplication.DAO.PassportApplicationDAO;
import com.mindtree.codingChallenge3.passportApplication.DAO.Impl.PassportApplicationDaoImpl;
import com.mindtree.codingChallenge3.passportApplication.Entity.Passport;
import com.mindtree.codingChallenge3.passportApplication.Entity.Person;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.PassportApplicationDAOException;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.PersonAbsentException;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.ServiceException.PassportApplicationServiceException;
import com.mindtree.codingChallenge3.passportApplication.Service.PassportApplicationSevice;

public class PassportApplicationServiceImpl implements PassportApplicationSevice {

	private PassportApplicationDAO dao = new PassportApplicationDaoImpl();

	public boolean addPersonToDB(Person person) throws PassportApplicationServiceException {
		boolean value = false;

		try {
			value = dao.addPersonToDB(person);
		} catch (PassportApplicationDAOException e) {
			throw new PassportApplicationServiceException(e);
		}
		return value;
	}

	public boolean addPassportToDB(Passport passport) throws PassportApplicationServiceException {
		boolean value = false;
		try {
			value = dao.addPassportToDB(passport);
		} catch (PassportApplicationDAOException e) {
			throw new PassportApplicationServiceException(e);
		}
		return value;
	}

	public List getPersonInformation(Person person, Passport passport, int id) throws PassportApplicationServiceException {
		List personList = null;
		try {
			personList=dao.getPersonInformation(person, passport, id);
		} catch (PassportApplicationDAOException e) {
			throw new PassportApplicationServiceException(e);
		}
		return personList;
		
		

	}

	public List sortBirthplace(Person person) throws PassportApplicationServiceException {
		List personList = null;
		try {
			personList=dao.sortBirthplace(person);
		} catch (PassportApplicationDAOException e) {
			throw new PassportApplicationServiceException(e);
		}
		return personList;
	}

	public boolean checkPersonIdPresentOrNot(int id) throws PassportApplicationServiceException {
		boolean value = false;
		try {
			value = dao.checkPersonIdPresentOrNot(id);
		} catch (PersonAbsentException e) {
			throw new PassportApplicationServiceException(e);
		}
		return value;
	}

	public boolean checkPassportIdPresentOrNot(int id) throws PassportApplicationServiceException {
		boolean value = false;
		try {
			value = dao.checkPassportIdPresentOrNot(id);
		} catch (PersonAbsentException e) {
			throw new PassportApplicationServiceException(e);
		}
		return value;
	}

}
