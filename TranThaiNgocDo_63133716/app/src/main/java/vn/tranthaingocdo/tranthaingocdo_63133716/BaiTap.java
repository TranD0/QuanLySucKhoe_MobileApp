package vn.tranthaingocdo.tranthaingocdo_63133716;

public class BaiTap {
    private String NameBT,moTa,imageBT;

    public String getNameBT() {
        return NameBT;
    }

    public void setNameBT(String nameBT) {
        NameBT = nameBT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getImageBT() {
        return imageBT;
    }

    public void setImageBT(String imageBT) {
        this.imageBT = imageBT;
    }

    public BaiTap(String nameBT, String moTa, String imageBT) {
        NameBT = nameBT;
        this.moTa = moTa;
        this.imageBT = imageBT;
    }
}
