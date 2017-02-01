SUMMARY = "RISC-V Front-end Server"
DESCRIPTION = "RISC-V Front-end Server"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "2f1a8fd5ce60c42c03dd95271c6a0336a2c20e55"
SRC_URI = "git://github.com/riscv/riscv-fesvr.git"

inherit autotools gettext cross-canadian pkgconfig binconfig-disabled

BBCLASSEXTEND = "native nativesdk"

S = "${WORKDIR}/git"

INSANE_SKIP_${PN}-dev = "dev-elf"

do_configure_prepend () {
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}

do_install_append-class-native () {
        # Make install doesn't properly install these
        oe_libinstall -so libfesvr ${D}/${libdir}
}

do_install_append_class-nativesdk () {
        # Make install doesn't properly install these
        oe_libinstall -so libfesvr ${D}/${base_libdir}
}
