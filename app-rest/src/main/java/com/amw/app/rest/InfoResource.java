package com.amw.app.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("info")
public class InfoResource {

    @RequestMapping(value = "version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVersion () {
        return "";
    }

}
