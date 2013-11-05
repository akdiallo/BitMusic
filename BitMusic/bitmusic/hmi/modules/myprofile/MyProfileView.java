/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hmi.modules.myprofile;

import hmi.patterns.AbstractController;
import hmi.patterns.AbstractView;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author khadre
 */
public class MyProfileView extends AbstractView{
    
    public MyProfileView(AbstractController abstractController) {
        super(abstractController);
        this.initPanel();
    }
    
    @Override
    public void initPanel() {
             System.out.println("--- MyProfileView.initPanel()");
        this.panel = new JPanel();
        Dimension d = new Dimension(80, 20);
        
        JLabel test = new JLabel("Mon profil");
        test.setSize(d);
        
        JLabel loginLabel = new JLabel("Pseudo");
        loginLabel.setSize(d);
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setSize(d);
        
        
        
        GroupLayout layout = new GroupLayout(this.panel);
        this.panel.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(test)
                              
        );
       
       /* layout.linkSize(SwingConstants.HORIZONTAL, loginLabel, loginField);
        layout.linkSize(SwingConstants.HORIZONTAL, passwordLabel, passwordField);*/
    }
}
