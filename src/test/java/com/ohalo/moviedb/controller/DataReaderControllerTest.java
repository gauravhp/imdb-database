package com.ohalo.moviedb.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

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
        ResponseEntity<List> response = restTemplate.getForEntity("/series/episodes-for-season?series=Game of Thrones&season=1", List.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().size()).isEqualTo(10);
        String episode1 = (String) response.getBody().get(0);
        Assertions.assertThat(episode1).isEqualTo("Winter Is Coming");
    }

}
