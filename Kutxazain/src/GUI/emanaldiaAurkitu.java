package GUI;

import Domain.Bezeroa;
import Domain.Emanaldia;
import Domain.Kontua;
import BussinesLogic.Kutxazaina;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;



import javax.swing.JTable;

import com.toedter.calendar.JCalendar;


public class emanaldiaAurkitu extends JFrame {

	private JPanel contentPane;
	private String zineCity=null;
	



	private Calendar calendar = null;
	private JComboBox herriak;
        private JComboBox zineakBox;
        private   JCalendar kalendarioa;
	private DefaultComboBoxModel herriakList = new DefaultComboBoxModel();
	private DefaultComboBoxModel zineakList = new DefaultComboBoxModel();

        private String Data,Zinea,Herria;
        private Vector<String> filmak;
	private List<String> Herriak;
        private List<Emanaldia> Emanaldiak;
        private List<String> Zineak;
        private Bezeroa bezeroa;
        private Kontua kontua;
        private Kutxazaina kutxazaina;
	private JTable table;


	public emanaldiaAurkitu(Bezeroa bez, Kontua kont,Kutxazaina kutxazain) {
		bezeroa=bez;
                kontua=kont;
                kutxazaina=kutxazain;
                Emanaldiak= new ArrayList<Emanaldia>();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 372, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmanaldia = new JLabel("Emanaldia Aukeratu");
		lblEmanaldia.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmanaldia.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmanaldia.setBounds(10, 11, 335, 26);
		contentPane.add(lblEmanaldia);

		JLabel lblHerria = new JLabel("Herria:");
		lblHerria.setBounds(10, 48, 46, 14);
		contentPane.add(lblHerria);

		herriak = new JComboBox();
		herriak.setBounds(62, 43, 283, 26);
		contentPane.add(herriak);

		JLabel lblZinea = new JLabel("Zinema:");
		lblZinea.setBounds(10, 108, 119, 14);
		contentPane.add(lblZinea);
		
		kalendarioa= new JCalendar();
		kalendarioa.setBounds(10, 142, 329, 173);
		contentPane.add(kalendarioa);

		final JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 591, 335, 22);
		contentPane.add(textArea);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 348, 335, 232);
		contentPane.add(scrollPane);
                
        zineakBox= new JComboBox();
		zineakBox.setBounds(62, 105, 283, 26);
		contentPane.add(zineakBox);

		final DefaultTableModel model = new DefaultTableModel(){
			public Class<?> getColumnClass(int column)
			{
				if (column == 0)
				{
					return Icon.class;
				}else{
					return Object.class;
				}
			}
		};
		model.setColumnIdentifiers(new Object[] {"Argazkia","Filma","Prezioa" });

		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.setRowHeight(70);
		table.setRowSelectionAllowed(true);

                
               
             
		


		Herriak = kutxazaina.getHerriak();
		for(int i=0; i<Herriak.size();i++){
			herriakList.addElement(Herriak.get(i));
		}
                
                   
		herriak.setModel(herriakList);
		herriak.setSelectedIndex(-1);
		herriak.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(!(herriak.getSelectedIndex()==-1)){
					zineCity = (String) herriak.getSelectedItem();
                                         Zineak= kutxazaina.getZineak(zineCity);
                                         for(int i=0; i<Zineak.size();i++){
                                            zineakList.addElement(Zineak.get(i));           
                                        }
                                        zineakBox.setModel(zineakList);
                                        zineakBox.setSelectedIndex(-1);
                
				}
			}
		});
                
                
		
		
		this.kalendarioa.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{
				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					kalendarioa.setLocale((Locale) propertychangeevent.getNewValue());
					DateFormat dateformat = DateFormat.getDateInstance(1, kalendarioa.getLocale());
					textArea.setText(dateformat.format(calendar.getTime()));
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendar = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, kalendarioa.getLocale());
					kalendarioa.setCalendar(calendar);
					textArea.setText(dateformat1.format(calendar.getTime()));
				}
			} 
		});
	
		
		JButton btnBilatu = new JButton("Bilatu");
		btnBilatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                                if(!(herriak.getSelectedIndex()==-1)&&!(zineakBox.getSelectedIndex()==-1) ){
                                    SimpleDateFormat format=new SimpleDateFormat("dd-M-yyyy");
                                    Emanaldiak= kutxazaina.aurkituFilmak(format.format(calendar.getTime()),(String) herriak.getSelectedItem(),(String) zineakBox.getSelectedItem());
                                    Data=format.format(calendar.getTime());
                                    Herria=(String) herriak.getSelectedItem();
                                    Zinea=(String) zineakBox.getSelectedItem();
                                    if(!Emanaldiak.isEmpty()){
					filmak = new Vector<String>();
					for(int i=0; i<Emanaldiak.size(); i++){
                                                boolean gordeta=false;
                                                for(int n=0; n<filmak.size();n++){
                                                    if(filmak.get(n).compareTo(Emanaldiak.get(i).getFilma())==0){
                                                        gordeta=true;
                                                        
                                                    }
                                                }
                                                if(!gordeta){
                                                    
                                                    filmak.add(Emanaldiak.get(i).getFilma());
                                                     Vector<Object> row = new Vector<Object>();

                                                     ImageIcon icon = null;
                                                     try {
                                                             icon = new ImageIcon(new URL(Emanaldiak.get(i).getArgazkia()));
                                                             Image image = icon.getImage(); // transform it 
                                                             Image newimg = image.getScaledInstance(140, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                                                             icon = new ImageIcon(newimg);  
                                                     } catch (MalformedURLException ex) {			
                                                             String path = "https://az853139.vo.msecnd.net/static/images/not-found.png";
                                                             System.out.println("Get Image from " + path);
                                                             try {
                                                                     icon = new ImageIcon(new URL(path));
                                                             } catch (MalformedURLException e) {
                                                             }
                                                             Image image = icon.getImage(); // transform it 
                                                             Image newimg = image.getScaledInstance(140, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                                                             icon = new ImageIcon(newimg); 
                                                     }	

                                                     // transform it back

                                                     row.add(icon);
                                                     row.add(String.valueOf(Emanaldiak.get(i).getFilma()));
                                                     row.add(Emanaldiak.get(i).getPrezioa());
                                                     model.addRow(row);
                                                }
					}
				}
                                }
                                else{
                                    textArea.setText("Aukeratu hiria eta zinea");
                                }
			}
		});
		btnBilatu.setBounds(10, 314, 329, 23);
		contentPane.add(btnBilatu);
		
			JButton btnErosi = new JButton("Jarraitu");
		btnErosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int j=table.getSelectedRow();
				
				if(j!=-1){
                                        String filma=filmak.get(j);                                  
					JFrame a = new sarreraErreserba(bezeroa,kontua,kutxazaina,Herria,Zinea,filma,Data);
					a.setVisible(true);
					dispose();
				}
				
			}
		});
		btnErosi.setBounds(116, 624, 117, 29);
		contentPane.add(btnErosi);


	}
}
