package com.core.codeChallengeAlbertoC92.test.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class DbItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "enclosure")
    private String enclosure;

    @Column(name = "pub_date")
    private String pubDate;

    @Column(name = "guid")
    private String guid;

    @Column(name = "feed_burner")
    private String feedBurner;

}
