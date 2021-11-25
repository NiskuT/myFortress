public class KeyConfig {
    private String jump;
    private String shoot;
    private String sprint;

    public KeyConfig(){
        jump = "SPACE";
        sprint = "SHIFT";
        shoot = "ALT";
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getShoot() {
        return shoot;
    }

    public void setShoot(String shoot) {
        this.shoot = shoot;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }
}
