/**
 CSC 210 0900
 GROUP PROJECT: Pizza Store
 Group Members: Nahidul Islam Nafi, Bilal ALi, Aaron Davis
 Due: 12/04/2024
 **/
package com.example.pizzastorev2;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class GalleryScene {

    public String title = "Pizza Shop Gallery";
    String selectedItem = "pizza";

    private static class CartManager {
        public ArrayList<String> cartItemNames = new ArrayList<>();
        public ArrayList<Integer> cartItemQuantity = new ArrayList<>();
        PizzaStore pizzaStore;

        public CartManager(PizzaStore ps){
            pizzaStore = ps;
        }

        public int getQuantityOf(String itemName) {
            int index = cartItemNames.indexOf(itemName);
            if (index != -1) {
                return cartItemQuantity.get(index);
            }
            return 0; // Return 0 if the item is not in the cart
        }

        /**
         * Appends quantity to an item by name.
         * If the item doesn't exist, it adds the item with an initial quantity of 1.
         */
        public void appendQuantityByName(String itemName) {
            int index = cartItemNames.indexOf(itemName);

            if (index != -1) {
                // Item exists, increment quantity
                cartItemQuantity.set(index, cartItemQuantity.get(index) + 1);
            } else {
                // Item doesn't exist, add it with quantity 1
                cartItemNames.add(itemName);
                cartItemQuantity.add(1);
            }
        }

        /**
         * Decreases the quantity of an item by name.
         * If the quantity becomes 0, the item is removed from the cart.
         */
        public void decreaseQuantByName(String itemName) {
            int index = cartItemNames.indexOf(itemName);

            if (index != -1) {
                int currentQuantity = cartItemQuantity.get(index);

                if (currentQuantity > 1) {
                    // Decrease quantity
                    cartItemQuantity.set(index, currentQuantity - 1);
                } else {
                    // Remove the item entirely if quantity reaches 0
                    cartItemNames.remove(index);
                    cartItemQuantity.remove(index);
                }
            } else {
                System.out.println("Item not found in cart.");
            }
        }


        public void logCart() {
            StringBuilder message = new StringBuilder();
            double totalCost = 0;

            // Add header for the cart summary
            message.append("Item | Quantity | Price\n");

            // Iterate through the cart and build the message
            for (int i = 0; i < cartItemNames.size(); i++) {
                String itemName = cartItemNames.get(i);
                int quantity = cartItemQuantity.get(i);
                double price = pizzaStore.getPrice(itemName);

                // Append item details
                message.append(String.format("%s | %d | $%.2f\n", itemName, quantity, price));

                // Accumulate the total cost
                totalCost += (price * quantity);
            }

            // Calculate tax and total cost
            double tax = pizzaStore.calculateTax(totalCost);
            double totalWithTax = pizzaStore.calculateTotalCost(totalCost, tax);

            // Append total to the message
            message.append(String.format("\nSubtotal = $%.2f\n", totalCost));
            message.append(String.format("Tax = $%.2f\n", tax));
            message.append(String.format("Total = $%.2f\n", totalWithTax));

            // Create an Alert window
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cart Summary");
            alert.setHeaderText("Your Cart");
            alert.setContentText(message.toString());

            // Add a button to close the Alert
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }


    public Scene getScene(PizzaStore pizzaStoreData) {

            // Init cart manager
            CartManager cartManager = new CartManager(pizzaStoreData);

            // Create an image display section
            ImageView imageView = new ImageView();
            imageView.setFitWidth(300);
            imageView.setFitHeight(250);
            imageView.setPreserveRatio(true);
            imageView.setImage(new Image(pizzaStoreData.getImageUrl(selectedItem))); // Set the image from the URL

            // Initialize the quantity label
            String labelStyle = "-fx-background-color: #7a3333; -fx-text-fill: white; -fx-padding: 5px;";
            Label quantityLabel = new Label(String.valueOf(0));
            quantityLabel.setStyle(labelStyle);

            // Initialize the Price label.
            Label priceLabel = new Label("");
            priceLabel.setStyle(labelStyle + "-fx-font-size: 20px; " + "-fx-font-weight: bold;" );

            // Combine Quantity and Price
            VBox labelBox = new VBox(5, quantityLabel, priceLabel);
            labelBox.setAlignment(Pos.TOP_RIGHT);
            labelBox.setVisible(false);

            // buttons for incrementing and decrementing the quantity
            Button plusButton = new Button("+");
            plusButton.setOnAction(event -> {
                cartManager.appendQuantityByName(selectedItem);
                int quantity = cartManager.getQuantityOf(selectedItem);
                quantityLabel.setText(String.valueOf(quantity));
            });

            Button minusButton = new Button("-");
            minusButton.setOnAction(event -> {
                cartManager.decreaseQuantByName(selectedItem);
                int quantity = cartManager.getQuantityOf(selectedItem);
                quantityLabel.setText(String.valueOf(quantity));
            });

            // Arrange the buttons vertically below the quantity label
            VBox buttonBox = new VBox(5, plusButton, minusButton);
            buttonBox.setAlignment(Pos.TOP_CENTER); // Center-align the buttons
            buttonBox.setVisible(false);

            // Combine them into a gridPane
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER); // Center the entire grid in the scene
            gridPane.add(imageView, 0, 0);

            // Add the quantity label to the grid (top right corner)
            gridPane.add(labelBox, 0, 0);
            GridPane.setHalignment(labelBox, HPos.RIGHT);
            GridPane.setValignment(labelBox, VPos.TOP);

            // Add the button box to the grid (centered under the label)
            gridPane.add(buttonBox, 1, 0);
            GridPane.setHalignment(buttonBox, HPos.RIGHT);
            GridPane.setValignment(buttonBox, VPos.CENTER);

            // Dropdown menu for item selection
            ComboBox<String> itemDropdown = new ComboBox<>();
            String[] names = pizzaStoreData.getItemNames();
            itemDropdown.getItems().addAll(
                names[0],
                names[1],
                names[2],
                names[3]
            );
            itemDropdown.setPromptText("Select an item");
            itemDropdown.resize(300,20);

            // Update image when an item is selected
            itemDropdown.setOnAction(event -> {
                selectedItem = itemDropdown.getValue();

                if (selectedItem != null) {

                    // show UI
                    labelBox.setVisible(true);
                    buttonBox.setVisible(true);

                    // Replace this with actual image URLs or paths
                    int quantity = cartManager.getQuantityOf(selectedItem);
                    String imageUrl = pizzaStoreData.getImageUrl(selectedItem);
                    System.out.println("image loading...");
                    imageView.setImage(new Image(imageUrl));
                    quantityLabel.setText(String.valueOf(quantity));
                    priceLabel.setText("$"+String.valueOf(pizzaStoreData.getPrice(selectedItem)));
                }

            });

            // Checkout button
            Button checkoutButton = new Button("Checkout");
            checkoutButton.setOnAction(event -> {
                System.out.println("Checkout clicked");
                cartManager.logCart();
                // show complete screen on okay
            });

            // Layout using VBox
            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(
                    gridPane,
                    itemDropdown,
                    checkoutButton
            );

            return new Scene(layout, 700, 360); // Adjust scene size as needed
        }

}
