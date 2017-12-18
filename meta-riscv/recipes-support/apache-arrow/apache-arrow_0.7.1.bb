SUMMARY = "a set of technologies that enable big-data systems to process and move data fast"
DESCRIPTION = "The reference Arrow implementations contain a number of distinct software components: \
Columnar vector and table-like containers (similar to data frames) supporting flat or nested types \
Fast, language agnostic metadata messaging layer (using Google's Flatbuffers library) \
Reference-counted off-heap buffer memory management, for zero-copy memory sharing and handling memory-mapped files \
Low-overhead IO interfaces to files on disk, HDFS (C++ only) \
Self-describing binary wire formats (streaming and batch/file-like) for remote procedure calls (RPC) and interprocess communication (IPC) \
Integration tests for verifying binary compatibility between the implementations (e.g. sending data from Java to C++) \
Conversions to and from other in-memory data structures (e.g. Python's pandas library)"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE.txt;md5=bbaa9c3a78459827a0298bdf18cd0dde"

SRC_URI = "\
       https://github.com/apache/arrow/archive/apache-arrow-0.7.1.tar.gz \
"
SRC_URI[md5sum] = "83877894c7a5e3261350c3b02f1caac8"
SRC_URI[sha256sum] = "22667b9d3f4d36c2060d5ade8c904c528325ea4ffcea2e71671013addcd033af"

SRC_URI_append += "file://arrow-cpp-cmakelists.patch;striplevel=2"
SRC_URI_append += "file://arrow-cpp-thirdparty.patch;striplevel=2"

PATCHTOOL="patch"

S = "${WORKDIR}/arrow-apache-arrow-0.7.1/cpp" 

DEPENDS += "cmake cmake-native boost python python-native python-numpy-native python-cython python-cython-native flatbuffers flatbuffers-native"

inherit cmake texinfo

export STAGING_INCDIR_NATIVE
export STAGING_LIBDIR_NATIVE
export STAGING_INCDIR
export STAGING_LIBDIR

do_configure_prepend() {
export STAGING_INCDIR=${STAGING_INCDIR_NATIVE}
export STAGING_LIBDIR=${STAGING_LIBDIR_NATIVE}
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

  mkdir ${D}/usr/bin/plasma-dir
  mv ${D}/usr/bin/plasma_store ${D}/usr/bin/plasma-dir/plasma_store
  base_do_install
}



EXTRA_OECMAKE += "-DARROW_PLASMA=on -DARROW_PYTHON=on -DPYTHON_EXECUTABLE=${WORKDIR}/recipe-sysroot-native/usr/bin/python-native/python2.7 -DARROW_WITH_BROTLI=off -DARROW_WITH_LZ4=off -DARROW_WITH_SNAPPY=off -DARROW_WITH_ZLIB=off -DARROW_WITH_ZSTD=off -DFLATBUFFERS_COMPILER:FILEPATH=${WORKDIR}/recipe-sysroot-native/usr/bin/flatc"

#EXTRA_OEMAKE_class-target = "LIBTOOLFLAGS='--tag=CC'"
EXTRA_OEMAKE = "LIBTOOLFLAGS='--tag=CC'"

DEPENDS_${PN} += "\
    python python-numpy python-dev python-distutils python-numpy-native python-cython python-cython-native\
"

BBCLASSEXTEND = "native"
