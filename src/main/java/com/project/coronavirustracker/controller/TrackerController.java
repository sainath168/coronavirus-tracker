package com.project.coronavirustracker.controller;

import com.project.coronavirustracker.model.Tracker;
import com.project.coronavirustracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class TrackerController {

    @Autowired
    TrackerService trackerService;

    @RequestMapping("/")
    public String getCoronaTrackingData(Model model) {
        List<Tracker> trackerList = trackerService.getResult();
        model.addAttribute("reports", trackerList);
        return "home";
    }

    @RequestMapping("/reports")
    public String sortData(@RequestParam boolean sortAsc, Model model) {
        List<Tracker> trackerList = trackerService.getResult();
        if (sortAsc) {
            trackerList.sort((o1, o2) -> {
                return o1.getCurrentTotal().compareTo(o2.getCurrentTotal());
            });
        } else {
            trackerList.sort((o1, o2) -> {
                return o2.getCurrentTotal().compareTo(o1.getCurrentTotal());
            });
        }
        model.addAttribute("reports", trackerList);
        return "home";
    }

    @RequestMapping("/references")
    public String getReferenceData(Model model) {
        return "references";
    }

}
