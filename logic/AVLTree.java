package logic;

import Actors.factories.dragons.Dragon;

import java.util.LinkedList;

public class AVLTree {

    private AVLNode root;
    private LinkedList<Dragon> List = new LinkedList<>();

    public AVLTree(){
        root = null;
        LinkedList<Dragon> List = new LinkedList<>();
    }


    public AVLTree(LinkedList<Dragon> enemies){
        for(int i=0; i<enemies.size(); i++){
            insert(enemies.get(i));
        }
    }

    public AVLNode insert(AVLNode place, AVLNode newNode){
        if (place == null) {
            return newNode;
        }

        if (place.getKey()>newNode.getKey()) {
            place.setLeft(insert(place.getLeft(), newNode));
        } else {
            place.setRight(insert(place.getRight(), newNode));
        }
        return place.balance();
    }

    public void insert(Dragon dragon) {
        root = insert(root, new AVLNode(dragon));
        root.setLevel(0);
        root.setPosY(350);
    }

    public void setPositions(AVLNode node, int lvl, int posY, Dragon xPos){
        if(node!=null){
            node.getDragon().setPosXfinal(xPos.getPosX()+(lvl*76));
            node.getDragon().setPosYfinal(posY);
            setPositions(node.getRight(), lvl+1, posY-(130/(lvl+1)), xPos);
            setPositions(node.getLeft(), lvl+1, posY+(130/(lvl+1)),xPos);
        }
    }

    public void arrangedPos(AVLNode node){

        if (node == null){
            return;}

        List.add(node.getDragon());
        if(node.getLeft()!=null){
            arrangedPos(node.getLeft());
        }
        if(node.getRight()!=null){
            arrangedPos(node.getRight());
        }
    }

    public AVLNode getRoot() {
        return root;
    }

    public LinkedList<Dragon> getList() {
        return List;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }
}
