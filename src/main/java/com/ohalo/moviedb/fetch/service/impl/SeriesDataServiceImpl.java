package com.ohalo.moviedb.fetch.service.impl;

import com.ohalo.moviedb.fetch.model.Constants;
import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import com.ohalo.moviedb.fetch.service.SeriesDataService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User: Gaurav Parmar
 * Date: 19-07-2021
 * Time: 17:37
 */
@Service
public class SeriesDataServiceImpl implements SeriesDataService {

    private String getUriPrefix() {
        return Constants.URI + Constants.API_KEY;
    }

    @Override
    public Integer getNumberOfEpisodesForSeries(String seriesName) {
        final String uri = getUriPrefix() + "&t=" + seriesName;
        RestTemplate restTemplate = new RestTemplate();
        HashMap result = restTemplate.getForObject(uri, HashMap.class);

        return Integer.parseInt((String) result.get("totalSeasons"));
    }

    @Override
    public List<SeriesSeasonEpisodes> getEpisodeDataListForSeriesAndSeason(String seriesName, String seasonNumber) {
        final String uri = getUriPrefix() + "&t="+seriesName+"&Season="+seasonNumber;
        RestTemplate restTemplate = new RestTemplate();
        HashMap result = restTemplate.getForObject(uri, HashMap.class);
        List<Map<String,String>> episodeData = (List<Map<String, String>>) result.get("Episodes");
        List<String> episodes = episodeData.stream().map(x -> x.get("Title")).collect(Collectors.toList());
        return getEpisodeData(seriesName, seasonNumber, episodes);
    }

    private List<SeriesSeasonEpisodes> getEpisodeData(String seriesName, String seasonNumber, List<String> episodes) {
        List<SeriesSeasonEpisodes> response = new ArrayList<>();
        for(String episode: episodes){
            response.add(new SeriesSeasonEpisodes(seriesName, seasonNumber,episode,false));
        }
        return response;
    }
}
