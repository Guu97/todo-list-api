package it.gu.todolist.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="ToDos")
public class ToDo {
	@Id 
	private String id;
	private String title;
	private String description;
	private String todoBefore;
	private String todoAfter;
	private String user;
	
	
	
	public String getUser() {
		return user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTodoBefore() {
		return todoBefore;
	}
	public void setTodoBefore(String todoBefore) {
		this.todoBefore = todoBefore;
	}
	public String getTodoAfter() {
		return todoAfter;
	}
	public void setTodoAfter(String todoAfter) {
		this.todoAfter = todoAfter;
	}
	
	
	
}
