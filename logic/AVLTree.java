package logic;

import Actors.factories.dragons.Dragon;
import Movement.DragonHorde;

import java.util.LinkedList;

public class AVLTree {

    private AVLNode root;
    private LinkedList<Dragon> List;

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

    public void setPositions(AVLNode node, int lvl, int posY){
        if(node!=null){
            node.getDragon().setPosX(root.getDragon().getPosX()+(lvl*76));
            node.getDragon().setPosY(posY);
            setPositions(node.getRight(), lvl+1, posY-(230/(lvl+1)));
            setPositions(node.getLeft(), lvl+1, posY+(230/(lvl+1)));
        }
    }

    public void arrangedPos(AVLNode node){
        List.add(node.getDragon());
        if(node.getLeft()!=null){
            arrangedPos(node.getLeft());
        }
        if(node.getRight()!=null){
            arrangedPos(node.getRight());
        }
    }

    public void activateFireR(AVLNode node){
        if(node!=null){
            node.getDragon().setCanFire(true);
            activateFireR(node.getRight());
        }
    }

    public void activateFireL(AVLNode node){
        if(node!=null){
            node.getDragon().setCanFire(true);
            activateFireL(node.getLeft());
        }
    }

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }
}
