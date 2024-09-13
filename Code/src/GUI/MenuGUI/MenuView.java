package GUI.MenuGUI;

import GUI.GUISettings;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {

    private final CardLayout layout;
    private final MenuPanel menuPanel;
    private final ChoosePartyPanel choosePartyPanel;
    private final GameOverPanel gameOverPanel;
    private final PlayerWinPanel playerWinPanel;

    public MenuView() {
        layout = new CardLayout();

        //Setting panel
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(GUISettings.heightAndWidth,GUISettings.heightAndWidth));
        this.setBackground(Color.BLACK);

        //Setting cards
        menuPanel = new MenuPanel();
        choosePartyPanel = new ChoosePartyPanel();
        gameOverPanel = new GameOverPanel();
        playerWinPanel = new PlayerWinPanel();

        this.add("Start",menuPanel);
        this.add("Choose",choosePartyPanel);
        this.add("GameOver",gameOverPanel);
        this.add("Win", playerWinPanel);

    }

    public void changeState(int newState){
        switch (newState){
            case MenuState.START -> layout.show(this,"Start");
            case MenuState.CHOOSING_CHARACTER -> layout.show(this,"Choose");
            case MenuState.LOADING_GAME -> layout.show(this,"Loading");
            case MenuState.GAME_OVER -> layout.show(this,"GameOver");
            case MenuState.PLAYER_WIN -> layout.show(this,"Win");
        }
        refresh();
    }

    public void refresh(){
        choosePartyPanel.refresh();

        this.revalidate();
        this.repaint();
    }
}
