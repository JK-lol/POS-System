/*
Inheritance: Inherite itemNames and itemQTY from superclass item.java 

Inheritance 2: Inherite the interface ActionListener from item.java
  
File rewrite: Rewrite the info in file.txt based on superclass method update_file() on line 304 in item.java  
 
Interface: All the actions for action Listener is specified starting from line 81
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class add_item extends item{

    private static Choice choice;
    private static Font displayFont;
    private static JTextField add_qty;
    private static JButton addqtyButton;
    private static JButton close;
    private static JFrame additem_frame;
    private static JPanel additem_panel;

    
    public add_item(){

    itemframe.dispose();
    new item();

    additem_frame = new JFrame();
    additem_panel = new JPanel();
    additem_frame.setTitle("Add item quantity");

    displayFont = new Font("Times New Roman",Font.CENTER_BASELINE,15);

    additem_frame.setSize(300,300);
    additem_frame.add(additem_panel);

    additem_panel.setLayout(null);
    additem_frame.setLocationRelativeTo(null);

    choice = new Choice();
    choice.setSize(150,40);
    choice.setFont(displayFont);
    choice.setBounds(70, 50, 150, 40);
    choice.add(super.itemNames[0]);
    choice.add(super.itemNames[1]);
    choice.add(super.itemNames[2]);
    choice.add(super.itemNames[3]);
    choice.add(super.itemNames[4]);
    choice.add(super.itemNames[5]);
    choice.add(super.itemNames[6]);
    choice.add(super.itemNames[7]);

    add_qty = new JTextField(20);
    add_qty.setFont(displayFont);
    add_qty.setBounds(70,100,75,20);
    additem_panel.add(add_qty);

    addqtyButton = new JButton("Add");
    addqtyButton.setFont(displayFont);
    addqtyButton.setBounds(160,100,60,20);
    addqtyButton.addActionListener(this);
    additem_panel.add(addqtyButton);

    close = new JButton("Close");
    close.setFont(displayFont);
    close.setBounds(70,140,160,20);
    close.addActionListener(this);
    additem_panel.add(close);


    additem_panel.add(choice);
    
    additem_frame.setVisible(true);

    }

    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource()==addqtyButton)
        {
            String selectedItem= choice.getSelectedItem();
            int quantityToAdd = Integer.parseInt(add_qty.getText());
            
        if(quantityToAdd>=0)
        {
            // Add the quantity of the selected item
            if (selectedItem.equals(itemNames[0])) {
                itemQTY[0] += quantityToAdd;
                pen_stock.setText("Quantity: "+super.itemQTY[0]);
            } else if (selectedItem.equals(itemNames[1])) {
                itemQTY[1] += quantityToAdd;
                pencil_stock.setText("Quantity: "+super.itemQTY[1]);
            } else if (selectedItem.equals(itemNames[2])) {
                itemQTY[2] += quantityToAdd;
                notebook_stock.setText("Quantity: "+super.itemQTY[2]);
            } else if (selectedItem.equals(itemNames[3])) {
                itemQTY[3] += quantityToAdd;
                eraser_stock.setText("Quantity: "+super.itemQTY[3]);
            } else if (selectedItem.equals(itemNames[4])) {
                itemQTY[4] += quantityToAdd;
                tape_stock.setText("Quantity: "+super.itemQTY[4]);
            } else if (selectedItem.equals(itemNames[5])) {
                itemQTY[5] += quantityToAdd;
                ruler_stock.setText("Quantity: "+super.itemQTY[5]);
            } else if (selectedItem.equals(itemNames[6])) {
                itemQTY[6] += quantityToAdd;
                highlighter_stock.setText("Quantity: "+super.itemQTY[6]);
            } else if (selectedItem.equals(itemNames[7])) {
                itemQTY[7] += quantityToAdd;
                stapler_stock.setText("Quantity: "+super.itemQTY[7]);
            }
            update_file();
        }
        
        }
        else if (e.getSource()==close)
        {
            additem_frame.dispose();
            itemframe.dispose();
            new item();
        }
      
        
    }
}