package com.comcast.cable.hibernate.examples.versioning;

import java.util.List;

import com.comcast.cable.hibernate.examples.versioning.model.User;
import org.junit.Assert;
import org.junit.Test;

public class AppTest extends AbstractDbUnitJpaTest {
	@Test
	public void testBasicInsert() {

		User newUser = new User();
		newUser.setName("insert");

		entityManager.getTransaction().begin();

		entityManager.persist(newUser);
		long id = newUser.getId();

		entityManager.getTransaction().commit();

		User user = entityManager.find(User.class, id);
		Assert.assertNotNull(user);
        Assert.assertEquals(0, user.getVersion());
		Assert.assertEquals("insert", user.getName());
	}

	@Test
	public void testBasicVersionBump() {

		User newUser = new User();
		newUser.setName("insert");

		entityManager.getTransaction().begin();

		entityManager.persist(newUser);
		long id = newUser.getId();

		entityManager.getTransaction().commit();

		User user = entityManager.find(User.class, id);
		Assert.assertNotNull(user);
		Assert.assertEquals(0, user.getVersion());
		Assert.assertEquals("insert", user.getName());

		entityManager.getTransaction().begin();
		user.setName("my new cool name");
		entityManager.getTransaction().commit();

		user = entityManager.find(User.class, id);
		Assert.assertNotNull(user);
		Assert.assertEquals(1, user.getVersion());
		Assert.assertEquals("my new cool name", user.getName());
	}
}
