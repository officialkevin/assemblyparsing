package com.example.Assemblyparsing.controller;


import com.example.Assemblyparsing.model.AssemblyParsing;
import com.example.Assemblyparsing.service.Assemblyparsingservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Assemblyparsingcontroller {


    @Autowired
    Assemblyparsingservice assemblyparsingService;
    @PostMapping("/assembly")
    public String addAssemblyProgram(@RequestBody AssemblyParsing assemblyProgram) {

        assemblyparsingService.saveAssemblyProgram(assemblyProgram);

return  "added";



    }





}
