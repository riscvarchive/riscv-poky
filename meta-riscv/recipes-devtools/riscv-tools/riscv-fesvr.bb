SUMMARY = "RISC-V Front-end Server"
DESCRIPTION = "RISC-V Front-end Server"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "0f34d7ad311f78455a674224225f5b3056efba1d"
SRC_URI = "git://github.com/riscv/riscv-fesvr.git \
           file://fesvr-makefile.patch"

inherit autotools gettext cross-canadian

BBCLASSEXTEND = "native nativesdk"

S = "${WORKDIR}/git"

do_configure_prepend () {
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}

do_install_append () {
        # Make install doesn't properly install these
        oe_libinstall -so libfesvr ${D}${libdir}
}
