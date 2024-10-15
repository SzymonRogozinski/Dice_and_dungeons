package GUI.EquipmentGUI;

import Character.PlayerCharacter;
import Equipment.CharacterEquipment;
import GUI.Compents.DimensionlessGameLabel;
import GUI.Compents.GameButton;
import GUI.Compents.GameLabel;
import GUI.Compents.GameProgressBar;
import GUI.GUISettings;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class CharactersInfoPanel extends JPanel {

    private static final ImageIcon BAG_SLOT_ICON = new ImageIcon("Texture/EmptySlots/slot-bag.png");

    private final CharacterInfoPanel charactersInfoPanel;
    private final PartyInfoPanel partyInfoPanel;
    private final ChangePanel changeCharacterPanel;
    private final UseItemPanel useItemPanel;
    private final ChangePanel changeBackpackPagePanel;

    public CharactersInfoPanel(Border border) {
        this.setSize(GUISettings.SMALL_PANEL_SIZE, GUISettings.PANEL_SIZE);
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setVgap(15);
        this.setLayout(layout);
        this.setBackground(Color.BLACK);
        this.setBorder(border);

        DimensionlessGameLabel headline = new DimensionlessGameLabel(
                "Info", SwingConstants.CENTER,
                GUISettings.BIG_FONT, Color.WHITE);

        charactersInfoPanel = new CharacterInfoPanel();
        partyInfoPanel = new PartyInfoPanel();
        changeCharacterPanel = new ChangePanel("Next character", "Previous character", _ -> GameManager.getEquipment().changeCharacter(true), _ -> GameManager.getEquipment().changeCharacter(false));
        useItemPanel = new UseItemPanel();
        changeBackpackPagePanel = new ChangePanel("Next page", "Previous page", _ -> GameManager.getEquipment().changeBackpackPage(true), _ -> GameManager.getEquipment().changeBackpackPage(false));

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

    public class CharacterInfoPanel extends JPanel {

        //name,strength,endurance,intelligence,charisma, cunning,luck
        private final GameLabel[] statisticLabels;

        public CharacterInfoPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, (int) (GUISettings.PANEL_SIZE * 0.25)));
            FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            statisticLabels = new GameLabel[7];
            for (int i = 0; i < 7; i++) {
                statisticLabels[i] = new GameLabel(
                        "",
                        SwingConstants.LEFT,
                        GUISettings.SMALL_PANEL_SIZE - 20,
                        GUISettings.SMALL_PANEL_SIZE / 10,
                        Color.WHITE
                );
                this.add(statisticLabels[i]);
            }
        }

        public void refresh() {
            PlayerCharacter player = GameManager.getEquipment().getCurrentCharacter();
            statisticLabels[0].setText(player.getName());
            statisticLabels[1].setText("Strength: " + player.getStrength());
            statisticLabels[2].setText("Endurance: " + player.getEndurance());
            statisticLabels[3].setText("Intelligence: " + player.getIntelligence());
            statisticLabels[4].setText("Charisma: " + player.getCharisma());
            statisticLabels[5].setText("Cunning: " + player.getCunning());
            statisticLabels[6].setText("Luck: " + player.getLuck());
        }
    }

    public class PartyInfoPanel extends JPanel {

        private final GameProgressBar healthBar, manaBar;

        public PartyInfoPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE / 4));
            FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            DimensionlessGameLabel health = new DimensionlessGameLabel("Party health", SwingConstants.CENTER, Color.WHITE);
            this.add(health);

            healthBar = new GameProgressBar(Color.RED, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
            this.add(healthBar);

            DimensionlessGameLabel mana = new DimensionlessGameLabel("Party mana", SwingConstants.CENTER, Color.WHITE);
            this.add(mana);

            manaBar = new GameProgressBar(Color.BLUE, GUISettings.SMALL_PANEL_SIZE - 6, GUISettings.SMALL_PANEL_SIZE / 8);
            this.add(manaBar);

        }

        public void refresh() {
            healthBar.setMaximum(PlayerInfo.getParty().getMaxHealth());
            manaBar.setMaximum(PlayerInfo.getParty().getMaxMana());
            healthBar.setValue(PlayerInfo.getParty().getCurrentHealth());
            String healthString = PlayerInfo.getParty().getCurrentHealth() + "/" + PlayerInfo.getParty().getMaxHealth();
            if (PlayerInfo.getParty().getShield() > 0)
                healthString += " +" + PlayerInfo.getParty().getShield();
            healthBar.setString(healthString);
            manaBar.setValue(PlayerInfo.getParty().getCurrentMana());
            manaBar.setString(PlayerInfo.getParty().getCurrentMana() + "/" + PlayerInfo.getParty().getMaxMana());
        }
    }

    public class ChangePanel extends JPanel {

        public ChangePanel(String nextButtonText, String prevButtonText, ActionListener nextButtonAction, ActionListener prevButtonAction) {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, GUISettings.PANEL_SIZE / 4));
            FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(1);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            GameButton next = new GameButton(nextButtonText,
                    GUISettings.SMALL_PANEL_SIZE - 20,
                    (int) (GUISettings.PANEL_SIZE * 0.075),
                    nextButtonAction
            );
            next.setMargin(new Insets(0, 0, 0, 0));

            GameButton prev = new GameButton(prevButtonText,
                    GUISettings.SMALL_PANEL_SIZE - 20,
                    (int) (GUISettings.PANEL_SIZE * 0.075),
                    prevButtonAction
            );
            prev.setMargin(new Insets(0, 0, 0, 0));

            this.add(next);
            this.add(prev);
        }
    }

    public class UseItemPanel extends JPanel {

        private final ItemSlot itemSlot;

        public UseItemPanel() {
            this.setPreferredSize(new Dimension(GUISettings.SMALL_PANEL_SIZE - 20, (int) (GUISettings.PANEL_SIZE * 0.25)));
            FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
            layout.setVgap(10);
            this.setLayout(layout);
            this.setBackground(Color.BLACK);

            GameButton useItem = new GameButton(
                    "Use item",
                    GUISettings.SMALL_PANEL_SIZE - 50,
                    (int) (GUISettings.PANEL_SIZE * 0.05),
                    _ -> GameManager.getEquipment().useChosenItem()
            );

            itemSlot = new ItemSlot(null, BAG_SLOT_ICON, 0, CharacterEquipment.USE_SLOT);

            this.add(itemSlot);
            this.add(useItem);
        }

        void refresh() {
            itemSlot.setItem(GameManager.getEquipment().getUseItem());
        }

    }

}
