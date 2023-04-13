//
// Created by rbodai
//

#ifndef OBJ_LOADER_H
#define OBJ_LOADER_H

#include "lalgebra.h"

typedef enum linetype
{
	READ_VERTEX,
	READ_FACE
} LineType;

typedef struct face
{
	int* vertices;
	size_t n;
	Matrix* normal;
} Face;

typedef struct model
{
	char* path;
	Matrix** vertices;
	Face** faces;
	size_t n_vertices;
	size_t n_faces;
	size_t max_vertices;
	size_t max_faces;
} Model;


Model* obj_init(const char* path);
void obj_load(Model* model);
void obj_print(Model* model);
void obj_getinfo(Model* model, const char *info);
void obj_addvertex(Model* model, float coords[4]);
void obj_addface(Model* model, int* vertices, size_t n);
void obj_calc_normals(Model* model);

#endif
