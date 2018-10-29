
package Interfaz;

/**
 * This is the the class of {@link btree.Btree} NodeBtree. In the class, we declare and define the the variables that we
 * need in the node of the {@link btree.Btree}. Also, we define the constructor of the node class.
 * 
 * for CS 380 - Final Project: Implementation of B-Tree Data Structure
 * @since 12/01/2011
 * @author Abdulrhman Alkhodiry, Ahmed Alsalama
 */
public class NodeBtree {

    /**
     * number of nodes
     */
    public int numberOfNodes;              // number of nodes
    /**
     * the array that holds the keys value.
     */
    public String key[];                      // the array that holds the keys value.
    /**
     * the array that holds the references of the keys in the node.
     */
    public NodeBtree children[];                // the array that holds the references of the keys in the node.
    /**
     * the variable to determined if the node is is Leaf or not.
     */
    public boolean isLeaf;                   // the variable to deterime if the node is is Leaf or not.

    /**
     * The constructor of the node class
     * The node can have at most 3 keys. We have 4 references for each node, and assign the node to be isLeaf.
     */
    NodeBtree() {
        key = new String[3];             // The node can have at most 3 keys
        children = new NodeBtree[4];       // We have 4 references for each node
        isLeaf = true;                  // assign the node to be Leaf.
    }
}
