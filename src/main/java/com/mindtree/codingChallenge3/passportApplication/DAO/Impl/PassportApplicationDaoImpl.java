package com.mindtree.codingChallenge3.passportApplication.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.codingChallenge3.passportApplication.DAO.PassportApplicationDAO;
import com.mindtree.codingChallenge3.passportApplication.Entity.Passport;
import com.mindtree.codingChallenge3.passportApplication.Entity.Person;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.DatabaseConnectionFailedException;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.PassportApplicationDAOException;
import com.mindtree.codingChallenge3.passportApplication.Exceptions.DAOException.PersonAbsentException;
import com.mindtree.codingChallenge3.passportApplication.Utility.JDBCConnection;

public class PassportApplicationDaoImpl implements PassportApplicationDAO {

	private static JDBCConnection connection = new JDBCConnection();

	

	public boolean addPersonToDB(Person person) throws PassportApplicationDAOException {
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connection.getConnection();
			String insertQuery = "Insert into Person values(?,?,?,?)";
			ps = con.prepareStatement(insertQuery);

			ps.setInt(1, person.getId());
			ps.setString(2, person.getName());
			ps.setString(3, person.getBirthPlace());
			ps.setInt(4, person.getAge());

			ps.executeUpdate();
			isInserted = true;
		} catch (Exception e) {
			throw new PassportApplicationDAOException(e);
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}

		return isInserted;
	}

	public boolean addPassportToDB(Passport passport) throws PassportApplicationDAOException {
		boolean isInserted = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = connection.getConnection();
			String insertQuery = "Insert into Passport values(?,?,?)";
			ps = con.prepareStatement(insertQuery);

			ps.setInt(1, passport.getId());
			ps.setString(2, passport.getPassportNumber());
			ps.setInt(3, passport.getPerson().getId());

			ps.executeUpdate();
			isInserted = true;
		} catch (Exception e) {
			throw new PassportApplicationDAOException(e);
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}

		return isInserted;
	}
	
	public boolean checkPersonIdPresence(int id) throws PersonAbsentException {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean valid = false;
		try {

			con = connection.getConnection();
			String query = "Select id from Person Where Id=" + id;
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;
				}
			}

			if (valid == false) {
				throw new PersonAbsentException("No such PersonID there in the system");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			throw new PersonAbsentException("No such PersonID there in the system");
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		return valid;

	}
	
	public boolean checkPersonIdPresentOrNot(int id) throws PersonAbsentException {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean valid = false;
		try {

			con = connection.getConnection();
			String query = "Select id from Person Where Id=" + id;
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;
				}
			}

			if (valid == true) {
				throw new PersonAbsentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			throw new PersonAbsentException("Already Present");
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		return valid;

	}
	public boolean checkPassportIdPresentOrNot(int id) throws PersonAbsentException {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		boolean valid = false;
		try {

			con = connection.getConnection();
			String query = "Select id from Passport Where Id=" + id;
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
				if (count == id) {
					valid = true;
					break;
				}
			}

			if (valid == true) {
				throw new PersonAbsentException("Already Present");
			}

			if (ps != null) {
				ps.close();
			}

		} catch (Exception e) {
			throw new PersonAbsentException("Already Present");
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
		}
		return valid;

	}


	public List<Person> getPersonInformation(Person person, Passport passport, int id) throws PassportApplicationDAOException {
		List<Person> personList = new ArrayList<Person>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 1;

		try {
			checkPersonIdPresence(id);
			try {
				con = connection.getConnection();
				String query = "Select Person.id,Person.name,Person.birthPlace,Person.age,Passport.id,Passport.passportNumber from Person Left join Passport on Person.id=Passport.personId;";
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();

				while (rs.next()) {
					
					person = new Person(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));
					personList.add(person);
					if(id==rs.getInt(1)) {
						System.out.println();
						System.out.println(count + "\t " + "Person Id:- " + rs.getInt(1));
						System.out.println("\t Person Name: " + rs.getString(2));
						System.out.println("\t Birth Place: " + rs.getString(3));
						System.out.println("\t Age: " + rs.getInt(4));
						System.out.println("\t Passport ID "+rs.getInt(5));
						System.out.println("\t Passport Number "+rs.getString(6));
						count++;
					}
					
				}

			} catch (DatabaseConnectionFailedException e) {
				throw new PassportApplicationDAOException(e);
			} catch (Exception e) {
				throw new PassportApplicationDAOException(e);
			} finally {
				connection.closeResources(con);
				connection.closeResources(ps);
				connection.closeResources(rs);
			}
		} catch (PersonAbsentException e1) {
			throw new PassportApplicationDAOException(e1);
		}
		

		return personList;
	}

	public List<Person> sortBirthplace(Person person) throws PassportApplicationDAOException {
		List<Person> personList = new ArrayList<Person>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 1;
		
		try {
			con = connection.getConnection();
			String query = "Select name,birthPlace from Person order by birthPlace;";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				person=new Person(rs.getString(1),rs.getString(2));
				//String display=rs.getString(1)+" : 	\t"+rs.getString(2);
				personList.add(person);
			}

		} catch (DatabaseConnectionFailedException e) {
			throw new PassportApplicationDAOException(e);
		} catch (Exception e) {
			throw new PassportApplicationDAOException(e);
		} finally {
			connection.closeResources(con);
			connection.closeResources(ps);
			connection.closeResources(rs);
		}
		return personList;
		
	}

}
