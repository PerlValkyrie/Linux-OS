package redCube;

import graphicslib3D.*;
import graphicslib3D.GLSLUtils.*;
import java.nio.*;
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GLContext;

public class Code extends JFrame implements GLEventListener
{  private GLCanvas myCanvas;
   private int rendering_program;
   private int vao[ ] = new int[1];
   private int vbo[ ] = new int[2];
   private float cameraX, cameraY, cameraZ;
   private float cubeLocX, cubeLocY, cubeLocZ;
   private GLSLUtils util = new GLSLUtils();
   private Matrix3D pMat;
   public Code()
   {  setTitle("Chapter4 - program1");
      setSize(600, 600);
      myCanvas = new GLCanvas();
      myCanvas.addGLEventListener(this);
      this.add(myCanvas);
      setVisible(true);
   }
   public void init(GLAutoDrawable drawable)
   {  GL4 gl = (GL4) GLContext.getCurrentGL();
      rendering_program = createShaderProgram();
      setupVertices();
      cameraX=0.0f; cameraY=0.0f; cameraZ=8.0f;
      cubeLocX=0.0f; cubeLocY=-2.0f; cubeLocZ=0.0f;  // shifted down along the Y-axis to reveal perspective
      
      // Create a perspective matrix, this one has fovy=60, aspect ratio matches screen window.
      // Values for near and far clipping planes can vary as discussed in Section 4.9.
      float aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
      pMat = perspective(60.0f, aspect, 0.1f, 1000.0f);
   }
// main(), reshape(), and dispose() are are unchanged
   public static void main(String[ ] args)
   {  new Code();
   }
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) { }
   public void dispose(GLAutoDrawable drawable) { }
   public void display(GLAutoDrawable drawable)
   {  GL4 gl = (GL4) GLContext.getCurrentGL();
      gl.glClear(GL_DEPTH_BUFFER_BIT);
      gl.glUseProgram(rendering_program);
      // build view matrix
      Matrix3D vMat = new Matrix3D();
      vMat.translate(-cameraX,-cameraY,-cameraZ);
      // build model matrix
      Matrix3D mMat = new Matrix3D();
      mMat.translate(cubeLocX, cubeLocY, cubeLocZ);
      // concatenate model and view matrix to create MV matrix
      Matrix3D mvMat = new Matrix3D();
      mvMat.concatenate(vMat);
      mvMat.concatenate(mMat);
      // copy perspective and MV matrices to corresponding uniform variables
      int mv_loc = gl.glGetUniformLocation(rendering_program, "mv_matrix");
      int proj_loc = gl.glGetUniformLocation(rendering_program, "proj_matrix");
      gl.glUniformMatrix4fv(proj_loc, 1, false, pMat.getFloatValues(), 0);
      gl.glUniformMatrix4fv(mv_loc, 1, false, mvMat.getFloatValues(), 0);
      // associate VBO with the corresponding vertex attribute in the vertex

      gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]); 
      gl.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
      gl.glEnableVertexAttribArray(0);
      // adjust OpenGL settings and draw model
      gl.glEnable(GL_DEPTH_TEST);
      gl.glDepthFunc(GL_LEQUAL);
      gl.glDrawArrays(GL_TRIANGLES, 0, 36);
}
   private void setupVertices()
   {  GL4 gl = (GL4) GLContext.getCurrentGL();
      // 36 vertices of the 12 triangles making up a 2 x 2 x 2 cube centered at the origin
      float[ ] vertex_positions =
      {  -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 
    		  -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f,
          1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 
          -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f,
          1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f, 
          -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f,
         -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 
         -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f,
         -1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 
         -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, -1.0f, 1.0f,
         -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 
         1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, -1.0f
      };
      gl.glGenVertexArrays(vao.length, vao, 0);
      gl.glBindVertexArray(vao[0]);
      gl.glGenBuffers(vbo.length, vbo, 0);
      gl.glBindBuffer(GL_ARRAY_BUFFER, vbo[0]);
      FloatBuffer vertBuf = Buffers.newDirectFloatBuffer(vertex_positions);
      gl.glBufferData(GL_ARRAY_BUFFER, vertBuf.limit()*4, vertBuf, GL_STATIC_DRAW);
   }
   // . . . createShaderProgram(), and perspective() are unchanged  
}

/*
layout (location=0) in vec3 position;
uniform mat4 mv_matrix;
uniform mat4 proj_matrix;

void main(void)
{  gl_Position = proj_matrix * mv_matrix * vec4(position,1.0);
}

out vec4 color;
uniform mat4 mv_matrix;
uniform mat4 proj_matrix;

void main(void)
{  color = vec4(1.0, 0.0, 0.0, 1.0);
}
*/


      
      
      
      
      
      
      
      
      
      
