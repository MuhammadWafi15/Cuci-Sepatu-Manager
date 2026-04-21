package teorikomputasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cuciSepatuManager extends JFrame {
    private JTextField[] inputFields = new JTextField[3];
    private JLabel[] statusLabels = new JLabel[3];
    private String[] fieldLabels = {
        "Kode Layanan (Contoh: CSM-DEP/FST-00)", 
        "Berat Sepatu (Kg)", 
        "Jam Pengambilan "
    };
    private JButton btnCek;

    public cuciSepatuManager() {
        setTitle("CleanStep Manager - Sistem Layanan Cuci Sepatu");
        setSize(550, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        
        JPanel panelForm = new JPanel(new GridLayout(9, 1, 5, 5));
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

       
        for (int i = 0; i < 3; i++) {
            panelForm.add(new JLabel("Masukkan " + fieldLabels[i] + ":"));
            inputFields[i] = new JTextField();
            panelForm.add(inputFields[i]);
            
            statusLabels[i] = new JLabel("Status: -");
            statusLabels[i].setFont(new Font("Monospaced", Font.BOLD, 12));
            panelForm.add(statusLabels[i]);
        }

        btnCek = new JButton("Validasi Data");
        btnCek.setBackground(new Color(50, 100, 200));
        btnCek.setForeground(Color.WHITE);

        add(panelForm, BorderLayout.CENTER);
        add(btnCek, BorderLayout.SOUTH);

        btnCek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eksekusiValidasi();
            }
        });

        setLocationRelativeTo(null);
    }

    private void eksekusiValidasi() {
     
        String[] regexRules = {
            "^CSM-[A-Z]{3}-[0-9]{2}$",        
            "^[0-9]\\.[0-9]{2}$",             
            "^([01][0-9]|2[0-3]):[0-5][0-9]$"         
        };

        String[] errorHints = {
            "Gagal: Gunakan format CSM-KATEGORI-ANGKA.",
            "Gagal: Wajib 1 angka, titik, dan 2 desimal.",
            "Gagal: Gunakan format 24 jam (00:00 - 23:59)."
        };

        for (int i = 0; i < 3; i++) {
            String textInput = inputFields[i].getText();
            
            if (textInput.matches(regexRules[i])) {
                statusLabels[i].setText("Status: VALID");
                statusLabels[i].setForeground(new Color(0, 150, 0));
            } else {
                statusLabels[i].setText("Status: TIDAK VALID - " + errorHints[i]);
                statusLabels[i].setForeground(Color.RED); 
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new cuciSepatuManager().setVisible(true);
        });
    }
}