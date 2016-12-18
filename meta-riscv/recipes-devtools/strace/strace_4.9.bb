SUMMARY = "System call tracing tool"
HOMEPAGE = "http://strace.sourceforge.net"
SECTION = "console/utils"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=124500c21e856f0912df29295ba104c7"

SRC_URI = "git://github.com/riscv/riscv-strace.git;branch=riscv-4.9 \
          file://no-git-version.patch \
          "
SRCREV = "f320e1897832fd07a62e18ed288e75d8e79f4c5b"
SRC_URI[md5sum] = "d49ccd4a40254552ad369de4601d4575"
SRC_URI[sha256sum] = "23fcb421dbb14ad6988d06d0e0eb4af48e2fc46482cc416df4a8a63c96fd2b32"

inherit autotools
RDEPENDS_${PN}-ptest += "make coreutils grep gawk"

#PACKAGECONFIG_class-target ??= "\
#    libaio ${@bb.utils.contains('DISTRO_FEATURES', 'acl', 'acl', '', d)} \
#    ${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez', '', d)} \
#"

#PACKAGECONFIG[libaio] = "--enable-aio,--disable-aio,libaio"
PACKAGECONFIG[acl] = "--enable-acl,--disable-acl,acl"
PACKAGECONFIG[libunwind] = "--with-libunwind, --without-libunwind, libunwind"
#PACKAGECONFIG[bluez] = "ac_cv_header_bluetooth_bluetooth_h=yes,ac_cv_header_bluetooth_bluetooth_h=no,${BLUEZ}"

TESTDIR = "tests"

S = "${WORKDIR}/riscv-strace-riscv-${PV}"

do_install_append() {
	# We don't ship strace-graph here because it needs perl
	rm ${D}${bindir}/strace-graph
}

do_compile_ptest() {
	oe_runmake -C ${TESTDIR} buildtest-TESTS OS=linux ARCH="${TARGET_ARCH}"
}

#do_install_ptest() {
#	oe_runmake -C ${TESTDIR} install-ptest BUILDDIR=${B} DESTDIR=${D}${PTEST_PATH} TESTDIR=${TESTDIR}
#}

BBCLASSEXTEND = "native"
