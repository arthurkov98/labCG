package lab1;

import javax.swing.*;

public class Main {
    public static void main(String[] av){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Scene sc = new Scene();
                sc.play();
            }
        });

    }
}
