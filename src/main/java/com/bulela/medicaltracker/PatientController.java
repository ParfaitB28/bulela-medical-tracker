package com.bulela.medicaltracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @GetMapping("/new")
    public String showPatientForm(Model model){
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    @PostMapping
    public String savePatients(@ModelAttribute Patient patient){
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping
    public String listPatients(Model model){
        model.addAttribute("patients", patientRepository.findAll());
        return "patient-list";
    }
}