package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "Document number is required")
    private String numDocument;

    @NotBlank(message = "Name is required")
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;

    @NotNull(message = "Birthday is required")
    private LocalDate birthday;

    public String fullName() {
        return this.firstName.concat(" ")
                .concat(this.secondName).concat(" ")
                .concat(this.firstSurname).concat(" ")
                .concat(this.secondSurname);
    }
}

