package GUI.EquipmentGUI;

import GUI.Components.DimensionlessGameLabel;
import GUI.GUISettings;
import GUI.Shared.Components.ChangePanel;
import Game.GameManager;
import GUI.EquipmentGUI.Components.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CharactersInfoPanel extends JPanel {

    private final CharacterInfoPanel charactersInfoPanel;
    private final PartyInfoPanel partyInfoPanel;
    private final ChangePanel changeCharacterPanel;
    private final UseItemPanel useItemPanel;
    private final ChangePanel changeBackpackPagePanel;
    private final FlowLayout layout;
    private final DimensionlessGameLabel headline;

    public CharactersInfoPanel(Border border) {
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(15);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        headline = new DimensionlessGameLabel(
                "Info", SwingConstants.CENTER,
                GUISettings.BIG_FONT, Color.WHITE);

        charactersInfoPanel = new CharacterInfoPanel();
        partyInfoPanel = new PartyInfoPanel();
        changeCharacterPanel = new ChangePanel("Next character", "Prev character", _ -> GameManager.getEquipment().changeCharacter(true), _ -> GameManager.getEquipment().changeCharacter(false));
        useItemPanel = new UseItemPanel();
        changeBackpackPagePanel = new ChangePanel("Next page", "Prev page", _ -> GameManager.getEquipment().changeBackpackPage(true), _ -> GameManager.getEquipment().changeBackpackPage(false));

        setEquipmentVisibility(true);

        this.add(headline);
        this.add(charactersInfoPanel);
        this.add(partyInfoPanel);
        this.add(changeCharacterPanel);
        this.add(useItemPanel);
        this.add(changeBackpackPagePanel);
    }

    public void setEquipmentVisibility(boolean isVisible) {
        charactersInfoPanel.setVisible(isVisible);
        changeCharacterPanel.setVisible(isVisible);

        useItemPanel.setVisible(!isVisible);
        changeBackpackPagePanel.setVisible(!isVisible);
    }

    public void refresh() {
        partyInfoPanel.refresh();
        charactersInfoPanel.refresh();
        useItemPanel.refresh();
    }

    public void resize(){
        this.setSize(GUISettings.getResizedValue(GUISettings.SMALL_PANEL_SIZE), GUISettings.getResizedValue(GUISettings.PANEL_SIZE));

        layout.setVgap(GUISettings.getResizedValue(15));

        headline.resize();
        useItemPanel.resize();
        changeCharacterPanel.resize();
        changeBackpackPagePanel.resize();
        charactersInfoPanel.resize();
        partyInfoPanel.resize();
    }

}
