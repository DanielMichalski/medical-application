package util;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Daniel Michalski
 */

/**
 * Dosyć użyteczna klasa pomagająca w walidacji formalarzy. W kostruktorze podajemy TextComponent
 * (JTextField, JPasswordField) który ma być sprawdzany.
 * Nie mozna w nie wpisać dziwnych znaków oraz nie może przekroczyć maxLength
 */
public class keyDigitalListener extends KeyAdapter {
    private JTextComponent textComponent;
    private int maxLength;

    /**
     * Konstruktor w ktorym podajemy JTextComponent który będziemy sprawdzać
     *
     * @param textComponent Komponent który będziemy sprawdzać
     * @param maxLength     Maksymalna długość znaków jakie możemy wprowadzić w pole tekstowe
     */
    public keyDigitalListener(JTextComponent textComponent, int maxLength) {
        this.textComponent = textComponent;
        this.maxLength = maxLength;
        disableShortcutsKeyboard();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //jesli znak wpisany nie jest to litera lub cyfra to usuwa i wlacza beep. Dodatkowo nie mozna wpisac loginu ani hasla dluzszego niz 30znakow
        if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '\u0008' || textComponent.getText().length() >= maxLength) {
            e.consume();   // usunięcie znaku
            Toolkit.getDefaultToolkit().beep();  // uruchamia dźwięk ostrzegawczy
        }
    }

    private void disableShortcutsKeyboard() {
        textComponent.setTransferHandler(null);
    }

}