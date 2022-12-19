package Model;

import Controller.BondManagement;
import Controller.BondMaster;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LoadMaster {
    public HashMap<String, BondMaster> loadBondMaster() throws IOException{
        String masterData = "MasterData.csv";
        BufferedReader br = null;
        String[] arrayStr = new String[5];
        //BondMaster bondMaster = new BondMaster();
        HashMap<String,BondMaster> masterMap = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(masterData, StandardCharsets.UTF_8));
            String message;
            while ((message = br.readLine())!= null){
                arrayStr = message.split(",");
                String ticker = arrayStr[0];
                String issuer = arrayStr[1];
                BigDecimal yield = new BigDecimal(arrayStr[3]);
                int maturity = Integer.parseInt(arrayStr[2]);
                int coupon = Integer.parseInt(arrayStr[4]);
                BondMaster masterobject = new BondMaster(ticker, issuer, yield, maturity, coupon);
                masterMap.put(ticker, masterobject);
            }
        }catch (IOException e){
            System.out.println("マスターファイルの読み込みに失敗しました");
        }finally {
            System.out.println("マスターファイルの読み込みを行いました。");
            br.close();

        }return masterMap;
    }
}
