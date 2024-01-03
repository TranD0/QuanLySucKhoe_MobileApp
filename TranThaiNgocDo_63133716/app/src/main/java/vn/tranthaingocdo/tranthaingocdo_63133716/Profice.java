package vn.tranthaingocdo.tranthaingocdo_63133716;

public class Profice {
    String FName,LName,Email, Gender;
    int Year;

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    float Weight,Hight;
    public Profice() {
    }
    public Profice(String FName, String LName, String email, String Gender, float weight, float hight,int Year) {
        this.FName = FName;
        this.LName = LName;
        this.Email = email;
        this.Gender = Gender;
        this.Weight = weight;
        this.Hight = hight;
        this.Year = Year;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public float getHight() {
        return Hight;
    }

    public void setHight(float hight) {
        Hight = hight;
    }
}
