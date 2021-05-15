package com.codemaster.project.controller;

import com.codemaster.project.Properties;
import com.codemaster.project.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = Properties.API_VERSION + "/echo",
        method = {RequestMethod.GET})
public class EchoController {

    @Autowired
    private EchoService echoService;

    @GetMapping("/{queryText}")
    public ResponseEntity<String> echoGet(@PathVariable("queryText") String queryText){
        return new ResponseEntity<String>(echoService.echo(queryText), HttpStatus.OK);
    }

}
