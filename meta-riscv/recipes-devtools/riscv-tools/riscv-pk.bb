SUMMARY = "RISC-V Proxy Kernel"
DESCRIPTION = "RISC-V Proxy Kernel"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "85ae17aa149b9ea114bdd70cc30ea7e73813fb48"
SRC_URI = "git://github.com/riscv/riscv-pk.git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LDFLAGS_append = " -Wl,--build-id=none"

inherit autotools

DEPENDS = "riscv-fesvr-native riscv-spike-native"

INHIBIT_PACKAGE_STRIP = "1"

S = "${WORKDIR}/git"

do_configure_prepend () {
        if [ ! -e ${S}/acinclude.m4 ]; then
                cp ${S}/aclocal.m4 ${S}/acinclude.m4
        fi
}

do_install_prepend () {
        install -d ${STAGING_DIR_NATIVE}/${datadir}/riscv-pk
        install -m 755 ${WORKDIR}/build/bbl ${STAGING_DIR_NATIVE}${datadir}/riscv-pk
}

PROVIDES_${PN}_class += "${PN}-bbl"
PACKAGES_class += " ${PN}-bbl"
