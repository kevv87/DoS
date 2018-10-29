import dragons.Dragon;

public class AVLNode {

    private int Key;
    private int Height;
    private int Level;
    private int posY;
    private Dragon dragon;
    private AVLNode Left;
    private AVLNode Right;

    public AVLNode (){
        dragon= null;
        Key=0;
        Height=0;
    }

    public AVLNode(Dragon enemy){
        dragon=enemy;
        Key=dragon.getEdad();
        Height=determineHeight();
    }

    public int determineHeight(){
        Height=1;
        if(Left!=null){
            Height=Height+Left.Height;
        }
        if(Right!=null){
            Height=Height+Right.Height;
        }
        return Height;
    }

    public int getBalance()
    {
        int balance = 0;
        if (Left != null) {
            balance += Left.Height;
        }
        if (Right != null) {
            balance -= Right.Height;
        }
        return balance;
    }

    public AVLNode balance(){
        determineHeight();
        int balance = getBalance();
        if (balance > 1) {
            if (Left.getBalance() < 0) {
                Left = Left.rotateLeft();
            }
            return rotateRight();
        } else if (balance < -1) {
            if (Right.getBalance() > 0) {
                Right = Right.rotateRight();
            }
            return rotateLeft();
        }

        return this;
    }

    public AVLNode rotateRight() {
        AVLNode temp = this.Left;

        this.Left = temp.Right;
        this.determineHeight();

        temp.Right = this;
        temp.determineHeight();

        return temp;
    }

    public AVLNode rotateLeft() {
        AVLNode temp = this.Right;

        this.Right = temp.Left;
        this.determineHeight();

        temp.Left = this;
        temp.determineHeight();

        return temp;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public int getKey() {
        return Key;
    }

    public AVLNode getLeft() {
        return Left;
    }

    public void setLeft(AVLNode left) {
        Left = left;
    }

    public AVLNode getRight() {
        return Right;
    }

    public void setRight(AVLNode right) {
        Right = right;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
