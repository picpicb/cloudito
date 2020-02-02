package com.ackincolor.cloudito;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.ackincolor.cloudito.GeolocationService.GeolocationAndroidService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class GeolocationServiceTest {

    @Test
    public void calculateDistanceTest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getContext();
        GeolocationAndroidService service = new GeolocationAndroidService(appContext);

        try {
            service.calculateDistance(12,58);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Signal can't be > 0");
            Log.d("DEBUG TEST","Erreur throwed");
        }
        try {
            service.calculateDistance(-56,-14);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Frequency can't be < 0");
            Log.d("DEBUG TEST","Erreur throwed");
        }
        try {
            service.calculateDistance(85,-14);
        } catch (Exception e) {
            assertEquals(e.getMessage(),"Signal can't be > 0");
            Log.d("DEBUG TEST","Erreur throwed");
        }

        try {
            assertTrue(Math.pow(10.0,(27.55 - (20 * Math.log10(10)) + Math.abs(-10)) / 20.0) == service.calculateDistance(-10,10));
            Log.d("DEBUG TEST","SAME VALUE OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
