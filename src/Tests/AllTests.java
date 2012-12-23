package Tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  ManagerTest.class, TeacherTest.class,  BookingTest.class, TeacherFacadeTest.class})
public class AllTests 
{

}
