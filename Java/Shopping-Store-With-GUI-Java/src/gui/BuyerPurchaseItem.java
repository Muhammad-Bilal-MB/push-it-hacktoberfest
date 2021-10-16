/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import assignment.Buyer;
import assignment.Item;
import assignment.MyReader;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author xxhackerxx
 */
public class BuyerPurchaseItem extends JFrame {

    JPanel panel;
    Buyer buyer;
    JScrollPane pane;

    public BuyerPurchaseItem(Buyer buyer) {
        this.buyer = buyer;
        showItems();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(554, 450);
        setLocation(380, 180);
        pane = new JScrollPane(panel);
        add(pane);
        
    }

    private void showItems() {
        ArrayList<Item> items = MyReader.getAvailableItems();
        panel = new JPanel(new GridLayout(items.size(), 0));
        panel.setVisible(true);
        for (Item item : items) {
            panel.add(new BuyerItemView(item, buyer));
        }
    }
}
