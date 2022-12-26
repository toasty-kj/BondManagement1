package View;

public class Separation {
    /**
     * 整形表示時の横の区切り線を表示する
     */
    public void sep(){
        System.out.print("+");
        for (int i=0; i<120; i++){
            System.out.print("-");
        }System.out.println("+");
    }
}
