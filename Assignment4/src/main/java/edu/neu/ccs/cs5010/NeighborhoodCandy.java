package edu.neu.ccs.cs5010;

public class NeighborhoodCandy extends NeighborhoodMemberLeaf {

    private String candySize;

    /**
     * Candy that exists in the neighborhood
     *
     * @param candyName name of the candy
     * @param candySize size of the candy
     */
    NeighborhoodCandy(String candyName, String candySize) {

        super(candyName);
        this.candySize = candySize;
    }

    String getCandyName() {

        return this.getName();
    }

    public void setCandyName(String candyName) {

        this.setName(candyName);
    }

    String getCandySize() {

        return candySize;
    }

    void setCandySize(String candySize) {

        this.candySize = candySize;
    }

    @Override
    void accept(INeighborhoodCandyTraversalVisitor visitor) {

        visitor.visit(this);


    }

    @Override
    public void accept(INeighborhoodVisitor testVisitor) {

        testVisitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof NeighborhoodCandy)) {
            return false;
        }

        NeighborhoodCandy that = (NeighborhoodCandy) o;

        return this.getCandyName().equals(that.getCandyName()) &&
        candySize.equals(that.candySize);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + candySize.hashCode();
        return result;
    }

    @Override
    public String toString() {

        return "NeighborhoodCandy{}";
    }
}
