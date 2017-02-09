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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    public Integer getId(){
    	return this.id;
    }
   
    @OneToMany(mappedBy="project")
    private List<Users> users;
    
//
//	public void setId(Long id) {
//		this.id = id;
//	}
    
    
    @Column(name = "description")
    private String description;
    
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	    
    protected Projects(){
    	
    }
    
    public Projects(String description) {
        this.description = description;
    }
 
    @Override
    public String toString() {
        return String.format("%d: %s", id, description);
    }
    
}