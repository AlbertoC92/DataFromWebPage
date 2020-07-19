package com.core.codeChallengeAlbertoC92.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    private String title;
    private String link;
    private String description;
    private String pubDate;
}