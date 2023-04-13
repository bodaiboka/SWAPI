//
// Created by rbodai
//

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include "lalgebra.h"

Matrix* la_initmatrix(int r, int c)
{
    int i;
    Matrix* A = (Matrix*)malloc(sizeof(Matrix));
    A->r = r;
    A->c = c;
    A->e = (float**)malloc(r * sizeof(float*));
    for (i = 0; i < r; i++)
    {
        A->e[i] = (float*)malloc(c * sizeof(float));
    }
    return A;
}

Matrix* la_copy(Matrix* M)
{
    int i, j;
    Matrix* newmatrix;

    newmatrix = la_initmatrix(M->r, M->c);
    for (i = 0; i < M->r; i++)
    {
        for (j = 0; j < M->c; j++)
        {
            newmatrix->e[i][j] = M->e[i][j];
        }
    }
    return newmatrix;
}

void la_delmatrix(Matrix* M)
{
    int i;
    if (M != NULL)
    {
        for (i = 0; i < M->r; i++)
        {
            free(M->e[i]);
        }
        free(M->e);
        free(M);
    }
}

void la_printmatrix(Matrix* A)
{
    int i, j;
    for (i = 0; i < A->r; i++)
    {
        for (j = 0; j < A->c; j++)
        {
            printf("%6.2f%c", A->e[i][j], j == (A->c - 1) ? '\n' : ' ');
        }
    }
    printf("\n");
}

void la_identity(Matrix* I)
{
    int i, j;

    for (i = 0; i < I->r; i++)
    {
        for (j = 0; j < I->c; j++)
        {
            I->e[i][j] = (i == j) ? 1 : 0;
        }
    }
}

Matrix* la_fillmatrix(Matrix* A, float* values)
{
    int k, i, j;
    k = i = j = 0;
    for (k = 0; k < (A->r * A->c); k++)
    {
        i = floor(k / (A->c));
        j = k % (A->c);
        A->e[i][j] = values[k];
    }
    return A;
}

Matrix* la_rotate3z(Matrix* R, float theta)
{
    float sint, cost;

    if (R == NULL)
    {
        R = la_initmatrix(4, 4);
    }

    la_identity(R);
    theta = theta * PI / 180.0;
    sint = sin(theta);
    cost = cos(theta);

    R->e[0][0] = cost; R->e[0][1] = -sint;
    R->e[1][0] = sint; R->e[1][1] = cost;

    return R;
}

Matrix* la_rotate3x(Matrix* R, float theta)
{
    float sint, cost;

    if (R == NULL)
    {
        R = la_initmatrix(4, 4);
    }
    la_identity(R);
    theta = theta * PI / 180.0;
    sint = sin(theta);
    cost = cos(theta);

    R->e[1][1] = cost; R->e[1][2] = -sint;
    R->e[2][1] = sint; R->e[2][2] = cost;

    return R;
}

Matrix* la_rotate3y(Matrix* R, float theta)
{
    float sint, cost;

    if (R == NULL)
    {
        R = la_initmatrix(4, 4);
    }
    la_identity(R);
    theta = theta * PI / 180.0;
    sint = sin(theta);
    cost = cos(theta);

    R->e[0][0] = cost; R->e[0][2] = sint;
    R->e[2][0] = -sint; R->e[2][2] = cost;

    return R;
}

Matrix* la_translate3(Matrix* T, float tx, float ty, float tz)
{
    if (T == NULL)
    {
        T = la_initmatrix(4, 4);
    }
    la_identity(T);
    T->e[0][3] = tx;
    T->e[1][3] = ty;
    T->e[2][3] = tz;

    return T;
}

Matrix* la_scale3(Matrix* S, float sx, float sy, float sz)
{
    if (S == NULL)
    {
        S = la_initmatrix(4, 4);
    }
    la_identity(S);
    S->e[0][0] = sx;
    S->e[1][1] = sy;
    S->e[2][2] = sz;

    return S;
}

Matrix* la_projortho(Matrix* P, float r, float l, float t, float b, float n, float f)
{
    if (P == NULL)
    {
        P = la_initmatrix(4, 4);
        la_identity(P);
    }
    P->e[0][0] = 2 / (r - l);
    P->e[0][3] = (r + l) * (-1.0) / (r - l);
    P->e[1][1] = 2 / (t - b);
    P->e[1][3] = (t + b) * (-1.0) / (t - b);
    P->e[2][2] = -2 / (f - n);
    P->e[2][3] = (f + n) * (-1.0) / (f - n);
    return P;
}

void la_multmatrices(Matrix* A, Matrix* B, Matrix* C)
{
    int i, j, k;
    if (A->c != B->r)
    {
        printf("Error: can\'t multiply matrices because of incompatible sizes(ac=%d, br=%d).\n returning NULL\n", A->c, B->r);
        la_printmatrix(A);
        la_printmatrix(B);
    }

    for (i = 0; i < C->r; i++)
    {
        for (j = 0; j < C->c; j++)
        {
            C->e[i][j] = 0;
            for (k = 0; k < A->c; k++)
            {
                C->e[i][j] += A->e[i][k] * B->e[k][j];
            }
        }
    }
}

Matrix* la_multmatricesn(Matrix* A, Matrix* B)
{
    int i, j, k;
    Matrix* M;
    if (A->c != B->r)
    {
        printf("Error: can\'t multiply matrices because of incompatible sizes(ac=%d, br=%d).\n returning NULL\n", A->c, B->r);
        return NULL;
    }

    M = la_initmatrix(A->r, B->c);
    for (i = 0; i < M->r; i++)
    {
        for (j = 0; j < M->c; j++)
        {
            M->e[i][j] = 0;
            for (k = 0; k < A->c; k++)
            {
                M->e[i][j] += A->e[i][k] * B->e[k][j];
            }
        }
    }
    return M;
}

Matrix* la_vect3(float x, float y, float z)
{
    float c[4];

    c[0] = x;
    c[1] = y;
    c[2] = z;
    c[3] = 1;

    return la_fillmatrix(la_initmatrix(4, 1), c);
}

Matrix* la_subtract(Matrix* A, Matrix* B)
{
    Matrix* R;
    int i, j;

    R = la_initmatrix(4, 1);
    for (i = 0; i < A->r; i++)
    {
        for (j = 0; j < A->c; j++)
        {
            R->e[i][j] = A->e[i][j] - B->e[i][j];
        }
    }
    R->e[3][0] = 1.0;
    return R;
}

void la_subtract_i(Matrix* A, Matrix* B, Matrix* R)
{
    int i, j;

    for (i = 0; i < A->r; i++)
    {
        for (j = 0; j < A->c; j++)
        {
            R->e[i][j] = A->e[i][j] - B->e[i][j];
        }
    }
    R->e[3][0] = 1;
}

Matrix* la_crossproduct(Matrix* A, Matrix* B)
{
    Matrix* R;

    R = la_initmatrix(4, 1);
    R->e[0][0] = A->e[1][0] * B->e[2][0] - A->e[2][0] * B->e[1][0];
    R->e[1][0] = A->e[2][0] * B->e[0][0] - A->e[0][0] * B->e[2][0];
    R->e[2][0] = A->e[0][0] * B->e[1][0] - A->e[1][0] * B->e[0][0];
    R->e[3][0] = 1;

    return R;
}

float la_dotproduct(Matrix* A, Matrix* B)
{
    float result;

    result = A->e[0][0] * B->e[0][0] + A->e[1][0] * B->e[1][0] + A->e[2][0] * B->e[2][0];
    return result;
}

int la_culltest(Matrix** face, Matrix* VP)
{
    Matrix* v0, * v1, * v2, * N;
    float DP;

    v1 = la_subtract(face[1], face[0]);
    v2 = la_subtract(face[2], face[0]);
    N = la_crossproduct(v1, v2);
    v0 = la_subtract(face[0], VP);
    DP = la_dotproduct(v0, N);

    la_delmatrix(v1);
    la_delmatrix(v2);
    la_delmatrix(v0);
    la_delmatrix(N);

    return DP >= 0;
}
