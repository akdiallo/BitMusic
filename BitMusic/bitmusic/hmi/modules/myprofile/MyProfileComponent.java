/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hmi.modules.myprofile;

import hmi.modules.connection.ConnectionController;
import hmi.modules.connection.ConnectionModel;
import hmi.modules.connection.ConnectionView;
import hmi.patterns.AbstractModuleComponent;

/**
 *
 * @author khadre
 */
public class MyProfileComponent extends AbstractModuleComponent {

    public MyProfileComponent() {
        this.model = new MyProfileModel();
        this.controller = new MyProfileController(this.model);
        this.view = new ConnectionView(this.controller);
        this.model.addObserver(this.view);
        this.view.setVisible(true);
    }
}
