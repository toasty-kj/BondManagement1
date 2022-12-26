package Model;

import Controller.BondPosition;
import Model.LoadPosition;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CheckPosition {
    /**
     * 保有銘柄リストを参照して残高、簿価もしくは保有の有無を確認する
     * @param ticker
     * @return
     * @throws IOException
     */
    public BigDecimal amountCheck (String ticker) throws IOException {
        LoadPosition loadPosition = new LoadPosition();
        List<BondPosition> bondPositionList = new ArrayList<>();
        bondPositionList = loadPosition.loadPosition();
        BigDecimal amount = new BigDecimal("0");

        for (int i=0; i<bondPositionList.size(); i++){
            if (bondPositionList.get(i).getTicker().equals(ticker)) {
                amount = bondPositionList.get(i).getAmount();
            }
        }return amount;
    }
    public BigDecimal priceCheck(String ticker) throws IOException{
        LoadPosition loadPosition = new LoadPosition();
        List<BondPosition> bondPositionList = new ArrayList<>();
        bondPositionList = loadPosition.loadPosition();
        BigDecimal currentPrice = new BigDecimal("0");
        for (int i=0; i<bondPositionList.size(); i++){
            if (bondPositionList.get(i).getTicker().equals(ticker)){
                currentPrice = bondPositionList.get(i).getPurchasedPrice();
            }
        }return currentPrice;
    }

    public Boolean checkExist(String ticker, ArrayList<BondPosition> bondPositionList)throws IOException{
        int existance = 0;

        for (int i=0; i<bondPositionList.size(); i++){
            if (bondPositionList.get(i).getTicker().equals(ticker)){
                existance = 1;
            }
        }if (existance == 1){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
    public int getNumRow(String ticker)throws IOException{
        //保有銘柄リストの何番目に引数として与えられたtickerがあるか調べてそのindexを返す
        List<BondPosition> bondPositionList = new ArrayList<>();
        LoadPosition loadPosition = new LoadPosition();
        bondPositionList = loadPosition.loadPosition();
        int index=100;

        for (int i=0; i<bondPositionList.size(); i++){
            if (bondPositionList.get(i).getTicker().equals(ticker)){
                index = i;
            }

        }return index;
    }
}
