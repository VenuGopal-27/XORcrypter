import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import java.io.FileOutputStream;

public class FileEncryption {
    public static void operate(int key){
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try{
            FileInputStream fis=new FileInputStream(file);;
            byte []data=new byte[fis.available()];
            fis.read(data);

            int i=0;
            for(byte d:data){
                data[i]=(byte)(d-key);
                i++;
            }
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null,"Encrypted");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void de_operate(int key){
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();
        try{
            FileInputStream fis=new FileInputStream(file);;
            byte []data=new byte[fis.available()];
            fis.read(data);

            int i=0;
            for(byte d:data){
                data[i]=(byte)(d+key);
                i++;
            }
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null,"Decrypted");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    private final static String newLine="\n";
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setTitle("File Encryption");
        f.setSize(450,300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font=new Font("Roboto",Font.BOLD,15);
        Font font2=new Font("Times new Roman",Font.ITALIC,13);

        JLabel label=new JLabel("Enter your PIN here:");
        label.setSize(20,10);

        JTextArea jpm =new JTextArea(6,26);//"Note: Encrypted file gets multiple encryption if encrypted multiple times.\n Decryption acts like encryption when a non-encrypted file is decrypted.")
        jpm.setEditable(false);
        Insets margin = new Insets(100,0,0,0);

        jpm.append("Note: Encrypted file gets multiple encryption,\nif encrypted multiple times.\n\nDecryption acts like encryption,\nwhen a non-encrypted file is decrypted.\n");
        JScrollPane scrollPAne=new JScrollPane(jpm);
        jpm.setFont(font2);
        jpm.setForeground(Color.RED);

        JTextField textField = new JTextField(10);
        textField.setFont(font);

        JButton button=new JButton();
        JButton button2=new JButton();
        button2.setText("Select file to decrypt");
        button2.setFont(font);
        button.setText("Select file to encrypt");
        button.setFont(font);
        button.setSize(30,15);
        button2.setSize(30,15);

        button.addActionListener(e->{
            String text= textField.getText();
            int temp=Integer.parseInt(text);
            operate(temp);
        });

        button2.addActionListener(e->{
            String text= textField.getText();
            int temp=Integer.parseInt(text);
            de_operate(temp);
        });

        f.setLayout(new FlowLayout());

        f.add(label);
        f.add(textField);
        f.add(button);
        f.add(button2);
        f.add(scrollPAne);

        f.setVisible(true);

    }
}