package com.example.demo2.controller;

import com.example.demo2.dto.SearchDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class SearchController {
    @GetMapping("/")
    public String fetchIndex() {
        return "index";
    }

    private static final List<SearchDTO> db = new ArrayList<>();

    static {
        db.add(new SearchDTO("1","2"));
        db.add(new SearchDTO("2","3"));
    }

    private boolean checkIfExistInDB(SearchDTO searchDTO) {
        return db.stream().anyMatch(o -> o.getFname().equals(searchDTO.getFname())
                && o.getLname().equals(searchDTO.getLname()));
    }

    private static final Random RD = new Random();

    @PostMapping(value = "/search")
    public ModelAndView search(SearchDTO searchDTO, ModelAndView mav){

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<10; i++){
            sb.append("<tr><td>")
                    .append("https://google.com/" + searchDTO.getFname() + "/" + RD.nextInt(1000))
                    .append("</td><td>").append(RD.nextInt(1000)).append("</td></tr>");
        }

        mav.addObject("answers", sb.toString());
        mav.setViewName("answer");

        return mav;
    }

    private final ObjectMapper OM = new ObjectMapper();

    @RequestMapping(value = "/search-json", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String searchUserJson(@RequestBody String searchDTOString){
        SearchDTO searchDTO;
        try {
            searchDTO = OM.readValue(searchDTOString, SearchDTO.class);
            return String.format("Exist of %s, %s: ", searchDTO.getFname(), searchDTO.getLname())
                    + ": " + checkIfExistInDB(searchDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "problem";
    }
    /*
    test by Postman:
    POST/GET http://localhost:9090/search-json
    Body - Json:
    {
    "fname": "1",
    "lname": "2"
    }

    Response:
    Exist of 1, 2: : true

    -----
    Body - Json:
    {
    "fname": "1",
    "lname": "2"666
    }

    Response:
    problem
    */
}
