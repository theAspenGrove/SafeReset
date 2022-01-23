package net.mov51.safeReset.util;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationConstruct {

    public Location MakeLocation(int x, int y, int z, World world){
        return new Location(world,x,y,z);
    }

}
