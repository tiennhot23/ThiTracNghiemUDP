package CSDL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.Pair;
import javax.swing.JOptionPane;


public class UDPServer {
    private static DBAccess db;
    
    public static void main(String[] args) throws SocketException, IOException, SQLException {
        // tao socket
        DatagramSocket server = new DatagramSocket(8888);
        System.out.println("Server is running");
        boolean conti = true;
        
        while(conti){
        byte arrayChoice[] = new byte [1000];
        DatagramPacket packetChoice = new DatagramPacket( arrayChoice , arrayChoice.length );
        server.receive(packetChoice);
        int option = Integer.parseInt( new String( packetChoice.getData() , 0 , packetChoice.getLength() ).trim() );
        System.out.println(option);
        switch (option) {
            case 1:
                try {
                    String result="CONNECT SUCCESS";
                    byte arrayResult[] = new byte[1000];
                    arrayResult = result.getBytes();

                    DatagramPacket packetResult = new DatagramPacket( arrayResult , arrayResult.length , packetChoice.getAddress() , packetChoice.getPort() );
                    server.send(packetResult);
                    
                } catch (Exception e) {
                    
                }
                break;
            case 2:
                try{
                    byte arrayA[] = new byte [1000];
                    DatagramPacket packetA = new DatagramPacket( arrayA , arrayA.length );
                    server.receive(packetA);
                    String chuoiketnoi = new String( packetA.getData() , 0 , packetA.getLength() );
                    Connection conn = thucthiketnoi(chuoiketnoi);
                    String result = "";
                    if(conn!=null){
                        result = "Kết nối Datababe thành công";
                        db = new DBAccess(conn);
                    }else {
                        result = "Kết nối Database thất bại";
                    }
                    byte arrayResult[] = new byte[1000];
                    arrayResult = result.getBytes();

                    DatagramPacket packetResult = new DatagramPacket( arrayResult , arrayResult.length , packetChoice.getAddress() , packetChoice.getPort() );
                    server.send(packetResult);
                }catch(Exception e){
                    
                }
                
                break;
            case -1:
                conti = false;
                break;
            case 3:
                try {
                    
                    byte array[] = new byte [1000];
                    DatagramPacket packet = new DatagramPacket( array , array.length );
                    server.receive(packet);
                    String thongtin[] = new String(packet.getData(), 0, packet.getLength()).split(",");
                    String hoten = thongtin[0];
                    String mssv = thongtin[1];
                    String sdt = thongtin[2];
                    
                    String r = themsinhvien(hoten, mssv, sdt);
                    array = r.getBytes();
                    packet = new DatagramPacket( array , array.length , packet.getAddress() , packet.getPort() );
                    server.send(packet);
                    if(!r.equals("Sinh viên đã thi!")){
                        List<CauHoi> listcauhoi = laydscauhoi(StaticClass.soluongcauhoi);
                        for(int i=0; i<listcauhoi.size(); i++){
                            String cauhoi = listcauhoi.get(i).getCauhoi() + "//"
                                    + listcauhoi.get(i).getNoidung()+ "//"
                                    + listcauhoi.get(i).getA() + "//"
                                    + listcauhoi.get(i).getB() + "//"
                                    + listcauhoi.get(i).getC() + "//"
                                    + listcauhoi.get(i).getD();
                            byte arraycauhoi[] = new byte[1000];
                            arraycauhoi = cauhoi.getBytes();

                            DatagramPacket packetcauhoi = new DatagramPacket( arraycauhoi , arraycauhoi.length , packet.getAddress() , packet.getPort() );
                            server.send(packetcauhoi);
                        }
                    }
                    
                } catch (Exception e) {
                    
                }
                break;
            case 4:
                try {
                    
                    byte array[] = new byte [1000];
                    DatagramPacket packet = new DatagramPacket( array , array.length );
                    server.receive(packet);
                    String masv = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("MSSV: " + masv);
                    
                    List<Pair<Integer, String>> listcautraloi = new ArrayList<>();
                    for(int i=0; i<StaticClass.soluongcauhoi; i++){
                        array = new byte [1000];
                        packet = new DatagramPacket( array, array.length );
                        server.receive(packet);
                        String a[] = new String(packet.getData(), 0, packet.getLength()).split(",");
                        int cauhoi = Integer.parseInt(a[0]);
                        String cautraloi = a[1];
                        listcautraloi.add(new Pair<Integer, String>(cauhoi, cautraloi));
                        System.out.println(listcautraloi.get(i).getKey() + "," + listcautraloi.get(i).getValue());
                    }
                    System.out.println(listcautraloi.size());
                    int diem = luudiem(masv, listcautraloi);
                    String result = "";
                    if(diem == -1){
                        result = "<html> Xảy ra lỗi khi cập nhật điểm cho sinh viên <br /> Sinh viên đạt " + diem + " điểm.";
                    }else{
                        result = "Sinh viên đã thi được " + diem + " điểm.";
                    }
                    
                    byte arrayResult[] = new byte[1000];
                    arrayResult = result.getBytes();
                    System.out.println(result);
                    DatagramPacket packetResult = new DatagramPacket( arrayResult , arrayResult.length , packet.getAddress() , packet.getPort() );
                    server.send(packetResult);
                    System.out.println(result);
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
        }
        }
       
    }
    
    public static Connection thucthiketnoi(String url){
        Connection conn = null;
        //url = "jdbc:sqlserver://localhost;databaseName=THITRACNGHIEM;user=sa;password=123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url);
            if(conn!=null){
                System.out.println("Kết nối Datababe thành công");
            }else {
                System.out.println("Kết nối Database thất bại");
            }
        } catch (ClassNotFoundException|SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    } 
    
    public static List<CauHoi> laydscauhoi(int soluong){
        List<CauHoi> listcauhoi = new ArrayList<>();
        String sql = "SELECT TOP "+soluong+" BODE.CAUHOI, BODE.NOIDUNG, BODE.A, BODE.B, BODE.C, BODE.D"
                + " FROM BODE ORDER BY NEWID()";
        try {
            ResultSet rs = db.truyvan(sql);
            while(rs.next()){
                listcauhoi.add(new CauHoi(rs.getInt("CAUHOI"),
                                            rs.getString("NOIDUNG"),
                                            rs.getString("A"),
                                            rs.getString("B"),
                                            rs.getString("C"),
                                            rs.getString("D")));
            }
            
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listcauhoi;
    }
    
    public static String themsinhvien(String hoten, String mssv, String sdt) {
        String sql = "INSERT [dbo].[SINHVIEN] ([MASV], [HOTEN], [SDT]) VALUES (N'"+mssv+"', N'"+hoten+"', '"+sdt+"')";
        System.out.println(sql);
        String result = null;
        int q;
        q = db.update(sql);
        if (q != -1) {
            result = mssv;
        } else {
            result = "Sinh viên đã thi!";
        }
        return result;
    }
    
    private static int luudiem(String masv, List<Pair<Integer, String>> listcautraloi){
        int diem = tinhdiem(listcautraloi);
        String sql = "UPDATE SINHVIEN SET DIEM = "+diem+" WHERE MASV = '"+masv+"'";
        System.out.println(sql);
        int q;
        q = db.update(sql);
        System.out.println("Q: " + q);
        if (q != -1) {
            return diem;
        } else {
            return -1;
        }
    }
    
    private static int tinhdiem(List<Pair<Integer, String>> listcautraloi){
        int diem = 0;
        for(Pair<Integer, String> p : listcautraloi){
            String sql = "SELECT DAP_AN FROM BODE WHERE CAUHOI = '" + p.getKey() + "'";
            System.out.println(sql);
            try {
                ResultSet rs = db.truyvan(sql);
                while(rs.next()){
                    String dapan = rs.getString("DAP_AN");
                    System.out.println(dapan + "-" + p.getValue());
                    if(p.getValue().equals(dapan)){
                        diem += 1;
                    }
                }
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return diem;
    }
}
