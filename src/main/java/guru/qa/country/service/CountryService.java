package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static guru.qa.country.model.Country.toEntity;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(Country::fromEntity)
                .toList();
    }

    public CountryEntity addCountry(Country country) {
        return countryRepository.save(toEntity(country));
    }

    public CountryEntity updateCountryName(String countryCode, Country country) {

        CountryEntity countryEntity = getCountryByCode(countryCode);

        if (countryEntity != null) {
            countryEntity.setCountryName(country.countryName());
        } else {
            throw new IllegalArgumentException("Country with code: '" + countryCode + "' is not found");
        }

        return countryRepository.save(countryEntity);
    }

    public CountryEntity getCountryByCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }
}