package View;

import View.JobList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class MenuSel {
    public int menuSel()throws IOException {
        JobList jobList = new JobList();
        String[] joblist = jobList.jobList();
        String starting = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
        String ending = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        System.out.println("下記のメニューから行いたい数字を入力してください。");
        for (int i=0; i< joblist.length; i++){
            System.out.println(i+". "+joblist[i]);
        }
        System.out.println(ending);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer funcInt = null;
        try{
            funcInt =Integer.parseInt(br.readLine());
            if (funcInt<0){
                System.out.println("0~3の整数を入力してください。");
            }
        } catch (IOException e){
            System.out.println("0~3の整数を入力してください。");
        }
        return funcInt;
    }
}
