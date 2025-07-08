package br.com.starter.application.api.common;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    String country;
    String city;
    String state;
}