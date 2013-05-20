package GUI;

import GUI.listeners.MenuGFBackgroudColListener;
import GUI.listeners.MenuListener;
import GUI.listeners.RadioMenuListener;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import main.Const;

public class GameMenu extends JMenuBar {

    MenuListener ml = new MenuListener();
    RadioMenuListener rml = new RadioMenuListener();
    MenuGFBackgroudColListener mbc = new MenuGFBackgroudColListener();

    public GameMenu() {
        JMenu menu, submenu;
        JMenuItem menuItem;

        menu = new JMenu("Game");
        {
            submenu = new JMenu(Const.NEW_GAME);
            submenu.setMnemonic(KeyEvent.VK_N);
            {
                menuItem = new JMenuItem(Const.LOCAL_GAME);
                menuItem.addActionListener(ml);
                submenu.add(menuItem);
                menuItem = new JMenuItem(Const.LAN_GAME);
                menuItem.addActionListener(ml);
                submenu.add(menuItem);
            }
            menu.add(submenu);
            menuItem = new JMenuItem(Const.SAVE_GAME);
            menuItem.setMnemonic(KeyEvent.VK_S);
            menuItem.addActionListener(ml);
            menu.add(menuItem);

            menuItem = new JMenuItem(Const.LOAD_GAME);
            menuItem.setMnemonic(KeyEvent.VK_L);
            menuItem.addActionListener(ml);
            menu.add(menuItem);

            menuItem = new JMenuItem(Const.EXIT);
            menuItem.setMnemonic(KeyEvent.VK_Q);
            menuItem.addActionListener(ml);
            menu.add(menuItem);
        }
        this.add(menu);

        menu = new JMenu("Settings");
        {
            submenu = new JMenu("Icons");
            ButtonGroup bg = new ButtonGroup();
            {
                JRadioButtonMenuItem jrbmi = new JRadioButtonMenuItem("Default");
                bg.add(jrbmi);
                submenu.add(jrbmi);
                jrbmi.addActionListener(rml);
                if (Const.getIconType() == null) {
                    jrbmi.setSelected(true);
                }
                
                File iconFolder = new File("icons");
                if (iconFolder.exists()) {
                    for (File file : iconFolder.listFiles()) {
                        if (file.isDirectory()) {
                            jrbmi = new JRadioButtonMenuItem(file.getName());
                            bg.add(jrbmi);
                            submenu.add(jrbmi);
                            jrbmi.addActionListener(rml);
                            if(jrbmi.getActionCommand().equals(Const.getIconType())){
                                jrbmi.setSelected(true);
                            }
                        }
                    }
                }
            }
            menu.add(submenu);


            menuItem = new JMenuItem("Background Color");
            menuItem.addActionListener(mbc);
            menu.add(menuItem);
        }
        this.add(menu);
    }
}
