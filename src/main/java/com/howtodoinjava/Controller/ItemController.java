package com.howtodoinjava.Controller;

import com.howtodoinjava.ItemModel.Item;
import com.howtodoinjava.ItemRepository.ItemRepository;
import com.howtodoinjava.exception.ItemNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items")
    List<Item> all() {
        return itemRepository.findAll();
    }



    @PostMapping("/items")
    public ResponseEntity<Item> createUser(@Valid @RequestBody Item item) {

        Item savedUser = itemRepository.save(item);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/items/{id}")
    Item getById(@PathVariable Integer id) {

        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @DeleteMapping("/items/{id}")
    public void delete(@PathVariable int id) {
        itemRepository.deleteById(id);
    }

    @PutMapping("/items/{id}")
    Item updateOrCreate(@RequestBody Item newItem, @PathVariable Integer id) {

        return itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    return itemRepository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return itemRepository.save(newItem);
                });
    }
}
