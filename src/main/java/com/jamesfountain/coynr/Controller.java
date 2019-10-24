package com.jamesfountain.coynr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
public class Controller {

    @GetMapping("/change/{target}")
    ArrayList<String> change(@RequestParam(value = "coins") String coins, @PathVariable("target") String target)  {

        log.info("Target = " + target);
        log.info("coins = " + coins);

        ArrayList<String> list = new ArrayList<String>();
        list.add(target);
        list.add(coins);

        String[] split = coins.split(",");
        int[] coinInts = new int[coins.length()];
        int i = 0;
        for (String s : split) {
            i++;
            int parsed = Integer.parseInt(s);
            coinInts[i] = parsed;
        }
        log.info(String.valueOf(coinInts));
        // validation on coins -  must have more than 0 integers

        // split coins and read into HASHMAP? or SET?


        // Calculate all permutations of coins that fit into target

        // Formatting? Can I format response as json or does it have to be in that horrible string format?

        return list;
    }


}
