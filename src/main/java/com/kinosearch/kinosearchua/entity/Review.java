package com.kinosearch.kinosearchua.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "movie_id")
    private Integer movieId;

    private String userNickname;

    private Integer movieRate;

    @Column(name="review_body", length = 1000)
    private String reviewBody;
}
