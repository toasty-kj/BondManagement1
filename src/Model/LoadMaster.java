package Model;

import Controller.BondManagement;
import Controller.BondMaster;
import Controller.BondPosition;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadMaster {
    public HashMap<String, BondMaster> loadBondMaster() throws IOException {
        String masterData = "MasterData.csv";
        BufferedReader br = null;
        String[] arrayStr = new String[5];
        //BondMaster bondMaster = new BondMaster();
        HashMap<String, BondMaster> masterMap = new HashMap<>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(masterData), "UTF-8"));
            String message;
            while ((message = br.readLine()) != null) {
                arrayStr = message.split(",");
                String ticker = arrayStr[0];
                String issuer = arrayStr[1];
                BigDecimal yield = new BigDecimal(arrayStr[3]);
                int maturity = Integer.parseInt(arrayStr[2]);
                int coupon = Integer.parseInt(arrayStr[4]);
                BondMaster masterobject = new BondMaster(ticker, issuer, yield, maturity, coupon);
                masterMap.put(ticker, masterobject);
            }
        } catch (IOException e) {
            System.out.println("マスターファイルの読み込みに失敗しました");
        } finally {
            br.close();

        }
        return masterMap;
    }

    public ArrayList masterHeaderList() {
        String masterData = "MasterData.csv";
        BufferedReader br = null;
        String[] arrayStr = new String[2];
        ArrayList<BondMaster> masterHeader = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(masterData), "UTF-8"));
            String message;
            while ((message = br.readLine()) != null) {
                arrayStr = message.split(",");
                String ticker = arrayStr[0];
                String issuer = arrayStr[1];
                BondMaster line = new BondMaster(ticker, issuer, BigDecimal.ZERO, 0, 0);
                masterHeader.add(line);
            }return masterHeader;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
