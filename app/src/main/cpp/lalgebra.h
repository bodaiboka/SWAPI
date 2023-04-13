//
// Created by rbodai
//

#ifndef L_ALGEBRA_H
#define L_ALGEBRA_H

typedef struct matrix
{
	int r;
	int c;
	float** e;
} Matrix;

#define PI 3.14159265

Matrix* la_initmatrix(int r, int c);
Matrix* la_copy(Matrix* M);
void la_delmatrix(Matrix* M);
void la_printmatrix(Matrix* A);
void la_identity(Matrix* I);
Matrix* la_fillmatrix(Matrix* A, float* values);
Matrix* la_rotate3z(Matrix* R, float theta);
Matrix* la_rotate3x(Matrix* R, float theta);
Matrix* la_rotate3y(Matrix* R, float theta);
Matrix* la_translate3(Matrix* T, float tx, float ty, float tz);
Matrix* la_scale3(Matrix* S, float sx, float sy, float sz);
Matrix* la_projortho(Matrix* P, float r, float l, float t, float b, float n, float f);
void la_multmatrices(Matrix* A, Matrix* B, Matrix* C);
Matrix* la_multmatricesn(Matrix* A, Matrix* B);
Matrix* la_vect3(float x, float y, float z);
Matrix* la_subtract(Matrix* A, Matrix* B);
Matrix* la_crossproduct(Matrix* A, Matrix* B);
float la_dotproduct(Matrix* A, Matrix* B);
void la_subtract_i(Matrix* A, Matrix* B, Matrix* R);
int la_culltest(Matrix** face, Matrix* VP);

#endif
