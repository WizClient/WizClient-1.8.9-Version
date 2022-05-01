package WizClient.font;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import WizClient.font.MinecraftFontRenderer;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.ibm.icu.impl.duration.impl.Utils;

@SuppressWarnings("NonAtomicOperationOnVolatileField")
public class FontUtil {
    public static volatile int completed;
    public static MinecraftFontRenderer normal;
    private static Font normal_;

    private static Font getFont(Map<String, Font> locationMap, String location, int size) {
        Font font = null;
        
        

        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            } else {
            	
            	
            	
                InputStream is = Minecraft.getMinecraft().getResourceManager()
                        .getResource(new ResourceLocation("WizClient/f/" + location)).getInputStream();
            	
                font = Font.createFont(0, is);
                
                locationMap.put(location, font);
                font = font.deriveFont(Font.PLAIN, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", Font.PLAIN, +10);
        }

        return font;
    }

    public static boolean hasLoaded() {
        return completed >= 3;
    }

    public static void bootstrap() {
        new Thread(() ->
        {
            Map<String, Font> locationMap = new HashMap<>();
            normal_ = getFont(locationMap, "arial.ttf", 19);
            completed++;
        }).start();
        new Thread(() ->
        {
            Map<String, Font> locationMap = new HashMap<>();
            completed++;
        }).start();
        new Thread(() ->
        {
            Map<String, Font> locationMap = new HashMap<>();
            completed++;
        }).start();

        while (!hasLoaded()) {
            try {
                //noinspection BusyWait
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //normal = new MinecraftFontRenderer(new Font("Arial", Font.BOLD, 12), true, true);
        normal = new MinecraftFontRenderer(normal_, true, true);
    }
    
    public static void test() {
    	try {
        	Font uu = new Font("Comic Sans MS",Font.BOLD,40);
        	
        	int u = 10;
        	
        	System.out.println(normal.getClass());
        	
        	normal = new MinecraftFontRenderer(uu, true, true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("pipi");
		}
    }

	private static char[] typeof(Font uu) {
		// TODO Auto-generated method stub
		return null;
	}
}