/*
Inherritance: Inherits item_sold from pay_item.java

Inherritance + file handling: When button for reset sales report is clicked, it will redefined all the item_sold to 0, and call
                              method (update_sales()) from superclass pay_item.java (Line 134)

Interface: All the detailes of ActionListener for this class is specified from Line 122
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.text.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingConstants;

public class sales_report extends pay_item{
  private static JFrame sales_frame;
  private static JPanel sales_panel;
  private static JLabel title;
  private static Font title_font; 
  private static Font table_font;
  private static Font display_font;
  private static JTable sales_report;
  private static LineBorder table_Border; 
  private static DecimalFormat df; 
  private static JLabel totalsalesdisplay;
  private static JButton gotobill;
  private static JButton gotostock;
  private static JButton resetstock;
  
  public sales_report() {
    sales_frame= new JFrame();
    sales_panel = new JPanel();

    posframe.dispose();

    totalsalesdisplay = new JLabel();

    df = new DecimalFormat("0.00");
    sales_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    sales_frame.setTitle("Sales Report");
    sales_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    sales_frame.setSize(1100, 600);
    sales_frame.add(sales_panel);
      
    sales_panel.setLayout(null);
    sales_frame.setLocationRelativeTo(null);

    title_font = new Font ("Times New Roman", Font.BOLD, 35);
    table_font = new Font ("Times New Roman",Font.PLAIN,18);
    display_font = new Font ("Times New Roman", Font.PLAIN,22);
    table_Border = new LineBorder(Color.BLACK,1);

    title = new JLabel("Overall Sales Report");
    title.setBounds(388, 25, 350, 35);
    title.setFont(title_font);


    Object[][] data = {{"Item","Item Quantity Sold","Sales Generated (RM)"},
                       {itemNames[0], itemsold[0], df.format(itemsold[0] * itemprice[0])},
                       {itemNames[1], itemsold[1], df.format(itemsold[1] * itemprice[1])},
                       {itemNames[2], itemsold[2], df.format(itemsold[2] * itemprice[2])},
                       {itemNames[3], itemsold[3], df.format(itemsold[3] * itemprice[3])},
                       {itemNames[4], itemsold[4], df.format(itemsold[4] * itemprice[4])},
                       {itemNames[5], itemsold[5], df.format(itemsold[5] * itemprice[5])},
                       {itemNames[6], itemsold[6], df.format(itemsold[6] * itemprice[6])},
                       {itemNames[7], itemsold[7], df.format(itemsold[7] * itemprice[7])}};

    double total = (itemsold[0]*itemprice[0])+(itemsold[1]*itemprice[1])+
                   (itemsold[2]*itemprice[2])+(itemsold[3]*itemprice[3])+
                   (itemsold[4]*itemprice[4])+(itemsold[5]*itemprice[5])+
                   (itemsold[6]*itemprice[6])+(itemsold[7]*itemprice[7]);

    

    
      
    String[] columnNames = {"Item","Quantity Sold","Total"};
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    sales_report = new JTable(model);
    sales_report.setBounds(195, 105, 660, 145);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    sales_report.setDefaultRenderer(Object.class, centerRenderer);
    sales_report.setFont(table_font);
    sales_report.setBorder(table_Border);
    sales_panel.add(sales_report);

    totalsalesdisplay = new JLabel("Total Sales Generated (RM): "+df.format(total));
    totalsalesdisplay.setBounds(195, 270, 550, 35);
    totalsalesdisplay.setFont(display_font);
    sales_panel.add(totalsalesdisplay);


    gotobill = new JButton("Bill");
    gotobill.setFont(display_font);
    gotobill.setBounds(625,320,230,35);
    gotobill.addActionListener(this);
    sales_panel.add(gotobill);

    gotostock = new JButton("Manage Item");
    gotostock.setFont(display_font);
    gotostock.setBounds(195,320,230,35);
    gotostock.addActionListener(this);
    sales_panel.add(gotostock);

    resetstock = new JButton("Reset Sales Report");
    resetstock.setFont(display_font);
    resetstock.setBounds(195,400,230,35);
    resetstock.addActionListener(this);
    sales_panel.add(resetstock);


    sales_panel.add(title);

    sales_frame.setVisible(true);
  }
  public void actionPerformed (ActionEvent e)
  {
    if (e.getSource()==gotobill)
      {
        sales_frame.dispose();
        new pay_item();
      }
    else if (e.getSource()==gotostock)
    {
      sales_frame.dispose();
      new item();
    }
    else if (e.getSource()==resetstock)
    {
      itemsold[0] = 0;
      itemsold[1] = 0;
      itemsold[2] = 0;
      itemsold[3] = 0;
      itemsold[4] = 0;
      itemsold[5] = 0;
      itemsold[6] = 0; 
      itemsold[7] = 0;

      update_sales();
      sales_frame.dispose();
      new sales_report();
      sales_frame.setVisible(true);
    }
  }
}