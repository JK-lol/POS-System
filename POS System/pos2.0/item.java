/* 
Set item as superclass 

Use of interface: Line 19 (Implements Action Listener) (All the actions is specified at starting from line 287)

File and exception handling (Line 70) // Read all the items from item.txt, then assign variable based on itemNames and itemQTY

File update method (Line 304) (Keep updating all the info in item.txt when there is an increase or a decrease inside the file)

*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.*;
import java.util.*;

public class item implements ActionListener {

  private static JLabel title;
  private static JButton gotoadd;
  private static JButton gotobill;
  private static JButton gotosales;
  
  private static JLabel pen;
  private static JLabel pencil;
  private static JLabel notebook;
  private static JLabel eraser;
  private static JLabel tape;
  private static JLabel ruler;
  private static JLabel highlighter;
  private static JLabel stapler;
  
  private static JLabel pen_in_storetxt;
  private static JLabel pencil_in_storetxt;
  private static JLabel notebook_in_storetxt;
  private static JLabel eraser_in_storetxt;
  private static JLabel tape_in_storetxt;
  private static JLabel ruler_in_storetxt;
  private static JLabel highlighter_in_storetxt;
  private static JLabel stapler_in_storetxt;

  public static JLabel pen_stock;
  public static JLabel pencil_stock;
  public static JLabel notebook_stock;
  public static JLabel eraser_stock;
  public static JLabel tape_stock;
  public static JLabel ruler_stock;
  public static JLabel highlighter_stock;
  public static JLabel stapler_stock;

  
  //private static LineBorder boarder;  
  
  protected String[] itemNames = {"Pen","Pencil","Notebook","Eraser","Tape","Ruler","Highlighter","Stapler"};
  protected int[] itemQTY = new int[itemNames.length];
  private static LineBorder boarder;

  private static Font big_font;
  private static Font title_font;

  protected static JFrame itemframe;
  protected static JPanel itemPanel;


    
    public item()
    {
      int i;
        String fileName = "item.txt";
        for (i = 0; i < itemQTY.length; i++) 
        {
            itemQTY[i] = -1; // Set default value to -1 (if item not found)
        }
        
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              // Split the line into parts
              String[] parts = line.split("\\s+"); // Split on any number of whitespace characters
              // The first part is the item name, and the second part is the quantity
              String name = parts[0];
              int qty = Integer.parseInt(parts[1]);
              for (i=0;i<itemNames.length;i++)
              {
              if (name.equals(itemNames[i])) {
                // Item was found
                itemQTY[i] = qty;
                break;
              }
            }
            }
            scanner.close();
          } 
          catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
          }
      
      
      itemframe = new JFrame();
      itemPanel = new JPanel();
      itemframe.setTitle("Item Information");

      big_font = new Font("Times New Roman",Font.BOLD,30);
      title_font = new Font("Arial Black",Font.BOLD,40);
      boarder = new LineBorder(Color.BLACK, 2);
      itemframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        pen = new JLabel();
        pen.setIcon(new ImageIcon("pen.png"));
        pen.setBounds(45, 25,130,130);
        pen.setBorder(boarder);
        itemPanel.add(pen);


        pencil = new JLabel();
        pencil.setIcon(new ImageIcon("pencil.png"));
        pencil.setBounds(530, 25,130,130);
        pencil.setBorder(boarder);
        itemPanel.add(pencil);

        notebook = new JLabel();
        notebook.setIcon(new ImageIcon("notebook.png"));
        notebook.setBounds(1015, 25,130,130);
        notebook.setBorder(boarder);
        itemPanel.add(notebook);

        eraser = new JLabel();
        eraser.setIcon(new ImageIcon("eraser.png"));
        eraser.setBounds(45, 230,130,130);
        eraser.setBorder(boarder);
        itemPanel.add(eraser);

        tape = new JLabel();
        tape.setIcon(new ImageIcon("tape.png"));
        tape.setBounds(530, 230,130,130);
        tape.setBorder(boarder);
        itemPanel.add(tape);
        
        ruler = new JLabel();
        ruler.setIcon(new ImageIcon("ruler.png"));
        ruler.setBounds(1015, 230,130,130);
        ruler.setBorder(boarder);
        itemPanel.add(ruler);

        highlighter = new JLabel();
        highlighter.setIcon(new ImageIcon("highlighter.png"));
        highlighter.setBounds(45, 435,130,130);
        highlighter.setBorder(boarder);
        itemPanel.add(highlighter);

        stapler = new JLabel();
        stapler.setIcon(new ImageIcon("stapler.png"));
        stapler.setBounds(530, 435,130,130);
        stapler.setBorder(boarder);
        itemPanel.add(stapler);



     //Frame size, background color
      itemframe.setSize(1500,800);
      itemPanel.setBackground(Color.lightGray);
      itemframe.add(itemPanel);
      itemPanel.setLayout(null);
      itemframe.setLocationRelativeTo(null);



      // pen label
      pen_in_storetxt = new JLabel(itemNames[0]);
      pen_in_storetxt.setFont(big_font);
      pen_in_storetxt.setBounds(185,25,150,35);
      itemPanel.add(pen_in_storetxt);

      pencil_in_storetxt = new JLabel(itemNames[1]);
      pencil_in_storetxt.setFont(big_font);
      pencil_in_storetxt.setBounds(670,25,150,25);
      itemPanel.add(pencil_in_storetxt);

      notebook_in_storetxt = new JLabel(itemNames[2]);
      notebook_in_storetxt.setFont(big_font);
      notebook_in_storetxt.setBounds(1155,25,150,25);
      itemPanel.add(notebook_in_storetxt);

      eraser_in_storetxt = new JLabel(itemNames[3]);
      eraser_in_storetxt.setFont(big_font);
      eraser_in_storetxt.setBounds(185,230,150,25);
      itemPanel.add(eraser_in_storetxt);

      tape_in_storetxt = new JLabel(itemNames[4]);
      tape_in_storetxt.setFont(big_font);
      tape_in_storetxt.setBounds(670,230,150,35);
      itemPanel.add(tape_in_storetxt);

      ruler_in_storetxt = new JLabel(itemNames[5]);
      ruler_in_storetxt.setFont(big_font);
      ruler_in_storetxt.setBounds(1155,230,150,35);
      itemPanel.add(ruler_in_storetxt);

      highlighter_in_storetxt = new JLabel(itemNames[6]);
      highlighter_in_storetxt.setFont(big_font);
      highlighter_in_storetxt.setBounds(185,435,180,35);
      itemPanel.add(highlighter_in_storetxt);

      stapler_in_storetxt = new JLabel(itemNames[7]);
      stapler_in_storetxt.setFont(big_font);
      stapler_in_storetxt.setBounds(670,435,180,35);
      itemPanel.add(stapler_in_storetxt);


      //Stock quantity display
      pen_stock = new JLabel("Quantity: "+itemQTY[0]);
      pen_stock.setFont(big_font);
      pen_stock.setBounds(185,55,270,45);
      itemPanel.add(pen_stock);

      pencil_stock = new JLabel("Quantity: "+itemQTY[1]);
      pencil_stock.setFont(big_font);
      pencil_stock.setBounds(670,55,270,45);
      itemPanel.add(pencil_stock);

      notebook_stock = new JLabel("Quantity: "+itemQTY[2]);
      notebook_stock.setFont(big_font);
      notebook_stock.setBounds(1155,55,270,45);
      itemPanel.add(notebook_stock);

      eraser_stock = new JLabel("Quantity: "+itemQTY[3]);
      eraser_stock.setFont(big_font);
      eraser_stock.setBounds(185,255,270,45);
      itemPanel.add(eraser_stock);
      
      tape_stock = new JLabel("Quantity: "+itemQTY[4]);
      tape_stock.setFont(big_font);
      tape_stock.setBounds(670,265,270,45);
      itemPanel.add(tape_stock);

      ruler_stock = new JLabel("Quantity: "+itemQTY[5]);
      ruler_stock.setFont(big_font);
      ruler_stock.setBounds(1155,265,270,45);
      itemPanel.add(ruler_stock);

      highlighter_stock = new JLabel("Quantity: "+itemQTY[6]);
      highlighter_stock.setFont(big_font);
      highlighter_stock.setBounds(185,465,270,45);
      itemPanel.add(highlighter_stock);

      stapler_stock = new JLabel("Quantity: "+itemQTY[7]);
      stapler_stock.setFont(big_font);
      stapler_stock.setBounds(670,465,270,45);
      itemPanel.add(stapler_stock);

      title = new JLabel("Item");
      title.setFont(title_font);
      title.setBounds(1300,550,300,300);
      itemPanel.add(title);

      gotoadd = new JButton("Add Item");
      gotoadd.setFont(big_font);
      gotoadd.setBounds(1015,435,230,45);
      gotoadd.addActionListener(this);
      itemPanel.add(gotoadd);

      gotobill = new JButton("Bill");
      gotobill.setFont(big_font);
      gotobill.setBounds(1015,500,230,45);
      gotobill.addActionListener(this);
      itemPanel.add(gotobill);

      gotosales = new JButton("Sales Report");
      gotosales.setFont(big_font);
      gotosales.setBounds(1015,565,230,45);
      gotosales.addActionListener(this);
      itemPanel.add(gotosales);


      itemframe.setVisible(true);
        
    }

    public void actionPerformed (ActionEvent e)
    {
      if (e.getSource()==gotoadd)
      {
        itemframe.dispose();
        new add_item();
        
      }
      if (e.getSource()==gotobill)
      {
        itemframe.dispose();
        new pay_item();
      }
      if (e.getSource()==gotosales)
      {
        itemframe.dispose();
        new sales_report();
      }

    }

    protected void update_file()
    {
      try {
        // Open the file for writing
        PrintWriter writer = new PrintWriter("item.txt", "UTF-8");
      
        // Write the updated values of itemQTY to the file
        for (int i = 0; i < itemNames.length; i++) {
          writer.println(itemNames[i] + " " + itemQTY[i]);
        }
      
        // Close the file
        writer.close();
      } catch (IOException ex) {
        System.out.println("An error occurred while writing to the file.");
        ex.printStackTrace();
      }
    }

    }