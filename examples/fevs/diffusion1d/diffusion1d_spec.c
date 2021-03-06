/* FEVS: A Functional Equivalence Verification Suite for High-Performance
 * Scientific Computing
 *
 * Copyright (C) 2009-2010, Stephen F. Siegel, Timothy K. Zirkel,
 * University of Delaware
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */


/* diffusion1d_seq.c: sequential version of 1d diffusion.
 * The length of the rod is 1. The endpoints are frozen at the input 
 * temperature.
 *
 */
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <math.h>
#include <string.h>
#include "gd.h"
#define MAXCOLORS 256
#define MAXTEMP 100.0
#define PHEIGHT 100
#define PWIDTH 2

/* Parameters: These are defined at the beginning of the input file:
 *
 *      nx = number of points in x direction, including endpoints
 *       k = D*dt/(dx*dx)
 *  nsteps = number of time steps
 *   wstep = write frame every this many steps
 *
 * Compiling with the flag -DDEBUG will also cause the data to be written
 * to a sequence of plain text files.
 */

/* Global variables */
int nx = -1;              /* number of discrete points including endpoints */
double k = -1;            /* D*dt/(dx*dx) */
int nsteps = -1;          /* number of time steps */
int wstep = -1;           /* write frame every this many time steps */
double *u;                /* temperature function */
FILE *file;               /* file containing animated GIF */
gdImagePtr im, previm;    /* pointers to consecutive GIF images */
int *colors;              /* colors we will use */

void quit() {
  printf("Input file must have format:\n\n");
  printf("nx = <INTEGER>\n");
  printf("k = <DOUBLE>\n");
  printf("nsteps = <INTEGER>\n");
  printf("wstep = <INTEGER>\n");
  printf("<DOUBLE> <DOUBLE> ...\n\n");
  printf("where there are nx doubles at the end.\n");
  fflush(stdout);
  exit(1);
}

void readint(FILE *file, char *keyword, int *ptr) {
  char buf[101];
  int value;
  int returnval;

  returnval = fscanf(file, "%100s", buf);
  if (returnval != 1) quit();
  if (strcmp(keyword, buf) != 0) quit();
  returnval = fscanf(file, "%10s", buf);
  if (returnval != 1) quit();
  if (strcmp("=", buf) != 0) quit();
  returnval = fscanf(file, "%d", ptr);
  if (returnval != 1) quit();
}

void readdouble(FILE *file, char *keyword, double *ptr) {
  char buf[101];
  int value;
  int returnval;

  returnval = fscanf(file, "%100s", buf);
  if (returnval != 1) quit();
  if (strcmp(keyword, buf) != 0) quit();
  returnval = fscanf(file, "%10s", buf);
  if (returnval != 1) quit();
  if (strcmp("=", buf) != 0) quit();
  returnval = fscanf(file, "%lf", ptr);
  if (returnval != 1) quit();
}


/* init: initializes global variables.  read nx, k, nsteps, u, 
 * from infile.  Open GIF output file. */
void init(char* infilename) {
  char keyword[101];
  FILE *infile = fopen(infilename, "r");
  int i;

  assert(infile);
  readint(infile, "nx", &nx);
  readdouble(infile, "k", &k);
  readint(infile, "nsteps", &nsteps);
  readint(infile, "wstep", &wstep);
  printf("Diffusion1d with nx=%d, k=%f, nsteps=%d, wstep=%d\n",
	 nx, k, nsteps, wstep);
  fflush(stdout);
  assert(nx>=2);
  assert(k>0 && k<.5);
  assert(nsteps >= 1);
  assert(wstep >= 1 && wstep <=nsteps);
  u = (double*)malloc(nx*sizeof(double));
  assert(u);
  for (i = 0; i < nx; i++) {
    if (fscanf(infile, "%lf", u+i) != 1) quit();
  }
  fclose(infile);
  file = fopen("./specout/out.gif", "wb");
  assert(file);
  colors = (int*)malloc(MAXCOLORS*sizeof(int));
  assert(colors);
}

/* write_plain: write current data to plain text file and stdout */
void write_plain(int time) {
  FILE *plain;
  char filename[50];
  char command[50];
  int i,j;
  
  sprintf(filename, "./specout/out_%d", time);
  plain = fopen(filename, "w");
  assert(plain);
  for (i = 0; i < nx; i++) fprintf(plain, "%8.2f", u[i]);
  fprintf(plain, "\n");
  fclose(plain);
  sprintf(command, "cat %s", filename);
  system(command);
}

/* write_frame: add a frame to animation */
void write_frame(int time) {
  int i,j;
  
  im = gdImageCreate(nx*PWIDTH,PHEIGHT);
  if (time == 0) {
    for (j=0; j<MAXCOLORS; j++)
      colors[j] = gdImageColorAllocate (im, j, 0, MAXCOLORS-j-1); 
    /* (im, j,j,j); gives gray-scale image */
    gdImageGifAnimBegin(im, file, 1, -1);
  } else {
    gdImagePaletteCopy(im, previm);
  }
  for (i=0; i<nx; i++) {
    int color = (int)(u[i]*MAXCOLORS/MAXTEMP);

    assert(color >= 0);
    if (color >= MAXCOLORS) color = MAXCOLORS-1;
    gdImageFilledRectangle(im, i*PWIDTH, 0, (i+1)*PWIDTH-1, PHEIGHT-1,
			   colors[color]);
  }
  if (time == 0) {
    gdImageGifAnimAdd(im, file, 0, 0, 0, 0, gdDisposalNone, NULL);
  } else {
    gdImageGifAnimAdd(im, file, 0, 0, 0, 5, gdDisposalNone, previm /*NULL*/);
    gdImageDestroy(previm);
  }
  previm=im;
  im=NULL;
#ifdef DEBUG
  write_plain(time);
#endif
}


/* updates u for next time step. */
void update() {
  int i;
  double u_new[nx];

  for (i = 0; i < nx; i++)
    u_new[i] =  u[i] + k*(u[(i+1)%nx] + u[(i+nx-1)%nx] -2*u[i]);
  for (i = 0; i < nx; i++)
    u[i] = u_new[i];
}

/* main: executes simulation, creates one output file for each time
 * step */
int main(int argc, char *argv[]) {
  int iter;

  assert(argc==2);
  init(argv[1]);
  write_frame(0);
  for (iter = 1; iter <= nsteps; iter++) {
    update();
    if (iter%wstep==0) write_frame(iter);
  }
  gdImageDestroy(previm);
  gdImageGifAnimEnd(file);
  fclose(file);
  free(colors);
  free(u);
  return 0;
}
