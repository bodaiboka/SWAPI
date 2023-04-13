//
// Created by rbodai
//

#include <jni.h>

//com.rbodai.icellswapi.presentation.activities
//Java_com_rbodai_icellswapi_presentation_views_G3DView_
#ifndef G3D_COM_RBODAI_ICELLSWAPI_PRESENTATION_ACTIVITIES
#define G3D_COM_RBODAI_ICELLSWAPI_PRESENTATION_ACTIVITIES

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     test_TestNative
 * Method:    openModel
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_openModel
        (JNIEnv *, jobject, jstring);

/*
 * Class:     test_TestNative
 * Method:    getmodelinfo
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_getmodelinfo
        (JNIEnv *, jobject, jlong);

/*
 * Class:     test_TestNative
 * Method:    getmodelfaces
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_getmodelfaces
        (JNIEnv *, jobject, jlong);

/*
 * Class:     test_TestNative
 * Method:    loadModel
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_loadModel
        (JNIEnv *, jobject, jlong);

/*
 * Class:     test_TestNative
 * Method:    initmatrices
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_initmatrices
        (JNIEnv *, jobject);

/*
 * Class:     test_TestNative
 * Method:    transRY
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_transRY
        (JNIEnv *, jobject, jfloat);

/*
 * Class:     test_TestNative
 * Method:    getpoly
 * Signature: (JI)[D
 */
JNIEXPORT jfloatArray JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_getpoly
        (JNIEnv *, jobject, jlong, jint);

/*
 * Class:     test_TestNative
 * Method:    getpoly
 * Signature: (JI)[D
 */
JNIEXPORT jfloatArray JNICALL Java_com_rbodai_icellswapi_presentation_views_G3DView_getpolylines
        (JNIEnv *, jobject, jlong, jint);

#ifdef __cplusplus
}
#endif

#endif //G3D_COM_RBODAI_ICELLSWAPI_PRESENTATION_ACTIVITIES_MAINACTIVITY_H
