package Model;

import Controller.BondPosition;
import Controller.InputPosition;
import View.FuncPrint;
import View.JobList;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class InputLoop {
    public ArrayList inputLoop(int funcInt) throws IOException {
        String masterData = "MasterData.csv";
        String holdingPosition = "HoldingPosition.csv";
        FuncPrint funcPrint = new FuncPrint();
        InputPosition inputPosition = new InputPosition();
        UpdatePosition updatePosition = new UpdatePosition();
        ArrayList<BondPosition> bondPositionList = new ArrayList<>();
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
        bondPositionList = updatePosition.updatePosition(ticker, buysell, amount,purchasedPrice);

        return bondPositionList;
    }
}
