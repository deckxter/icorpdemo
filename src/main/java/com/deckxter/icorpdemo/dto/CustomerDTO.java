package com.deckxter.icorpdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private Integer years;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date probableDeathDate;
}
