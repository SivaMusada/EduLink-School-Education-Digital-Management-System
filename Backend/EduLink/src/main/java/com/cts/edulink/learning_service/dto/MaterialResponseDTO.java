package com.cts.edulink.learning_service.dto;

import lombok.Data;

@Data
public class MaterialResponseDTO {
    private Long materialId;
    private String title;
    private String downloadUrl;
    private String status;
}