package com.example.miniprojet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class User {
    private int id;
    private String name;
    private String email;
    private String bloodType;
    private String location;
    private String lastDonation;

    public User(String name, String email, String bloodType, String location, String lastDonation) {
        this.name = name;
        this.email = email;
        this.bloodType = bloodType;
        this.location = location;
        this.lastDonation = lastDonation;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBloodType() { return bloodType; }
    public String getLocation() { return location; }
    public String getLastDonation() { return lastDonation; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public void setLocation(String location) { this.location = location; }
    public void setLastDonation(String lastDonation) { this.lastDonation = lastDonation; }
    public boolean isEligibleToDonate() {
        if (lastDonation == null) return true; // Aucune date enregistrée, donc éligible
        try {
            // Convertir la chaîne de caractères en LocalDate
            LocalDate lastDonationDate = LocalDate.parse(lastDonation);
            // Vérifier la différence en jours
            return ChronoUnit.DAYS.between(lastDonationDate, LocalDate.now()) >= 90;
        } catch (Exception e) {
            // Gestion des exceptions (par ex. si le format de la date est invalide)
            System.err.println("Erreur de conversion de lastDonation en LocalDate : " + e.getMessage());
            return false; // Considérer comme non éligible en cas d'erreur
        }
    }

}