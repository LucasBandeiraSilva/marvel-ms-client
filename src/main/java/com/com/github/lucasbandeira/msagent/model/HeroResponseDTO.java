package com.com.github.lucasbandeira.msagent.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HeroResponseDTO {

    private String heroCode;
    private String name;
    private List <String> skills;
    private Integer age;
    private List<String> characteristics;
}
