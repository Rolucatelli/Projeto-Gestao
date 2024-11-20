package com.example.bluevelvetmusicstore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ImageDetailsVO {
    private Long id;

    private String url;

    private Boolean isPrincipal;
}
