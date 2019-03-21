package com.deckxter.icorpdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private Integer years;
    private Timestamp birthday;
    private Timestamp probableDeathDate;
}
