/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSDL;

/**
 *
 * @author tienn
 */
public class CauHoi {
    private int cauhoi;
    private String noidung;
    private String a,b,c,d;

    public CauHoi() {
    }

    public CauHoi(int cauhoi, String noidung, String a, String b, String c, String d) {
        this.noidung = noidung;
        this.cauhoi = cauhoi;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public int getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(int cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    
    
}
