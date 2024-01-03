package vn.tranthaingocdo.tranthaingocdo_63133716;

public class Food {
    String Name;float Calo;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getCalo() {
        return Calo;
    }

    public void setCalo(float calo) {
        Calo = calo;
    }



    public Food() {
    }

    public Food(String name, float calo) {
        Name = name;
        Calo = calo;
    }
}
