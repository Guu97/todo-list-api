package it.gu.todolist.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="Users")
public class User {

    @Id
    private String id;
    private String password;
    private String[] roles;
    
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	
	public String[] getRoles() {
		return roles;
	}
    
}
