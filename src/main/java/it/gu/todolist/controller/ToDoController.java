package it.gu.todolist.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.gu.todolist.document.ToDo;
import it.gu.todolist.repo.ToDoRepository;

@RestController
@RequestMapping("/todo")
public class ToDoController {

	@Autowired
	private MongoTemplate template;

	@Autowired
	private ToDoRepository repo;

	@GetMapping("/findAll/{idUser}")
	public ResponseEntity<List<ToDo>> getUserToDos(@PathVariable String idUser){
		//return repo.findAllByUser(idUser);
		return new ResponseEntity<>(repo.findAllByUser(idUser), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<String> saveTodo(@RequestBody ToDo td){
		try {
			repo.save(td);
			return new ResponseEntity<>("Added Succesfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<String> updateTodo(@PathVariable String id, @RequestBody ToDo td){
		try {
			Document document = new Document();
			template.getConverter().write(td, document);
			Update update = new Update();
			document.forEach(update::set);
			template.findAndModify(Query.query(Criteria.where("_id").is(id)), update, ToDo.class);
			return new ResponseEntity<>("Updated Succesfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletTodo(@PathVariable String id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
