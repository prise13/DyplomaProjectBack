package com.kinosearch.kinosearchua.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDTO {

    private Integer movieRate;

    private String reviewBody;

}
