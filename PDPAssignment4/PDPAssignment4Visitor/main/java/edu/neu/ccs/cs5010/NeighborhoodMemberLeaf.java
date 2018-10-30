package edu.neu.ccs.cs5010;

/**
 * Node used in constructing a neighborhood tree
 * Does not have children
 */
public abstract class NeighborhoodMemberLeaf implements Visitable {
    
    private String name;
    private NeighborhoodMember parent;
    private boolean visited;
    
    
    NeighborhoodMemberLeaf(String name) {
        
        this.name = name;
        this.visited = false;
    }
    
    String getName() {
        
        return this.name;
    }
    
    public void setName(String name) {
        
        this.name = name;
    }
    
    NeighborhoodMember getParent() {
        
        return parent;
    }
    
    void setParent(NeighborhoodMember parent) {
        
        this.parent = parent;
    }
    
    boolean getVisited() {
        
        return visited;
    }
    
    void setVisited(boolean visited) {
        
        this.visited = visited;
    }
    
    public void accept(Visitor visitor) {
        
        visitor.visit(this);
    }
    
    public void accept(NeighborhoodCandyTraversalVisitor nctVisitor) {
        
        nctVisitor.visit(this);
    }
    
    public void accept(PopulateNeighborhoodVisitor popVisitor) {
        
        popVisitor.visit(this);
    }
    
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NeighborhoodMemberLeaf)) {
            return false;
        }
        
        NeighborhoodMemberLeaf that = (NeighborhoodMemberLeaf) o;
        
        if (!name.equals(that.name)) {
            return false;
        }
        return parent != null ? parent.equals(that.parent) : that.parent == null;
    }
    
    @Override
    public String toString() {
        return "NeighborhoodMemberLeaf{" +
            "name='" + name + '\'' +
            ", parent=" + parent +
            '}';
    }
}