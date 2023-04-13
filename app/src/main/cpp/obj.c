//
// Created by rbodai
//

#include <stdio.h>
#include <stdlib.h>
#include "lalgebra.h"
#include "obj.h"


void dummy(float* a)
{
    float b = *a;
    dummy(&b);
}

void obj_addvertex(Model* model, float coords[4])
{
    Matrix* M;
    M = la_initmatrix(4, 1);
    la_fillmatrix(M, coords);
    model->vertices[model->n_vertices++] = M;
}

void obj_addface(Model* model, int* vertices, size_t n)
{
    int i;
    int data;
    Face* f;

    i = 0;
    f = (Face*)malloc(sizeof(Face));
    f->vertices = (int*)malloc(n * sizeof(int));
    while ((data = vertices[i]) != -1)
    {
        f->vertices[i++] = data;
    }
    f->n = n;
    model->faces[model->n_faces++] = f;
}

Model* obj_init(const char* path)
{
    FILE* fp;
    char s[100];
    size_t n_vertices;
    size_t n_faces;
    Model* newmodel;

    n_vertices = n_faces = 0;

    fp = fopen(path, "r");
    if (fp == NULL)
    {
        return NULL;
    }

    while ((fgets(s, 100, fp)) != NULL)
    {
        if (s[0] == 'v')
            n_vertices++;

        else if (s[0] == 'f')
            n_faces++;
    }

    fclose(fp);
    newmodel = (Model*)malloc(sizeof(Model));
    newmodel->path = path;
    newmodel->vertices = (Matrix**)malloc(n_vertices * sizeof(Matrix*));
    newmodel->faces = (Face**)malloc(n_faces * sizeof(Face));
    newmodel->max_vertices = n_vertices;
    newmodel->max_faces = n_faces;
    newmodel->n_vertices = 0;
    newmodel->n_faces = 0;
    return newmodel;
}

void obj_load(Model* model)
{
    FILE* fp;
    int c;
    char data[100];
    int face_indices[20];
    float vertex_coords[4];

    /* i. digit of current data */
    int i_data;

    /* i. coordinate of vertex data */
    int i_coordinate;

    /* i. vertex of face data */
    int i_vertex;

    LineType l_type;

    fp = fopen(model->path, "r");
    if (fp == NULL || model == NULL)
    {
        return;
    }

    printf("Loading model %s ...\n", model->path);

    while ((c = fgetc(fp)) != EOF)
    {
        switch (c)
        {
        case 'v':
            l_type = READ_VERTEX;
            fgetc(fp);
            continue;
            break;
        case 'f':
            l_type = READ_FACE;
            fgetc(fp);
            continue;
            break;
        case 'o':
            fgetc(fp);
            fgets(data, 100, fp);
            printf("reading %s ...\n", data);
            continue;
            break;
        case '#':
        case 's':
            fgets(data, 100, fp);
            continue;
            break;
        }
        switch (l_type)
        {
        case READ_VERTEX:
            printf("vertices... %d\n", model->n_vertices);
            i_data = 0;
            i_coordinate = 0;
            do
            {
                if (c == ' ')
                {
                    data[i_data] = '\0';
                    vertex_coords[i_coordinate++] = atof(data);
                    i_data = 0;
                }
                else
                {
                    data[i_data++] = c;
                }
            } while ((c = fgetc(fp)) != '\n' && c != EOF);
            data[i_data] = '\0';
            vertex_coords[i_coordinate] = atof(data);
            vertex_coords[3] = 1.0;
            obj_addvertex(model, vertex_coords);
            break;
        case READ_FACE:
            printf("faces... %d\n", model->n_faces);
            i_data = 0;
            i_vertex = 0;
            do
            {
                if (c == ' ')
                {
                    data[i_data] = '\0';
                    face_indices[i_vertex++] = atoi(data);
                    i_data = 0;

                }
                else
                {
                    data[i_data++] = c;
                }
            } while ((c = fgetc(fp)) != '\n' && c != EOF);
            data[i_data] = '\0';
            face_indices[i_vertex++] = atoi(data);
            face_indices[i_vertex] = -1;
            obj_addface(model, face_indices, i_vertex);
            break;
        }
    }
    printf("Done.\n");
    fclose(fp);
}

void obj_calc_normals(Model* model)
{

}

void obj_print(Model* model)
{
    printf("filename: %s\n", model->path);
    printf("vertices: %d\n", model->max_vertices);
    printf("faces: %d\n", model->max_faces);
    printf("size: %d Bytes\n", sizeof(model));
}

void obj_getinfo(Model* model, const char* info)
{
	int j;

	j = 0;
	j  = sprintf(info, "filename: %s\n", model->path);
	j += sprintf(info + j, "vertices: %d\n", model->max_vertices);
	j += sprintf(info + j, "faces: %d\n", model->max_faces);
	j += sprintf(info + j, "size: %d Bytes\n", sizeof(model));
}

