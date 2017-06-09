#include "native.h"

#include <jni.h>
#include <math.h>


JNIEXPORT jfloat JNICALL Java_rtrk_pnrs1_ra38_12014_CalculateNative_getPercentage
  (JNIEnv *env, jobject obj, jint num, jint zavrseni)
{
    return (jfloat) ((double)num/zavrseni*100);
}

