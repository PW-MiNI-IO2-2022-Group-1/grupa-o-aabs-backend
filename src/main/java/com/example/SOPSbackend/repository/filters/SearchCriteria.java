package com.example.SOPSbackend.repository.filters;

import com.example.SOPSbackend.model.VaccinationSlotEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(String key, String operation, String value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public String getOperation() {
        return operation;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}

