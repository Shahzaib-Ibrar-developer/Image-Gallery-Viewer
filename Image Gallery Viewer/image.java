import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
//done 
class Gallery {
    JFrame frame = new JFrame("Image Gallery Viewer");
    JPanel panel = new JPanel();
    JButton addButton = new JButton("Add Image");
    JButton deleteButton = new JButton("Delete Image");
    JPanel imagePanel = new JPanel(new GridLayout(0, 4)); // Panel to hold the images with 4 columns
    JScrollPane scrollPane = new JScrollPane(imagePanel); // Scroll pane for the image panel
    JLabel selectedImageLabel = null; // Store the selected image label

    public Gallery() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedImage();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(buttonPanel, BorderLayout.NORTH);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public void selectImage() {
        JFileChooser fileChooser = new JFileChooser();

        // Show the file chooser dialog
        int returnValue = fileChooser.showOpenDialog(frame);

        // Check if a file was selected
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            java.io.File selectedFile = fileChooser.getSelectedFile();

            // Create an ImageIcon from the selected file
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            Image image = imageIcon.getImage();

            // Create a scaled ImageIcon with fixed size
            Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Create a JLabel with the scaled image icon
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setPreferredSize(new Dimension(200, 200));
            imageLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    selectedImageLabel = (JLabel) e.getSource();
                }
            });

            // Add the label to the image panel
            imagePanel.add(imageLabel);
            imagePanel.revalidate();

            frame.validate();
        } else {
            System.out.println("No file selected.");
        }
    }

    public void deleteSelectedImage() {
        if (selectedImageLabel != null) {
            imagePanel.remove(selectedImageLabel);
            selectedImageLabel = null;
            imagePanel.revalidate();
        }
    }
}

public class image {
    public static void main(String[] args) {
        Gallery gallery = new Gallery();
    }
}
