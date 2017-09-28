SUMMARY = "a flexible, high-performance distributed execution framework"
DESCRIPTION = "a flexible, high-performance distributed execution framework"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=67b36036d405e941941dc56dfe22494e"

SRC_URI = "\
       https://github.com/ray-project/ray/archive/ray-0.2.1.tar.gz \
"
SRC_URI[md5sum] = "6ec41e46a77b762fbb3c8e9079d26223"
SRC_URI[sha256sum] = "544a5ce29fb802a7ef20e28b3eb3e872946fd452cf25aa1dbfa2a78344ee4951"

SRC_URI_append += "file://py-setup.patch;striplevel=2"
SRC_URI_append += "file://py-services.patch;striplevel=2"
SRC_URI_append += "file://py-scripts.patch;striplevel=2"
SRC_URI_append += "file://py-init.patch;striplevel=2"


S = "${WORKDIR}/ray-ray-0.2.1/python"

                                                                    
DEPENDS += "redis boost python python-native python-numpy-native python-cython python-cython-native flatbuffers flatbuffers-native apache-arrow cmake cmake-native"
DEPENDS += "python-pip python-setuptools python-cython python-six python-pytest python-pandas jemalloc python-pyarrow"
DEPENDS += "python-redis python-flatbuffers python-cloudpickle python-click python-funcsigs python-psutil python-colorama"
DEPENDS += "ray"

inherit setuptools

EXTRA_OEMAKE += "LIBTOOLFLAGS='--tag=CC'"

DEPENDS_${PN} += "\
    python python-numpy python-dev python-distutils python-numpy-native python-cython python-cython-native apache-arrow\
    jemalloc python-redis python-cloudpickle python-click python-funcsigs python-psutil python-colorama python-flatbuffers flatbuffers-native python-native\
"
