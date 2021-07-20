package com.ohalo.moviedb.repo;

import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Gaurav Parmar
 * Date: 20-07-2021
 * Time: 10:04
 */
@Repository
public interface SeriesSeasonEpisodeRepo extends JpaRepository<SeriesSeasonEpisodes, Long> {
    SeriesSeasonEpisodes findByEpisodeName(String episodeName);
}
