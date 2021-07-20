package com.ohalo.moviedb.repo;

import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Gaurav Parmar
 * Date: 20-07-2021
 * Time: 10:04
 */
@Repository
public interface SeriesSeasonEpisodeRepo extends JpaRepository<SeriesSeasonEpisodes, Long> {
    SeriesSeasonEpisodes findByEpisodeName(String episodeName);
    @Query(value = "select s.episodeName from SeriesSeasonEpisodes s where s.webseriesName=:seriesName and s.season=:season")
    List<String> findBySeriesWebseriesNameAndSeason(@Param("seriesName") String seriesName, @Param("season")  String seasonNumber);

    @Query(value = "select s from SeriesSeasonEpisodes s where s.isFavorite=true")
    List<SeriesSeasonEpisodes> findAllFavorite();
}
