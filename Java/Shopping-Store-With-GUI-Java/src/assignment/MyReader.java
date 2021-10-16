package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MyReader {

    public static ArrayList<Admin> getAdmins() {
        ArrayList<Admin> admins = new ArrayList<Admin>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Admin.txt"));

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("-");
                Admin admin = new Admin(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
                admins.add(admin);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database ERROR", "Access denied", JOptionPane.ERROR_MESSAGE);
        }
        return admins;
    }

    public static ArrayList<Seller> getSellers() {
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Seller.txt"));

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("-");
                Seller seller = new Seller(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], Integer.parseInt(parts[7]));
                sellers.add(seller);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database ERROR", "Access denied", JOptionPane.ERROR_MESSAGE);
        }
        return sellers;
    }

    public static ArrayList<Buyer> getBuyers() {
        ArrayList<Buyer> buyers = new ArrayList<Buyer>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Buyer.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                Buyer buyer = new Buyer(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], Integer.parseInt(parts[7]));
                buyers.add(buyer);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database ERROR", "Access denied", JOptionPane.ERROR_MESSAGE);
        }

        return buyers;
    }

    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("ItemOnSale.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                Item item = new Item(Boolean.parseBoolean(parts[0]), parts[1], parts[2], Double.parseDouble(parts[3]), parts[4], parts[5], Double.parseDouble(parts[6]));
                item.setId(items.size());
                item.setSellerId(Integer.parseInt(parts[7]));
                items.add(item);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return items;

    }

    public static ArrayList<BoughtItem> getPurchasedItems() {
        ArrayList<BoughtItem> items = new ArrayList<BoughtItem>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("PurchasedItems.txt"));
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                BoughtItem item = new BoughtItem(Boolean.parseBoolean(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4]), parts[5], parts[6], Double.parseDouble(parts[7]));
                item.setId(items.size());
                item.setSellerId(Integer.parseInt(parts[8]));
                item.setBuyerId(Integer.parseInt(parts[0]));
                items.add(item);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return items;

    }

    public static ArrayList<Item> getItemBySellerId(String id) {
        ArrayList<Item> AllItems = getItems();
        ArrayList<Item> Items = new ArrayList<Item>();
        for (Item item : AllItems) {
            if (item.getSellerId() == Integer.parseInt(id)) {
                Items.add(item);

            }
        }
        return Items;
    }

    public static ArrayList<Item> getAvailableItems() {
        ArrayList<Item> items = getItems();
        ArrayList<Item> myItems = new ArrayList<Item>();
        for (Item item : items) {
            if (item.getAvalibility()) {
                myItems.add(item);
            }
        }
        return myItems;
    }

    public static String getSellerNameById(String id) {
        String name ="";
        ArrayList<Seller> AllSellers = getSellers();
        ArrayList<Seller> sellers = new ArrayList<Seller>();
        for (Seller seller : AllSellers) {
            if (seller.getId().equals(id)) {
                name = seller.getName();
            }
        }
        return name;
    }
    
     public static String getBuyerNameById(String id) {
        String name ="";
        ArrayList<Buyer> AllBuyers = getBuyers();
        ArrayList<Buyer> buyers = new ArrayList<Buyer>();
        for (Buyer buyer : AllBuyers) {
            if (buyer.getId().equals(id)) {
                name = buyer.getName();
            }
        }
        return name;
    }
}
