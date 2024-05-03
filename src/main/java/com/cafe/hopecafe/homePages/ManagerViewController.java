package com.cafe.hopecafe.homePages;

import com.cafe.hopecafe.utils.FxmlPaths;
import com.cafe.hopecafe.utils.Routing;

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
        Routing routing = new Routing();
        routing.navigateToLoginPage();
    }
}
