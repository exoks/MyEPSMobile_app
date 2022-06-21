package com.example.eps;

public class Modules {
    int ID ;
    String OTI;
    String CHECK;
    String BASKHOND;
    String VOLLEY;
    String GYM;
    String FOUR;

    public Modules(int ID, String OTI, String CHECK, String BASKHOND, String VOLLEY, String GYM, String FOUR) {
        this.ID = ID;
        this.OTI = OTI;
        this.CHECK = CHECK;
        this.BASKHOND = BASKHOND;
        this.VOLLEY = VOLLEY;
        this.GYM = GYM;
        this.FOUR = FOUR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOTI() {
        return OTI;
    }

    public void setOTI(String OTI) {
        this.OTI = OTI;
    }

    public String getCHECK() {
        return CHECK;
    }

    public void setCHECK(String CHECK) {
        this.CHECK = CHECK;
    }

    public String getBASKHOND() {
        return BASKHOND;
    }

    public void setBASKHOND(String BASKHOND) {
        this.BASKHOND = BASKHOND;
    }

    public String getVOLLEY() {
        return VOLLEY;
    }

    public void setVOLLEY(String VOLLEY) {
        this.VOLLEY = VOLLEY;
    }

    public String getGYM() {
        return GYM;
    }

    public void setGYM(String GYM) {
        this.GYM = GYM;
    }

    public String getFOUR() {
        return FOUR;
    }

    public void setFOUR(String FOUR) {
        this.FOUR = FOUR;
    }
}
