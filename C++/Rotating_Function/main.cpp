#include<windows.h>
#include <iostream>
#include <stdlib.h>
#include <GL/glew.h>
#include <GL/glut.h>
#include <typeinfo>
#include <math.h>
using namespace std;
float xpos1 = -0.5f, xpos2 = -1.0f, xpos3  = -1.5f;
float ypos1 = 0.5f, ypos2 = 1.5f, ypos3 = 0.5f;
float midx = (xpos1 + xpos2 + xpos3)/3;
float midy = (ypos1 + ypos2 + ypos3)/3;
float interval = 1.0f / 10.0f;
float anglee = interval;
float* cord1;
float* cord2;
float* cord3;
float* nextPoint(float angle, float midX, float midY, float xx, float yy) {
    float x = xx;
    float y = yy;
    float xPos;
    float yPos;
    if (midX == NULL) midX = 0;
    if (midY == NULL) midY = 0;
	// rotate about (0,0)
    float cosa = cos(angle);
	float sina = sin(angle);
	if (midX == 0 && midY == 0) {
		xPos = x * cosa + y * sina;
		yPos = -x * sina + y * cosa;
	} else {
		xPos = midX + (x - midX) * cosa + (y - midY) * sina;
		yPos = midY - (x - midX) * sina + (y - midY) * cosa;
	}
	x = xPos;
	y = yPos;
	float* r = new float[2];
	r[0] = x;
	r[1] = y;
	return r;
}

//Called when a key is pressed
void handleKeypress(unsigned char key, int x, int y) {
	switch (key) {
		case 27: //Escape key
			exit(0);
	}
}
//Initializes 3D rendering
void initRendering() {
	//Makes 3D drawing work when something is in front of something else
	glEnable(GL_DEPTH_TEST);
}
//Called when the window is resized
void handleResize(int w, int h) {
	//Tell OpenGL how to convert from coordinates to pixel values
	glViewport(0, 0, w, h);
	glMatrixMode(GL_PROJECTION); //Switch to setting the camera perspective
	//Set the camera perspective
	glLoadIdentity(); //Reset the camera
	gluPerspective(45.0,                  //The camera angle
				   (double)w / (double)h, //The width-to-height ratio
				   1.0,                   //The near z clipping coordinate
				   200.0);                //The far z clipping coordinate
}
//Draws the 3D scene
void drawScene() {

	//Clear information from last draw
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glMatrixMode(GL_MODELVIEW); //Switch to the drawing perspective
	glLoadIdentity(); //Reset the drawing perspective
	glBegin(GL_QUADS); //Begin quadrilateral coordinates
	//Trapezoid
	glVertex3f(-0.7f, -1.5f, -5.0f);
	glVertex3f(0.7f, -1.5f, -5.0f);
	glVertex3f(0.7f, -0.5f, -5.0f);
	glVertex3f(-0.7f, -0.5f, -5.0f);
	glEnd(); //End quadrilateral coordinates
	glBegin(GL_TRIANGLES); //Begin triangle coordinates
	//Triangle
	glVertex3f(xpos1, ypos1, -5.0f);
	glVertex3f(xpos2, ypos2, -5.0f);
	glVertex3f(xpos3, ypos3, -5.0f);
	glEnd(); //End triangle coordinates
	glutSwapBuffers();


}

void timer(int){
    cord1 = nextPoint(anglee, midx, midy, xpos1, ypos1);
	cord2 = nextPoint(anglee, midx, midy, xpos2, ypos2);
	cord3 = nextPoint(anglee, midx, midy, xpos3, ypos3);

	xpos1 = cord1[0];
    xpos2 = cord2[0];
    xpos3 = cord3[0];
    ypos1 = cord1[1];
    ypos2 = cord2[1];
    ypos3 = cord3[1];




    glutPostRedisplay();
    glutTimerFunc(100, timer, 0);

}
int main(int argc, char** argv) {
	//Initialize GLUT
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(400, 400); //Set the window size
	//Create the window
	glutCreateWindow("Rotating Function");
	initRendering(); //Initialize rendering
	//Set handler functions for drawing, keypresses, and window resizes
	glutDisplayFunc(drawScene);
	glutKeyboardFunc(handleKeypress);
	glutReshapeFunc(handleResize);
    glutTimerFunc(0,timer,0);
	glutMainLoop(); //Start the main loop
	return 0;
}
