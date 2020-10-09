package felixsam.github.com.foodordering.Models;

public class Item {
    private int itemId;
    private int userId;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private int orderId;

    public Item(int itemId, int userId, String name, double price, int quantity, String category, int orderId) {
        this.itemId = itemId;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.orderId = orderId;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }








}
