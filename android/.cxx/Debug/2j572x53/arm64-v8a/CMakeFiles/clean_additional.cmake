# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "/Users/coink/Desktop/projects/oink-hardware-plugins/capacitor-azkoyen/android/src/main/java/hardware/azkoyen/AzkoyenControl.java"
  "/Users/coink/Desktop/projects/oink-hardware-plugins/capacitor-azkoyen/android/src/main/java/hardware/azkoyen/AzkoyenControlJAVA_wrap.cxx"
  "/Users/coink/Desktop/projects/oink-hardware-plugins/capacitor-azkoyen/android/src/main/java/hardware/azkoyen/AzkoyenControlJNI.java"
  )
endif()
