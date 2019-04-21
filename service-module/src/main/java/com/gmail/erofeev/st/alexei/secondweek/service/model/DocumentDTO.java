package com.gmail.erofeev.st.alexei.secondweek.service.model;

public class DocumentDTO {
    private Long id;
    private String uniqueNumber;
    private String description;

    public DocumentDTO() {
    }

    public DocumentDTO(String description) {
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
        return "DocumentDTO{" +
                "id=" + id +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
