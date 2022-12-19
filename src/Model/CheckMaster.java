package Model;

import Controller.BondMaster;
import Model.LoadMaster;
import Model.LoadPosition;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class CheckMaster {
    public int getNumRow(String ticker){
        HashMap<String, BondMaster> masterMap = new HashMap<>();
        LoadMaster loadMaster = new LoadMaster();
        int index =100;
        try{
            masterMap = loadMaster.loadBondMaster();
            for (int i=0; i <= masterMap.size(); i++){
                if (masterMap.get(i).getTicker().equals(ticker)){
                    index = i;
                }
            }
        }catch (IOException e){
            System.out.println("マスターファイルが読み込めませんでした");
        }return index;
    }
}
