package felixsam.github.com.foodordering;

import android.os.Build;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;
import static org.junit.Assert.assertEquals;

import felixsam.github.com.foodordering.activities.Login_Activity;

@RunWith(RobolectricTestRunner.class)
@Config(sdk={Build.VERSION_CODES.O_MR1})

public class LoginTest {

    private Login_Activity activity;
    private Button loginButton;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(Login_Activity.class)
                .create()
                .resume()
                .get();

    }

    @Test
    public void loginButtonTest()
    {
        activity.findViewById(R.id.btn_login).performClick();

        assertEquals( ShadowToast.getTextOfLatestToast(), "Cannot login, no users registered" );
    }



}
