package it.gu.todolist.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.gu.todolist.document.User;

public interface UserRepository extends   MongoRepository<User, String> {   

}