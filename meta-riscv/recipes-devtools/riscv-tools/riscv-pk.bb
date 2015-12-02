SUMMARY = "RISC-V Proxy Kernel"
DESCRIPTION = "RISC-V Proxy Kernel"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "027ba300cbeaa425218fd472e1fcf45c785f645c"
SRC_URI = "git://github.com/riscv/riscv-pk.git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://bbl"

inherit autotools

DEPENDS = "riscv-fesvr-native riscv-spike-native"
DEPENDS_append_class-target = " ${PN}-native"

S = "${WORKDIR}/git"

CFLAGS = ""
CPPFLAGS = ""
CXXFLAGS = ""
LDFLAGS = ""

do_configure_prepend () {
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}

do_compile_class-native () {
        :
}

do_install_class-native () {
        install -d ${D}/${datadir}/riscv-pk
        install -m 755 ${WORKDIR}/bbl ${D}/${datadir}/riscv-pk
}

PROVIDES_${PN}_class-native += "${PN}-bbl"
PACKAGES_class-native += " ${PN}-bbl"

FILES_${PN}-bbl += "${datadir}/riscv-pk"
FILES_${PN}-bbl-dbg += "${datadir}/riscv-pk/.debug"

BBCLASSEXTEND = "native"