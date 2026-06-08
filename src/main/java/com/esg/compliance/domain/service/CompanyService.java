package com.esg.compliance.domain.service;

import com.esg.compliance.domain.model.Company;
import com.esg.compliance.domain.repository.CompanyRepository;
import com.esg.compliance.exception.BusinessException;
import com.esg.compliance.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Transactional
    public Company create(String name, String cnpj) {
        if(companyRepository.existsByCnpj(cnpj)) {
            throw new BusinessException("Company already exists with CNPJ: " +  cnpj);
        }

        Company created = Company.create(name, cnpj);
        return companyRepository.save(created);
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Company not found with ID: " + id)
        );
    }

    public List<Company> findAll() { return companyRepository.findAll(); }

    @Transactional
    public Company updateName(Long id, String name) {
        Company company = findById(id);
        company.updateName(name);

        return companyRepository.save(company);
    }

    @Transactional
    public void delete(Long id) {
        Company company = findById(id);
        companyRepository.delete(company);
    }
}