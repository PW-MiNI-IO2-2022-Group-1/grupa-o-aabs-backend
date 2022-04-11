package com.example.SOPSbackend.repository.filters;

import com.example.SOPSbackend.model.DoctorEntity;
import com.example.SOPSbackend.model.converter.VaccinationSlotStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ReportRepository {
        @PersistenceContext
        private EntityManager entityManager;

        public ReportRepository() {
        }

        public Page<VaccinationSlotStatus> find(DoctorEntity doctor, String onlyReserved, String startDate, String endDate, Pageable pageable) {
            String myQuery = "SELECT vs.id as id, vs.date as date, v as vaccination FROM VaccinationSlotEntity vs , VaccinationEntity v WHERE vs.doctor = :doctor ";
            String countQuery = "SELECT COUNT(*) FROM VaccinationSlotEntity vs , VaccinationEntity v WHERE vs.doctor = :doctor ";
            String queryFrom;
            String additionalParams = "";
            switch (onlyReserved){
                case "1":
                    queryFrom = "AND v.vaccinationSlot = vs ";
                    break;
                case "0":
                    queryFrom = "AND v.id IS NULL ";
                    break;
                default:
                    myQuery = "SELECT vs.id as id, vs.date as date, v as vaccination FROM VaccinationSlotEntity vs LEFT JOIN VaccinationEntity v ON v.vaccinationSlot = vs WHERE vs.doctor = :doctor ";
                    countQuery = "SELECT COUNT(*) FROM VaccinationSlotEntity vs LEFT JOIN VaccinationEntity v ON v.vaccinationSlot = vs WHERE vs.doctor = :doctor ";
                    queryFrom = "";
            }
            if(startDate != null) {
                additionalParams = "AND vs.date >= :startDate ";
            }
            if(endDate != null) {
                additionalParams += "AND vs.date <= :endDate ";
            }
            Query query = entityManager
                    .createQuery(
                            myQuery  + queryFrom + additionalParams);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            query.setParameter("doctor", doctor);
            int pageNumber =pageable.getPageNumber();
            int pageSize = pageable.getPageSize();
            query.setFirstResult((pageNumber) * pageSize);
            query.setMaxResults(pageSize);

            List<VaccinationSlotStatus> resultList;
            resultList = (List<VaccinationSlotStatus>) query.getResultList();
            Query queryTotal = entityManager.createQuery
                    (countQuery + queryFrom);
            queryTotal.setParameter("doctor", doctor);
            long countResult = (long)queryTotal.getSingleResult();
            int i=(int)countResult;
            return new PageImpl<>(resultList, pageable, i);
        }
}
