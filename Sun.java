package lab1;
import com.jogamp.opengl.GL2;
import static java.lang.Math.sin;

public class Sun {
    private int sceneHeight;
    private int sceneWidth;
    private int R = 100;
    private float x;
    private float y;
    private float cr, cb, cg;
    public boolean day = true;

    public Sun(int width, int height) {
        this.setSceneSize(width,height);
    }

    void setSceneSize(int width, int height){
        sceneHeight = height;
        sceneWidth = width;
        x = width - 200;
        y = height - 130;
        cr = 1.0f; cg = 0.90f; cb = 0.0f;
    }

    void render(GL2 gl){

        gl.glBegin( GL2.GL_TRIANGLE_FAN );

        gl.glColor3f( cr, cg, cb );
        gl.glVertex2f( x,y);

        for(int i = 0; i < 365; i++){
            gl.glVertex2f( (float)(R* Math.cos(i) + x),(float)(R*sin(i) + y));
        }

        gl.glEnd();

        if (x > -R){
            x--;
        }
        else {
            x = sceneWidth+R;
            if (day){
                day = false;
                R = 50;
                cr = 0.90f; cg = 0.90f; cb = 0.90f;
            }
            else {
                day = true;
                R = 100;
                cr = 1.0f; cg = 0.90f; cb = 0.0f;
            }
        }
    }
}
