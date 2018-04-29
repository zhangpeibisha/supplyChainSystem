package freemaker.model;

/**
 * Created by 11723 on 2017/5/6.
 */
public class Param {
    private String name;
    private String type;
    public Param(String name,String type){
        this.name = name;
        this.type = type;
    }
    public Param(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
