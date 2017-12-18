SUMMARY = "a flexible, high-performance distributed execution framework"
DESCRIPTION = "a flexible, high-performance distributed execution framework"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=67b36036d405e941941dc56dfe22494e"

SRC_URI = "\
       https://github.com/ray-project/ray/archive/ray-0.2.1.tar.gz \
"
SRC_URI[md5sum] = "6ec41e46a77b762fbb3c8e9079d26223"
SRC_URI[sha256sum] = "544a5ce29fb802a7ef20e28b3eb3e872946fd452cf25aa1dbfa2a78344ee4951"

SRC_URI_append += "file://top-cmake.patch;striplevel=1"
SRC_URI_append += "file://top-build.patch;striplevel=1"
SRC_URI_append += "file://common-common-cmake.patch;striplevel=1"
SRC_URI_append += "file://common-top-cmake.patch;striplevel=1"
SRC_URI_append += "file://globalsched-cmake.patch;striplevel=1"
SRC_URI_append += "file://hiredis-make.patch;striplevel=1"
SRC_URI_append += "file://localsched-cmake.patch;striplevel=1"
SRC_URI_append += "file://plasma-cmake.patch;striplevel=1"
SRC_URI_append += "file://redismodule-top-cmake.patch;striplevel=1"


S = "${WORKDIR}/ray-ray-0.2.1/"

                                                                    
DEPENDS += "redis boost python python-native python-numpy-native python-cython python-cython-native flatbuffers flatbuffers-native apache-arrow cmake cmake-native"
DEPENDS += "python-pip python-setuptools python-cython python-six python-pytest python-pandas jemalloc python-pyarrow python-flatbuffers"
DEPENDS += "python-redis python-cloudpickle python-click python-funcsigs python-psutil python-numpy"

inherit cmake texinfo

export STAGING_INCDIR_NATIVE
export STAGING_LIBDIR_NATIVE
export STAGING_INCDIR
export STAGING_LIBDIR

do_configure_prepend() {
#export STAGING_INCDIR=${STAGING_INCDIR_NATIVE}
#export STAGING_LIBDIR=${STAGING_LIBDIR_NATIVE}
}

cmake_do_compile()  {
  base_do_compile
}

cmake_do_install() {
  oe_runmake install 'DESTDIR=${D}'
        # Info dir listing isn't interesting at this point so remove it if it exists.
        if [ -e "${D}${infodir}/dir" ]; then
                rm -f ${D}${infodir}/dir
        fi
  base_do_install
}


EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release -DPYTHON_EXECUTABLE=${WORKDIR}/recipe-sysroot-native/usr/bin/python-native/python2.7 -DFLATBUFFERS_COMPILER:FILEPATH=${WORKDIR}/recipe-sysroot-native/usr/bin/flatc"

EXTRA_OEMAKE += "LIBTOOLFLAGS='--tag=CC'"

DEPENDS_${PN} += "\
    python python-numpy python-dev python-distutils python-numpy-native python-cython python-cython-native apache-arrow\
    jemalloc python-redis python-cloudpickle python-click python-funcsigs python-psutil python-colorama\
"
