package com.ohalo.moviedb.save;

import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import com.ohalo.moviedb.fetch.service.SeriesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Gaurav Parmar
 * Date: 20-07-2021
 * Time: 09:50
 */
@RestController
@RequestMapping("/write")
public class DataSaveController {
    @Autowired
    SeriesDataService seriesDataService;

    @PostMapping(value = "/add-favorite")
    public void addFavoriteEpisode(@RequestBody SeriesSeasonEpisodes seriesSeasonEpisodes){
        seriesSeasonEpisodes.setIsFavorite(true);
        seriesDataService.saveFavoriteEpisode(seriesSeasonEpisodes);
    }

    @PostMapping(value = "/remove-favorite")
    public void saveFavoriteEpisode(@RequestBody SeriesSeasonEpisodes seriesSeasonEpisodes){
        seriesSeasonEpisodes.setIsFavorite(false);
        seriesDataService.saveFavoriteEpisode(seriesSeasonEpisodes);
    }
}
