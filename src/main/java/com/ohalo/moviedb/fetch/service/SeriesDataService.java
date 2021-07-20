package com.ohalo.moviedb.fetch.service;

import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * User: Gaurav Parmar
 * Date: 19-07-2021
 * Time: 17:33
 */
public interface SeriesDataService {
    public Integer getNumberOfEpisodesForSeries(String seriesName);
    public List<SeriesSeasonEpisodes> getEpisodeDataListForSeriesAndSeason(String seriesName, String seasonNumber);
    public void saveFavoriteEpisode(SeriesSeasonEpisodes seriesSeasonEpisodes);
    public Boolean getEpisodeIsFavorite(SeriesSeasonEpisodes seriesSeasonEpisodes);
}
