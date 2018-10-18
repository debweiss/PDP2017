package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.List;

public abstract class NeighborhoodMember extends NeighborhoodMemberLeaf {

    private String name;
    private List<NeighborhoodMemberLeaf> children = new ArrayList<>();

    NeighborhoodMember(String name) {

        super(name);

    }

    NeighborhoodMember(String name, List<NeighborhoodMemberLeaf> childrenList) {

        super(name);
        this.children = childrenList;

    }

    public void addChild(NeighborhoodMemberLeaf child) {

        if (this.children != null) {
            ; // associate the child with the parent
            this.children.add(child);
            child.setParent(this);
        }

        else {

            this.children = new ArrayList<>();
            this.children.add(child);
            child.setParent(this);

        }
    }

    List<NeighborhoodMemberLeaf> getChildren() {

        return this.children;
    }

    public void setChildren(List<NeighborhoodMemberLeaf> children) {

        this.children = children;
    }

    @Override
    void accept(INeighborhoodCandyTraversalVisitor visitor) {

        visitor.visit(this);
    }


    void accept(IPopulateNeighborhoodVisitor visitor) {

        // visitor.visit(this);
    }

    @Override
    public int hashCode() {

        int result = name.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NeighborhoodMember{" +
                   "name='" + name + '\'' +
                   ", children=" + children +
                   '}';
    }
}

