package util.finder;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

public class WordsFinder extends JTextField implements KeyListener {

    private PressEnterListener pressEnterListener;

    public WordsFinder(PressEnterListener pressEnterListener) {
        super();
        this.pressEnterListener = pressEnterListener;
        addKeyListener(this);
    }

    public boolean containsAtLeastOneSequence(String... sequences) {
        String searchWord = getText().toLowerCase();
        boolean searchingWordIsNotNull = searchWord != null && !searchWord.equals("");
        if (searchingWordIsNotNull) {
            List<String> wordsList = Arrays.asList(sequences);
            for (String word : wordsList) {
                if (word.toLowerCase().contains(searchWord)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            pressEnterListener.onClickEnter();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
