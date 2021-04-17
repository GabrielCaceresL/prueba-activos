package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String numDocument;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private LocalDate birthday;

    public String fullName() {
        return this.firstName.concat(" ")
                .concat(this.secondName).concat(" ")
                .concat(this.firstSurname).concat(" ")
                .concat(this.secondSurname);
    }
}

