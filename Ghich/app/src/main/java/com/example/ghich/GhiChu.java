package com.example.ghich;

public class GhiChu {
    private String TieuDe;
    private String NoiDung;
    private String ThoiGianChinhSua;
    private  String Tag;
    public GhiChu(String tieude, String noidung, String thoigianchinhsua, String tag){
        TieuDe=tieude;
        NoiDung=noidung;
        ThoiGianChinhSua=thoigianchinhsua;
        Tag=tag;

    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }
    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung=noiDung;
    }

    public String getThoiGianChinhSua() {
        return ThoiGianChinhSua;
    }

    public void setThoiGianChinhSua(String thoiGianChinhSua) {
        ThoiGianChinhSua = thoiGianChinhSua;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
}
