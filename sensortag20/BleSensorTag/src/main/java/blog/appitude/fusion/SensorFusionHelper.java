package blog.appitude.fusion;

import java.util.Timer;

public abstract class SensorFusionHelper {

    private static final int TIMER_RATE = 30;

    private Timer fuseTimer = null;
    private SensorFusionEngine fusionEngine = null;

    private boolean isRunning = false;

    public void start() {
        fuseTimer = new Timer();
        fusionEngine = new SensorFusionEngine();
    }

    public void stop() {
        if (fuseTimer != null) {
            fuseTimer.cancel();
            fuseTimer = null;
        }

        if (fusionEngine != null) {
            fusionEngine = null;
        }
    }

    private synchronized void startSensorFusion() {
        if (isRunning)
            return;

        isRunning = true;
    }

    public void onAccDataUpdate(float[] acc) {

        fusionEngine.onAccDataUpdate(acc);
    //    onOrientationChanged(fusionEngine.getFusedOrientation());
    }

    public void onMagDataUpdate(float[] magnet) {


        fusionEngine.onMagDataUpdate(magnet);
        //onOrientationChanged(fusionEngine.getFusedOrientation());
    }

    public void onGyroDataUpdate(float[] gyro) {

        fusionEngine.onGyroDataUpdate(gyro);
   //     onOrientationChanged(fusionEngine.getFusedOrientation());
    }


    public abstract void onOrientationChanged(float[] orientation);



}
