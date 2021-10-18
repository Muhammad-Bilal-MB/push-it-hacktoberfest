package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Item {

    private boolean avalibility = true;
    private String itemTitle;
    private String itemDescribtion;
    private double itemPrice;
    private String itemCategory;
    private String methodOfDelivery;
    private int sellerId;
//    private int BuyerId;
    private int itemId;
    private double successFees;
    private String filename;

    public Item(boolean avalibility, String itemTitle, String itemDescribtion, double itemPrice, String itemCategory, String methodOfDelivery, double successFees) {
        this.avalibility = avalibility;
        this.itemTitle = itemTitle;
        this.itemDescribtion = itemDescribtion;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.methodOfDelivery = methodOfDelivery;
        this.successFees = successFees;
        //this.filename = filename;
    }

    public boolean getAvalibility() {
        return avalibility;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescribtion() {
        return itemDescribtion;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getId() {
        return itemId;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getMethodOfDelivery() {
        return methodOfDelivery;
    }

    public double getSuccessFees() {
        return successFees;
    }
    
    public String getFile(){
        return filename;
    }

    public void setId(int itemId) {
        this.itemId = itemId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    
    public void setFile(String fileName){
        this.filename = fileName;
    }

    public void setBought() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("ItemOnSale.txt"));
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            int counter = 0;
            while ((strLine = br.readLine()) != null) {
                String parts[] = strLine.split("-");
                if (parts.length > 0) {
                    if (counter == getId()) {

                        String newLine = false + "-" + parts[1] + "-" + parts[2] + "-" + parts[3] + "-" + parts[4] + "-" + parts[5] + "-" + parts[6] + "-" + parts[7];
                        fileContent.append(newLine);
                        fileContent.append("\n");
                    } else {
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
                counter++;
            }
            FileWriter fstreamWrite = new FileWriter("ItemOnSale.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static double calculateSucessFees(double amount) {
        double cal = 0;
        if (amount >= 5 && amount <= 100) {
            cal = amount * 0.05;
        } else if (amount >= 101 && amount <= 1000) {
            cal = amount * 0.10;
        } else if (amount > 1000) {
            cal = amount * 0.15;
        }
        return cal + amount;
    }

    @Override
    public String toString() {
        return avalibility + "-" + itemTitle + "-" + itemDescribtion + "-" + itemPrice + "-" + itemCategory + "-" + methodOfDelivery + "-" + successFees + "-" + getSellerId();
    }

}
