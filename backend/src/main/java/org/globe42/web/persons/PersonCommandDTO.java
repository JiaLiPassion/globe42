package org.globe42.web.persons;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.globe42.domain.FiscalStatus;
import org.globe42.domain.Gender;
import org.globe42.domain.Housing;
import org.globe42.domain.MaritalStatus;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Command sent to create or update a person
 * @author JB Nizet
 */
public final class PersonCommandDTO {
    private final String firstName;

    @NotEmpty
    private final String lastName;
    private final String nickName;

    private final LocalDate birthDate;
    private final String address;

    @Valid
    private final CityDTO city;
    private final String email;
    private final boolean adherent;
    private final LocalDate entryDate;

    @NotNull
    private final Gender gender;
    private final String phoneNumber;
    private final MaritalStatus maritalStatus;

    private final Housing housing;
    private final Integer housingSpace;
    private final FiscalStatus fiscalStatus;
    private final LocalDate fiscalStatusDate;
    private final boolean fiscalStatusUpToDate;
    private final FamilySituationDTO frenchFamilySituation;
    private final FamilySituationDTO abroadFamilySituation;


    @JsonCreator
    public PersonCommandDTO(@JsonProperty String firstName,
                            @JsonProperty String lastName,
                            @JsonProperty String nickName,
                            @JsonProperty LocalDate birthDate,
                            @JsonProperty String address,
                            @JsonProperty CityDTO city,
                            @JsonProperty String email,
                            @JsonProperty boolean adherent,
                            @JsonProperty LocalDate entryDate,
                            @JsonProperty Gender gender,
                            @JsonProperty String phoneNumber,
                            @JsonProperty MaritalStatus maritalStatus,
                            @JsonProperty Housing housing,
                            @JsonProperty Integer housingSpace,
                            @JsonProperty FiscalStatus fiscalStatus,
                            @JsonProperty LocalDate fiscalStatusDate,
                            @JsonProperty boolean fiscalStatusUpToDate,
                            @JsonProperty FamilySituationDTO frenchFamilySituation,
                            @JsonProperty FamilySituationDTO abroadFamilySituation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.email = email;
        this.adherent = adherent;
        this.entryDate = entryDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.maritalStatus = maritalStatus;
        this.housing = housing;
        this.housingSpace = housingSpace;
        this.fiscalStatus = fiscalStatus;
        this.fiscalStatusDate = fiscalStatusDate;
        this.fiscalStatusUpToDate = fiscalStatusUpToDate;
        this.frenchFamilySituation = frenchFamilySituation;
        this.abroadFamilySituation = abroadFamilySituation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public CityDTO getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdherent() {
        return adherent;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public Housing getHousing() {
        return housing;
    }

    public Integer getHousingSpace() {
        return housingSpace;
    }

    public FiscalStatus getFiscalStatus() {
        return fiscalStatus;
    }

    public LocalDate getFiscalStatusDate() {
        return fiscalStatusDate;
    }

    public boolean isFiscalStatusUpToDate() {
        return fiscalStatusUpToDate;
    }

    public FamilySituationDTO getFrenchFamilySituation() {
        return frenchFamilySituation;
    }

    public FamilySituationDTO getAbroadFamilySituation() {
        return abroadFamilySituation;
    }
}
