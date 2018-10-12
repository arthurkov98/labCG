package lab1;
import com.jogamp.opengl.GL2;

public class Earth {
    private int sceneHeight;
    private int sceneWidth;

    public Earth(int width, int height) {
        this.setSceneSize(width,height);
    }

    void setSceneSize(int width, int height){
        sceneHeight = height;
        sceneWidth = width;
    }

    void render(GL2 gl){

        gl.glBegin( GL2.GL_QUADS );

        gl.glColor3f( 0.67f, 0.91f, 0.31f );
        gl.glVertex2f( 0.0f,0.0f);
        gl.glVertex2f( 0.0f,sceneHeight/2);
        gl.glVertex2f( sceneWidth,sceneHeight/2);
        gl.glVertex2f( sceneWidth,0f);

        //house
        gl.glColor3f( 0.82f, 0.40f, 0.04f );
        gl.glVertex2f( sceneWidth/8,sceneHeight/8+170);
        gl.glVertex2f( sceneWidth/8,sceneHeight/2+50);
        gl.glVertex2f( sceneWidth/4,sceneHeight/2+50);
        gl.glVertex2f( sceneWidth/4,sceneHeight/8+170);

        gl.glEnd();

        gl.glBegin( GL2.GL_TRIANGLES );

        // Drawing Using Triangles

        gl.glColor3f( 0.70f, 0.0f, 0.0f );
        gl.glVertex2f( 3*sceneWidth/16,sceneHeight/2+150);    // Top

        gl.glVertex2f( sceneWidth/8,sceneHeight/2+50); // Bottom Left

        gl.glVertex2f( sceneWidth/4,sceneHeight/2+50);   // Bottom Right

        gl.glEnd();
    }
}
