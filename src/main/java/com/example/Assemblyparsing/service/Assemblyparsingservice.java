package com.example.Assemblyparsing.service;


import com.example.Assemblyparsing.model.AssemblyParsing;
import com.example.Assemblyparsing.repo.IassemblyParsing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class Assemblyparsingservice {


    @Autowired
     IassemblyParsing assemblyparsingRepository;

    public void saveAssemblyProgram(AssemblyParsing assemblyProgram) {


//        String customString =
        String assemblyCode = assemblyProgram.ProgramText;
        HashMap<String, Integer> registerMap = new HashMap<>();

        // Pattern to match "MV REGx,#value"
        Pattern pattern = Pattern.compile("MV\\s+(REG\\d+),\\s*#(\\d+)");
        Matcher matcher = pattern.matcher(assemblyCode);

        // Extract and store the registers and their values
        while (matcher.find()) {
            String register = matcher.group(1);  // e.g., "REG1"
            Integer value = Integer.parseInt(matcher.group(2).replace("#", ""));    // e.g., "#2000"
            registerMap.put(register, value);
        }



        // Pattern to match "ADD REGx, REGy" or "ADD REGx, number"
        Pattern addPattern = Pattern.compile("ADD\\s+(REG\\d+),\\s*(REG\\d+|\\d+)");
        Matcher addMatcher = addPattern.matcher(assemblyCode);


        while (addMatcher.find()) {
            String targetRegister = addMatcher.group(1);  // e.g., "REG1"
            String operand = addMatcher.group(2);  // e.g., "REG2" or "600"

            // Check if the operand is a register or a number
            int valueToAdd;
            if (operand.startsWith("REG")) {
                // Operand is a register
                valueToAdd = registerMap.getOrDefault(operand, 0);
            } else {
                // Operand is a number
                valueToAdd = Integer.parseInt(operand);
            }

            // Update the target register
            registerMap.put(targetRegister, registerMap.getOrDefault(targetRegister, 0) + valueToAdd);
        }


        // Display the stored values
        String[] keyValueArray = new String[registerMap.size()];
        int index = 0;
        for (HashMap.Entry<String, Integer> entry : registerMap.entrySet()) {
            keyValueArray[index] = entry.getKey() + "=" + entry.getValue();
            index++;
        }





        String fin="";

        for(int i=0;i<keyValueArray.length;i++){
            fin+=keyValueArray[i]+" ";
        }



        assemblyProgram.setReg(fin);


        assemblyparsingRepository.save(assemblyProgram);

    }






}
