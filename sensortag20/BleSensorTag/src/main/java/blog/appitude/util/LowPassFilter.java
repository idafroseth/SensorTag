package blog.appitude.util;

/**
 * Created by Zelus on 19/06/2017.
 */

public class LowPassFilter {

    static final float ALPHA = 0.25f; // if ALPHA = 1 OR 0, no filter applies.

    public static float[] filter(float[] dataToFilter, float[] lastFilteredValues){
        if ( lastFilteredValues == null  ){
            return dataToFilter;
        }
        for ( int i=0; i < dataToFilter.length; i++ ) {
            lastFilteredValues[i] = lastFilteredValues[i] + ALPHA * (dataToFilter[i] - lastFilteredValues[i]);
        }
        return dataToFilter;
    }
}
