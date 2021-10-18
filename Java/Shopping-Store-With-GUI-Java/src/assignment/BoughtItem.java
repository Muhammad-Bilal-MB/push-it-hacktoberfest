package assignment;



public class BoughtItem {

    private boolean avalibility = true;
    private String itemTitle;
    private String itemDescribtion;
    private double itemPrice;
    private String itemCategory;
    private String methodOfDelivery;
    private int sellerId;
    private int buyerId;
    private int id;
    private double successFees=0;

    public BoughtItem(boolean avalibility, String itemTitle, String itemDescribtion, double itemPrice, String itemCategory, String methodOfDelivery,double successFees) {
        this.avalibility = avalibility;
        this.itemTitle = itemTitle;
        this.itemDescribtion = itemDescribtion;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.methodOfDelivery = methodOfDelivery;
        this.successFees = successFees;
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

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public int getId() {
        return id;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public String getMethodOfDelivery() {
        return methodOfDelivery;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }  
     
    
    @Override
    public String toString() {
        return getBuyerId()+"-"+avalibility + "-" + itemTitle + "-" + itemDescribtion + "-" + itemPrice + "-" + itemCategory + "-" + methodOfDelivery + "-" +successFees+"-"+getSellerId() ;
    }

}
