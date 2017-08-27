SUMMARY = "RISC-V Front-end Server"
RPROVIDES_${PN}_class-nativesdk = "libfesvr.so"
DESCRIPTION = "RISC-V Front-end Server"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "f683e01542acf60e50774d061bcb396b628e3e67"
SRC_URI = "git://github.com/riscv/riscv-fesvr.git"

inherit autotools gettext pkgconfig

BBCLASSEXTEND = "native nativesdk"

FILES_${PN} += "${libdir}/libfesvr.so"

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

do_package_qa () {
}
