package edu.neu.ccs.cs5010;

public class NeighborhoodCandy extends NeighborhoodMemberLeaf {
    
    private String candySize;
    
    /**
     * Represents a candy that exists in the neighborhood
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
    
    /** Accepts a NeighborhoodCandyTraversalVisitor, which compares the
     * desired candy name and size to the visited candy's name and size
     * to find a match.
     * @param visitor compares the candy with desired candy
     */
    @Override
    public void accept(NeighborhoodCandyTraversalVisitor visitor) {
        
        visitor.visit(this);
    }
    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + candySize.hashCode();
        return result;
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
    public String toString() {
        
        return "NeighborhoodCandy{}";
    }
}
