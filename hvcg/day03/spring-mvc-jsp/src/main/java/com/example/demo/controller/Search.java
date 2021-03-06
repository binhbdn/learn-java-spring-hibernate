package com.example.demo.controller;

import com.example.demo.dto.SearchDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Search {
    private static final List<SearchDTO> db = new ArrayList<>();

    static {
        db.add(new SearchDTO("1","2"));
        db.add(new SearchDTO("2","3"));
    }

    private boolean checkIfExistInDB(SearchDTO searchDTO) {
        return db.stream().anyMatch(o -> o.getFname().equals(searchDTO.getFname())
                && o.getLname().equals(searchDTO.getLname()));
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public String searchUser(SearchDTO searchDTO){
        return String.format("Exist of %s, %s: ", searchDTO.getFname(), searchDTO.getLname())
                + ": " + checkIfExistInDB(searchDTO);
    }

    @PostMapping(value = "/search-json")
    @ResponseBody
    public String searchUserJson(@RequestBody SearchDTO searchDTO){
        return String.format("Exist of %s, %s: ", searchDTO.getFname(), searchDTO.getLname())
                + ": " + checkIfExistInDB(searchDTO);
    }
    /*
    test by Postman:
    POST http://localhost:9090/search-json
    Body - Json:
    {
    "fname": "1",
    "lname": "2"
    }

    Response:
    Exist of 1, 2: : true
    */
}
