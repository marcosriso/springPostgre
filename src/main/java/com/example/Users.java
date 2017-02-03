package com.example;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    public long getId(){
    	return this.id;
    }
    
//
//	public void setId(Long id) {
//		this.id = id;
//	}

    
    
    @Column(name = "name")
    private String name;
    
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name = "password")
    private String password;
    
    protected Users(){
    	
    }
    
    public Users(String name, String password) {
        this.name     = name;
        this.password = password;
    }
 
    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', password='%s']", id, name, password);
    }
    
}