//
// Created by rbodai
//

#include <malloc.h>
#include "com_rbodai_icellswapi_presentation_activities_MainActivity.h"
#include "obj.h"

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("hello");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("hello")
//      }
//    }

Matrix* T;


JNIEXPORT jlong JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_openModel(JNIEnv *env, jobject thiz, jstring javastring) {
    const char* path = (*env)->GetStringUTFChars(env, javastring, 0);
    Model *model = obj_init(path);
    if( path != 0 )
    {
        printf("Boka File ok\n");
    }
    else
    {
        printf("Boka File fail\n");
    }
    (*env)->ReleaseStringUTFChars(env, javastring, path);
    return (jlong)model;
}

JNIEXPORT jstring JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_getmodelinfo(JNIEnv *env, jobject thiz, jlong pmodel) {
    char info[400];
    Model *model = (Model *)pmodel;
    obj_getinfo(model, info);
    jstring jinfo = (*env)->NewStringUTF(env, info);
    return jinfo;
}

JNIEXPORT jint JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_getmodelfaces(JNIEnv *env, jobject thiz, jlong pmodel) {
    return ((Model*)pmodel)->max_faces;
}

JNIEXPORT void JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_loadModel(JNIEnv *env, jobject thiz, jlong pmodel) {
    obj_load((Model *)pmodel);
}

JNIEXPORT void JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_initmatrices(JNIEnv *env, jobject thiz) {
    T = la_initmatrix(4, 4);
    la_identity(T);
}

JNIEXPORT void JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_transRY(JNIEnv *env, jobject thiz, jfloat theta, jfloat tx, jfloat ty, jfloat s) {
    Matrix *R, *S, *TR, *C;

    la_identity(T);
    R = la_initmatrix(4, 4);
    C = la_initmatrix(4, 4);
    S = la_initmatrix(4, 4);
    TR = la_initmatrix(4, 4);
    la_rotate3y(R, theta);
    la_scale3(S, s, -s, s);
    la_translate3(TR, tx, ty, 0);
    la_multmatrices(R, T, C);
    la_delmatrix(T);
    T = C;
    C = la_initmatrix(4, 4);
    la_multmatrices(S, T, C);
    la_delmatrix(T);
    T = C;
    C = la_initmatrix(4, 4);
    la_multmatrices(TR, T, C);
    la_delmatrix(T);
    T = C;
}

JNIEXPORT jfloatArray JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_getpoly(JNIEnv *env, jobject thiz, jlong pmodel, jint face) {
    Model *model;
    Matrix *vertex;
    jfloat *vertices;
    jfloatArray jvertices;
    Face *f;
    int i;

    vertex = la_initmatrix(4, 1);
    model = (Model*)pmodel;
    f = model->faces[face];
    vertices = (jfloat*)malloc( f->n * 2 * sizeof(jfloat) );
    for( i = 0; i < f->n; i++ )
    {
        la_multmatrices(T, model->vertices[f->vertices[i] - 1], vertex);
        vertices[i * 2] = vertex->e[0][0];
        vertices[i * 2 + 1] = vertex->e[1][0];
    }
    jvertices = (*env)->NewFloatArray(env, f->n * 2);
    (*env)->SetFloatArrayRegion(env, jvertices, 0, f->n * 2, vertices);
    return jvertices;
}

JNIEXPORT jfloatArray JNICALL
Java_com_rbodai_icellswapi_presentation_views_G3DView_getpolylines(JNIEnv *env, jobject thiz, jlong pmodel, jint face) {
    Model *model;
    Matrix *vertex;
    jfloat *vertices;
    jfloatArray jvertices;
    Face *f;
    int i;

    vertex = la_initmatrix(4, 1);
    model = (Model*)pmodel;
    f = model->faces[face];
    vertices = (jfloat*)malloc( (f->n) * 4 * sizeof(jfloat) );
    for( i = 0; i < f->n-1; i++ )
    {
        la_multmatrices(T, model->vertices[f->vertices[i] - 1], vertex);
        vertices[i * 4] = vertex->e[0][0];
        vertices[i * 4 + 1] = vertex->e[1][0];
        la_multmatrices(T, model->vertices[f->vertices[(i+1) % f->n] - 1], vertex);
        vertices[i * 4 + 2] = vertex->e[0][0];
        vertices[i * 4 + 3] = vertex->e[1][0];
    }
    jvertices = (*env)->NewFloatArray(env, (f->n) * 4);
    (*env)->SetFloatArrayRegion(env, jvertices, 0, (f->n) * 4, vertices);
    free(vertices);
    la_delmatrix(vertex);
    return jvertices;
}
