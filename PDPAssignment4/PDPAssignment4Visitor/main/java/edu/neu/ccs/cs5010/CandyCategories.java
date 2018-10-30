package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;


/**
 * Candy Categories -- Provides access to the lists of all the candy categories
 * and where each candy resides
 */
public class CandyCategories {
    
    // overall list of candy names
    private List<String> candyNameList = new ArrayList<>();
    
    // household-specific candy names
    private List<String> mansionCandyNameList = new ArrayList<>();
    private List<String> duplexCandyNameList = new ArrayList<>();
    private List<String> detachedHouseCandyNameList = new ArrayList<>();
    private List<String> townhomeCandyNameList = new ArrayList<>();
    
    // overall list of candy sizes
    private List<String> candySizeList = new ArrayList<>();
    
    // household-specific candy sizes
    private List<String> mansionCandySizeList = new ArrayList<>();
    private List<String> duplexCandySizeList = new ArrayList<>();
    private List<String> detachedHouseCandySizeList = new ArrayList<>();
    private List<String> townhomeCandySizeList = new ArrayList<>();
    
    
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
    
    /******* Neighborhood Candy sizes *******/
    
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
        ALMOND_JOY_SIZE("fun size");
        
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
    
        KIT_KAT_SIZE("regular size"),
        WHOPPERS_SIZE("regular size"),
        BABY_RUTH_SIZE("regular size"),
        ALMOND_JOY_SIZE("regular size"),
        TOBLERONE_SIZE("regular size"),
        TWIX_SIZE("regular size"),
        SNICKERS_SIZE("regular size"),
        MARS_SIZE("regular size");
    
        private String category;
    
        TownhomeSizeCategory(String category) {
        
            this.category = category;
        
        }
    
        public String getValue() {
        
            return category;
        
        }
    }
    
    List<String> getCandyNameList() {
        
        return this.candyNameList;
    }
    
    public List<String> getCandySizeList() {
        
        return this.candySizeList;
    }
    
    /* Mansion-related functions */
    
    List<String> getMansionCandyNameList() {
        
        return this.mansionCandyNameList;
    }
    
    public void setMansionCandyNameList(List<String>
        mansionCandyNameList) {
        
        this.mansionCandyNameList = mansionCandyNameList;
    }
    
    List<String> getMansionCandySizeList() {
        
        return this.mansionCandySizeList;
    }
    
    public void setMansionCandySizeList(List<String>
        mansionCandySizeList) {
        
        this.mansionCandySizeList = mansionCandySizeList;
    }
    
    /**
     * Creates a list of the candies that belong in Mansions
     */
    void buildMansionCandyNameList() {
        
        for (MansionCandyCategory mansionCandyCategory :
            MansionCandyCategory.values()) {
            
            this.mansionCandyNameList.add(mansionCandyCategory
                .getValue());
            
        }
    }
    /**
     * Creates a list of the candy sizes that belong in Mansions
     */
    void buildMansionCandySizeList() {
        
        for (CandyCategories.MansionSizeCategory mansionSizeCategory :
            CandyCategories.MansionSizeCategory.values()) {
            
            this.mansionCandySizeList.add(mansionSizeCategory
                .getValue());
            
        }
    }
    
    /* Duplex-related functions */
    
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
        
        for (CandyCategories.DuplexCandyCategory duplexCandyCategory :
            CandyCategories.DuplexCandyCategory.values()) {
            
            this.duplexCandyNameList.add(duplexCandyCategory.getValue());
            
        }
    }
    
    List<String> getDuplexCandySizeList() {
        
        return duplexCandySizeList;
    }
    
    public void setDuplexCandySizeList(List<String> duplexCandySizeList) {
        
        this.duplexCandySizeList = duplexCandySizeList;
    }
    
    /**
     * Creates a list of the candy sizes that belong in Duplexes
     */
    
    void buildDuplexCandySizeList() {
        
        for (CandyCategories.DuplexSizeCategory duplexSizeCategory :
            CandyCategories.DuplexSizeCategory.values()) {
            
            this.duplexCandySizeList.add(duplexSizeCategory.getValue());
            
        }
    }
    
    /* Detached house-related functions */
    
    List<String> getDetachedHouseCandyNameList() {
        
        return this.detachedHouseCandyNameList;
    }
    
    public void setDetachedHouseCandyNameList(List<String>
        detachedHouseCandyNameList) {
        
        this.detachedHouseCandyNameList = detachedHouseCandyNameList;
    }
    
    
    /**
     * Creates a list of the candies that belong in Detached Houses
     */
    void buildDetachedHouseCandyNameList() {
        
        for (CandyCategories.DetachedHouseCandyCategory detachedHouseCandyCategory :
            CandyCategories.DetachedHouseCandyCategory.values()) {
            
            this.detachedHouseCandyNameList.add(detachedHouseCandyCategory
                .getValue());
            
        }
    }
    
    List<String> getDetachedHouseCandySizeList() {
        
        return detachedHouseCandySizeList;
    }
    
    public void setDetachedHouseCandySizeList(List<String>
        detachedHouseCandySizeList) {
        
        this.detachedHouseCandySizeList = detachedHouseCandySizeList;
    }
    
    /**
     * Creates a list of the candy sizes that belong in Detached Houses
     */
    void buildDetachedHouseCandySizeList() {
        
        for (CandyCategories.DetachedHouseSizeCategory dhSizeCategory :
            CandyCategories.DetachedHouseSizeCategory.values()) {
            
            this.detachedHouseCandySizeList.add(dhSizeCategory.getValue());
            
        }
    }
    
    /* Townhome-related functions */
    
    List<String> getTownhomeCandyNameList() {
        
        return this.townhomeCandyNameList;
    }
    
    public void setTownhomeCandyNameList(List<String>
        townhomeCandyNameList) {
        
        this.townhomeCandyNameList = townhomeCandyNameList;
    }
    
    /**
     * Creates a list of the candies that belong in Townhouses
     */
    void buildTownhomeCandyNameList() {
        
        for (CandyCategories.TownhomeCandyCategory townhomeCandyCategory :
            CandyCategories.TownhomeCandyCategory.values()) {
            
            this.townhomeCandyNameList.add(townhomeCandyCategory
                .getValue());
            
        }
    }
    
    List<String> getTownhomeCandySizeList() {
        
        return townhomeCandySizeList;
    }
    
    public void setTownhomeCandySizeList(List<String>
        townhomeCandySizeList) {
        
        this.townhomeCandySizeList = townhomeCandySizeList;
    }
    
    void buildTownhomeCandySizeList() {
        
        for (CandyCategories.TownhomeSizeCategory thSizeCategory :
            CandyCategories.TownhomeSizeCategory.values()) {
            
            this.townhomeCandySizeList.add(thSizeCategory.getValue());
            
        }
    }
    
}

