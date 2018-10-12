package lab1;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;

public class Scene extends JFrame implements GLEventListener{
    private final String TITLE = "Lab №1";  // window's title
    private final int CANVAS_WIDTH = 1200;  // width of the drawable
    private final int CANVAS_HEIGHT = 700; // height of the drawable
    private final int FPS = 60; // frames per second
    private FPSAnimator animator = null;
    private Sun sun;
    private Earth earth;

    public Scene(){
        super("Simple OpenGL");
        //set profile opengl 3.0
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // make canvas
        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(this);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // add animation
        this.animator = new FPSAnimator(canvas, FPS, true);

        //add canvas
        this.setName(this.TITLE);
        this.getContentPane().add(canvas);
        this.pack();

        // if close window
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }
                }.start();
            }
        });

        this.setVisible(true);
        this.setResizable(false);
        canvas.requestFocusInWindow();

        sun = new Sun(CANVAS_WIDTH, CANVAS_HEIGHT);
        earth = new Earth(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    public void play(){
        animator.start(); // start the animation loop
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.29f, 1.72f, 2.14f, 1.0f);

        gl.glViewport(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluOrtho2D(0.0, CANVAS_WIDTH, 0.0, CANVAS_HEIGHT);
        gl.glShadeModel(GL_SMOOTH);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        if(sun.day) gl.glClearColor(0.29f, 1.72f, 2.14f, 1.0f);
        else gl.glClearColor(0.26f, 0.36f, 0.65f, 1.0f);

        sun.render(gl);
        earth.render(gl);

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

}

