/*
Inheritance: Inherits item_purchased and itemNames from pay_item.java  
  
File handling and exception: Read all the values from temp.txt and assign variables based on its content (Line 73)
  
Interface: Details upon ActionListener inherrited from item.java is specified on (Line 139)  
  
 
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;


public class invoice extends pay_item
{
  private static JFrame invoice_frame;
  private static JPanel invoice_panel;  
  private static DecimalFormat df;
  private static Font title_font; 
  private static JLabel title;
  private static JTable invoice;
  private static Font table_font;
  private static LineBorder table_Border; 
  private static JLabel totaldisplay;
  private static JButton back;
  private static Font display_font;


  public invoice()
    {
      invoice_frame= new JFrame();
      invoice_panel = new JPanel();
      table_font = new Font ("Times New Roman",Font.PLAIN,18);
      table_Border = new LineBorder(Color.BLACK,1);

      totaldisplay = new JLabel();

      posframe.dispose();  
      
      df = new DecimalFormat("0.00");

      invoice_frame.setTitle("Invoice");
      invoice_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      title_font = new Font ("Times New Roman", Font.BOLD, 35);
      display_font = new Font ("Times New Roman", Font.PLAIN,22);
    
      invoice_frame.setSize(450, 550);
      invoice_frame.add(invoice_panel);
      
      invoice_panel.setLayout(null);
      invoice_frame.setLocationRelativeTo(null);

      title = new JLabel("Invoice");
      title.setBounds(160, 25, 350, 35);
      title.setFont(title_font);
      invoice_panel.add(title);


      int i;
        for (i = 0; i < itempurchased.length; i++) 
        {
            itempurchased[i] = 0;
        }
        
        try {
            Scanner scanner = new Scanner(new File("temp.txt"));
            while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              // Split the line into parts
              String[] parts = line.split("\\s+"); 
              String name = parts[0];
              int qty = Integer.parseInt(parts[1]);
              for (i=0;i<itemNames.length;i++)
              {
              if (name.equals(itemNames[i])) {
                // Item was found
                itempurchased[i] = qty;
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

        Object[][] data = {{"Item","Quantity","Price (RM)"},
        {itemNames[0], itempurchased[0], df.format(itempurchased[0] * itemprice[0])},
        {itemNames[1], itempurchased[1], df.format(itempurchased[1] * itemprice[1])},
        {itemNames[2], itempurchased[2], df.format(itempurchased[2] * itemprice[2])},
        {itemNames[3], itempurchased[3], df.format(itempurchased[3] * itemprice[3])},
        {itemNames[4], itempurchased[4], df.format(itempurchased[4] * itemprice[4])},
        {itemNames[5], itempurchased[5], df.format(itempurchased[5] * itemprice[5])},
        {itemNames[6], itempurchased[6], df.format(itempurchased[6] * itemprice[6])},
        {itemNames[7], itempurchased[7], df.format(itempurchased[7] * itemprice[7])}};

          double total = 
          (itempurchased[0]*itemprice[0])+(itempurchased[1]*itemprice[1])+
          (itempurchased[2]*itemprice[2])+(itempurchased[3]*itemprice[3])+
          (itempurchased[4]*itemprice[4])+(itempurchased[5]*itemprice[5])+
          (itempurchased[6]*itemprice[6])+(itempurchased[7]*itemprice[7]);

    String[] columnNames = {"Item","Quantity","Price"};

    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    invoice = new JTable(model);
    invoice.setBounds(50, 105, 350, 145);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    invoice.setDefaultRenderer(Object.class, centerRenderer);
    invoice.setFont(table_font);
    invoice.setBorder(table_Border);
    invoice_panel.add(invoice);

    totaldisplay = new JLabel("Total Price (RM): "+df.format(total));
    totaldisplay.setBounds(50, 270, 550, 35);
    totaldisplay.setFont(display_font);
    invoice_panel.add(totaldisplay);

    back = new JButton("Back");
    back.setFont(display_font);
    back.setBounds(50,320,180,35);
    back.addActionListener(this);
    invoice_panel.add(back);

    invoice_frame.setVisible(true);

    }
    public void actionPerformed (ActionEvent e)
    {
      if (e.getSource()==back)
      {
        invoice_frame.dispose();
        new pay_item();
      }
    }
}