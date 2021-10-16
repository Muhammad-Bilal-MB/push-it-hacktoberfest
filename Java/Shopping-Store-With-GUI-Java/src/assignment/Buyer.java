package assignment;

import gui.Login;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Buyer extends User {

    private int rating;

    public Buyer(String id, String username, String password, String name, String emailAddress, String ContactNo, String address, int rating) {
        super(id, username, password, name, emailAddress, ContactNo, address);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<Item> viewItemsOnSale() {
        int counter = 1;
        ArrayList<Item> items = MyReader.getItems();
        ArrayList<Item> myItems = new ArrayList();
        for (Item item : items) {
            if (item.getAvalibility()==true) {
                myItems.add(item);
                counter++;
            }
        }
        return myItems;
    }

    
//    === added new methods
    public double addCredit(double amount) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("BuyerAccountDetails.txt"));
            String strLine;
            String newLine = "";
            StringBuilder fileContent = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                String parts[] = strLine.split("-");
                if (parts.length > 0) {
                    if (parts[1].equals(getId())) {
                        double bl = Double.parseDouble(parts[0]) + amount;
                        newLine = bl + "-" + parts[1];
                        fileContent.append(newLine);
                        fileContent.append("\n");
                    } else {
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }

            }
            FileWriter fstreamWrite = new FileWriter("BuyerAccountDetails.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return amount;
    }

    public boolean hasEnoughCredit(double amount) {
        String strLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader("BuyerAccountDetails.txt"));
            while ((strLine = br.readLine()) != null) {
                String parts[] = strLine.split("-");

                if (getId().equals(String.valueOf(parts[1])) && (Double.parseDouble(parts[0])) >= amount) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }
    
    
    public int deductSeccuessFees(double amount) {
        if (!this.hasEnoughCredit(amount)) {
            return -1;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader("BuyerAccountDetails.txt"));
            String strLine;
            String newLine = "";
            System.out.println("Buyer.java deduct....");
            System.out.println("item price: " + amount);

            StringBuilder fileContent = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                String parts[] = strLine.split("-");
                if (parts.length > 0) {
                    if (parts[1].equals(getId()) && amount < Double.valueOf(parts[0])) {

                        double bl = Double.parseDouble(parts[0]) - amount;
                        newLine = bl + "-" + parts[1];
                        fileContent.append(newLine);
                        fileContent.append("\n");
                    } else {
                        fileContent.append(strLine);
                        fileContent.append("\n");

                    }
                }

            }

            FileWriter fstreamWrite = new FileWriter("BuyerAccountDetails.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 1;
    }

    
//    ======================================

    public ArrayList<Item> viewItemsByCategory(String category) {

        ArrayList<Item> items = MyReader.getItems();
        ArrayList<Item> myItems = new ArrayList<Item>();
        for (Item item : items) {
            if ((category.equals("All Categories")&& (item.getAvalibility()==true)) || (item.getItemCategory().equals(category)&& (item.getAvalibility() == true))){
                myItems.add(item);
            }
        }

        return myItems;
    }

    public int purchaseItem(Item item) {

        File buy = new File("PurchasedItems.txt");
        String line = item.toString();
        int t = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(buy, true))) {

            
            Buyer b = MyReader.getBuyers().get(Login.currUserId - 1);
            t = b.deductSeccuessFees(Item.calculateSucessFees(item.getItemPrice()));
            if (t == 1) {
                writer.write(getId() + "-" + line + System.getProperty("line.separator"));
                item.setBought();
            }
            incrementRating();
        } catch (Exception e) {
            System.out.println("Files can not be opened");
        }
        return t;

    }

    public ArrayList<BoughtItem> viewOwnBoughtItems() {
        ArrayList<BoughtItem> items = MyReader.getPurchasedItems();
        ArrayList<BoughtItem> myItems = new ArrayList<BoughtItem>();
        for (BoughtItem item : items) {
            String[] parts = item.toString().split("-");
            if (String.valueOf(parts[0]).equals(getId())) {
                myItems.add(item);
            }

        }
        return myItems;
    }

    public void editDetails(String name, String password, String emailAddress, String address, String contactNo) {
        try {

            BufferedReader br = new BufferedReader(new FileReader("Buyer.txt"));
            String currentLine;

            StringBuilder fileContent = new StringBuilder();
            //Read File Line By Line
            while ((currentLine = br.readLine()) != null) {

                String parts[] = currentLine.split("-");
                if (parts.length > 0) {
                    // Here parts[0] will have value of ID
                    if (parts[0].equals(getId())) {
                        parts[2] = password;
                        parts[3] = name;
                        parts[4] = emailAddress;
                        parts[5] = contactNo;
                        parts[6] = address;
                        String newLine = parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3] + "-" + parts[4] + "-" + parts[5] + "-" + parts[6] + "-" + parts[7];
                        fileContent.append(newLine);
                        fileContent.append("\n");
                    } else {
                        // update content as it is
                        fileContent.append(currentLine);
                        fileContent.append("\n");
                    }
                }
            }
            // Now fileContent will have updated content , which you can override into file
            FileWriter w = new FileWriter("Buyer.txt");
            BufferedWriter out = new BufferedWriter(w);
            out.write(fileContent.toString());
            out.close();
            //Close the input stream
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        
    }

    public void incrementRating() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Buyer.txt"));
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            int counter = 1;
            while ((strLine = br.readLine()) != null) {
                String parts[] = strLine.split("-");
                if (parts.length > 0) {
                    if (counter == Integer.parseInt(getId())) {
                        int total = Integer.valueOf(parts[7]) + 1;

                        String newLine = parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3] + "-" + parts[4] + "-" + parts[5] + "-" + parts[6] + "-" + total;
                        fileContent.append(newLine);
                        fileContent.append("\n");
                    } else {
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
                counter++;
            }
            FileWriter fstreamWrite = new FileWriter("Buyer.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

}
