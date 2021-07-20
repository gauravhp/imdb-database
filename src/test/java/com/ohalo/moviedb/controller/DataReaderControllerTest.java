package com.ohalo.moviedb.controller;

import com.ohalo.moviedb.fetch.model.SeriesSeasonEpisodes;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Gaurav Parmar
 * Date: 19-07-2021
 * Time: 15:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataReaderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenSeriesName_WhenQueried_respondWithNumberOfSeasons() throws Exception{
        ResponseEntity<Integer> response = restTemplate.getForEntity("/series/no-of-episodes/Game of Thrones", Integer.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(8);
    }

    @Test
    public void givenSeriesNameAndSeasonNumber_WhenQueried_respondWithEpisodeName() throws Exception{
        ResponseEntity<List> response = restTemplate.getForEntity("/series/episodes-for-season?series=Dark&season=2", List.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().size()).isEqualTo(8);
        LinkedHashMap hashMap =(LinkedHashMap) response.getBody().get(2);
        Assertions.assertThat(hashMap.get("episodeName")).isEqualTo("Ghosts");
    }

    @Test
    public void addingFavoriteEpisodeTest() throws InterruptedException {
        SeriesSeasonEpisodes seriesSeasonEpisodes = new SeriesSeasonEpisodes("Dark","2","Ghosts",true);
        ResponseEntity response = restTemplate.postForEntity("/write/add-favorite", seriesSeasonEpisodes, SeriesSeasonEpisodes.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResponseEntity<Boolean> getResponse = restTemplate.postForEntity("/series/episodes-is-favorite",seriesSeasonEpisodes,Boolean.class);
        Assertions.assertThat((Boolean) getResponse.getBody()).isTrue();
    }

    @Test
    public void removingFavoriteEpisodeTest() {
        SeriesSeasonEpisodes seriesSeasonEpisodes = new SeriesSeasonEpisodes("Dark","2","Ghosts",false);
        ResponseEntity response = restTemplate.postForEntity("/write/remove-favorite", seriesSeasonEpisodes, SeriesSeasonEpisodes.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResponseEntity<Boolean> getResponse = restTemplate.postForEntity("/series/episodes-is-favorite",seriesSeasonEpisodes,Boolean.class);
        Assertions.assertThat((Boolean) getResponse.getBody()).isFalse();
    }

    @Test
    public void getListOfAllTheFavoriteEpisodesTest(){
        SeriesSeasonEpisodes seriesSeasonEpisodes = new SeriesSeasonEpisodes("Dark","2","Ghosts",true);
        ResponseEntity response = restTemplate.postForEntity("/write/add-favorite", seriesSeasonEpisodes, SeriesSeasonEpisodes.class);
        ResponseEntity<SeriesSeasonEpisodes[]> respArray = restTemplate.getForEntity("/series/get-all-favorite", SeriesSeasonEpisodes[].class);
        List<SeriesSeasonEpisodes> respList = Arrays.asList(respArray.getBody());
        for(SeriesSeasonEpisodes s : respList){
            System.out.println(s.getEpisodeName());
        }
        Assertions.assertThat(respArray.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(respList.size()).isGreaterThan(0);
    }
}
