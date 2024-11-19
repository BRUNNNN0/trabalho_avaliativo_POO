package com.example.kabantop.controller;

import com.example.kabantop.model.*;
import com.example.kabantop.service.Tarefaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class Tarefacontroller {

    @Autowired()
    private Tarefaservice kanbanService;


    @GetMapping()
    public Map<Statusmodel, List<Tarefamodel>> getAllTask() throws Exception{
        return kanbanService.listarTarefas();
    }


    @PutMapping("{id}")
    public String getOneTask(@PathVariable long id, @RequestBody updataskmodel atualizartask) throws Exception{
        return kanbanService.atualizarTarefa(id, atualizartask);
    }


    @PostMapping()
    public String postTask(@RequestBody Creataskmodel criartarefa) {
        return kanbanService.criarTarefa(criartarefa);
    }


    @PutMapping("{id}/move")
    public String putTask(@PathVariable long id) throws Exception {
        return kanbanService.moverTarefa(id);
    }

    @DeleteMapping("{id}")
    public String deleteTask(@PathVariable long id) throws Exception {
        return kanbanService.deletarTarefa(id);
    }


    @GetMapping("/filter")
    public List<Tarefamodel> filtrarTarefas(
            @RequestParam(required = false) Prioridademodel prioridade,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateLimit) {
        return kanbanService.filtrarTarefas(prioridade, dateLimit);
    }


    @GetMapping("/report")
    public List<Tarefamodel> ralatorioTarefas() throws Exception{
        return kanbanService.relatorioTarefas();
    }
}