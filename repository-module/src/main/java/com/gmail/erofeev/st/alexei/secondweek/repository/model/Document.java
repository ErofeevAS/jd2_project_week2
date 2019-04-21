package com.gmail.erofeev.st.alexei.secondweek.repository.model;

public class Document {
    private Long id;
    private String uniqueNumber;
    private String description;

    public Document() {
    }

    public Document(String uniqueNumber, String description) {
        this.uniqueNumber = uniqueNumber;
        this.description = description;
    }

    public Document(Long id, String uniqueNumber, String description) {
        this.id = id;
        this.uniqueNumber = uniqueNumber;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
