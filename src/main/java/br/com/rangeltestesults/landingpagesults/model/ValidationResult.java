package br.com.rangeltestesults.landingpagesults.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class ValidationResult {
    private boolean success;
    private String message;
    private String invalidField;
}
