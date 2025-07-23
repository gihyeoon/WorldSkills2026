package com.lgh.x_frenchtravel;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String PACKAGE_NAME = "com.lgh.x_frenchtravel";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    @Before
    public void launchApp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        device.pressHome();

        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_NAME);
        if (intent == null) throw new RuntimeException("앱 실행 인텐트 없음");

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void testHome() {
        UiObject2 homeTitle = device.wait(Until.findObject(By.res(PACKAGE_NAME, "textView")), 3000);
        assertNotNull("홈 화면이 정상적으로 표시되지 않음", homeTitle);

        UiObject2 bookingBtn = device.wait(Until.findObject(By.res(PACKAGE_NAME, "hotelBookBtn")), 3000);
        assertNotNull("예약 버튼이 없음", bookingBtn);
        bookingBtn.click();
    }

}