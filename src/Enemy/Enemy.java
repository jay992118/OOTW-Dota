package Enemy;

import java.awt.*;

public abstract class Enemy {
    protected float x,y;
    protected Rectangle bounds;
    protected double health;
    protected double maxhealth;
    protected int ID;
    protected int enemyType;

    public Enemy(float x,float y,int id, int enemyType) {
        this.x=x;
        this.y=y;;
        this.ID=id;
        this.enemyType=enemyType;
        bounds = new Rectangle((int) x, (int) y, 64,64);
    }

    protected void setStartHealth(){
        health = Constants.enemys.GetStartHealth(enemyType);
        maxhealth = health;
    }

    public void beAttack(double damage){
        health = health-damage;
    }

    public double getHealthFloat(){
        return health/(float) maxhealth;
    }

    public void move(float x ,float y ){
        this.x += x;
        this.y += y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public double getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public float getSpeed(int enemyType){
        return Constants.enemys.getSpeed(enemyType);
    }

}
