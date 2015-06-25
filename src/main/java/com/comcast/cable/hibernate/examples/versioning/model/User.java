package com.comcast.cable.hibernate.examples.versioning.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Table(name = "users")
@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "name", nullable = false, unique=true, length=50)
	private String name;

	@Version
	@Column(name = "version", nullable = false)
	private long version;

    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "USERFRIEND", joinColumns = @JoinColumn(name="user_id", nullable = false))
    @BatchSize(size = 50)
    private List<Friend> friends;

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

	public long getVersion() {
		return version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
