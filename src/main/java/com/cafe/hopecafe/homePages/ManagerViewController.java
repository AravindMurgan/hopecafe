package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.RootBorderPaneHolder;
import com.cafe.hopecafe.utils.Routing;
import com.cafe.hopecafe.utils.UserData;


/**
 * This class creates manager page pane and methods to control.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class ManagerViewController {

    public void addStaffOnAction(){
        Routing routing = new Routing();
        routing.navigateToPage(FxmlPaths.ADD_STAFF_FXML);
    }

    public void removeStaffOnAction(){
        Routing routing = new Routing();
        routing.navigateToPage(FxmlPaths.REMOVE_STAFF_FXML);

    }

    public void logoutOnAction(){
        UserData.getInstance().clearUserData();
        Routing routing = new Routing();
        routing.navigateToLoginPage();
    }
}
