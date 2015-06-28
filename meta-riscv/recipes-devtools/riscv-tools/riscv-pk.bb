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
