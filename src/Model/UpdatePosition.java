package Model;


import Controller.BondPosition;
import Model.LoadPosition;

import java.io.IOException;
import java.lang.reflect.InaccessibleObjectException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class UpdatePosition {
    public ArrayList updatePosition(String ticker, int buysell, BigDecimal amount, BigDecimal price) throws InaccessibleObjectException, IOException {
        LoadPosition loadPosition = new LoadPosition();
        ArrayList<BondPosition> bondPositionList = new ArrayList<>();
        CheckPosition checkPosition = new CheckPosition();
        BigDecimal marketPrice = new BigDecimal("0");
        try {
            bondPositionList = loadPosition.loadPosition();
            int index = checkPosition.getNumRow(ticker);
        }catch (IOException e){
            System.out.println("ファイルの読み込みに失敗しました。");
        }
        if (buysell == 1){
            BondPosition newPosition = new BondPosition(ticker, amount, price, marketPrice);
            if (checkPosition.checkExist(ticker)){
                int index = checkPosition.getNumRow(ticker);
                BigDecimal postPurchasedPrice =  bondPositionList.get(index).getPurchasedPrice();
                BigDecimal postAmount = bondPositionList.get(index).getAmount();
                System.out.println(postAmount + "*" +postPurchasedPrice + "+" +price +"*"+amount +"="+ (postPurchasedPrice.multiply(postAmount).add(price.multiply(amount))));
                price = ((postPurchasedPrice.multiply(postAmount).add(price.multiply(amount))).divide(postAmount.add(amount),BigDecimal.ROUND_HALF_UP));
                amount = postAmount.add(amount);
                newPosition = new BondPosition(ticker, amount, price, marketPrice);
                bondPositionList.set(index, newPosition);
            }else {
                bondPositionList.add(newPosition);
            }

        }
        else if (buysell == 2) {
            int index;
            try {
                index = checkPosition.getNumRow(ticker);
                //平均ばいかの計算
                BigDecimal postPurchasedPrice =  bondPositionList.get(index).getPurchasedPrice();
                BigDecimal postAmount = bondPositionList.get(index).getAmount();
                amount = postAmount.subtract(amount);
                price = postPurchasedPrice;

                BondPosition newPosition = new BondPosition(ticker, amount, price, marketPrice);
                bondPositionList.set(index, newPosition);
            } catch (IOException e) {
                System.out.println("インデックスの取得に失敗しました。");
            }

        }return bondPositionList;

    }//買いのときは保有している場合としていない場合に分ける必要がある
    //売りの場合は保有している銘柄のみなのでtickerを引数にしてgetNumRowからindexなどのデータを取り出してamount, priceを変更する
}
