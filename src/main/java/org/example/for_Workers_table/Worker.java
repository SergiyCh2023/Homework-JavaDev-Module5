package org.example.for_Workers_table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Worker {

    private int id;
    private String name;
    private LocalDate birthday;
    private String levelTechSkill;
    private int salary;

}
