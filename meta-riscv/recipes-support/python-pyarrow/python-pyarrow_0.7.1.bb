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

SRC_URI_append += "file://top-cmake.patch;striplevel=2"
SRC_URI_append += "file://findarrow-cmake.patch;striplevel=2"
SRC_URI_append += "file://findplasma-cmake.patch;striplevel=2"

S = "${WORKDIR}/arrow-apache-arrow-0.7.1/python" 

DEPENDS += "boost python python-native python-numpy-native python-cython python-cython-native flatbuffers flatbuffers-native apache-arrow cmake cmake-native"

inherit setuptools


distutils_do_compile() {
         STAGING_INCDIR=${STAGING_INCDIR} \
         STAGING_LIBDIR=${STAGING_LIBDIR} \
         PYARROW_WITH_PLASMA=1 \
         ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py build ${DISTUTILS_BUILD_ARGS} || \
         bbfatal_log "${PYTHON_PN} setup.py build execution failed."
}

distutils_stage_headers() {
        install -d ${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}
        ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py install_headers ${DISTUTILS_STAGE_HEADERS_ARGS} || \
        bbfatal_log "${PYTHON_PN} setup.py install_headers execution failed."
}

distutils_stage_all() {
        STAGING_INCDIR=${STAGING_INCDIR} \
        STAGING_LIBDIR=${STAGING_LIBDIR} \
        PYARROW_WITH_PLASMA=1 \
        install -d ${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR}
        PYTHONPATH=${STAGING_DIR_HOST}${PYTHON_SITEPACKAGES_DIR} \
        ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py install ${DISTUTILS_STAGE_ALL_ARGS} || \
        bbfatal_log "${PYTHON_PN} setup.py install (stage) execution failed."
}



distutils_do_install() {
        install -d ${D}${PYTHON_SITEPACKAGES_DIR}
        bbnote "datadir is ${datadir}"
        bbnote "prefix is ${prefix}"
        bbnote "PYTHON_SITEPACKAGES_DIR is ${PYTHON_SITEPACKAGES_DIR}"
        bbnote "D is ${D}"
        bbnote "DISTUTILS_INSTALL_ARGS is ${DISTUTILS_INSTALL_ARGS}"
        STAGING_INCDIR=${STAGING_INCDIR} \
        STAGING_LIBDIR=${STAGING_LIBDIR} \
        PYTHONPATH=${D}${PYTHON_SITEPACKAGES_DIR} \
        PYARROW_WITH_PLASMA=1 \
        ${STAGING_BINDIR_NATIVE}/${PYTHON_PN}-native/${PYTHON_PN} setup.py install --install-lib=${D}/${PYTHON_SITEPACKAGES_DIR} ${DISTUTILS_INSTALL_ARGS} || \
        bbfatal_log "${PYTHON_PN} setup.py install execution failed."

        # support filenames with *spaces*
        # only modify file if it contains path  and recompile it
        find ${D} -name "*.py" -exec grep -q ${D} {} \; -exec sed -i -e s:${D}::g {} \; -exec ${STAGING_BINDIR_NATIVE}/python-native/python -mcompileall {} \;

        if test -e ${D}${bindir} ; then
            for i in ${D}${bindir}/* ; do \
                if [ ${PN} != "${BPN}-native" ]; then
                        sed -i -e s:${STAGING_BINDIR_NATIVE}/python-native/python:${USRBINPATH}/env\ python:g $i
                fi
                sed -i -e s:${STAGING_BINDIR_NATIVE}:${bindir}:g $i
            done
        fi

        if [ -e ${D}${sbindir} ]; then
            for i in ${D}${sbindir}/* ; do \
                if [ ${PN} != "${BPN}-native" ]; then
                        sed -i -e s:${STAGING_BINDIR_NATIVE}/python-native/python:${USRBINPATH}/env\ python:g $i
                fi
                sed -i -e s:${STAGING_BINDIR_NATIVE}:${bindir}:g $i
            done
        fi

        rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/easy-install.pth
        rm -f ${D}${PYTHON_SITEPACKAGES_DIR}/site.py*

        #
        # FIXME: Bandaid against wrong datadir computation
        #
        if [ -e ${D}${datadir}/share ]; then
            mv -f ${D}${datadir}/share/* ${D}${datadir}/
            rmdir ${D}${datadir}/share
        fi

        # Fix backport modules
        if [ -e ${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/backports/__init__.py ] && [ -e ${D}${PYTHON_SITEPACKAGES_DIR}/backports/__init__.py ]; then
           rm ${D}${PYTHON_SITEPACKAGES_DIR}/backports/__init__.py;
           rm ${D}${PYTHON_SITEPACKAGES_DIR}/backports/__init__.pyc;
        fi

}


EXTRA_OEMAKE = "LIBTOOLFLAGS='--tag=CC'"

DEPENDS_${PN} += "\
    python python-numpy python-dev python-distutils python-numpy-native python-cython python-cython-native apache-arrow\
"

BBCLASSEXTEND = "native"

