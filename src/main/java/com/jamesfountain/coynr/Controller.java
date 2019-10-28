package com.jamesfountain.coynr;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
public class Controller {

    //TODO write tests for controller and service
    @GetMapping(
            value = "/change/{target}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity change(
            @RequestParam(value = "coins") String coins,
            @PathVariable("target") @NotBlank String target)
    {
        //Validate input
        if (coins.length() < 1)
            return new ResponseEntity<>("Supply one or more coins! eg /change/10?coins=1,2,10,5", HttpStatus.NOT_ACCEPTABLE);

        int[] coinInts = Service.stringArrayToIntArray(coins);

        // Calculate all permutations of coins that fit into target
        Service service = new Service();
        service.findCombos(0, Integer.parseInt(target), coinInts, "");

        //TODO format responses into json properly
        return new ResponseEntity<>(new Gson().toJson(service.getValidCombos()), HttpStatus.OK);
    }

}
