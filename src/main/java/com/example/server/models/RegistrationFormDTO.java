package com.example.server.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonPropertyOrder({"email", "password", "nickname", "firstName", "lastName", "sex", "city", "age"})
public class RegistrationFormDTO {

    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String nickname;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String sex;
    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private int age;

}
