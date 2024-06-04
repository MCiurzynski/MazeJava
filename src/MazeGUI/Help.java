/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MazeGUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author golde
 */
public class Help extends JFrame {

    public Help() {
        super("Pomoc");
        setSize(600, 400);
        JTextArea helpText = new JTextArea();
        helpText.setText("""
                            aMAZEing solver to aplikacja służąca do rozwiązywania labiryntów. Poniżej znajduje się opis funkcji dostępnych w aplikacji oraz instrukcje ich użycia.
                         
                            Menu Główne:
                            1. Wczytaj plik tekstowy - Umożliwia załadowanie labiryntu z pliku tekstowego.
                            2. Wczytaj plik binarny - Umożliwia załadowanie labiryntu z pliku binarnego.
                            3. Zapisz labirynt - Umożliwia zapisanie aktualnego obrazu labiryntu do pliku graficznego.
                            4. Zapisz dane - Pozwala zapisać dane tekstowe labiryntu.
                            5. Ustawienia - Otwiera panel ustawień.
                            6. Pomoc - Otwiera niniejszą pomoc.
                         
                            Rozwiązywanie Labiryntu:
                            1. Znajdź ścieżkę - Rozpoczyna proces znajdowania ścieżki w labiryncie.
                         
                            Instrukcja Obsługi:
                            1. Wczytywanie Labiryntu - Kliknij na 'Menu' i wybierz 'Wczytaj plik tekstowy' lub 'Wczytaj plik binarny'.
                            2. Zapisywanie Labiryntu - Kliknij na 'Menu' i wybierz 'Zapisz labirynt'.
                            3. Rozwiązywanie Labiryntu - Upewnij się, że labirynt jest wczytany. Kliknij na 'Menu' i wybierz 'Znajdź ścieżkę'.
                            4. Konfiguracja Aplikacji - Kliknij na 'Menu' i wybierz 'Ustawienia'.
                         
                            Przyciski "Zapisz labirynt", "Zapisz dane" i "Znajdź ścieżkę" są nieaktywne jeśli żaden labirynt nie jest wczytany.
                            """);
        helpText.setEditable(false);
        helpText.setFocusable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(helpText);

        add(scrollPane);
        setVisible(true);
    }
}
