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
	public List<ToDo> getUserToDos(@PathVariable String idUser){
		return repo.findAllByUser(idUser);
	}
	
	@PostMapping("/add")
    public String saveTodo(@RequestBody ToDo td){
        repo.save(td);
        return "Added Successfully";
    }
	
	@PostMapping("/update/{id}")
    public String updateTodo(@PathVariable String id, @RequestBody ToDo td){
		
		Document document = new Document();
		template.getConverter().write(td, document);
		Update update = new Update();
		document.forEach(update::set);
		template.findAndModify(Query.query(Criteria.where("_id").is(id)), update, ToDo.class);
		
        return "Updated Successfully";
    }
	
	@DeleteMapping("/delete/{id}")
	public String deletTodo(@PathVariable String id) {
		repo.deleteById(id);
		return "Deleted Succesfully";
	}
}
