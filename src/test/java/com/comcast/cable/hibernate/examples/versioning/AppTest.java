package com.comcast.cable.hibernate.examples.versioning;

import java.util.ArrayList;
import java.util.List;

import com.comcast.cable.hibernate.examples.versioning.model.Friend;
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

    @Test
    public void testEmbededVersionBump() {

        User newUser = new User();
        newUser.setName("insert");
        List<Friend> newFriends = new ArrayList<>();
        Friend newFriend = new Friend();
        newFriend.setName("buddy");
        newFriend.setIsTrusted(true);
        newFriends.add(newFriend);
        newUser.setFriends(newFriends);

        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        long id = newUser.getId();
        entityManager.getTransaction().commit();

        User user = entityManager.find(User.class, id);
        Assert.assertNotNull(user);
        Assert.assertEquals(0, user.getVersion());
        Assert.assertEquals(1, user.getFriends().size());
        Assert.assertEquals("insert", user.getName());
        Assert.assertEquals("buddy", user.getFriends().get(0).getName());

        entityManager.getTransaction().begin();
        Friend friend = user.getFriends().get(0);
        friend.setName("enemy");
        friend.setIsTrusted(false);
        entityManager.getTransaction().commit();

        user = entityManager.find(User.class, id);
        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getVersion());
        Assert.assertEquals("enemy", user.getFriends().get(0).getName());
    }
}
