// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class InventoryApp {
//     public static void main(String[] args) {
//         // 1. Create the main window frame
//         JFrame frame = new JFrame("College Inventory System");
//         frame.setSize(400, 300);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(null); // Using absolute positioning for simplicity

//         // 2. Create a Text Label
//         JLabel label = new JLabel("Enter Item Name:");
//         label.setBounds(50, 40, 150, 30); // (x, y, width, height)
//         frame.add(label);

//         // 3. Create an Input Text Box
//         JTextField itemTextField = new JTextField();
//         itemTextField.setBounds(180, 40, 150, 30);
//         frame.add(itemTextField);

//         // 4. Create a Button
//         JButton addButton = new JButton("Add Item");
//         addButton.setBounds(120, 100, 120, 40);
//         frame.add(addButton);

//         // 5. Create a Status Label to show output
//         JLabel statusLabel = new JLabel("");
//         statusLabel.setBounds(50, 170, 300, 30);
//         frame.add(statusLabel);

//         // 6. Action Listener: What happens when the button is clicked?
//         addButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String itemName = itemTextField.getText(); // Grab text from input box
                
//                 if (itemName.trim().isEmpty()) {
//                     statusLabel.setText("❌ Error: Item name cannot be empty!");
//                 } else {
//                     statusLabel.setText("✅ Successfully added item: " + itemName);
//                     itemTextField.setText(""); // Clear the input box
//                 }
//             }
//         });

//         // 7. Make the window visible on the screen
//         frame.setVisible(true);
//     }
// }

// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;

// public class InventoryApp {
//     public static void main(String[] args) {
//         JFrame frame = new JFrame("College Inventory System v2.0");
//         frame.setSize(400, 320);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(null);

//         JLabel label = new JLabel("Enter Item Name:");
//         label.setBounds(50, 40, 150, 30);
//         frame.add(label);

//         JTextField itemTextField = new JTextField();
//         itemTextField.setBounds(180, 40, 150, 30);
//         frame.add(itemTextField);

//         JButton addButton = new JButton("Add Item");
//         addButton.setBounds(120, 100, 120, 40);
//         frame.add(addButton);

//         JLabel statusLabel = new JLabel("");
//         statusLabel.setBounds(50, 170, 300, 30);
//         frame.add(statusLabel);

//         // Action Listener linked to local file writing logic
//         addButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String itemName = itemTextField.getText().trim();
                
//                 if (itemName.isEmpty()) {
//                     statusLabel.setText("❌ Error: Item name cannot be empty!");
//                     return;
//                 }

//                 // --- DATA PERSISTENCE LAYER: FILE WRITING ---
//                 // We use a try-with-resources statement to handle file exceptions gracefully
//                 try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv", true))) {
//                     writer.write(itemName);
//                     writer.newLine(); // Move to the next line for the next entry
                    
//                     statusLabel.setText("💾 Saved to hard drive: " + itemName);
//                     itemTextField.setText(""); 
//                 } catch (IOException ex) {
//                     // Critical backend exception catching to prevent the application from crashing
//                     statusLabel.setText("❌ File Error: Could not save data.");
//                     ex.printStackTrace();
//                 }
//             }
//         });

//         frame.setVisible(true);
//     }
// }

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class InventoryApp {
    private static final String FILE_NAME = "inventory.csv";

    public static void main(String[] args) {
        // Main Dashboard Frame
        JFrame frame = new JFrame("Enterprise Inventory Dashboard v5.0");
        frame.setSize(850, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // --- LEFT COLUMN: INPUT CONTROLS ---
        JLabel titleLabel = new JLabel("Inventory Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(40, 20, 200, 30);
        frame.add(titleLabel);

        JLabel nameLabel = new JLabel("Item Name:");
        nameLabel.setBounds(40, 70, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(140, 70, 180, 25);
        frame.add(nameField);

        JLabel qtyLabel = new JLabel("Quantity:");
        qtyLabel.setBounds(40, 110, 100, 25);
        frame.add(qtyLabel);

        JTextField qtyField = new JTextField();
        qtyField.setBounds(140, 110, 180, 25);
        frame.add(qtyField);

        JLabel priceLabel = new JLabel("Price (₹):");
        priceLabel.setBounds(40, 150, 100, 25);
        frame.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(140, 150, 180, 25);
        frame.add(priceField);

        JButton addButton = new JButton("Add Record");
        addButton.setBounds(40, 200, 130, 35);
        frame.add(addButton);

        JButton viewButton = new JButton("Refresh View");
        viewButton.setBounds(190, 200, 130, 35);
        frame.add(viewButton);

        // --- RIGHT COLUMN: SEARCH & LIVE MONITOR ---
        JLabel searchLabel = new JLabel("Search Keyword:");
        searchLabel.setBounds(400, 20, 120, 25);
        frame.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setBounds(510, 20, 180, 25);
        frame.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(700, 19, 90, 26);
        frame.add(searchButton);

        // Display Console with Scroll Pane
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(400, 60, 390, 360);
        frame.add(scrollPane);

        // --- FOOTER STATS PANEL ---
        JLabel statusLabel = new JLabel("System Status: Ready");
        statusLabel.setBounds(40, 440, 340, 30);
        frame.add(statusLabel);

        JLabel valuationLabel = new JLabel("Total Asset Valuation: ₹0.00");
        valuationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        valuationLabel.setBounds(400, 440, 390, 30);
        frame.add(valuationLabel);

        // =========================================================
        // FEATURE ACTION LOGIC BLOCK
        // =========================================================

        // ACTION 1: ADD RECORD + INPUT VALIDATION (Features 1 & 2)
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String qtyInput = qtyField.getText().trim();
                String priceInput = priceField.getText().trim();

                if (name.isEmpty() || qtyInput.isEmpty() || priceInput.isEmpty()) {
                    statusLabel.setText("<html><font color='red'>❌ Fill all fields!</font></html>");
                    return;
                }

                int quantity;
                double price;
                try {
                    quantity = Integer.parseInt(qtyInput);
                    price = Double.parseDouble(priceInput);
                    if (quantity < 0 || price < 0) {
                        statusLabel.setText("<html><font color='red'>❌ Metrics can't be negative!</font></html>");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    statusLabel.setText("<html><font color='red'>❌ Invalid numeric inputs!</font></html>");
                    return;
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                    writer.write(name + "," + quantity + "," + price);
                    writer.newLine();
                    statusLabel.setText("<html><font color='green'>💾 Record written to disk.</font></html>");
                    
                    nameField.setText("");
                    qtyField.setText("");
                    priceField.setText("");
                    
                    // Auto-refresh layout stats after adding
                    viewButton.doClick();
                } catch (IOException ex) {
                    statusLabel.setText("❌ File system access fault.");
                }
            }
        });

        // ACTION 2 & 5: VIEW DATA + FINANCIAL VALUATION PARSER (Features 3 & 5)
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(""); // Wipe old screen console view
                displayArea.append(String.format("%-20s %-10s %-10s\n", "ITEM NAME", "QTY", "PRICE (₹)") + "\n");
                displayArea.append("------------------------------------------\n");

                double totalValuation = 0.0;
                File file = new File(FILE_NAME);
                if (!file.exists()) {
                    statusLabel.setText("System Info: Database empty.");
                    return;
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data.length == 3) {
                            String name = data[0];
                            int qty = Integer.parseInt(data[1]);
                            double price = Double.parseDouble(data[2]);

                            // Math parsing layer execution
                            totalValuation += (qty * price);

                            displayArea.append(String.format("%-20s %-10d ₹%-10.2f\n", name, qty, price));
                        }
                    }
                    valuationLabel.setText(String.format("Total Asset Valuation: ₹%.2f", totalValuation));
                } catch (Exception ex) {
                    statusLabel.setText("❌ Database processing fault.");
                }
            }
        });

        // ACTION 4: SEARCH ENGINE REGEX FILTERING (Feature 4)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText().trim().toLowerCase();
                if (keyword.isEmpty()) {
                    viewButton.doClick(); // Reset view if search string is clear
                    return;
                }

                displayArea.setText("");
                displayArea.append("🔍 Showing filtered records for: '" + keyword + "'\n\n");
                displayArea.append(String.format("%-20s %-10s %-10s\n", "ITEM NAME", "QTY", "PRICE (₹)") + "\n");
                displayArea.append("------------------------------------------\n");

                try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data.length == 3 && data[0].toLowerCase().contains(keyword)) {
                            displayArea.append(String.format("%-20s %-10d ₹%-10.2f\n", data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2])));
                        }
                    }
                } catch (Exception ex) {
                    statusLabel.setText("❌ Filter engine processing error.");
                }
            }
        });

        frame.setVisible(true);
        // Execute an initial data load right when the app opens
        viewButton.doClick();
    }
}