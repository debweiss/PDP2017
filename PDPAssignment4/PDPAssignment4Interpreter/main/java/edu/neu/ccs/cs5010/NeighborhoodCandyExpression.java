package edu.neu.ccs.cs5010;

import org.jetbrains.annotations.NotNull;

/**
 * This class defines a set of and & or expressions that when combined and evaluated,
 * can determine whether a given candy exists in the neighborhood and what household type
 * it belongs to.
 */
class NeighborhoodCandyExpression {


    /* Candies and sizes that are combined to make up the neighborhood candies */

    private IExpression twix = new TerminalExpression("twix");
    private IExpression snickers = new TerminalExpression("snickers");
    private IExpression mars = new TerminalExpression("mars");
    private IExpression whoppers = new TerminalExpression("whoppers");
    private IExpression kitKat = new TerminalExpression("kit kat");
    private IExpression crunch = new TerminalExpression("crunch");
    private IExpression toblerone = new TerminalExpression("toblerone");
    private IExpression almondJoy = new TerminalExpression("almond joy");
    private IExpression babyRuth = new TerminalExpression("baby ruth");
    private IExpression milkyWay = new TerminalExpression("milky way");

    private IExpression superSize = new TerminalExpression("super size");
    private IExpression funSize = new TerminalExpression("fun size");
    private IExpression kingSize = new TerminalExpression("king size");
    private IExpression regularSize = new TerminalExpression("regular size");



    /** Expression that describes what has to be true about a candy to know that it
     * exists in the neighborhood. In this case, it needs to either belong to a mansion,
     * a duplex, a detached house, or townhome.
     * @return an expression that can be used to evaluate incoming candies.
     */
    @org.jetbrains.annotations.NotNull
    private IExpression getNeighborhoodCandyExpression() {

        /* The candy belongs to the neighborhood if it belongs to either a mansion, duplex,
        detached house, or townhome */
        return new OrExp(new OrExp(getMansionCandyExpression(), getDuplexCandyExpression()),
            new OrExp(getTownHomeCandyExpression(), getDetachedHouseCandyExpression()));
    }

    /************************** Mansion-related expressions***************************/

    /** Expression that describes what has to be true about a candy to know that it
     * belongs in a mansion.
     * @return an expression that is used to evaluate incoming candies.
     */
    @NotNull
    private IExpression getMansionCandyExpression() {

        /* The candy belongs to a mansion if it is any of the sizes and candy names that
        mansions have */
        return new OrExp(new OrExp(getMansionFunSizeCandyExpression(),
           getMansionKingSizeCandyExpression()), getMansionSuperSizeCandyExpression());
    }

    /** Expression that describes what fun size candies belong in a mansion.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getMansionFunSizeCandyExpression() {

        // A Mansion's fun size candies are any of toblerone, baby ruth, or almond joy
        return new AndExp(funSize, (new OrExp(new OrExp(toblerone, babyRuth), almondJoy)));
    }

    /** Expression that describes which king size candies belong in a mansion.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getMansionKingSizeCandyExpression() {

        // A Mansion's king size candies are any of whoppers, crunch, or kit kat
        return new AndExp(kingSize, (new OrExp(new OrExp(whoppers, crunch), kitKat)));
    }

    /** Expression that describes which super size candies belong in a mansion.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getMansionSuperSizeCandyExpression() {

        // A Mansion's super size candies are any of twix, snickers, or mars
        return new AndExp(superSize, (new OrExp(new OrExp(twix, snickers), mars)));
    }

    /************************** Duplex-related expressions*******************************/

    /** Expression that describes what has to be true about a candy to know that it
     * belongs in a duplex.
     * @return an expression that is used to evaluate incoming candies.
     */
    @NotNull
    private IExpression getDuplexCandyExpression() {

        /* The candy belongs to a duplex if it is any of the sizes and candy names that
        mansions have */
        return new OrExp(new OrExp(getDuplexFunSizeCandyExpression(),
           getDuplexKingSizeCandyExpression()), getDuplexSuperSizeCandyExpression());
    }

    /** Expression that describes which fun size candies belong in a duplex.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getDuplexFunSizeCandyExpression() {

        /* A Duplex's fun size candies are any of kit kat, whoppers, milky way,
        crunch */
        return new AndExp(funSize, (new OrExp(new OrExp(kitKat, whoppers),
           new OrExp(milkyWay, crunch))));
    }

    /** Expression that describes which king size candies belong in a duplex.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getDuplexKingSizeCandyExpression() {

        // A Duplex's king size candies are any of twix, snickers, or mars
        return new AndExp(kingSize, (new OrExp(new OrExp(twix, snickers), mars)));
    }

    /** Expression that describes which super size candies belong in a duplex.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getDuplexSuperSizeCandyExpression() {

        /*  A Duplex's super size candies are any of toblerone, baby ruth,
        or almond joy */
        return new AndExp(superSize, (new OrExp(new OrExp(toblerone, babyRuth), almondJoy)));
    }

    /************************** DetachedHouse-related expressions******************************/

    /** Expression that describes what has to be true about a candy to know that it belongs
     * in a detached house.
     * @return an expression that is used to evaluate incoming candies.
     */
    @NotNull
    private IExpression getDetachedHouseCandyExpression() {

        /* The candy belongs to a detached house if it is any of the sizes and candy names
         that detached houses have */
        return new OrExp(new OrExp(getDetachedHouseFunSizeCandyExpression(),
            getDetachedHouseKingSizeCandyExpression()), getDetachedHouseSuperSizeCandyExpression());
    }

    /** Expression that describes which fun size candies belong in a detached house.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getDetachedHouseFunSizeCandyExpression() {

        // A Detached House's fun size candies are any of twix, snickers, or mars
        return new AndExp(funSize, (new OrExp(new OrExp(twix, snickers), mars)));
    }

    /** Expression that describes which king size candies belong in a detached house.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getDetachedHouseKingSizeCandyExpression() {

        // A Detached House's king size candy is only toblerone
        return new AndExp(kingSize, toblerone);
    }

    /** Expression that describes which super size candies belong in a detached house.
     * @return an expression that is used to evaluate incoming candies
     */
    @NotNull
    private IExpression getDetachedHouseSuperSizeCandyExpression() {

        /* A Detached House's super size candies are any of kit kat, whoppers,
        milky way, crunch */
        return new AndExp(superSize, (new OrExp(new OrExp(kitKat, whoppers),
           new OrExp(milkyWay, crunch))));
    }

    /************************** Townhome-related expressions*******************************/

    /** Expression that describes what has to be true about a candy to know that it belongs
     * in a townhome.
     * @return an expression that is used to evaluate incoming candies.
     */
    @NotNull
    private IExpression getTownHomeCandyExpression() {

        /* A Townhome only has regular size candies, and they are kit kat, whoppers,
        twix, snickers, mars, baby ruth, almond joy, and toblerone */
        return new AndExp(regularSize, (new OrExp(new OrExp(new OrExp(kitKat, whoppers),
           new OrExp(twix, snickers)), new OrExp(new OrExp(mars, babyRuth),
           new OrExp(almondJoy, toblerone)))));
    }

    /** Establishes whether it is possible to traverse the neighborhood and gather the
     * specified candy.
     * @param candy the desired candy
     * @return true if the candy exists in the neighborhood, false if not
     */
    boolean traversalPossible(String candy) {

        IExpression traversalPossible = getNeighborhoodCandyExpression();
        return traversalPossible.interpret(candy);

    }

    /** From the candy involved, derive the name of the house type it belongs to
     * @param candy being evaluated
     * @return the name of the house type the candy belongs to
     */
    String getHouseTypeFromCandy(String candy) {

        String houseType = null;

        IExpression belongsToMansion = getMansionCandyExpression();
        IExpression belongsToDuplex = getDuplexCandyExpression();
        IExpression belongsToDetachedHouse = getDetachedHouseCandyExpression();
        IExpression belongsToTownHome = getTownHomeCandyExpression();


        if (belongsToMansion.interpret(candy)) {

            houseType = "Mansion";
        }

        else if (belongsToDuplex.interpret(candy)) {

            houseType = "Duplex";
        }

        else if (belongsToDetachedHouse.interpret(candy)) {

            houseType = "Detached House";
        }

        else if (belongsToTownHome.interpret(candy)) {

            houseType = "Townhome";
        }

        return houseType;

    }
}