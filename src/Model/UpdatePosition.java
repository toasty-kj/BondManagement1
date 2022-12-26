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
    /**
     * updatePositionメソッドでは保有銘柄について与えられた引数から保有銘柄リストを更新する。
     * @param ticker 保有銘柄コード
     * @param buysell 買いが1,売りが2
     * @param amount 買いもしくは売りの量
     * @param price かったもしくは売った価格
     * @return 更新した保有銘柄リスト
     * @throws InaccessibleObjectException
     * @throws IOException
     */
    public ArrayList updatePosition(ArrayList<BondPosition> bondPositionList, String ticker, int buysell, BigDecimal amount, BigDecimal price, BigDecimal marketPrice) throws InaccessibleObjectException, IOException {
        /**
         * 在庫入力されたデータを保有銘柄のArrayListに追加する
         */
        CheckPosition checkPosition = new CheckPosition();
        int index = checkPosition.getNumRow(ticker);
        /**買いのときは移動平均簿価を計算する必要がある。
         買う銘柄が既に保有している場合としていない場合で場合分けした。
         */
        if (buysell == 1){
            BondPosition newPosition = new BondPosition(ticker, amount, price, marketPrice);
            if (checkPosition.checkExist(ticker, bondPositionList)){
                index = checkPosition.getNumRow(ticker);
                BigDecimal postPurchasedPrice =  bondPositionList.get(index).getPurchasedPrice();
                BigDecimal postAmount = bondPositionList.get(index).getAmount();
                price = ((postPurchasedPrice.multiply(postAmount).add(price.multiply(amount))).divide(postAmount.add(amount),BigDecimal.ROUND_HALF_UP));
                amount = postAmount.add(amount);
                newPosition = new BondPosition(ticker, amount, price, marketPrice);
                bondPositionList.set(index, newPosition);
            }else {
                bondPositionList.add(newPosition);
            }

        }/**売りの場合は簿価は変化しないため場合分けしない
         */
        else if (buysell == 2) {
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

    }
}
