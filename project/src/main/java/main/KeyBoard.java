package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
    Simulator sim;
    public boolean PressedUp, PressedDown, PressedRT, PressedLF = false;

    public KeyBoard(Simulator sim) {
        this.sim = sim;
    }

    // Methods
    @Override
    public void keyTyped(KeyEvent e) {
        //not used
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //Commands for using the > in the states
        if(sim.gameState > sim.pauseState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                sim.ui.setCommandNum(sim.ui.getCommandNum()-1);
                if (sim.ui.getCommandNum() < 0) {
                    sim.ui.setCommandNum(1);
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                sim.ui.setCommandNum(sim.ui.getCommandNum()+1);
                if (sim.ui.getCommandNum() > 1) {
                    sim.ui.setCommandNum(0);
                }
            }
            //ALL the Different Options for the states

            //All state options to start the playgameState
            if (code == KeyEvent.VK_ENTER) {
                if (sim.ui.getCommandNum() == 0 && sim.gameState == sim.titleState || sim.gameState == sim.transitionState) {
                    sim.gameState = sim.playGameState;
                }
                //All state quit options for the states
                if (sim.ui.getCommandNum() == 1 && sim.gameState == sim.titleState ||sim.gameState == sim.gameWinSate || sim.gameState == sim.transitionState ) {
                    System.exit(0);
                }
                //Game Over options
                if (sim.ui.getCommandNum() == 0 && sim.gameState == sim.gameOverSate) {
                    sim.gameState = sim.playGameState;
                    sim.reset();
                    sim.restart();

                }
                if (sim.ui.getCommandNum() == 1 && sim.gameState == sim.gameOverSate) {
                    sim.gameState = sim.titleState;
                    sim.reset();
                    sim.restart();
                }
                //Game Win Screen Options
                if (sim.ui.getCommandNum() == 0 && sim.gameState == sim.gameWinSate) {
                    sim.gameState = sim.playGameState;
                    sim.currentMap = 0;
                    sim.reset();
                    sim.restart();
                }
            }
        }
        if (code==KeyEvent.VK_UP || code==KeyEvent.VK_W)
        {
            PressedUp= true;
        }
        if (code==KeyEvent.VK_DOWN || code==KeyEvent.VK_S)
        {
            PressedDown= true;
        }
        if (code==KeyEvent.VK_LEFT || code==KeyEvent.VK_A)
        {
            PressedLF= true;
        }
        if (code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_D)
        {
            PressedRT= true;
        }
        //Pause Game Shortcut
        if (code==KeyEvent.VK_P)
        {
            if(sim.gameState == sim.playGameState){
                sim.gameState = sim.pauseState;
            }
            else if(sim.gameState == sim.pauseState){
                sim.gameState = sim.playGameState;
            }
        }

 }

    @Override
    public void keyReleased(KeyEvent e) {
        int code= e.getKeyCode();

        if (code==KeyEvent.VK_UP || code==KeyEvent.VK_W)
        {
            PressedUp= false;
        }
        if (code==KeyEvent.VK_DOWN || code==KeyEvent.VK_S)
        {
            PressedDown= false;
        }
        if (code==KeyEvent.VK_LEFT || code==KeyEvent.VK_A)
        {
            PressedLF= false;
        }
        if (code==KeyEvent.VK_RIGHT || code==KeyEvent.VK_D)
        {
            PressedRT= false;
        }
    }
}
