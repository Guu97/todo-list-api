package it.gu.todolist.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.gu.todolist.document.ToDo;
import it.gu.todolist.document.User;

public interface ToDoRepository extends  MongoRepository<ToDo, String>{
	List<ToDo> findAllByUser(String user);
}
