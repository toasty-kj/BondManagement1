package Controller;


import Model.CheckPosition;
import Model.LoadMaster;
import Model.LoadPosition;
import View.GetTableHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputPosition {
    public int inputBuySell(String ticker){
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        CheckPosition checkPosition = new CheckPosition();
        Integer buysell = 0;
        while (true){
            System.out.println("買いなら1,売りなら2を入力してくだい");
            try {
                buysell = Integer.parseInt(br.readLine());
                if (buysell<0 || buysell>3){
                }
                if (checkPosition.checkExist(ticker)) {
                    break;
                }else if (buysell == 2){
                    System.out.println("その銘柄は保有していないため売ることはできません。");
                }
                else {break;}
            } catch (NumberFormatException e){
                System.out.println("半角で入力してください。");
            }catch (IOException e){
                System.out.println(e);
            }
        }return buysell;
    }
    public BigDecimal inputPrice(int buysell) {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BigDecimal price;
        while (true){
            if (buysell == 1) {
                System.out.println("購入金額を入力してください。");
            }
            else if (buysell == 2){
                System.out.println("売却価格を入力してください。");
            }
            try {
                price = new BigDecimal(br.readLine());
                if (price.compareTo(new BigDecimal("0"))>0 && price.compareTo(new BigDecimal("999999"))<0 ){break;}
            }catch (IOException e){
            }catch (NumberFormatException e){
                System.out.println("半角で購入金額を入力してください。");
            }
        }
        return price;
    }
    public String inputTicker(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoadMaster loadMaster = new LoadMaster();
        HashMap<String, BondMaster> masterMap = new HashMap<>();
        GetTableHeader getTableHeader = new GetTableHeader();

        String ticker = null;
        while (true){
            try {
                System.out.println("銘柄コードを入力してください。");
                ticker = br.readLine();
                masterMap = loadMaster.loadBondMaster();
                if (masterMap.containsKey(ticker)){
                    break;
                }else {
                    System.out.println("マスターファイルに存在しません");
                    System.out.println("マスターファイルに存在する債券は以下の通りです。");
                    getTableHeader.getMasterHeader(loadMaster.masterHeaderList());
                    continue;
                }
            }catch (IOException e){
            }catch (NumberFormatException e){
                System.out.println("半角でティッカーを入力してください。");
            }
        }return ticker;
    }
}
