package com.ohalo.moviedb.fetch.controller;

import com.ohalo.moviedb.fetch.model.Constants;
import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import com.ohalo.moviedb.fetch.service.SeriesDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/series")
public class DataReaderController {
	@Autowired
	SeriesDataService seriesDataService;
	@GetMapping("/no-of-episodes/{seriesName}")
	public Integer getNumberOfEpisodesForSeries(@PathVariable("seriesName") String seriesName)
	{
		return seriesDataService.getNumberOfEpisodesForSeries(seriesName);
	}

	@GetMapping("/episodes-for-season")
	public List<SeriesSeasonEpisodes> getEpisodeDataListForSeriesAndSeason(@RequestParam Map<String, String> seriesSeason)
	{
		return seriesDataService.getEpisodeDataListForSeriesAndSeason(seriesSeason.get("series"),seriesSeason.get("season"));
	}

	@PostMapping("/episodes-is-favorite")
	public Boolean getEpisodeIsFavorite(@RequestBody SeriesSeasonEpisodes seriesSeasonEpisodes){
		return seriesDataService.getEpisodeIsFavorite(seriesSeasonEpisodes);
	}
}
