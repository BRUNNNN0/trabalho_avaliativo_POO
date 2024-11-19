package com.example.kabantop.service;

import com.example.kabantop.model.*;
import com.example.kabantop.repository.kanbanreposy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Tarefaservice {

    @Autowired
    private kanbanreposy kanbanRepository;

    public Map<Statusmodel, List<Tarefamodel>> listarTarefas() throws Exception{
        List<Tarefamodel> tarefas = kanbanRepository.findAll();

        if (tarefas.isEmpty()) {
            throw new Exception("Nenhuma tarefa encontrada!");
        }

        Map<Statusmodel, List<Tarefamodel>> tarefasPorStatus = tarefas.stream()
                .collect(Collectors.groupingBy(Tarefamodel::getStatus));

        for (Map.Entry<Statusmodel, List<Tarefamodel>> entry : tarefasPorStatus.entrySet()) {
            List<Tarefamodel> tarefasOrdenadas = entry.getValue().stream()
                    .sorted(Comparator.comparing(Tarefamodel::getPrioridade))
                    .toList();
            entry.setValue(tarefasOrdenadas);
        }
        return tarefasPorStatus;
    }

    public String criarTarefa(Creataskmodel kanbanCreat) {
        Tarefamodel tarefa = new Tarefamodel();

        tarefa.setTitulo(kanbanCreat.getTitulo());
        tarefa.setDescricao(kanbanCreat.getDescricao());
        tarefa.setStatus(Statusmodel.aFazer);
        tarefa.setPrioridade(kanbanCreat.getPrioridade());
        tarefa.setDatalimite(kanbanCreat.getDatalimitec());

        kanbanRepository.save(tarefa);
        return "Tarefa criada com sucesso";
    }

    public String atualizarTarefa(long id, updataskmodel kanbantask) throws Exception {
        Tarefamodel tarefa = tarefa(id);

        boolean updated = false;

        if (kanbantask.getTitulo() != null && !kanbantask.getTitulo().trim().isEmpty()) {
            tarefa.setTitulo(kanbantask.getTitulo());
            updated = true;
        }
        if (kanbantask.getDescricao() != null && !kanbantask.getDescricao().trim().isEmpty()) {
            tarefa.setDescricao(kanbantask.getDescricao());
            updated = true;
        }
        if (kanbantask.getPrioridade() != null) {
            tarefa.setPrioridade(kanbantask.getPrioridade());
            updated = true;
        }

        if (updated) {
            kanbanRepository.save(tarefa);
            return "Tarefa atualizada com sucesso!";
        } else {
            return "Nenhum campo foi alterado. Nenhuma atualização realizada.";
        }
    }

    public String moverTarefa(long id) throws Exception {
        Tarefamodel tarefa = tarefa(id);

        if(tarefa.getStatus() == Statusmodel.aFazer){
            tarefa.setStatus(Statusmodel.emProgresso) ;
        }else if(tarefa.getStatus() == Statusmodel.emProgresso){
            tarefa.setStatus(Statusmodel.concluida);
        }else{
            throw new Exception("A tarefa já foi concluida");
        }
        kanbanRepository.save(tarefa);
        return "Tarefa movida para com sucesso";
    }

    public Tarefamodel tarefa(long id) throws Exception {
        Optional<Tarefamodel> optionalTarefa = kanbanRepository.findById(id);
        if (!optionalTarefa.isPresent()) {
            throw new Exception("Tarefa não encontrada!");
        }
        return optionalTarefa.get();
    }

    public String deletarTarefa(long id) throws Exception {
        Tarefamodel deletar = tarefa(id);
        kanbanRepository.delete(deletar);
        return "Tarefa " + id +" deletada com sucesso!!";
    }

    public List<Tarefamodel> filtrarTarefas(Prioridademodel prioridade, Date dateLimit) {
        List<Tarefamodel> tarefas = kanbanRepository.findAll();
        List<Tarefamodel> tarefasFiltradas = new ArrayList<>();

        for (Tarefamodel tarefa : tarefas) {
            boolean prioridadeMatch = (prioridade == null || tarefa.getPrioridade().equals(prioridade));
            boolean dataMatch = (dateLimit == null || tarefa.getDatalimite().equals(dateLimit));
            if (prioridadeMatch && dataMatch) {
                tarefasFiltradas.add(tarefa);
            }
        }

        return tarefasFiltradas;
    }

    public List<Tarefamodel> relatorioTarefas() throws Exception {
        Map<Statusmodel, List<Tarefamodel>> tarefas = listarTarefas();
        List<Tarefamodel> tarefasAtrasadas = new ArrayList<>();
        Date hoje = new Date();

        for (Map.Entry<Statusmodel, List<Tarefamodel>> entry : tarefas.entrySet()) {
            if (!entry.getKey().equals(Statusmodel.concluida)) {
                tarefasAtrasadas.addAll(
                        entry.getValue().stream()
                                .filter(tarefa -> tarefa.getDatalimite() != null && tarefa.getDatalimite().before(hoje))
                                .collect(Collectors.toList())
                );
            }
        }
        tarefasAtrasadas.sort(Comparator.comparing(Tarefamodel::getDatalimite));

        return tarefasAtrasadas;
    }
}