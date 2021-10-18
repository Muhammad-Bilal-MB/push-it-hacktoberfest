package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Seller extends User {

    private int rating;

    public Seller(String id, String username, String password, String name, String emailAddress, String ContactNo, String address, int rating) {
        super(id, username, password, name, emailAddress, ContactNo, address);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void addItem(Item item) {
        DecimalFormat df2 = new DecimalFormat(".##"); // to format the double variable(successFees) to 2 dp only
        try {
            File writer = new File("ItemOnSale.txt");

            // if file doesnt exists, then create it
            if (!writer.exists()) {
                writer.createNewFile();
            }

            FileWriter fw = new FileWriter(writer.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            String parts[] = item.toString().split("-");
            Double successFees = Double.parseDouble(parts[6]); // to get the success fees from the toString method
            String line = parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3] + "-" + parts[4] + "-" + parts[5] + "-" + df2.format(successFees);
            bw.write(line + "-" + getId() + System.lineSeparator());
            bw.close();
            incrementRating();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public ArrayList<Item> viewOwnSoldItems() {
        String line;

        ArrayList<Item> items = MyReader.getItems();
        ArrayList<Item> soldItems = new ArrayList<Item>();

        for (Item item : items) {
            if (item.getSellerId() == Integer.parseInt(getId()) && (item.getAvalibility() == false)) {
                soldItems.add(item);
            }
        }

        return soldItems;
    }

    public ArrayList<Item> viewOwnListedItems() {
        String line;
        ArrayList<Item> items = MyReader.getItems();
        ArrayList<Item> listedItems = new ArrayList<Item>();

        for (Item item : items) {
            if (item.getSellerId()== Integer.parseInt(getId())) {
                listedItems.add(item);
            }
        }
        return listedItems;
    }

    public String editDetails(String name, String password, String emailAddress, String address, String contactNo) {
        try {

            BufferedReader br = new BufferedReader(new FileReader("Seller.txt"));
            String strLine;
            StringBuilder fileContent = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                String parts[] = strLine.split("-");
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
                        fileContent.append(strLine);
                        fileContent.append("\n");
                    }
                }
            }
            // Now fileContent will have updated content , which you can override into file
            FileWriter fstreamWrite = new FileWriter("Seller.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            //Close the input stream
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

//    public double addCredit(double amount) {
//
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("SellerAccountDetails.txt"));
//            String strLine;
//            String newLine = "";
//            StringBuilder fileContent = new StringBuilder();
//            while ((strLine = br.readLine()) != null) {
//                String parts[] = strLine.split("-");
//                if (parts.length > 0) {
//                    if (parts[1].equals(getId())) {
//                        double bl = Double.parseDouble(parts[0]) + amount;
//                        newLine = bl + "-" + parts[1];
//                        fileContent.append(newLine);
//                        fileContent.append("\n");
//                    } else {
//                        fileContent.append(strLine);
//                        fileContent.append("\n");
//                    }
//                }
//
//            }
//            FileWriter fstreamWrite = new FileWriter("SellerAccountDetails.txt");
//            BufferedWriter out = new BufferedWriter(fstreamWrite);
//            out.write(fileContent.toString());
//            out.close();
//            br.close();
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return amount;
//    }

//    public boolean hasEnoughCredit() {
//        String strLine;
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("SellerAccountDetails.txt"));
//            while ((strLine = br.readLine()) != null) {
//                String parts[] = strLine.split("-");
//
//                if (getId().equals(String.valueOf(parts[1])) && (Double.parseDouble(parts[0])) >= 10) {
//                    return true;
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        return false;
//    }

    public void incrementRating() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Seller.txt"));
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
            FileWriter fstreamWrite = new FileWriter("Seller.txt");
            BufferedWriter out = new BufferedWriter(fstreamWrite);
            out.write(fileContent.toString());
            out.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
//    public void deductSeccuessFees(double amount) {
//        try {
//            BufferedReader br = new BufferedReader(new FileReader("SellerAccountDetails.txt"));
//            String strLine;
//            String newLine = "";
//
//            StringBuilder fileContent = new StringBuilder();
//            while ((strLine = br.readLine()) != null) {
//                String parts[] = strLine.split("-");
//                if (parts.length > 0) {
//                    if (parts[1].equals(getId()) && amount < Double.valueOf(parts[0])) {
//
//                        double bl = Double.parseDouble(parts[0]) - amount;
//                        newLine = bl + "-" + parts[1];
//                        fileContent.append(newLine);
//                        fileContent.append("\n");
//                    } else {
//                        fileContent.append(strLine);
//                        fileContent.append("\n");
//
//                    }
//                }
//
//            }
//
//            FileWriter fstreamWrite = new FileWriter("SellerAccountDetails.txt");
//            BufferedWriter out = new BufferedWriter(fstreamWrite);
//            out.write(fileContent.toString());
//            out.close();
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//}
