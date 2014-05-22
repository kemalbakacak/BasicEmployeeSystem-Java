package edu.pau.hsqldbproject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pencere extends JFrame implements ActionListener 
{

    JButton previous, next, find;
    JButton allEntry, newEntry, deleteEntry, updateEntry;
    JTextField now, total;
    JTextField employeIDTF, firstNameTF, lastNameTF, emailTF, departmentTF, titleTF, findTF;
    JLabel slash, employeeID, firstName, lastName, email, department, title, text, empty;
    JComboBox choiceCB;
    JPanel topPanel,midPanel,botPanelTop,botPanelBot, jPanel1, jPanel2;
    
    ArrayList<Employee> arrayList = new ArrayList<Employee>();
    ArrayList<Employee> emailControl = new ArrayList<Employee>();
    ArrayList<Employee> findList = new ArrayList<Employee>();
    String[] choice = {"Find by Last Name", "Find by Department", "Find by Title"};
    
    DBHandler dbh = null; // Her metot görebilmesi için global değişken tanımlandı.
    
    public Pencere() 
    {
        super();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        
        // Üst taraftakiler tanıtıldı ve eklendi. Durum cubuğu.
        previous = new JButton("Previous");
        add(previous);
        now = new JTextField(10);
        now.setEditable(false);
        add(now);
        previous.addActionListener(this);
        slash = new JLabel("/");
        add(slash);
        total = new JTextField(10);
        add(total);
        total.setEditable(false);
        next = new JButton("Next");
        add(next);
        next.addActionListener(this);
        
        // Orta ksıım tanıtıldı ve eklendi. Textfield ve label.
        employeeID = new JLabel("Employee ID");
        add(employeeID);
        employeIDTF = new JTextField(20);
        employeIDTF.setEditable(false);
        add(employeIDTF);

        firstName = new JLabel("First Name");
        add(firstName);
        firstNameTF = new JTextField(20);
        add(firstNameTF);

        lastName = new JLabel("Last Name");
        add(lastName);
        lastNameTF = new JTextField(20);
        add(lastNameTF);

        email = new JLabel("Email");
        add(email);
        emailTF = new JTextField(20);
        add(emailTF);

        department = new JLabel("Department");
        add(department);
        departmentTF = new JTextField(20);
        add(departmentTF);

        title = new JLabel("Title");
        add(title);
        titleTF = new JTextField(20);
        add(titleTF);
        
        // Alt kısım tanıtıldı ve eklendi. Find kısmı
        text = new JLabel("Find by a property");
        add(text);
        
        choiceCB = new JComboBox(choice);
        ComboBoxModel secenekler = new DefaultComboBoxModel(choice);
        choiceCB.setModel(secenekler);
        add(choiceCB);
        choiceCB.addActionListener(this);
        
        findTF = new JTextField(10);
        add(findTF);
        
        find = new JButton("Find");
        add(find);
        find.addActionListener(this); 
        
        allEntry = new JButton("Browse all entry");
        add(allEntry);
        allEntry.addActionListener(this);
        newEntry = new JButton("Insert new entry");
        add(newEntry);
        newEntry.addActionListener(this);
        deleteEntry = new JButton("Delete entry");
        add(deleteEntry);
        deleteEntry.addActionListener(this);
        updateEntry = new JButton("Update entry");
        add(updateEntry);
        updateEntry.addActionListener(this);
        
        // 2 adet panele eklendi.
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(now, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(employeeID)
                    .addComponent(firstName)
                    .addComponent(lastName)
                    .addComponent(email)
                    .addComponent(department)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleTF)
                    .addComponent(departmentTF)
                    .addComponent(emailTF)
                    .addComponent(lastNameTF)
                    .addComponent(firstNameTF)
                    .addComponent(employeIDTF)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previous)
                    .addComponent(next)
                    .addComponent(now, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slash)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeID)
                    .addComponent(employeIDTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstName)
                    .addComponent(firstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastName)
                    .addComponent(lastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(department)
                    .addComponent(departmentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title)
                    .addComponent(titleTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );
        
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(choiceCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(findTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(find)))
                .addGap(0, 60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(allEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(text)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(choiceCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(find))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newEntry)
                    .addComponent(allEntry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateEntry)
                    .addComponent(deleteEntry))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        // Panellere eklemenin sonu.
        
        String connectionString = "jdbc:hsqldb:file:db/employeedb"; // Connect to DB.
        try 
        {
            dbh = new DBHandler(connectionString);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // İlk açıldığında bütün employee tablosunu görebilmek için başlangıçta çalıştırılıyor.
        String query = "SELECT * FROM employee";
        try 
        {
            arrayList = dbh.executeQuery(query);
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        int adet = arrayList.size(); // Kaç adet kayıt olduğunu tutmak amacıyla yazıldı.
        String adetStr = Integer.toString(adet);
        total.setText(adetStr);
        
        int defaultDeger = 1; // İlk kayıt aslında 0 ancak günlük yaşamda 0. olmadığı için tanımlandı.
                              // actionPerformed metotunda  -1 yaparak bu açık kapanıyor.
        String defaultDegerString = Integer.toString(defaultDeger);
        now.setText(defaultDegerString);
        
        display(defaultDeger);
    }

    public void actionPerformed(ActionEvent e) 
    {
        int adet = arrayList.size(); // Boyutu öğrenildi.
        
        String degerKontrol = now.getText();
        int deger = Integer.parseInt(degerKontrol);
        
        // Giriş yerleri boş mu diye bakılıyor.Trim fonksiyonu baştan ve sondan bulunan boşlukları temizliyor.
        boolean flagBoslukKontrol = true;
        if(firstNameTF.getText().trim().equals("") || lastNameTF.getText().trim().equals("") || emailTF.getText().trim().equals("") 
                || departmentTF.getText().trim().equals("") || titleTF.getText().trim().equals(""))
        {
            flagBoslukKontrol = false;
            JOptionPane.showMessageDialog(null, "Fields is empty.");    
        }
        
        // isim değerinin karakter uzunkluğu 20'den uzun ise
        Boolean flagLenght = true;
        if(firstNameTF.getText().length() > 20)
        {
            JOptionPane.showMessageDialog(null, "Fist name is too long.");
            flagLenght = false;
        }
        
        // Soyisim değerinin karakter uzunluğu 20'den uzun ise
        if(lastNameTF.getText().length() > 20)
        {
            JOptionPane.showMessageDialog(null, "Last name is too long.");
            flagLenght = false;
        }
        
        // Email değerinin karakter uzunluğu 70'den uzun ise
        if(emailTF.getText().length() > 70)
        {
            JOptionPane.showMessageDialog(null, "Email is too long.");
            flagLenght = false;
        }
        
        // Department değerinin karakter uzunluğu 50'den uzun ise
        if(departmentTF.getText().length() > 50)
        {
            JOptionPane.showMessageDialog(null, "Department is too long.");
            flagLenght = false;
        }
        
        // Title değerinin karakter uzunluğu 50'den uzun ise
        if(titleTF.getText().length() > 50)
        {
            JOptionPane.showMessageDialog(null, "Title is too long.");
            flagLenght = false;
        }      
        // Kontrollerin sonu...
        
        // Artık butonların işlevleri kontrol ediliyor.
        if (e.getActionCommand().equals("Next")) 
        {
            if(deger < adet)
            {
                deger++;
                display(deger); // Stringe çevirmeden önce yollanmalı. Önemli nokta.
                String degerString = Integer.toString(deger);
                now.setText(degerString);
            }
        } 
        else if (e.getActionCommand().equals("Previous")) 
        { 
            if(deger > 1) // Sıfırdan büyük ise
            {
                deger--;
                display(deger);
                String degerString = Integer.toString(deger);
                now.setText(degerString);
            }
        }
        else if(e.getActionCommand().equals("Find"))
        {
            String aramaTercihi = (String)choiceCB.getSelectedItem();
            boolean flagFindBosluk = true;
            if(findTF.getText().trim().equals(""))
                flagFindBosluk = false;
            
            if(flagBoslukKontrol == true && flagFindBosluk == true)
            {
                switch(aramaTercihi)
                {
                    case "Find by Last Name":
                        String lastNameFind = findTF.getText();
                        String queryLastName = "SELECT * FROM employee WHERE LastName LIKE '" + lastNameFind + "%'";
                        arrayList.clear();
                    try {
                        arrayList = dbh.executeQuery(queryLastName);
                        int uzunluk = arrayList.size();
                        if(uzunluk > 0)
                        {
                            String uzunlukStr = Integer.toString(uzunluk);
                            total.setText(uzunlukStr);
                            deger = 1;
                            now.setText(Integer.toString(deger));
                            employeIDTF.setText(Integer.toString(arrayList.get(deger-1).getId()));
                            firstNameTF.setText(arrayList.get(deger-1).getFirstName());
                            lastNameTF.setText(arrayList.get(deger-1).getLastName());
                            emailTF.setText(arrayList.get(deger-1).getEmail());
                            departmentTF.setText(arrayList.get(deger-1).getDepartment());
                            titleTF.setText(arrayList.get(deger-1).getTitle());
                        }
                        else // Demek ki öyle bir kişi veya kişiler yok.
                        {
                            JOptionPane.showMessageDialog(null, "User is not found.");
                            String query = "SELECT * FROM employee";
                            arrayList = dbh.executeQuery(query);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        break;
                    case "Find by Department": 
                        arrayList.clear();
                        String departmentFind = findTF.getText();
                        String queryDepartment = "SELECT * FROM employee WHERE Department LIKE '" + departmentFind + "%'";
                    try {
                        arrayList = dbh.executeQuery(queryDepartment);
                        int uzunluk = arrayList.size();
                        if(uzunluk > 0)
                        {
                            String uzunlukStr = Integer.toString(uzunluk);
                            total.setText(uzunlukStr);
                            deger = 1;
                            now.setText(Integer.toString(deger));
                            employeIDTF.setText(Integer.toString(arrayList.get(deger-1).getId()));
                            firstNameTF.setText(arrayList.get(deger-1).getFirstName());
                            lastNameTF.setText(arrayList.get(deger-1).getLastName());
                            emailTF.setText(arrayList.get(deger-1).getEmail());
                            departmentTF.setText(arrayList.get(deger-1).getDepartment());
                            titleTF.setText(arrayList.get(deger-1).getTitle());
                        }
                        else // Demek ki öyle bir kişi veya kişiler yok.
                        {
                            JOptionPane.showMessageDialog(null, "User is not found.");
                            String query = "SELECT * FROM employee";
                            arrayList = dbh.executeQuery(query);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        break;
                    case "Find by Title": 
                        arrayList.clear();
                        String titleFind = findTF.getText();
                        String queryTitle = "SELECT * FROM employee WHERE Title LIKE '" + titleFind + "%'";
                    try {
                        arrayList = dbh.executeQuery(queryTitle);
                        int uzunluk = arrayList.size();
                        if(uzunluk > 0)
                        {
                            String uzunlukStr = Integer.toString(uzunluk);
                            total.setText(uzunlukStr);
                            deger = 1;
                            now.setText(Integer.toString(deger));
                            employeIDTF.setText(Integer.toString(arrayList.get(deger-1).getId()));
                            firstNameTF.setText(arrayList.get(deger-1).getFirstName());
                            lastNameTF.setText(arrayList.get(deger-1).getLastName());
                            emailTF.setText(arrayList.get(deger-1).getEmail());
                            departmentTF.setText(arrayList.get(deger-1).getDepartment());
                            titleTF.setText(arrayList.get(deger-1).getTitle());
                        }
                        else // Demek ki öyle bir kişi veya kişiler yok.
                        {
                            JOptionPane.showMessageDialog(null, "User is not found.");
                            String query = "SELECT * FROM employee";
                            arrayList = dbh.executeQuery(query);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
            else
            {
                try {
                    JOptionPane.showMessageDialog(null, "Search field is empty.");
                    String query = "SELECT * FROM employee";
                    arrayList = dbh.executeQuery(query);
                } catch (SQLException ex) {
                    Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if(e.getActionCommand().equals("Browse all entry"))
        {
            String queryAll = "SELECT * FROM employee";
            try {
                arrayList = dbh.executeQuery(queryAll);
                adet = arrayList.size();
                String adetStr = Integer.toString(adet);
                total.setText(adetStr);
                deger = 1;
                String degerString = Integer.toString(deger);
                now.setText(degerString);
                display(deger);
                
            } catch (SQLException ex) {
                Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getActionCommand().equals("Insert new entry"))
        {
            String firstNameInsert = firstNameTF.getText();
            String lastNameInsert = lastNameTF.getText();
            String emailInsert = emailTF.getText();
            String departmentInsert = departmentTF.getText();
            String titleInsert = titleTF.getText();
            
            String query = "SELECT * FROM Employee WHERE Email='" + emailInsert + "'"; // Değişkeni yolluyoruz.
            
            boolean emailBooleanDeger = emailControl(emailInsert); // true gelirse devam edecek.
            if(flagLenght == true && emailBooleanDeger == true && flagBoslukKontrol == true) // Uzınlukların kontrolünden geçerse ancak buraya girecek.
            {
                try {
                emailControl.clear(); // ArrayListi boşaltıyoruz.
                emailControl = dbh.executeQuery(query); // Query yolladık
                
                int emailControlDeger = emailControl.size(); // Eğer boyutu 1'den fazla ise demek ki böyle bir mail adresi var
                
                if(emailControlDeger == 0) // Yani veritabanında o isimde email yoksa ekleyecek.
                { 
                    String queryInsert = "INSERT INTO Employee(FirstName, LastName, Email, Department, Title) VALUES('"
                                + firstNameInsert + "','"  
                                + lastNameInsert + "','" + emailInsert + "','" 
                            + departmentInsert + "','" + titleInsert + "')";
                    dbh.executeQuery(queryInsert);

                    String queryAll = "SELECT * FROM employee";
                    arrayList = dbh.executeQuery(queryAll);
                    adet = arrayList.size();
                    String adetStr = Integer.toString(adet);
                    total.setText(adetStr);
                    now.setText(adetStr);
                    display(adet);
                }
                else // emailControl 0 veya 1 değil.
                {
                     JOptionPane.showMessageDialog(null, "This email address is using.");
                }
                } catch (SQLException ex) {
                Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
        else if(e.getActionCommand().equals("Delete entry"))
        {
            int idDelete = Integer.parseInt(employeIDTF.getText());
            String query = "DELETE FROM employee WHERE ID='" + idDelete + "'";
            int silmeOnay = JOptionPane.showConfirmDialog(null, "Are you sure?", "For Delete", JOptionPane.YES_NO_OPTION);
            if(silmeOnay == JOptionPane.YES_OPTION)
            {
                try {
                dbh.executeNonQuery(query);
                String queryAll = "SELECT * FROM employee";
                arrayList = dbh.executeQuery(queryAll);
                adet = arrayList.size();
                String adetStr = Integer.toString(adet);
                total.setText(adetStr);
                deger = 1;
                String degerString = Integer.toString(deger);
                now.setText(degerString);
                display(deger);

                } catch (SQLException ex) {
                    Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if(e.getActionCommand().equals("Update entry"))
        {
            int idUpdate = Integer.parseInt(employeIDTF.getText());
            String firstNameUpdate = firstNameTF.getText();
            String lastNameUpdate = lastNameTF.getText();
            String emailUpdate = emailTF.getText();
            String departmentUpdate = departmentTF.getText();
            String titleUpdate = titleTF.getText();
            
            String queryEmail = "SELECT * FROM Employee WHERE Email='" + emailUpdate + "'"; // Değişkeni yolluyoruz.
                        
            boolean emailBooleanDeger = emailControl(emailUpdate); // true gelirse devam edecek.
            if(flagLenght == true && emailBooleanDeger == true && flagBoslukKontrol == true) // Uzınlukların kontrolünden geçerse ancak buraya girecek.
            {
                emailControl.clear();
                try {
                    emailControl = dbh.executeQuery(queryEmail); // Query yolladık
                    int emailControlDeger = emailControl.size(); // Eğer boyutu 1'den fazla ise demek ki böyle bir mail adresi var
                        
                    if(emailControlDeger == 0 || emailControlDeger == 1) // unique olması için 0 değeri olabilir. 
                       // Ancak kişiyi güncellerken email adresi değişmek zorunda değil bu yüzden 1 değeride alabilir.
                    {
                        String queryUpdate = "UPDATE employee SET FirstName='" + firstNameUpdate + "', LastName='" + lastNameUpdate + "',"
                                    + "Email='" + emailUpdate + "', Department='" + departmentUpdate + "', Title='" + titleUpdate + "' WHERE "
                                    + "ID='" + idUpdate + "'";
                        String queryAll = "SELECT * FROM employee";
                        dbh.executeQuery(queryUpdate);
                        arrayList = dbh.executeQuery(queryAll);
                        display(deger);
                    }
                    else // emailContorlDegeri 0 değil ise
                    {
                        JOptionPane.showMessageDialog(null, "This email address is using.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    } // actionPermofmed metod sonu.
    
   private void display(int deger)
   {
       employeIDTF.setText(Integer.toString(arrayList.get(deger-1).getId()));
       firstNameTF.setText(arrayList.get(deger-1).getFirstName());
       lastNameTF.setText(arrayList.get(deger-1).getLastName());
       emailTF.setText(arrayList.get(deger-1).getEmail());
       departmentTF.setText(arrayList.get(deger-1).getDepartment());
       titleTF.setText(arrayList.get(deger-1).getTitle());
   }
   
   // Bu metot içerisinde @ ve . var mı diye kontrol edecek e-mail de ancak bu kadar yapılabilir.
   private boolean emailControl(String deger)
   {
       boolean atKontrol=false, noktaKontrol=false;
       for(int i=0; i<deger.length(); i++)
       {
           char a = deger.charAt(i);
           
           if(a == '@')
               atKontrol = true;
           if(a == '.')
               noktaKontrol = true;
       }
       if(atKontrol == true && noktaKontrol == true)
           return true;
       JOptionPane.showMessageDialog(null, "Invalid email address.");
       return false;
   } 
}