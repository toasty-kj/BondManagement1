package Controller;

import Model.*;
import View.GetTable;
import View.JobList;
import View.MenuSel;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BondManagement {
    public static void main(String[] args) throws IOException {
        BondManagement bondManagement = new BondManagement();
        bondManagement.mainLoop();
    }
    public void mainLoop()throws IOException{
        JobList jobList = new JobList();
        List<BondPosition> bondPositionList = new ArrayList<>();
        //BondPosition bondPosition = new BondPosition;
        //LoadPosition loadPosition = new LoadPosition();
        BigDecimal t = new BigDecimal(0.0);
        BondMaster bondMaster = new BondMaster("", "",t ,0,0);
        HashMap<String, BondMaster> masterMap =new HashMap<>();
        LoadMaster loadMaster = new LoadMaster();
        masterMap = loadMaster.loadBondMaster();
        Controller.UpdateMarketData updateMarketData = new Controller.UpdateMarketData();


        InputLoop inputLoop = new InputLoop();
        GetTable getTable = new GetTable();


        MenuSel menuSel = new MenuSel();


        System.out.println("債権の商品在庫管理システム");
        String[] joblist = jobList.jobList();
        String starting = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        String ending = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        String masterData = "MasterData.csv";
        String holdingPosition = "HoldingPosition.csv";

        Integer funcInt;
        do {
            funcInt = menuSel.menuSel();
            System.out.println(funcInt);
            BufferedReader br = null;
            switch (funcInt){
                case 0:
                    //<保有銘柄残高一覧表示>
                    //holdingPosition.csv を読み込んで整形表示する
                    //getTableで書き出す
                    getTable.getTable(bondPositionList,masterMap);
                    break;
                case 1:
                    //<在庫入力>
                    //読み込んでholdingPositionに追加
                    inputLoop.inputLoop(funcInt);
                    break;
                case 2:
                    //値洗い
                    updateMarketData.updateMarketData();
                    break;
                case 3:
                    //終了
                    System.out.println(starting);
                    System.out.println(joblist[funcInt]);
                    System.out.println(ending);
                    break;

            }
        }while (funcInt != 3);
        System.out.println("処理が完了しました。");
    }
}
