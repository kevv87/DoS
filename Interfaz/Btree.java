
package Interfaz;

import utils.LinkedList;

/**
 *
 * @author Tomás
 */
 /** This is the {@link btree.Btree} class. We define the necessary variables and methods that we need to build the {@link btree.Btree}
 data structure.
*/

public class Btree {
    /**
     * Create a public root node
     */
    public NodeBtree root;
    private String chain = "";
    public LinkedList almacen;
    /**
     * The B Tree Constructor.
     *  Create new root, assign the root node to be a isLeaf,and initial the key value in the root to -1 (null)
     */
    public Btree() {
        // new root
        root = new NodeBtree();
        // assign the root node to be a isLeaf
        root.isLeaf = true;
        root.numberOfNodes = 0;
        // initial the key value in the root to -1 (null)
        root.key[0] = null;
    }

    /**
     * The Insert Method:
     * This method insert a key k into a B Tree.
     * The insert method takes one argument
     * The run time of the Insert Method is O(t log n)
     * 
     * @param k is the key that will be inserted into the node in the B Tree
     */
    public void insert(String k)
    {
        // root node r
        NodeBtree r = root;  
        /* If the root node r is full, the root will be splited and the new node s will be the root
         * The new root ndoe s will have two children.
         * The height of the B Tree will grow by one when the root node r gets split
         */
        if (r.numberOfNodes == 3) {
            // Create a new node s
            NodeBtree s = new NodeBtree();
            // the new node s will become a new root node after root node r gets spilt
            root = s;
            s.numberOfNodes = 0;
            // the new root node is no longer a isLeaf
            s.isLeaf = false;
            s.children[0] = r;

            //Call the split method to split the node r because it is full
            splitChild(s, 1, r);
            //Call the insert in none full node method to insert the key k into the new node s
            insertNonfull(s, k);
        } // If the root r node is not full, just insert the key k into it by calling the insert in none full node method
        else {
            insertNonfull(r, k);
        }
    }

    /**
     * The Insert in none full node Method:
     * This method insert a key k into a node that is already full.
     * The insert method takes two arguments: NodeBtree x, and integer k as a value
 The run time of the Insert in None Full NodeBtree algorithm is: O(t log n).
     * 
     * 
     * @param node that the value will be inserted in.
     * @param value the value to be inserted
     */
    public void insertNonfull(NodeBtree node, String value) {
        // Alocate the node 
        int i = node.numberOfNodes;
        // if the node is a isLeaf, we insert the value into the node 
        if (node.isLeaf) {
            while (i >= 1 && conversion(value) < conversion(node.key[i - 1])) {
                node.key[i] = node.key[i - 1];
                i--;
            }
            node.key[i] = value;
            node.numberOfNodes++;
        } /*
         * if the node is not a isLeaf, the value will be inserted into the appropriate isLeaf node in the subtree.
         * Also, it will be checking if the node is full; if yes then split the node.
         */ else {
            while (i >= 1 && conversion(value) < conversion(node.key[i - 1])) {
                i--;
            }
            i++;
            // if the node is full, split it.
            if (node.children[i - 1].numberOfNodes == 3) {
                // call the Split child method to split the node to two nodes
                splitChild(node, i, node.children[i - 1]);
                // Determine which of the two children is now the correct one to descend to.
                if (conversion(value) > conversion(node.key[i - 1])) {
                    i++;
                }
            }
            // Recursive Call
            insertNonfull(node.children[i - 1], value);
        }
    }

    /**
     * The Split Child Method
 It takes three arguments: NodeBtree x, int i, NodeBtree y
 The Basic idea of split child is: if we want to insert a key value into a node we should 
 check if the node is not full ( node can have at most 2*t-1 keys). If it is full, then we must
 split the node into two node.
 The Run Time of splitChild algorithm is: O(t), where t is a constant
 NodeBtree y: is x’s i th child.
     * 
     * @param parentNode the Parent NodeBtree
     * @param childIndex The index of the element
     * @param newChild  
     */
    public void splitChild(NodeBtree parentNode, int childIndex, NodeBtree newChild) {
        // Create a new node z, z will be the new child of node x
        NodeBtree z = new NodeBtree();
        z.isLeaf = newChild.isLeaf;
        z.numberOfNodes = 1;   // fix it to t-1
        z.key[0] = newChild.key[2];

        // if node y is not a isLeaf, assign the largest t-1 keys and child to the node z.
        if (!newChild.isLeaf) {
            z.children[1] = newChild.children[3];
            z.children[0] = newChild.children[2];
        }
        newChild.numberOfNodes = 1; // fix it to t -1

        // Here we insert node z as a child of node x
        for (int j = parentNode.numberOfNodes + 1; j >= childIndex + 1; j--) {
            parentNode.children[j] = parentNode.children[j - 1];
            parentNode.key[j - 1] = parentNode.key[j - 2];
        }
        // assign the reference to the node z ( node z is a child of the node x)
        parentNode.children[childIndex] = z;
        parentNode.key[childIndex - 1] = newChild.key[1];
        // increment
        parentNode.numberOfNodes++;
    }

    /**
     * The Search Method
     * The Search method is a straightforward method. If the key k is in the B Tree
     * , the method returns true. Otherwise return false.
     * The run time of the Search algorithm is : O(t log n).
     * 
     * @param node The node do be searched
     * @param value the value to search for.
     * @return True if found and false otherwise.
     */
    public boolean search(NodeBtree node, String value) {
        // initial i to be equal to 1
        int i = 1;
        // find the smallest index i such that k<= x.keyi, or else set i to x.n ++
        while (i <= node.numberOfNodes && conversion(value) > conversion(node.key[i - 1])) {
            //increment i
            i++;
        }
        // check if the key k is found
        if (i <= node.numberOfNodes && conversion(value) == conversion(node.key[i - 1])) {
            // return true if the key k is found in the B Tree
            return true;
        }
        /* if the x node is an internal node, terminate the search unsuccessful or 
         * recurse to search the appropriate subtree of node x.
         */
        // if the x node is not a isLeaf
        if (!node.isLeaf) {
            // recursive call to search in the subtree of the node x
            return search(node.children[i - 1], value);
        }
        // return false if the key k is not found in the B Tree
        return false;
    }

    /**
     * This method is use to make the search easier. We start the search from the root and going down to subtress
     * @param k the value to be searched for.
     * @return True if found and false otherwise.
     */
    public boolean search(String k) {
        // assign the node x to be the root and start search from the root
        NodeBtree x = root;
        // send the root x and the key k as an arguments to the the method search above
        return search(x, k);
    }
  
     /* 
     * @param node the node 
     * @param value the value the will be deleted
     * @return Ture if deleted, false otherwise.
     */
    public void insertion(LinkedList lista){
        System.out.println("Este es el tamano de la lista que se insertara: "+lista.getTamanio());//Debug
        for(int i = 0; i<lista.getTamanio(); i++){
        this.insert((String)lista.recorrer().get(i));
       }
    }
    
    
    /**
     * To print the B-Tree
     * @return 
     */
    public String print() {
        // Call the printBtree Method to print the B-Tree
        this.chain = "";
        printBtree(root, "");
        return this.chain;
    }
    
    /**
     * This Method is to print the B-Tree
     * @param node the node to be printed
     * @param indent the wanted indent. 
     */
    public void printBtree(NodeBtree node, String indent) {
        // if the node is null print "The B-Tree is Empty"
   
        if (node == null) {
            this.chain = this.chain+(indent + "The B-Tree is Empty" + "\n");
            System.out.println(indent + "The B-Tree is Empty");
        } else {
            //System.out.println(indent + " ");
             //this.chain = this.chain+(indent + " " + "\n");
            // declare a string variable
            String childIndent = indent + "\t";

            // for loop to print the B-Tree, recursively print the B-Tree Strucure
            for (int i = node.numberOfNodes-1; i >= 0; i--) {
                if (!node.isLeaf) // Recursive Call
                {
                    printBtree(node.children[i], childIndent);
                }
                // print the keys
                if(conversion(node.key[i]) > 0){
                    this.chain = this.chain+(childIndent +node.key[i] + "\n");
                    System.out.println(childIndent + node.key[i]);
                }     
            }
            if (!node.isLeaf) // Recirsive Call
            {
                printBtree(node.children[node.numberOfNodes], childIndent);
            }
        }
    }
    public int conversion(String nombre){
        if (nombre==null){
            return 0;
        }else{
        if(nombre.contains("A")){
            return 1400*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("B")){
            return 1300*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("C")){
            return 1200*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("D")){
            return 1100*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("E")){
            return 1000*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("F")){
            return 900*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("G")){
            return 800*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("H")){
            return 700*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("I")){
            return 600*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("J")){
            return 500*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("K")){
            return 400*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("L")){
            return 300*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("M")){
            return 200*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("N")){
            return 100*Integer.parseInt(nombre.substring(1));
        }else if(nombre.contains("O")){
            return 1*Integer.parseInt(nombre.substring(1));
        }else{
            return -1;
        }
        }
    }
}
