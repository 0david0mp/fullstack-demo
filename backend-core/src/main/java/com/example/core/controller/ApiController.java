package com.example.core.controller;

import com.example.core.database.DemoService;
import com.example.core.database.Workcolumn;
import com.example.core.database.Workitem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/workitem")
    public List<Workitem> getWorkitems() {
        System.out.println("[++] /api/workitem");
        List <Workitem> result = demoService.allWorkitems();
        System.out.println(result.toString());
        return result;
    }

    @GetMapping("/workcolumn")
    // TODO: no tiene sentido todos, todos los de una columna o algo así
    public List<Workcolumn> getWorkcolumns() {
        System.out.println("[++] /api/workcolumn");
        List <Workcolumn> result = demoService.allWorkcolumns();
        System.out.println(result.toString());
        return result;
    }
}
