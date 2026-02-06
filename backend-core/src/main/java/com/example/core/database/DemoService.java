package com.example.core.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {
    @Autowired
    private ItemRepo itemRepository;
    @Autowired
    private ColumnRepo columnRepository;

    public void saveWorkitem(Workitem w) {
        itemRepository.save(w);
    }

    public List<Workitem> allWorkitems() {
        // return itemRepository.listarTodas();
        return itemRepository.findAll();
    }

    public List<Workcolumn> allWorkcolumns() {
        // return itemRepository.listarTodas();
        return columnRepository.findAll();
    }
}
