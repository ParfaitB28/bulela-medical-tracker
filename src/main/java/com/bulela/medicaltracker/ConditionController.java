package com.bulela.medicaltracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/conditions")
public class ConditionController {

    private final ConditionRepository conditionRepository;
    private final PatientRepository patientRepository;

    public ConditionController(
            ConditionRepository conditionRepository,
            PatientRepository patientRepository) {

        this.conditionRepository = conditionRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/new")
    public String showConditionForm(Model model) {
        model.addAttribute("condition", new Condition());
        model.addAttribute("patients", patientRepository.findAll());
        return "condition-form";
    }

    @PostMapping
    public String saveCondition(
            @ModelAttribute Condition condition,
            @RequestParam Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        condition.setPatient(patient);
        conditionRepository.save(condition);

        return "redirect:/conditions";
    }

    @GetMapping
    public String listConditions(Model model) {
        model.addAttribute("conditions", conditionRepository.findAll());
        return "condition-list";
    }
}