package ru.laba.training.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.laba.training.persist.TrainProgram;
import ru.laba.training.persist.TrainingRepository;

@Controller
public class TrainingController {

    private TrainingRepository repository;

    @Autowired
    public TrainingController(TrainingRepository repository) {
        this.repository=repository;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("items",repository.findAll());
        model.addAttribute("item",new TrainProgram());
        return "index";
    }

    @PostMapping
    public String newTrainProgram(TrainProgram trainProgram) {
        repository.save(trainProgram);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTrainProgram(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
