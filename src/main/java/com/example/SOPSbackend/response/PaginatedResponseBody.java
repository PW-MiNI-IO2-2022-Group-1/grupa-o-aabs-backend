package com.example.SOPSbackend.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.stream.Stream;

@Getter
public class PaginatedResponseBody<T> {
    private final Pagination pagination;
    private final Stream<T> data;

    public PaginatedResponseBody(Page<T> page) {
        pagination = new Pagination(page);
        data = page.stream();
    }

    @Getter
    private class Pagination {
        private final int currentPage;
        private final int totalPages;
        private final long currentRecords;
        private final long totalRecords;

        public Pagination(Page<T> page) {
            this.currentPage = page.getNumber() + 1;
            this.totalPages = page.getTotalPages();
            this.currentRecords = page.stream().count();
            this.totalRecords = page.getTotalElements();
        }
    }
}
