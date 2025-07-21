package GUI.FightGUI.Components;

import Equipment.Items.UsableItem;
import GUI.Components.GameButton;
import Game.GameActionQueue;
import Game.GameManager;
import Game.PlayerInfo;

import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class BackpackCardPanel extends CardPanel{

    private final static int backpackButtonHGap = buttonHGap / 2;
    private final static int backpackButtonVGap = buttonVGap / 3;
    private final int pageSize = 8; //Plus goBackButton
    private final int itemButtonHeight = buttonHeight / 2;
    private final GameButton next, prev;
    private int pageNumber;
    private ArrayList<UsableItem> items;
    private final GoBackCallBack goBack;

    public BackpackCardPanel(Border border, ArrayList<GameButton> buttons, String goBackName, GoBackCallBack goBack) {
        super(border, buttons, goBackName, goBack);
        getGoBackButton().setPreferredSize(new Dimension(buttonWidth, itemButtonHeight));
        getGoBackButton().setMargin(new Insets(0, 0, 0, 0));

        getFlowLayout().setHgap(backpackButtonHGap);
        getFlowLayout().setVgap(backpackButtonVGap / 3);

        next = new GameButton(
                "Next",
                buttonWidth, itemButtonHeight,
                _ -> GameActionQueue.action(this::next)
        );

        prev = new GameButton(
                "Prev",
                buttonWidth, itemButtonHeight,
                _ -> GameActionQueue.action(this::prev)
        );

        this.goBack=goBack;
    }

    public void reset(ArrayList<UsableItem> items) {
        this.items = items;
        pageNumber = 0;
        ArrayList<GameButton> buttons = new ArrayList<>();
        //Check if add next button
        int itemCount = items.size() <= pageSize ? items.size() : pageSize - 1;
        //Loading action
        for (int i = 0; i < itemCount; i++) {
            UsableItem item = items.get(i);
            GameButton button = new GameButton(
                    item.shortName,
                    buttonWidth, itemButtonHeight,
                    _ -> GameActionQueue.action(()->{
                        GameManager.getFight().chosenAction(item.getAction());
                        PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                        changePage();
                    })
            );
            button.addMouseListener(new ButtonItemMouseListener(item.name));
            button.resize();
            buttons.add(button);
        }
        if (items.size() > pageSize)
            buttons.add(next);
        loadNewAction(buttons);
    }

    public void resize(){
        super.resize();

        next.resize();
        prev.resize();
    }

    private void next() {
        //check if there is more items on next page
        if (items.size() <= pageSize)
            return;
        int startIndex, itemCount;
        startIndex = pageNumber == 0 ? 0 : ((pageSize - 1) + (pageNumber - 1) * (pageSize - 2));
        if (startIndex + pageSize - 1 >= items.size())
            return;
        pageNumber++;
        ArrayList<GameButton> buttons = new ArrayList<>();
        startIndex += pageNumber == 1 ? (pageSize - 1) : (pageSize - 2);
        itemCount = (startIndex + pageSize - 1) >= items.size() ? (items.size() - startIndex) : (pageSize - 2);

        //Loading action
        for (int i = startIndex; i < startIndex + itemCount; i++) {
            UsableItem item = items.get(i);
            GameButton button = new GameButton(
                    item.shortName,
                    buttonWidth, itemButtonHeight,
                    _ -> GameActionQueue.action(()->{
                        GameManager.getFight().chosenAction(item.getAction());
                        PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                        changePage();
                    })
            );
            button.addMouseListener(new ButtonItemMouseListener(item.name));
            button.resize();
            buttons.add(button);
        }
        buttons.add(prev);
        if (items.size() > startIndex + itemCount)
            buttons.add(next);
        loadNewAction(buttons);
    }

    private void prev() {
        if (pageNumber == 0)
            return;
        pageNumber--;
        int startIndex, itemCount;
        ArrayList<GameButton> buttons = new ArrayList<>();
        if (pageNumber == 0) {
            startIndex = 0;
            itemCount = pageSize - 1;
        } else {
            startIndex = ((pageSize - 1) + (pageNumber - 1) * (pageSize - 2));
            itemCount = (startIndex + pageSize - 1) >= items.size() ? (items.size() - startIndex) : (pageSize - 2);
        }
        //Loading action
        for (int i = startIndex; i < startIndex + itemCount; i++) {
            UsableItem item = items.get(i);
            GameButton button = new GameButton(
                    item.shortName,
                    buttonWidth, itemButtonHeight,
                    _ -> GameActionQueue.action(()->{
                        GameManager.getFight().chosenAction(item.getAction());
                        PlayerInfo.getParty().getBackpack().removeFromBackpack(item);
                        changePage();
                    })
            );
            button.addMouseListener(new ButtonItemMouseListener(item.name));
            button.resize();
            buttons.add(button);
        }
        if (pageNumber > 0)
            buttons.add(prev);
        if ((startIndex + pageSize - 1) < items.size())
            buttons.add(next);
        loadNewAction(buttons);
    }

    private void changePage() {
        goBack.goBack("Start");
    }
}
