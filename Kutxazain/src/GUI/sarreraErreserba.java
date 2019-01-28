package GUI;



import Domain.Bezeroa;
import Domain.Bezeroa;
import Domain.Emanaldia;
import Domain.Kontua;
import Domain.Kontua;
import BussinesLogic.Kutxazaina;
import BussinesLogic.Kutxazaina;
import Domain.Sarrera;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import static java.awt.event.ActionEvent.ACTION_FIRST;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class sarreraErreserba extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox orduaBox;
	private JButton btnAukeratu;
	private JButton btnErosi;
	private JButton btnAmaitu;
        private Bezeroa bezeroa;
        private Kontua kontua;
        private Kutxazaina kutxazaina;
        private String herria,zinea,data,filma,ordua;
        private List<String> Orduak;
        private List<Sarrera> Sarrerak;
        
        private DefaultComboBoxModel orduakList = new DefaultComboBoxModel();
        private ImageIcon libre= null;
        private ImageIcon okupatua = null;
        private DefaultTableModel model;
        private boolean errorea=false;
      



	public sarreraErreserba(Bezeroa bez, Kontua kont,Kutxazaina kutx, String her,String zin,String film,String dat) {
		setTitle("Erreserba");
               
                
              
                bezeroa=bez;
                kontua=kont;
                kutxazaina=kutx;
                herria=her;
                zinea=zin;
                filma=film;
                data=dat;
                System.out.println(herria+zinea+filma+data);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 635, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 602, 349);
		contentPane.add(scrollPane);
		
		orduaBox = new JComboBox();
		orduaBox.setBounds(10, 23, 152, 20);
		contentPane.add(orduaBox);
		
		Orduak= kutxazaina.aurkituOrduak(data,herria,zinea,filma);          
		for(int i=0; i<Orduak.size();i++){
			orduakList.addElement(Orduak.get(i));
		}
                
                   
		orduaBox.setModel(orduakList);
		orduaBox.setSelectedIndex(-1);
                
                
                	model = new DefaultTableModel(){
			public Class<?> getColumnClass(int column)
			{
				
					return Icon.class;
				
			}
		};
		model.setColumnIdentifiers(new Object[] {"1","2","3","4","5","6","7" });
		 
              
                libre = new ImageIcon("libre.jpg");                                                             
                Image Libre = libre.getImage(); // transform it 
                Image newimg1 = Libre.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                libre = new ImageIcon(newimg1);  
                                                            
                okupatua = new ImageIcon("okupatua.jpg");
                Image Okupatua = okupatua.getImage(); // transform it 
                Image newimg2 = Okupatua.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                okupatua = new ImageIcon(newimg2);
                
                
		btnAukeratu = new JButton("Aukeratu");
		btnAukeratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            if(orduaBox.getSelectedIndex()!=-1){
                                int size=model.getRowCount();
                                  for(int i=0; i<size;i++){

                                        model.removeRow(0);
                                        System.out.println(i);
                                       
                                    }
                                 
                                
                                ordua=(String)orduaBox.getSelectedItem();
                               
                                Sarrerak=kutxazaina.aurkituSarrerak( data, herria, zinea, filma, ordua);  
                                    if(!Sarrerak.isEmpty()){			
                                                     int n=0;
                                                     Vector<Object> row = new Vector<Object>();
                                                     for(int i=0; i<Sarrerak.size();i++){                                                     
                                                     
                                                     if(Sarrerak.get(i).getSalduta()){
                                                            row.add(okupatua);
                                                           
                                                     }
                                                     else{
                                                         row.add(libre);
                                                     }
                                                     n++;
                                                     if(n==7){
                                                         n=0;
                                                         model.addRow(row);   
                                                         row = new Vector<Object>();
                                                     }                                                    
                                                }
                                                if(n!=0){
                                                     model.addRow(row);                                                    
                                                }
                                    }                              
                            }
			}
		});
		btnAukeratu.setBounds(172, 11, 89, 44);
		contentPane.add(btnAukeratu);
		
	
		

		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setCellSelectionEnabled(true);     
                table.setRowSelectionAllowed(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
                table.getColumnModel().getColumn(1).setPreferredWidth(80);
                table.getColumnModel().getColumn(2).setPreferredWidth(80);
                table.getColumnModel().getColumn(3).setPreferredWidth(80);
                table.getColumnModel().getColumn(4).setPreferredWidth(80);
                table.getColumnModel().getColumn(5).setPreferredWidth(80);
                table.getColumnModel().getColumn(6).setPreferredWidth(80);
		
		table.setRowHeight(40);
		table.setRowSelectionAllowed(true);
		
		
		
		btnErosi = new JButton("Erosi");
		btnErosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            int zutabe= table.getSelectedColumn();
                            int lerro= table.getSelectedRow();
                            if(zutabe!=-1 && lerro!=-1){
                                int pos= (lerro*7)+zutabe;
                                Sarrera sarrera=Sarrerak.get(pos);
                                
                                if(!sarrera.getSalduta()){
                                    if(sarrera.getPrezioa()<=kontua.saldoBerria()){
                                            kutxazaina.erosketaEgin(sarrera.getPrezioa(),sarrera.getZenbakia());
                                            if(orduaBox.getSelectedIndex()!=-1){
                                         int size=model.getRowCount();
                                          for(int i=0; i<size;i++){

                                                model.removeRow(0);
                                                System.out.println(i);

                                            }
                                        ordua=(String)orduaBox.getSelectedItem();

                                        Sarrerak=kutxazaina.aurkituSarrerak( data, herria, zinea, filma, ordua);  
                                            if(!Sarrerak.isEmpty()){			
                                                             int n=0;
                                                             Vector<Object> row = new Vector<Object>();
                                                             for(int i=0; i<Sarrerak.size();i++){                                                     

                                                             if(Sarrerak.get(i).getSalduta()){
                                                                    row.add(okupatua);

                                                             }
                                                             else{
                                                                 row.add(libre);
                                                             }
                                                             n++;
                                                             if(n==7){
                                                                 n=0;
                                                                 model.addRow(row);   
                                                                 row = new Vector<Object>();
                                                             }                                                    
                                                        }
                                                        if(n!=0){
                                                             model.addRow(row);                                                    
                                                        }
                                            }                              
                                           }
                                        }
                                    else{
                                        Component frame = null;
                                        JOptionPane.showMessageDialog( frame, "Ez dago nahiko diru kontuan", "Errorea", JOptionPane.WARNING_MESSAGE);
                                        errorea= true;
                                    }
                                }
                                else{
                                    Component frame = null;
                                    JOptionPane.showMessageDialog( frame, "Aukeratutako butaka jada erreserbatua", "Errorea", JOptionPane.WARNING_MESSAGE);
                                    errorea= true;
                                }
                            }
			}
		});
		btnErosi.setBounds(271, 11, 207, 44);
		contentPane.add(btnErosi);
		
	
		btnAmaitu = new JButton("Amaitu");
		btnAmaitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            dispose();
			}
		});
		btnAmaitu.setBounds(488, 11, 124, 44);
		contentPane.add(btnAmaitu);







	}
        
        
        
        //testerako

    public boolean getErrorea() {
        return errorea;
    }
    public void aukeratuButaka (){
        orduaBox.setSelectedIndex(0);
        btnAukeratu.doClick();
        table.setRowSelectionInterval(0,0);
        table.setColumnSelectionInterval(0,0);   
       btnErosi.doClick();
    }
        
}
