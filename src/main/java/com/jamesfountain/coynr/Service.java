package com.jamesfountain.coynr;

import java.util.ArrayList;
import java.util.List;

class Service {

    private List<int[]> validCoinComboList = new ArrayList<>();

    List<int[]> getValidCombos() {
        return validCoinComboList;
    }

    int findCombos(int ci, int remaining, int[] currencies, String paid) {
        // recursive magic!

        // paid is a valid combo
        if (remaining == 0) {
            // add paid to a list of valid combos
            int[] validCoinCombo = stringArrayToIntArray(paid);
            validCoinComboList.add(validCoinCombo);
            //'paid' is a valid combo
            return 1;
        }

        // combo is over the target value - paid is not a valid combo
        if (remaining < 0) {
            return 0;
        }

        int comboCount = 0;
        // start a loop over coins starting with the last one so we dont have any repeat combos
        for (int i = ci; i < currencies.length; i++) {

            String validCombo = paid + currencies[i] + ",";

            //make a list here and add the return
            comboCount += findCombos(i, remaining - currencies[i], currencies, validCombo);
        }

        return comboCount;

    }

    static int[] stringArrayToIntArray(String paid) {
        String[] split = paid.split(",");

        int[] validComboCoins = new int[split.length];
        int i = 0;
        for (String s : split) {
            i++;
            int parsed = Integer.parseInt(s);
            validComboCoins[i - 1] = parsed;
        }
        return validComboCoins;
    }
}
