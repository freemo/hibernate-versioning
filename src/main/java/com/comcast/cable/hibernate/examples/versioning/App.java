package com.comcast.cable.hibernate.examples.versioning;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.comcast.cable.hibernate.examples.versioning.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Will load the user inserted by the script import-users.sql
 */
public class App {
	
	private static Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		User found = entityManager.find(User.class, 1L);
		log.info("found=" + found);
	}
}
