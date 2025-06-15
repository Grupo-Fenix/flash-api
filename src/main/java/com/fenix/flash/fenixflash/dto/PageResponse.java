package com.fenix.flash.fenixflash.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T>(
        int page,
        int numOfItems,
        long totalItems,
        int totalPages,
        List<T> items,
        boolean hasNext,
        boolean hasPrevious
) {
    public static <T> PageResponse<T> create(Page<T> pageRequest) {
        return new PageResponse<>(pageRequest.getNumber(),
                pageRequest.getNumberOfElements(),
                pageRequest.getTotalElements(),
                pageRequest.getTotalPages(),
                pageRequest.get().toList(),
                pageRequest.hasNext(),
                pageRequest.hasPrevious()
        );
    }
}
