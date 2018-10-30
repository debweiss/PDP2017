package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

/**
 * Node used in constructing the neighborhood tree
 * Extends NeighborhoodMemberLeaf by adding children
 */
abstract class NeighborhoodMember extends NeighborhoodMemberLeaf {
    
    private List<NeighborhoodMemberLeaf> children = new ArrayList<>();
    
    NeighborhoodMember(String name) {
        
        super(name);
        
    }
    
    NeighborhoodMember(String name, List<NeighborhoodMemberLeaf>
        childrenList) {
        
        super(name);
        this.children = childrenList;
        
    }
    
    List<NeighborhoodMemberLeaf> getChildren() {
        
        return this.children;
    }
    
    public void setChildren(List<NeighborhoodMemberLeaf>
        children) {
        
        this.children = children;
    }
    
    /** Adds a new child to the NeighborhoodMember
     * @param child child of the neighborhoodMember
     */
    public void addChild(NeighborhoodMemberLeaf child) {
        
        if (this.children != null) {
            ; // associate the child with the parent
            this.children.add(child);
            child.setParent(this);
        } else {
            
            this.children = new ArrayList<>();
            this.children.add(child);
            child.setParent(this);
            
        }
    }
    
}




