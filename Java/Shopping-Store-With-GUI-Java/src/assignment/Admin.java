package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Admin extends User {

    public Admin(String id, String username, String password, String name, String emailAddress, String ContactNo, String address) {
        super(id, username, password, name, emailAddress, ContactNo, address);
    }

    public String editDetails(String name, String password, String emailAddress, String address, String contactNo) {
        try {

            BufferedReader br = new BufferedReader(new FileReader("Admin.txt"));
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
                        String newLine = parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3] + "-" + parts[4] + "-" + parts[5] + "-" + parts[6];
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
            FileWriter w = new FileWriter("Admin.txt");
            BufferedWriter out = new BufferedWriter(w);
            out.write(fileContent.toString());
            out.close();
            //Close the input stream
            br.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Item> viewItemsOnSale() {
        ArrayList<Item> items = MyReader.getItems();
        ArrayList<Item> myItems = new ArrayList();
        for (Item item : items) {
            String[] parts = item.toString().split("-");

            if (String.valueOf(parts[0]).equals("true")) {
                myItems.add(item);
            }
        }
        return myItems;
    }
   
    public ArrayList<Item> viewSuccessFess() {
        ArrayList<Item> items = MyReader.getItems();
        ArrayList<Item> myItems = new ArrayList();
        for (Item item : items) {
            String[] parts = item.toString().split("-");

            if (String.valueOf(parts[0]).equals("false")) {
                myItems.add(item);
            }
        }
        return myItems;
    }


//    public ArrayList<ItemWithBuyer> viewAllSoldItems() {
//        ArrayList<ItemWithBuyer> items = MyReader.getPurchasedItems();
//        ArrayList<ItemWithBuyer> myItems = new ArrayList<ItemWithBuyer>();
//        for (ItemWithBuyer item : items) {
//            //String line = getId() + "-" + item.toString();
//            String[] parts = item.toString().split("-");
//            myItems.add(item);
//            System.out.println(getId());
//
//        }
//        return myItems;
//    }
}
