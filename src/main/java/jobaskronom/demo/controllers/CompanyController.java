package jobaskronom.demo.controllers;

//alt enter на неисползуемые импорты

import jakarta.validation.Valid;
import jobaskronom.demo.DTO.CompanyRequest;
import jobaskronom.demo.models.Company;
import jobaskronom.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<Company> addCompany(@RequestBody @Valid CompanyRequest companyRequest){
        return new ResponseEntity(companyService.saveNewCompanyFromCompanyRequest(companyRequest), HttpStatus.CREATED);
    }
}
