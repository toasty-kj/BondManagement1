package Model;

import Controller.InputPosition;
import View.FuncPrint;
import View.JobList;

import java.io.IOException;
import java.math.BigDecimal;

public class InputLoop {
    public void inputLoop(int funcInt) throws IOException {
        String masterData = "MasterData.csv";
        String holdingPosition = "HoldingPosition.csv";
        FuncPrint funcPrint = new FuncPrint();
        InputPosition inputPosition = new InputPosition();
        UpdatePosition updatePosition = new UpdatePosition();
        //jobの名前のprint
        funcPrint.funcPrint(funcInt);
        //tickerの入力
        String ticker = inputPosition.inputTicker();
        //売り買いの選択
        int buysell = inputPosition.inputBuySell();
        //購入or売却量の入力
        BigDecimal amount = inputPosition.inputAmount(ticker, buysell);
        //購入or売却価格の入力
        BigDecimal purchasedPrice = inputPosition.inputPrice(buysell);
        //Listの更新
        updatePosition.updatePosition(ticker, buysell, amount,purchasedPrice);
    }
}
