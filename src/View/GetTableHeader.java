package View;

import Controller.BondMaster;

import java.util.ArrayList;

public class GetTableHeader {
    public void  getTableHeader(){
        //表示するのは銘柄コード,銘柄名、償還年月日、利率、クーポン回数、保有数量、簿価、時価、評価損益
        //銘柄コードからmapの銘柄名、年月、利率、クーポン回数を取り出して
        //Listのほうから保有数量、簿価、時価、評価損益を取り出す。
        Separation separation = new Separation();
        String starting = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        String ending = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        String[] headline = new String[9];
        headline[0] = "銘柄コード";
        headline[1] = "   銘柄名   ";
        headline[2] = "償還年月日";
        headline[3] = "利率";
        headline[4] = "クーポン回数";
        headline[5] = "保有数量";
        headline[6] = "簿価";
        headline[7] = "時価";
        headline[8] = "評価損益";

        System.out.println("保有銘柄残高一覧表示");
        separation.sep();
        System.out.print("|");
        for (int i =0; i<headline.length-1; i++){
            System.out.print("  "+headline[i] + "  ");
            System.out.print(" |");
        }
        System.out.print(headline[8]);
        System.out.println("|");
        separation.sep();
    }
    public void getMasterHeader(ArrayList<BondMaster> masterHeaderList){
        Separation separation = new Separation();
        separation.sep();
        for (int i=0; i< masterHeaderList.size(); i++){
            String ticker = masterHeaderList.get(i).getTicker();
            String issuer = masterHeaderList.get(i).getIssuer();
            System.out.print("|");
            System.out.print(String.format("%-15s",ticker)+"|");
            System.out.print(issuer);

            int length = 0;
            char[] c = issuer.toCharArray();
            for (int l=0; l<c.length; l++){
                if (String.valueOf(c[l]).getBytes().length<=1){
                    length += 1;
                }else {
                    length += 2;
                }
            }
            for (int ite=0; ite<17-length; ite++){
                System.out.print(" ");
            }
            System.out.println("|");
        }
        separation.sep();
    }
}


