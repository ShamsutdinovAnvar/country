package guru.qa.country.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;

import java.util.Date;

public record Country(@JsonProperty("country_name")
                      String countryName,
                      @JsonProperty("country_code")
                      String countryCode,
                      Date independencyDate) {

    public static Country fromEntity(CountryEntity ce) {
        return new Country(
                ce.getCountryName(),
                ce.getCountryCode(),
                new Date()
        );
    }

    public static CountryEntity toEntity(Country country) {
        CountryEntity ce = new CountryEntity();
        ce.setCountryName(country.countryName);
        ce.setCountryCode(country.countryCode);

        return ce;
    }
}