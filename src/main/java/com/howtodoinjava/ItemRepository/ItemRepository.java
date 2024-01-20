package com.howtodoinjava.ItemRepository;

import com.howtodoinjava.ItemModel.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
