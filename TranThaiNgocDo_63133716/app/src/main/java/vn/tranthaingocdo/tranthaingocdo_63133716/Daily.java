package vn.tranthaingocdo.tranthaingocdo_63133716;

public class Daily {
    String date;
    int water,count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    float calo;

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public float getCalo() {
        return calo;
    }

    public void setCalo(float calo) {
        calo = calo;
    }

    public Daily() {
    }
    public Daily(int water, float calo,String date,int count) {
        this.water = water;
       this.calo = calo;
       this.count=count;

    }


}
