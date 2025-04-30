package com.com.github.lucasbandeira.msagent.model.dto;


import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class HeroRequestDTO{

        private String name;
        private String heroCode;
        private List <String> skills;
        private Integer age;
        private List<String> characteristics;
}
