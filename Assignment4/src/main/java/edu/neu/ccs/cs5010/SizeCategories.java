package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public class SizeCategories {

    private List<String> candySizeList = new ArrayList<>();
    private List<String> mansionCandySizeList = new ArrayList<>();
    private List<String> duplexCandySizeList = new ArrayList<>();
    private List<String> detachedHouseCandySizeList = new ArrayList<>();
    private List<String> townhomeCandySizeList = new ArrayList<>();

    SizeCategories() {

        for (SizeCategories.SizeCategory c : SizeCategories.SizeCategory.values()) {

            this.candySizeList.add(c.getValue());
        }

    }

    public List<String> getCandySizeList() {

        return this.candySizeList;
    }

    List<String> getMansionCandySizeList() {

        return this.mansionCandySizeList;
    }

    public void setMansionCandySizeList(List<String> mansionCandySizeList) {

        this.mansionCandySizeList = mansionCandySizeList;
    }

    void buildMansionCandySizeList() {

        for (MansionSizeCategory mansionSizeCategory : MansionSizeCategory.values()) {

            this.mansionCandySizeList.add(mansionSizeCategory.getValue());

        }
    }

    List<String> getDuplexCandySizeList() {

        return duplexCandySizeList;
    }

    public void setDuplexCandySizeList(List<String> duplexCandySizeList) {

        this.duplexCandySizeList = duplexCandySizeList;
    }

    void buildDuplexCandySizeList() {

        for (DuplexSizeCategory duplexSizeCategory : DuplexSizeCategory.values()) {

            this.duplexCandySizeList.add(duplexSizeCategory.getValue());

        }
    }

    List<String> getDetachedHouseCandySizeList() {

        return detachedHouseCandySizeList;
    }

    public void setDetachedHouseCandySizeList(List<String> detachedHouseCandySizeList) {

        this.detachedHouseCandySizeList = detachedHouseCandySizeList;
    }

    void buildDetachedHouseCandySizeList() {

        for (DetachedHouseSizeCategory dhSizeCategory : DetachedHouseSizeCategory.values()) {

            this.detachedHouseCandySizeList.add(dhSizeCategory.getValue());

        }
    }

    List<String> getTownhomeCandySizeList() {

        return townhomeCandySizeList;
    }

    public void setTownhomeCandySizeList(List<String> townhomeCandySizeList) {

        this.townhomeCandySizeList = townhomeCandySizeList;
    }

    void buildTownhomeCandySizeList() {

        for (TownhomeSizeCategory thSizeCategory : TownhomeSizeCategory.values()) {

            this.townhomeCandySizeList.add(thSizeCategory.getValue());

        }
    }

    public enum SizeCategory {

        REGULAR_SIZE("regular size"),
        FUN_SIZE("fun size"),
        SUPER_SIZE("super size"),
        KING_SIZE("king size");

        private String category;

        SizeCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;
        }

    }


    private enum MansionSizeCategory {

        TWIX_SIZE("super size"),
        SNICKERS_SIZE("super size"),
        MARS_SIZE("super size"),
        KIT_KAT_SIZE("king size"),
        WHOPPERS_SIZE("king size"),
        CRUNCH_SIZE("king size"),
        TOBLERONE_SIZE("fun size"),
        BABY_RUTH_SIZE("fun size"),
        ALMOND_JOY("fun size");

        private String category;

        MansionSizeCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }
    }

    private enum DuplexSizeCategory {

        TOBLERONE_SIZE("super size"),
        BABY_RUTH_SIZE("super size"),
        ALMOND_JOY_SIZE("super size"),
        TWIX_SIZE("king size"),
        SNICKERS_SIZE("king size"),
        MARS_SIZE("king size"),
        KIT_KAT_SIZE("fun size"),
        WHOPPERS_SIZE("fun size"),
        MILKY_WAY_SIZE("fun size"),
        CRUNCH_SIZE("fun size");


        private String category;


        DuplexSizeCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }
    }

    public enum DetachedHouseSizeCategory {

        KIT_KAT_SIZE("super size"),
        WHOPPERS_SIZE("super size"),
        MILKY_WAY_SIZE("super size"),
        CRUNCH_SIZE("super size"),
        TOBLERONE_SIZE("king size"),
        TWIX_SIZE("fun size"),
        SNICKERS_SIZE("fun size"),
        MARS_SIZE("fun size");


        private String category;


        DetachedHouseSizeCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }
    }

    public enum TownhomeSizeCategory {

        KIT_KAT("regular size"),
        WHOPPERS("regular size"),
        BABY_RUTH("regular size"),
        ALMOND_JOY("regular size"),
        TOBLERONE("regular size"),
        TWIX("regular size"),
        SNICKERS("regular size"),
        MARS("regular size");

        private String category;

        TownhomeSizeCategory(String category) {

            this.category = category;

        }

        public String getValue() {

            return category;

        }
    }

}


