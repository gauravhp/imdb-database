package com.ohalo.moviedb.fetch.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: Gaurav Parmar
 * Date: 20-07-2021
 * Time: 09:33
 */
@Data
@Entity
@Table(name = "FAVORITE_EPISODES")
public class SeriesSeasonEpisodes {
    @Id
    private Long id;
    private String webseriesName;
    private String season;
    private String episodeName;
    private Boolean isFavorite=false;

    public SeriesSeasonEpisodes(String webseriesName, String season, String episode, boolean b) {
        this.webseriesName=webseriesName;
        this.season=season;
        this.episodeName=episode;
        this.isFavorite=b;
    }
}
