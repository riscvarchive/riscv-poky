SUMMARY = "RISC-V Proxy Kernel"
DESCRIPTION = "RISC-V Proxy Kernel"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.GPLv2;md5=751419260aa954499f7abaabaa882bbe"

SRCREV = "69b5de4ffa20d6e2028b1656b2de68fbb929c231"
SRC_URI = "git://github.com/riscv/riscv-pk.git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

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
