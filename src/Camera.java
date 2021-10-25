public class Camera {
    private Integer x;
    private Integer y;

    public Camera(Integer x, Integer y){
        this.x =x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public String toString(){
        return x.toString()+","+y.toString();
    }

    public void updateCamera(Integer x, Integer y){
        this.x=x;
        this.y=y;
    }

}
