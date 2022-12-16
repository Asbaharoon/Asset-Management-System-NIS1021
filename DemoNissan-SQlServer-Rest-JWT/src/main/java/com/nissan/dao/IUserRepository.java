package com.nissan.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.User;
@Repository
public interface IUserRepository extends CrudRepository<User, Long> {


	//JPQL
	//Stored procedure  email password
	User findOneByEmailIdIgnoreCaseAndPassword(String emailId,String password);
}
