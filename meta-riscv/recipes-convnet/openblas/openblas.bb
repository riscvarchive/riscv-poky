DESCRIPTION = "OpenBLAS is an optimized BLAS library based on GotoBLAS2 1.13 BSD version."
SUMMARY = "OpenBLAS : An optimized BLAS library"
AUTHOR = "Xianyi Zhang"
HOMEPAGE = "http://www.openblas.net/"
PRIORITY= "optional"
SECTION = "libs"
LICENSE = "BSD"
PR = "r0"

DEPENDS = "make"

LIC_FILES_CHKSUM = "file://LICENSE;md5=5adf4792c949a00013ce25d476a2abc0"


SRCREV = "ddceda2aa8e3d619e776681cc2f4d93ae29e0054"
SRC_URI = "gitsm://github.com/jerryz123/riscv-OpenBLAS.git;destsuffix=${S};branch=rv64"


S = "${WORKDIR}/OpenBLAS-${PV}"

do_compile () {
	   make TARGET=RISCV64 ARCH=rv64 CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" HOSTCC=${BUILD_CC} USE_THREAD=0 ONLY_CBLAS=1 NO_LAPACK=1 NO_LAPACKE=1 FC=${TARGET_PREFIX}gfortran AR="${TARGET_PREFIX}ar"
}

do_install () {
	   oe_runmake TARGET=RISCV64 ARCH=rv64 CC="${TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" HOSTCC=${BUILD_CC} USE_THREAD=0 ONLY_CBLAS=1 NO_LAPACK=1 NO_LAPACKE=1 FC=${TARGET_PREFIX}gfortran PREFIX=${D}/usr install
}

do_install_append() {
        rm -rf ${D}/usr/bin
        rm -rf ${D}/usr/lib/cmake
        rm -rf ${D}/usr/lib/pkgconfig
}


FILES_${PN} += "${libdir}/*"
FILES_${PN}-dev = "${includedir} ${libdir}/lib${PN}.so"

