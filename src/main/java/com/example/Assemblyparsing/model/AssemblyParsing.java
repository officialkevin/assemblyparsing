package com.example.Assemblyparsing.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor


public class AssemblyParsing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String ProgramText;
    public String Reg;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgramText() {
        return ProgramText;
    }

    public void setProgramText(String programText) {
        ProgramText = programText;
    }

    public String getReg() {
        return Reg;
    }

    public void setReg(String reg) {
        Reg = reg;
    }
}
