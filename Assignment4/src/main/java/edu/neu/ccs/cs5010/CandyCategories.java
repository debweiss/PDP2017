package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;


/**
 * Candy Categories -- Maintains the lists of all the candy categories and where each candy resides
 */
public class CandyCategories {

    private List<String> candyNameList = new ArrayList<>(); // overall list of candy names
    private List<String> duplexCandyNameList = new ArrayList<>(); // candy that can be found in a duplex
    private List<String> mansionCandyNameList = new ArrayList<>(); // candy that can be found in a mansion
    private List<String> detachedHouseCandyNameList = new ArrayList<>(); // candy that can be found in a detached house
    private List<String> townhomeCandyNameList = new ArrayList<>(); // candy that can be found in a townhome

    List<String> getCandyNameList() {

        return this.candyNameList;
    }

    List<String> getMansionCandyNameList() {

        return this.mansionCandyNameList;
    }

    public void setMansionCandyNameList(List<String> mansionCandyNameList) {

        this.mansionCandyNameList = mansionCandyNameList;
    }

    /**
     * Creates a list of the candies that belong in Mansions
     */
    void buildMansionCandyNameList() {

        for (MansionCandyCategory mansionCandyCategory :
            MansionCandyCategory.values()) {

            this.mansionCandyNameList.add(mansionCandyCategory.getValue());

        }
    }

    List<String> getDuplexCandyNameList() {

        return this.duplexCandyNameList;
    }

    public void setDuplexCandyNameList(List<String> duplexCandyNameList) {

        this.duplexCandyNameList = duplexCandyNameList;
    }

    /**
     * Creates a list of the candies that belong in Duplexes
     */
    void buildDuplexCandyNameList() {

        for (CandyCategories.DuplexCandyCategory duplexCandyCategory : CandyCategories.DuplexCandyCategory.values()) {

            this.duplexCandyNameList.add(duplexCandyCategory.getValue());

        }
    }

    List<String> getDetachedHouseCandyNameList() {

        return this.detachedHouseCandyNameList;
    }

    public void setDetachedHouseCandyNameList(List<String> detachedHouseCandyNameList) {

        this.detachedHouseCandyNameList = detachedHouseCandyNameList;
    }


    /**
     * Creates a list of the candies that belong in Detached Houses
     */
    void buildDetachedHouseCandyNameList() {

        for (CandyCategories.DetachedHouseCandyCategory detachedHouseCandyCategory : CandyCategories
                                                                                         .DetachedHouseCandyCategory
                                                                                         .values()) {

            this.detachedHouseCandyNameList.add(detachedHouseCandyCategory.getValue());

        }
    }

    List<String> getTownhomeCandyNameList() {

        return this.townhomeCandyNameList;
    }

    public void setTownhomeCandyNameList(List<String> townhomeCandyNameList) {

        this.townhomeCandyNameList = townhomeCandyNameList;
    }

    /**
     * Creates a list of the candies that belong in Townhouses
     */
    void buildTownhomeCandyNameList() {

        for (CandyCategories.TownhomeCandyCategory townhomeCandyCategory : CandyCategories
                                                                               .TownhomeCandyCategory.values()) {

            this.townhomeCandyNameList.add(townhomeCandyCategory.getValue());

        }
    }

    /******* Neighborhood Candies *******/

    public enum CandyCategory {

        TWIX("twix"),
        SNICKERS("snickers"),
        MARS("mars"),
        KIT_KAT("kit kat"),
        WHOPPERS("whoppers"),
        MILKY_WAY("milky way"),
        TOBLERONE("toblerone"),
        CRUNCH("crunch"),
        BABY_RUTH("baby ruth"),
        ALMOND_JOY("almond joy");

        private String category;

        CandyCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }

    }

    /******* Mansion Candies *******/

    private enum MansionCandyCategory {

        TWIX("twix"),
        SNICKERS("snickers"),
        MARS("mars"),
        KIT_KAT("kit kat"),
        WHOPPERS("whoppers"),
        CRUNCH("crunch"),
        TOBLERONE("toblerone"),
        BABY_RUTH("baby ruth"),
        ALMOND_JOY("almond joy");

        private String category;

        MansionCandyCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }

    }

    /******* Duplex Candies *******/

    private enum DuplexCandyCategory {

        TOBLERONE("toblerone"),
        BABY_RUTH("baby ruth"),
        ALMOND_JOY("almond joy"),
        TWIX("twix"),
        SNICKERS("snickers"),
        MARS("mars"),
        KIT_KAT("kit kat"),
        WHOPPERS("whoppers"),
        MILKY_WAY("milky way"),
        CRUNCH("crunch");


        private String category;

        DuplexCandyCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }
    }

    /******* Detached House Candies *******/

    public enum DetachedHouseCandyCategory {

        KIT_KAT("kit kat"),
        WHOPPERS("whoppers"),
        MILKY_WAY("milky way"),
        CRUNCH("crunch"),
        TOBLERONE("toblerone"),
        TWIX("twix"),
        SNICKERS("snickers"),
        MARS("mars");


        private String category;


        DetachedHouseCandyCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }
    }

    /******* TownHome Candies *******/

    public enum TownhomeCandyCategory {

        KIT_KAT("kit kat"),
        WHOPPERS("whoppers"),
        BABY_RUTH("baby ruth"),
        ALMOND_JOY("almond joy"),
        TOBLERONE("toblerone"),
        TWIX("twix"),
        SNICKERS("snickers"),
        MARS("mars");


        private String category;

        TownhomeCandyCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }

    }
}