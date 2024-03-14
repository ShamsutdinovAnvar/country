package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static guru.qa.country.model.Country.fromEntity;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{countryCode}")
    public Country getCountryByCode(@PathVariable String countryCode) {
        return fromEntity(countryService.getCountryByCode(countryCode));
    }

    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country) {
        return fromEntity(countryService.addCountry(country));
    }

    @PatchMapping("/countries/{countryCode}")
    public Country updateCountryName(@PathVariable String countryCode, @RequestBody Country country) {
        return fromEntity(countryService.updateCountryName(countryCode, country));
    }
}