package players;

import main.Point;
import main.Value;

public abstract class Player{
    
    private Value mark;
    private String name;
    protected int playerType;

    public Player(String name, Value mark) {
        this.mark = mark;
        this.name = name;
    }   
   
    public abstract Point play();   
    
    public Value getMark(){
        return mark;
    }

    public int getPlayerType() {
        return playerType;
    }        

    public String getName() {
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void close(){
    }
    
    @Override
    public String toString(){
        return name+"  : "+mark;
    }
}
