package com.project.coronavirustracker.dao.impl;

import com.project.coronavirustracker.dao.ITrackerDao;
import com.project.coronavirustracker.model.Tracker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TrackerDaoImpl implements ITrackerDao {

    //corona virus tracker from github
    private static String CSV_LOCATION = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    List<Tracker> result = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *") //every minute refresh the results
    public void getData() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity restResponse = restTemplate.getForEntity(CSV_LOCATION, String.class);

        StringReader in = new StringReader(String.valueOf(restResponse.getBody()));
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        List<Tracker> trackerList = new ArrayList<>();
        for (CSVRecord record : records) {
            Tracker tracker = new Tracker(record.get("Province/State"),
                    record.get("Country/Region"),
                    Integer.valueOf(record.get(record.size() - 1)));
            trackerList.add(tracker);
        }

        setResult(trackerList);
        System.out.println(" --- updated resultant list ---");


        /*Flux<String> result = WebClient.create()
                .get()
                .uri(CSV_LOCATION)
                .retrieve()
                .bodyToFlux(String.class);
                */
    }

    public List<Tracker> getResult() {
        return result;
    }

    public void setResult(List<Tracker> result) {
        this.result = result;
    }
}
