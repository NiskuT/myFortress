public class Camera {
    private Double x;
    private Double y;

    private double vx;

    private long lastCall=0;

    public Camera(Double x, Double y){
        this.x =x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString(){
        return x.toString()+","+y.toString();
    }

    public void update(long time, double xPerso){
        if( lastCall==0) lastCall = time;
        double m = 1;
        double k = 4;
        double f = 1;
        double ax;

        ax=(k*(xPerso-x)-f*vx)/m;
        vx += ax*(time-lastCall)*Math.pow(10,-9);
        x+=vx*(time-lastCall)*Math.pow(10,-9);
        System.out.println(x);
        lastCall=time;


    }

}
