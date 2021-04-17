package com.test.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {


    private BasicUserData basicUserData;
    private LocalDate creationDate;
    private LocalDate updateDate;

    @Data
    @NoArgsConstructor
    public static class BasicUserData{

        private String numDocument;
        private String firstName;
        private String secondName;
        private String firstSurname;
        private String secondSurname;
        private LocalDate birthday;

        public String fullName(){
            return this.firstName.concat(" ")
                    .concat(this.secondName).concat(" ")
                    .concat(this.firstSurname).concat(" ")
                    .concat(this.secondSurname);
        }
    }

}
