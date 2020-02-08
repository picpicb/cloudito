package com.ackincolor.cloudito;

import android.content.Context;
import android.util.Log;

import com.ackincolor.cloudito.entities.Location;
import com.ackincolor.cloudito.ui.components.Map;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class MapTest {
    @Test
    public void testMapTranslation() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getContext();
        ArrayList<ArrayList<Location>> liste = new ArrayList<>();
        ArrayList<Location> temp = new ArrayList<>();
        temp.add(new Location(1,0,1,1));
        liste.add(temp);
        Map map = new Map(appContext,null);
        map.setMap(new com.ackincolor.cloudito.entities.Map(liste));
        map.setOffsetX(1);
        map.calculNewCoord2();
        com.ackincolor.cloudito.entities.Map modifiedMap = map.getMap();
        for(ArrayList<Location> al : modifiedMap.getListe()){
            for(Location l : al){
                Log.d("DEBUG",l.toString());
                assertTrue(l.equals(new Location(1,0,-900,-699)));
            }
        }
        map.setOffsetY(1);
        map.calculNewCoord2();
        modifiedMap = map.getMap();
        for(ArrayList<Location> al : modifiedMap.getListe()){
            for(Location l : al){
                Log.d("DEBUG",l.toString());
                assertTrue(l.equals(new Location(1,0,-900,-700)));
            }
        }
    }
}
