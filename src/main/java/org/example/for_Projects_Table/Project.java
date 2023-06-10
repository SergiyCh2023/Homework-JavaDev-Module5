package org.example.for_Projects_Table;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Project {
    private int id;
    private int clientId;
    private LocalDate startDate;
    private LocalDate finishDate;

}
