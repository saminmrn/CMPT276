package main;

import javax.swing.*;

public class main {
    public static void main(String [] args)
    {
        Simulator simulator= new Simulator();
        simulator.game_setup();
        simulator.startGameThread();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minions Laboratory Game");
        window.pack();
        window.setSize(simulator.get_screen_width()+16, simulator.get_screen_height()+38);
        window.add(simulator);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
