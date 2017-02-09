package com.example;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Projects_ID", nullable=true)
    private Projects project ;

   
    
    public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

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
    
    public Users(String name, String password, Projects project) {
        this.name     = name;
        this.password = password;
        this.project  = project;
    }
 
    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', password='%s']", id, name, password);
    }
    
}