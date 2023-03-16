/*
!! item_purchased is for invoice.java purpose and item_sold is for sales_report.java purpose

Set as superclass of invoice.java and sales_report.java

Inheritance: Inherite action listener from item.java

Inheritance 2: Inherrite itemQTy from item.java

Inheritance 3 (File handling) (use update_file() on line 304 in item.java)
Keep update and rewrite itemQTY in item.txt whenever an item is purchased and the button 'Pay' (Refer line 854) is clicked

File handling 1 (sales report.txt)
(Line 186: Read all the items from item.txt, then assign variable based on item name and items purchased)
(update_sales()) (File update on sales report.txt) (Line 914) (update sales report whenever button Pay is clicked and save the changes)


 
File handling 2 (temp.txt) used to store temperory message for invoice purpose 
(Line 40: set default value of item_purchased as 0 to reset all the item_purchased each time pay_item() is called)
When the button pay which indicates conform is clicked, current purchased item will stored to the temp.txt and all quantity for 
item purchased will be reset upon each completed transaction (refer update_temp() Line 935)

When button pay is clicked, current value of item_purchased stored in temp.txt will be overwritten with new item_purchased.

Interface: All the details for ActionListener is specified starting on line 642
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.text.*;
import java.io.*;
import java.util.*;

public class pay_item extends item
{
    protected String[] itemNames = {"Pen","Pencil","Notebook","Eraser","Tape","Ruler","Highlighter","Stapler"};
    private static DecimalFormat df;
    protected int[] itempurchased = {0,0,0,0,0,0,0,0,0};
    protected double[] itemprice={2.00,1.50,1.00,2.50,1.50,1.00,3.00,2.50};
    protected int[] itemsold = new int [itemNames.length];
    
    private static JButton reset;
    private static JButton receipt;
    private static JButton gotostock;
    private static JButton gotosales;
    
    //first 4 item image label
    private static JLabel pen;
    private static JLabel pencil;
    private static JLabel notebook;
    private static JLabel eraser;
    
    private static LineBorder boarder;

    //first 4 item name label
    private static JLabel pen_label;
    private static JLabel pencil_label;
    private static JLabel notebook_label;
    private static JLabel eraser_label;

    //fond for pricing
    private static Font p_font;
    private static Font big_font;

    //first 4 item price label
    private static JLabel pen_price;
    private static JLabel pencil_price;
    private static JLabel notebook_price;
    private static JLabel eraser_price;

    //label for quantity
    private static JLabel pen_quantity;
    private static JLabel pencil_quantity;
    private static JLabel notebook_quantity;
    private static JLabel eraser_quantity;

    //interface for editing item quantity pen
    private static JLabel pen_purchased;
    private static JButton increase_pen;
    private static JButton decrease_pen;

    //interface for editing item quantity pencil

    private static JLabel pencil_purchased;
    private static JButton increase_pencil;
    private static JButton decrease_pencil;
    

    //interface for editing item quantity notebook

    private static JLabel notebook_purchased;
    private static JButton increase_notebook;
    private static JButton decrease_notebook;

    //interface for editing item quantity eraser

    private static JLabel eraser_purchased;
    private static JButton increase_eraser;
    private static JButton decrease_eraser;

    //last 4 items
    private static JLabel tape;
    private static JLabel ruler;
    private static JLabel highlighter;
    private static JLabel stapler;

    //label
    private static JLabel tape_label;
    private static JLabel ruler_label;
    private static JLabel highlighter_label;
    private static JLabel stapler_label;

    //first 4 item price label
    private static JLabel tape_price;
    private static JLabel ruler_price;
    private static JLabel highlighter_price;
    private static JLabel stapler_price;

    //label for quantity
    private static JLabel tape_quantity;
    private static JLabel ruler_quantity;
    private static JLabel highlighter_quantity;
    private static JLabel stapler_quantity;

    //edit item quantity tape

    private static JLabel tape_purchased;
    private static JButton increase_tape;
    private static JButton decrease_tape;

    //edit item quantity ruler

    private static JLabel ruler_purchased;
    private static JButton increase_ruler;
    private static JButton decrease_ruler;

    //edit item quantity highlighter

    private static JLabel highlighter_purchased;
    private static JButton increase_highlighter;
    private static JButton decrease_highlighter;

    //edit item quantity stapler

    private static JLabel stapler_purchased;
    private static JButton increase_stapler;
    private static JButton decrease_stapler;

    //display total price
    private static JLabel total_price;
    protected double total_price (int itempurchased1, int itempurchased2, int itempurchased3, int itempurchased4,
    int itempurchased5, int itempurchased6, int itempurchased7, int itempurchased8)
    {
        double total_pen = itemprice[0] * itempurchased[0];
        double total_pencil = itemprice[1] * itempurchased[1];
        double total_notebook =itemprice[2] * itempurchased[2];
        double total_eraser = itemprice[3] * itempurchased[3];
        double total_tape = itemprice[4] * itempurchased[4];
        double total_ruler = itemprice[5] * itempurchased[5];
        double total_highlighter=itemprice[6] * itempurchased[6];
        double total_stapler = itemprice[7] * itempurchased[7];
        double total = total_pen+total_pencil+total_eraser+total_tape+total_notebook+total_ruler+total_highlighter+total_stapler;
        return total;
    }

    private static JLabel display_total_price;
    private static JLabel total_cash;
    private static JTextField display_total_cash;
    private static JButton read_balance;
    private static JLabel balence;
    private static JLabel total_balence;

    protected static JFrame posframe;
    protected static JPanel posPanel;
    
    public pay_item()
    {
        posframe = new JFrame();
        posPanel = new JPanel();
        df = new DecimalFormat("0.00");

      

        int i;
        String fileName = "sales report.txt";
        for (i = 0; i < itemsold.length; i++) 
        {
            itemsold[i] = 0; // Set default value to -1 (if item not found)
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
                itemsold[i] = qty;
                //System.out.println("Item sold: "+itemNames[i]+" : Sold Quantity:  "+itemsold[i]);
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



        p_font= new Font("San Serif",Font.BOLD,20);
        big_font = new Font("Times New Roman",Font.BOLD,30);
        boarder = new LineBorder(Color.BLACK, 2);
        posframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        itemframe.dispose();
        posframe.setTitle("Billing system");


       //Frame size, background color
        posframe.setSize(1500,800);
        posPanel.setBackground(Color.lightGray);
        posframe.add(posPanel);
        posPanel.setLayout(null);
        posframe.setLocationRelativeTo(null);

        //Insert image
        pen = new JLabel();
        pen.setIcon(new ImageIcon("pen.png"));
        pen.setBounds(45, 25,130,130);
        pen.setBorder(boarder);
        posPanel.add(pen);


        pencil = new JLabel();
        pencil.setIcon(new ImageIcon("pencil.png"));
        pencil.setBounds(45, 211,130,130);
        pencil.setBorder(boarder);
        posPanel.add(pencil);

        notebook = new JLabel();
        notebook.setIcon(new ImageIcon("notebook.png"));
        notebook.setBounds(45, 397,130,130);
        notebook.setBorder(boarder);
        posPanel.add(notebook);

        eraser = new JLabel();
        eraser.setIcon(new ImageIcon("eraser.png"));
        eraser.setBounds(45, 583,130,130);
        eraser.setBorder(boarder);
        posPanel.add(eraser);



        //First 4 item label and description
        pen_label = new JLabel("Pen");
        pen_label.setBounds(55,25,80,25);
        posPanel.add(pen_label);

        pencil_label = new JLabel("Pencil");
        pencil_label.setBounds(55,211,80,25);
        posPanel.add(pencil_label);

        notebook_label = new JLabel("Notebook");
        notebook_label.setBounds(55,397,80,25);
        posPanel.add(notebook_label);

        eraser_label = new JLabel("Eraser");
        eraser_label.setBounds(55,583,80,25);
        posPanel.add(eraser_label);

        
        //First 4 item price with quantity (Label)
        pen_price = new JLabel(" Price per unit: RM "+df.format(itemprice[0]));
        pen_price.setBounds(195,25,235,35);
        pen_price.setFont(p_font);
        pen_price.setBorder(boarder);
        posPanel.add(pen_price);

        pencil_price = new JLabel(" Price per unit: RM "+df.format(itemprice[1]));
        pencil_price.setBounds(195,211,235,35);
        pencil_price.setFont(p_font);
        pencil_price.setBorder(boarder);
        posPanel.add(pencil_price);

        notebook_price = new JLabel(" Price per unit: RM "+df.format(itemprice[2]));
        notebook_price.setBounds(195,397,235,35);
        notebook_price.setFont(p_font);
        notebook_price.setBorder(boarder);
        posPanel.add(notebook_price);

        eraser_price = new JLabel(" Price per unit: RM "+df.format(itemprice[3]));
        eraser_price.setBounds(195,583,235,35);
        eraser_price.setFont(p_font);
        eraser_price.setBorder(boarder);
        posPanel.add(eraser_price);

        pen_quantity = new JLabel(" Quantity:");
        pen_quantity.setBounds(195, 80, 100, 35);
        pen_quantity.setFont(p_font);
        posPanel.add(pen_quantity);

        pencil_quantity = new JLabel(" Quantity:");
        pencil_quantity.setBounds(195, 266, 100, 35);
        pencil_quantity.setFont(p_font);
        posPanel.add(pencil_quantity);

        notebook_quantity = new JLabel(" Quantity:");
        notebook_quantity.setBounds(195, 452, 100, 35);
        notebook_quantity.setFont(p_font);
        posPanel.add(notebook_quantity);

        eraser_quantity = new JLabel(" Quantity:");
        eraser_quantity.setBounds(195, 638, 100, 35);
        eraser_quantity.setFont(p_font);
        posPanel.add(eraser_quantity);

        pen_purchased=new JLabel(""+itempurchased[0]);
        pen_purchased.setBounds(350,80,45,35);
        pen_purchased.setFont(p_font);
        pen_purchased.setBorder(boarder);
        posPanel.add(pen_purchased);

        increase_pen= new JButton("+");
        increase_pen.setBounds(395,80,25,35);
        increase_pen.setFont(p_font);
        increase_pen.setBorder(boarder);
        increase_pen.addActionListener(this);
        posPanel.add(increase_pen);

        decrease_pen= new JButton("-");
        decrease_pen.setBounds(325,80,25,35);
        decrease_pen.setFont(p_font);
        decrease_pen.setBorder(boarder);
        decrease_pen.addActionListener(this);
        posPanel.add(decrease_pen);

        pencil_purchased=new JLabel(""+itempurchased[1]);
        pencil_purchased.setBounds(350,266,45,35);
        pencil_purchased.setFont(p_font);
        pencil_purchased.setBorder(boarder);
        posPanel.add(pencil_purchased);

        increase_pencil= new JButton("+");
        increase_pencil.setBounds(395,266,25,35);
        increase_pencil.setFont(p_font);
        increase_pencil.setBorder(boarder);
        increase_pencil.addActionListener(this);
        posPanel.add(increase_pencil);

        decrease_pencil= new JButton("-");
        decrease_pencil.setBounds(325,266,25,35);
        decrease_pencil.setFont(p_font);
        decrease_pencil.setBorder(boarder);
        decrease_pencil.addActionListener(this);
        posPanel.add(decrease_pencil);

        notebook_purchased=new JLabel(""+itempurchased[2]);
        notebook_purchased.setBounds(350,452,45,35);
        notebook_purchased.setFont(p_font);
        notebook_purchased.setBorder(boarder);
        posPanel.add(notebook_purchased);

        increase_notebook= new JButton("+");
        increase_notebook.setBounds(395,452,25,35);
        increase_notebook.setFont(p_font);
        increase_notebook.setBorder(boarder);
        increase_notebook.addActionListener(this);
        posPanel.add(increase_notebook);

        decrease_notebook= new JButton("-");
        decrease_notebook.setBounds(325,452,25,35);
        decrease_notebook.setFont(p_font);
        decrease_notebook.setBorder(boarder);
        decrease_notebook.addActionListener(this);
        posPanel.add(decrease_notebook);

        eraser_purchased=new JLabel(""+itempurchased[2]);
        eraser_purchased.setBounds(350,638,45,35);
        eraser_purchased.setFont(p_font);
        eraser_purchased.setBorder(boarder);
        posPanel.add(eraser_purchased);

        increase_eraser= new JButton("+");
        increase_eraser.setBounds(395,638,25,35);
        increase_eraser.setFont(p_font);
        increase_eraser.setBorder(boarder);
        increase_eraser.addActionListener(this);
        posPanel.add(increase_eraser);

        decrease_eraser= new JButton("-");
        decrease_eraser.setBounds(325,638,25,35);
        decrease_eraser.setFont(p_font);
        decrease_eraser.setBorder(boarder);
        decrease_eraser.addActionListener(this);
        posPanel.add(decrease_eraser);

        //last 4 item
        tape = new JLabel();
        tape.setIcon(new ImageIcon("tape.png"));
        tape.setBounds(550, 25, 130, 130);
        tape.setBorder(boarder);
        posPanel.add(tape);

        ruler = new JLabel();
        ruler.setIcon(new ImageIcon("ruler.png"));
        ruler.setBounds(550, 211, 130, 130);
        ruler.setBorder(boarder);
        posPanel.add(ruler);

        highlighter = new JLabel();
        highlighter.setIcon(new ImageIcon("highlighter.png"));
        highlighter.setBounds(550, 397, 130, 130);
        highlighter.setBorder(boarder);
        posPanel.add(highlighter);

        stapler = new JLabel();
        stapler.setIcon(new ImageIcon("stapler.png"));
        stapler.setBounds(550, 583, 130, 130);
        stapler.setBorder(boarder);
        posPanel.add(stapler);

        //display item name
        tape_label = new JLabel("Tape");
        tape_label.setBounds(560,25,80,25);
        posPanel.add(tape_label);

        ruler_label = new JLabel("Ruler");
        ruler_label.setBounds(560,211,80,25);
        posPanel.add(ruler_label);

        highlighter_label = new JLabel("Highlighter");
        highlighter_label.setBounds(560,397,80,25);
        posPanel.add(highlighter_label);

        stapler_label = new JLabel("Stapler");
        stapler_label.setBounds(560,583,80,25);
        posPanel.add(stapler_label);

        //display item price
        tape_price = new JLabel(" Price per unit: RM "+df.format(itemprice[4]));
        tape_price.setBounds(700,25,235,35);
        tape_price.setFont(p_font);
        tape_price.setBorder(boarder);
        posPanel.add(tape_price);

        ruler_price = new JLabel(" Price per unit: RM "+df.format(itemprice[5]));
        ruler_price.setBounds(700,211,235,35);
        ruler_price.setFont(p_font);
        ruler_price.setBorder(boarder);
        posPanel.add(ruler_price);

        highlighter_price = new JLabel(" Price per unit: RM "+df.format(itemprice[6]));
        highlighter_price.setBounds(700,397,235,35);
        highlighter_price.setFont(p_font);
        highlighter_price.setBorder(boarder);
        posPanel.add(highlighter_price);

        stapler_price = new JLabel(" Price per unit: RM "+df.format(itemprice[7]));
        stapler_price.setBounds(700,583,235,35);
        stapler_price.setFont(p_font);
        stapler_price.setBorder(boarder);
        posPanel.add(stapler_price);

        tape_quantity = new JLabel(" Quantity:");
        tape_quantity.setBounds(700, 80, 100, 35);
        tape_quantity.setFont(p_font);
        posPanel.add(tape_quantity);

        ruler_quantity = new JLabel(" Quantity:");
        ruler_quantity.setBounds(700, 266, 100, 35);
        ruler_quantity.setFont(p_font);
        posPanel.add(ruler_quantity);

        highlighter_quantity = new JLabel(" Quantity:");
        highlighter_quantity.setBounds(700, 452, 100, 35);
        highlighter_quantity.setFont(p_font);
        posPanel.add(highlighter_quantity);

        stapler_quantity = new JLabel(" Quantity:");
        stapler_quantity.setBounds(700, 638, 100, 35);
        stapler_quantity.setFont(p_font);
        posPanel.add(stapler_quantity);

        tape_purchased=new JLabel(""+itempurchased[4]);
        tape_purchased.setBounds(855,80,45,35);
        tape_purchased.setFont(p_font);
        tape_purchased.setBorder(boarder);
        posPanel.add(tape_purchased);

        increase_tape= new JButton("+");
        increase_tape.setBounds(900,80,25,35);
        increase_tape.setFont(p_font);
        increase_tape.setBorder(boarder);
        increase_tape.addActionListener(this);
        posPanel.add(increase_tape);

        decrease_tape= new JButton("-");
        decrease_tape.setBounds(830,80,25,35);
        decrease_tape.setFont(p_font);
        decrease_tape.setBorder(boarder);
        decrease_tape.addActionListener(this);
        posPanel.add(decrease_tape);

        ruler_purchased=new JLabel(""+itempurchased[5]);
        ruler_purchased.setBounds(855,266,45,35);
        ruler_purchased.setFont(p_font);
        ruler_purchased.setBorder(boarder);
        posPanel.add(ruler_purchased);

        increase_ruler= new JButton("+");
        increase_ruler.setBounds(900,266,25,35);
        increase_ruler.setFont(p_font);
        increase_ruler.setBorder(boarder);
        increase_ruler.addActionListener(this);
        posPanel.add(increase_ruler);

        decrease_ruler= new JButton("-");
        decrease_ruler.setBounds(830,266,25,35);
        decrease_ruler.setFont(p_font);
        decrease_ruler.setBorder(boarder);
        decrease_ruler.addActionListener(this);
        posPanel.add(decrease_ruler);

        highlighter_purchased=new JLabel(""+itempurchased[6]);
        highlighter_purchased.setBounds(855,452,45,35);
        highlighter_purchased.setFont(p_font);
        highlighter_purchased.setBorder(boarder);
        posPanel.add(highlighter_purchased);

        increase_highlighter= new JButton("+");
        increase_highlighter.setBounds(900,452,25,35);
        increase_highlighter.setFont(p_font);
        increase_highlighter.setBorder(boarder);
        increase_highlighter.addActionListener(this);
        posPanel.add(increase_highlighter);

        decrease_highlighter= new JButton("-");
        decrease_highlighter.setBounds(830,452,25,35);
        decrease_highlighter.setFont(p_font);
        decrease_highlighter.setBorder(boarder);
        decrease_highlighter.addActionListener(this);
        posPanel.add(decrease_highlighter);

        stapler_purchased=new JLabel(""+itempurchased[7]);
        stapler_purchased.setBounds(855,638,45,35);
        stapler_purchased.setFont(p_font);
        stapler_purchased.setBorder(boarder);
        posPanel.add(stapler_purchased);

        increase_stapler= new JButton("+");
        increase_stapler.setBounds(900,638,25,35);
        increase_stapler.setFont(p_font);
        increase_stapler.setBorder(boarder);
        increase_stapler.addActionListener(this);
        posPanel.add(increase_stapler);

        decrease_stapler= new JButton("-");
        decrease_stapler.setBounds(830,638,25,35);
        decrease_stapler.setFont(p_font);
        decrease_stapler.setBorder(boarder);
        decrease_stapler.addActionListener(this);
        posPanel.add(decrease_stapler);

        total_price = new JLabel("Total Price");
        total_price.setBounds(1080, 250, 150, 35);
        total_price.setFont(big_font);
        posPanel.add(total_price);

        display_total_price = new JLabel(""+df.format(total_price(itempurchased[0], itempurchased[1], 
        itempurchased[2], itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
        display_total_price.setBounds(1230, 250, 88, 35);
        display_total_price.setBorder(boarder);
        display_total_price.setFont(big_font);
        posPanel.add(display_total_price);

        total_cash = new JLabel("Cash");
        total_cash.setBounds(1080, 300, 150, 35);
        total_cash.setFont(big_font);
        posPanel.add(total_cash);

        display_total_cash = new JTextField(20);
        display_total_cash.setBounds(1230, 300, 88, 35);
        display_total_cash.setBorder(boarder);
        display_total_cash.setFont(big_font);
        posPanel.add(display_total_cash);

        balence = new JLabel("Balance");
        balence.setBounds(1080, 350, 150, 35);
        balence.setFont(big_font);
        posPanel.add(balence);
        
        total_balence = new JLabel("0.00");
        total_balence.setBounds(1230, 350, 150, 35);
        total_balence.setBorder(boarder);
        total_balence.setFont(big_font);
        posPanel.add(total_balence);

        read_balance = new JButton("Pay");
        read_balance.setFont(big_font);
        read_balance.setBounds(1175,450,100,50);
        read_balance.addActionListener(this);
        posPanel.add(read_balance);

        reset = new JButton("Reset");
        reset.setFont(big_font);
        reset.setBounds(1050,450,110,50);
        reset.addActionListener(this);
        posPanel.add(reset);

        receipt = new JButton("Invoice");
        receipt.setFont(big_font);
        receipt.setBounds(1290,450,140,50);
        receipt.addActionListener(this);
        receipt.setVisible(false);
        posPanel.add(receipt);

        gotostock = new JButton("Manage Item");
        gotostock.setFont(big_font);
        gotostock.setBounds(1050,520,370,50);
        gotostock.addActionListener(this);
        posPanel.add(gotostock);

        gotosales = new JButton("Sales Report");
        gotosales.setFont(big_font);
        gotosales.setBounds(1050,590,370,50);
        gotosales.addActionListener(this);
        posPanel.add(gotosales);

        posframe.setVisible(true);
 
    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==increase_pen)
        {
            if (super.itemQTY[0]!=0)
            {
            itempurchased[0]++;
            //this.itempurchased[0]=itempurchased[0];
            pen_purchased.setText(""+itempurchased[0]);
            super.itemQTY[0]--;
            pen_stock.setText("Quantity: " + super.itemQTY[0]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
        }
        else if(e.getSource()==decrease_pen)
        {
            if (itempurchased[0]!=0)
            {
                itempurchased[0]--;
                pen_purchased.setText(""+itempurchased[0]);
                super.itemQTY[0]++;
                pen_stock.setText("Quantity: " + super.itemQTY[0]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
            
    
        }
        else if(e.getSource()==increase_pencil)
        {
            if (super.itemQTY[1]!=0)
            {
            itempurchased[1]++;
            pencil_purchased.setText(""+itempurchased[1]);
            super.itemQTY[1]--;
            pencil_stock.setText("Quantity: " + super.itemQTY[1]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }

        }
        else if(e.getSource()==decrease_pencil)
        {
            if (itempurchased[1]!=0)
            {
                itempurchased[1]--;
                pencil_purchased.setText(""+itempurchased[1]);
                super.itemQTY[1]++;
                pencil_stock.setText("Quantity: " + super.itemQTY[1]);
                pencil_purchased.setText(""+itempurchased[1]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
            
    
        }
        else if(e.getSource()==increase_notebook)
        {
            if (super.itemQTY[2]!=0){
            itempurchased[2]++;
            notebook_purchased.setText(""+itempurchased[2]);
            super.itemQTY[2]--;
            notebook_stock.setText("Quantity: " + super.itemQTY[2]);
            //System.out.println(super.itemQTY[2]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
        }
        else if(e.getSource()==decrease_notebook)
        {
            if (itempurchased[2]!=0)
            {
                itempurchased[2]--;
                notebook_purchased.setText(""+itempurchased[2]);
                super.itemQTY[2]++;
                notebook_stock.setText("Quantity: " + super.itemQTY[2]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
    
        }
        else if(e.getSource()==increase_eraser)
        {
            if (super.itemQTY[3]!=0)
            {
            itempurchased[3]++;
            eraser_purchased.setText(""+itempurchased[3]);
            super.itemQTY[3]--;
            eraser_stock.setText("Quantity: "+super.itemQTY[3]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }

        }
        else if(e.getSource()==decrease_eraser)
        {
            if (itempurchased[3]!=0)
            {
                itempurchased[3]--;
                eraser_purchased.setText(""+itempurchased[3]);
                super.itemQTY[3]++;
                eraser_stock.setText("Quantity: "+super.itemQTY[3]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
    
        }
        else if(e.getSource()==increase_tape)
        {
            if (super.itemQTY[4]!=0)
            {
            itempurchased[4]++;
            tape_purchased.setText(""+itempurchased[4]);
            super.itemQTY[4]--;
            tape_stock.setText("Quantity: "+super.itemQTY[4]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }

        }
        else if(e.getSource()==decrease_tape)
        {
            if (itempurchased[4]!=0)
            {
                itempurchased[4]--;
                tape_purchased.setText(""+itempurchased[4]);
                super.itemQTY[4]++;
                tape_stock.setText("Quantity: "+super.itemQTY[4]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
    
        }
        else if(e.getSource()==increase_ruler)
        {
            if (super.itemQTY[5]!=0)
            {
            itempurchased[5]++;
            ruler_purchased.setText(""+itempurchased[5]);
            super.itemQTY[5]--;
            ruler_stock.setText("Quantity: "+super.itemQTY[5]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }

        }
        else if(e.getSource()==decrease_ruler)
        {
            if (itempurchased[5]!=0)
            {
                itempurchased[5]--;
                ruler_purchased.setText(""+itempurchased[5]);
                super.itemQTY[5]++;
                ruler_stock.setText("Quantity: "+super.itemQTY[5]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
    
        }
        else if(e.getSource()==increase_highlighter)
        {
            if (super.itemQTY[6]!=0)
            {
            itempurchased[6]++;
            highlighter_purchased.setText(""+itempurchased[6]);
            super.itemQTY[6]--;
            highlighter_stock.setText("Quantity: "+super.itemQTY[6]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }

        }
        else if(e.getSource()==decrease_highlighter)
        {
            if (itempurchased[6]!=0)
            {
                itempurchased[6]--;
                highlighter_purchased.setText(""+itempurchased[6]);
                super.itemQTY[6]++;
                highlighter_stock.setText("Quantity: "+super.itemQTY[6]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
    
        }
        else if(e.getSource()==increase_stapler)
        {
            if (super.itemQTY[7]!=0)
            {
            itempurchased[7]++;
            stapler_purchased.setText(""+itempurchased[7]);
            super.itemQTY[7]--;
            stapler_stock.setText("Quantity: "+super.itemQTY[7]);
            display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }

        }
        else if(e.getSource()==decrease_stapler)
        {
            if (itempurchased[7]!=0)
            {
                itempurchased[7]--;
                stapler_purchased.setText(""+itempurchased[7]);
                super.itemQTY[7]++;
                stapler_stock.setText("Quantity: "+super.itemQTY[7]);
                display_total_price.setText(""+df.format(total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
                itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7])));
            }
    
        }
        else if(e.getSource()==read_balance)
        {
        
            double balenced_input;
            balenced_input = Double.valueOf(display_total_cash.getText());
            double final_balence = balenced_input - total_price(itempurchased[0], itempurchased[1], itempurchased[2], 
            itempurchased[3], itempurchased[4], itempurchased[5], itempurchased[6], itempurchased[7]);

            if (final_balence<0)
            {
                total_balence.setText("ERROR");
            }
            else
            {
            receipt.setVisible(true);
            total_balence.setText(df.format(final_balence));
            update_file();
            itemsold[0]+=itempurchased[0];
            itemsold[1]+=itempurchased[1];
            itemsold[2]+=itempurchased[2];
            itemsold[3]+=itempurchased[3];
            itemsold[4]+=itempurchased[4];
            itemsold[5]+=itempurchased[5];
            itemsold[6]+=itempurchased[6];
            itemsold[7]+=itempurchased[7];
            this.update_sales();
            this.update_temp();
            }    

        }
        else if (e.getSource()==reset)
        {
            itempurchased[0] = 0;
            itempurchased[1] = 0;
            itempurchased[2] = 0;
            itempurchased[3] = 0;
            itempurchased[4] = 0;
            itempurchased[5] = 0;
            itempurchased[6] = 0;
            itempurchased[7] = 0;
            this.update_temp();
            posframe.dispose();
            new pay_item();
            posframe.setVisible(true);
            
        }
        else if (e.getSource()==receipt)
        {
            posframe.dispose();
            new invoice();
        }
        else if (e.getSource()==gotostock)
        {
            new item();
            posframe.dispose();
        }
        else if (e.getSource()==gotosales)
        {
            posframe.dispose();
            new sales_report();
        }
    }
    protected void update_sales()
    {
        try {
            // Open the file for writing
            PrintWriter writer = new PrintWriter("sales report.txt", "UTF-8");
          
            // Write the updated values of itemQTY to the file
            for (int i = 0; i < itemNames.length; i++) {
              writer.println(itemNames[i] + " " + itemsold[i]);
            }
          
            // Close the file
            writer.close();
          } catch (IOException ex) {
            System.out.println("An error occurred while writing to the file.");
            ex.printStackTrace();
          }
    }
    
    protected void update_temp()
    {
        try {
            // Open the file for writing
            PrintWriter writer = new PrintWriter("temp.txt", "UTF-8");
          
            // Write the updated values of itemQTY to the file
            for (int i = 0; i < itemNames.length; i++) {
              writer.println(itemNames[i] + " " + itempurchased[i]);
            }
          
            // Close the file
            writer.close();
          } catch (IOException ex) {
            System.out.println("An error occurred while writing to the file.");
            ex.printStackTrace();
          }
    }

}