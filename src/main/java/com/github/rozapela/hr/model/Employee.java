package com.github.rozapela.hr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ФИО не должно быть пустым")
    @Column(nullable = false)
    private String fullName;

    @Email(message = "Некорректный формат электронной почты")
    @NotBlank(message = "Почта не должна быть пустой")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^(\\+7|8)?\\d{10}$", message = "Телефон должен быть в формате +7XXXXXXXXXX или 8XXXXXXXXXX")
    @NotBlank(message = "Телефон не должен быть пустым")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Позиция не должна быть пустой")
    @Column(nullable = false)
    private String position;

    @DecimalMin(value = "0.01", message = "Зарплата должна быть больше 0")
    @NotNull(message = "Зарплата не должна быть пустой")
    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private Boolean active = true;
}