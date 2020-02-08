package com.netcrackerCRUD.demo.controller;

import com.netcrackerCRUD.demo.model.Model;
import com.netcrackerCRUD.demo.repository.ModelRepository;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/contacts"})
public class Controller {

    private ModelRepository repository;

    Controller(ModelRepository modelRepository) {
        this.repository = modelRepository;
    }

    @GetMapping
    public List findAll(){
        return (List) repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Model> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Model create(@RequestBody Model contact){
        return repository.save(contact);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Model> update(@PathVariable("id") long id,
                                          @RequestBody Model contact){
        return repository.findById(id)
                .map(record -> {
                    record.setName(contact.getName());
                    record.setEmail(contact.getEmail());
                    record.setPhone(contact.getPhone());
                    Model updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
