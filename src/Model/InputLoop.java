package Model;

import Controller.BondPosition;
import Controller.InputPosition;
import View.FuncPrint;
import View.GetTable;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class InputLoop {
    /**
     * 在庫入力を入力してもらう大元のメソッド
     * @param funcInt 選択してもらった機能の番号
     * @return 更新した保有銘柄リストを返す
     * @throws IOException
     */
    public ArrayList inputLoop(int funcInt, ArrayList<BondPosition> bondPositionList) throws IOException {
        String masterData = "MasterData.csv";
        String holdingPosition = "HoldingPosition.csv";
        FuncPrint funcPrint = new FuncPrint();
        InputPosition inputPosition = new InputPosition();
        InputAmount inputAmount = new InputAmount();
        UpdatePosition updatePosition = new UpdatePosition();
        CheckPosition checkPosition = new CheckPosition();
        //jobの名前のprint
        funcPrint.funcPrint(funcInt);
        //tickerの入力
        String ticker = inputPosition.inputTicker();
        //売り買いの選択
        int buysell = inputPosition.inputBuySell(ticker, bondPositionList);
        //購入or売却量の入力
        BigDecimal amount = inputAmount.inputAmount(ticker, buysell);
        //購入or売却価格の入力
        BigDecimal purchasedPrice = inputPosition.inputPrice(buysell);

        BigDecimal marketPrice = BigDecimal.ZERO;
        if (checkPosition.checkExist(ticker, bondPositionList)){
            marketPrice = bondPositionList.get(checkPosition.getNumRow(ticker)).getMarketPrice();
        }
        //Listの更新
        bondPositionList = updatePosition.updatePosition(bondPositionList,ticker, buysell, amount,purchasedPrice, marketPrice);

        return bondPositionList;
    }
}
