package com.com.github.lucasbandeira.msagent.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HeroResponseDTO {

    private String heroCode;
    private String name;
    private List <String> skills;
    private Integer age;
    private List<String> characteristics;
}
