package basetestsclass;

import appline.managers.InitManager;
import appline.managers.ManagerPages;
import org.junit.After;
import org.junit.Before;

public class BaseTestClass {
    protected ManagerPages app = ManagerPages.getManagerPages();

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach() {
        InitManager.quitFramework();
    }
}
